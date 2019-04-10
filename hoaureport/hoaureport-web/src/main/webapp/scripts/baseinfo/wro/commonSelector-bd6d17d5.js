/**
 * @memo 动态加载JavaScript文件
 */
var Hoau = Hoau || {};
Hoau.JsLoader = function() {
   this.load = function(url) {
		var me = this,
			scripts = document.getElementsByTagName("script"),
			len = scripts.length,
			i, script, head;
		for (i=0; i<len; i++) {
			if (scripts[i].src && scripts[i].src.indexOf(url) != -1) {
				me.onSuccess();
				return;
			}
		}
		script = document.createElement("script");
		script.type = "text/javascript";
		script.src = url;
		head = document.getElementsByTagName("head")[0];
		try {
			head.appendChild(script);
			script.onload = script.onreadystatechange = function() {
				if (script.readyState && script.readyState != 'loaded' && script.readyState != 'complete') return;
				script.onreadystatechange = script.onload = null; 
				me.onSuccess();
			}
		} catch(e) {
			//head.removeChild(script);
			if(typeof(me.onFailure) == 'function') {
				me.onFailure();
			} else if(Ext != 'undefined' && Ext != null) {
				Ext.MessageBox.alert("提示", "JavaScript文件加载失败.");
			}
		}
	}
}

// trim方法在IE下报错
String.prototype.trim = function() {
	return this.replace(/(^\s*)|(\s*$)/g, "");
}  
/**
 * 修改date对象数据的JSON提交方式
 * @param date 日期对象
 * @returns dateTime 日期对象的长整型数
 */
Ext.JSON.encodeDate = function(date) {
    var dateTime = date.getTime();
    return dateTime;
};


Ext.override(Ext.window.Window, {
    constrain: true
});

/**
 * 修改Ajax的请求超时时间，方便Debug
 * 仅适用于开发环境
 */
Ext.data.proxy.Server.override({
	timeout: 3000000
});


/**
 * 修改combox下拉后的页面中的分页
 */
/*Ext.view.BoundList.override({
	createPagingToolbar: function() {
        return Ext.widget('simplepaging', {
            id: this.id + '-paging-toolbar',
            pageSize: this.pageSize,
            store: this.store,
            border: false
        });
    }
});*/

/**
*	修改默认提示框按钮的焦点
*	更改提示框中按钮的默认位置需更改源码Ext.window.MessageBox类中
*    buttonIds: [
*        'cancel', 'no', 'yes', 'ok'
*    ]
*/
Ext.override(Ext.window.MessageBox, {
    defaultFocus: 2
});



/**
 * 关闭Tab功能
 * @param tID 待关闭的tab id
*/
/*function removeTab(tID) {
	var tabPanel = Ext.getCmp('mainAreaPanel'),
		tab = Ext.getCmp(tID);
	if(tabPanel==null){
		return;
	}
	tabPanel.remove(tab, true);
}*/

/**
 * 新建Tab功能
 * @param tID 待打开的tab id
 * @param tText tab显示文本值
 * @param tLoc tab页面请求的URL
 * @param params 请求的参数
*/
/*function addTab() {
	var tID = arguments[0],
		tText = arguments[1],
		tLoc = arguments[2],
		tabPanel = Ext.getCmp('mainAreaPanel'),
		tab = Ext.getCmp(tID);
	if(tabPanel==null){
		return;
	}
	tabPanel.params = arguments[3];
	if(!tab) {
		tabPanel.add({
			id: tID,
			title: tText,
			layout: 'fit',
			closable: true,
			tabConfig: { width: 150 },
			cls: 'autoHeight',
			bodyCls: 'autoHeight',
			loader: {
				scripts: true,
				autoLoad: true,
				url: '../'+tLoc
			}
		});
		tabPanel.setActiveTab(tabPanel.child('#'+tID));
	} else {
		tabPanel.setActiveTab(tab);
	}
}*/

/**
 * 动态下拉框查询
 */
Ext.define('Hoau.commonselector.DynamicComboSelector',{
	extend: 'Ext.form.ComboBox',
	alias: 'widget.dynamiccomboselector',
	triggerCls: Ext.baseCSSPrefix + 'form-search-trigger',
	listWidth: this.width,//下拉列表宽度，从外面传入
	multiSelect: false,//从外面传入
	isPaging: false,//是否要分页
	isEnterQuery: false,	//回车查询
	displayField: null,//显示的字段，从外面传入
	valueField: null,//提交时的字段,从外面传入
	showContent: null,//需要从外面传入如：showContent:'{deptName}&nbsp;&nbsp;{deptCode}'
	queryParam: null,//查询参数
	triggerAction: 'query',
	minChars:1,
	hideTrigger:false,
	listConfig: {
		getInnerTpl: function() {
			return this.up('combo').showContent;
		}
	},
	getValueModel: function(){
		var models = this.valueModels;
		if(Ext.isEmpty(models)&&models.length>0){
			return models[0];
		}else{
			return null;
		}
	},
	/*getChange: function(combo,newValue,oldValue){
		if(combo.isExpanded==true){
			combo.collapse();
		}
	},
	getKeyPress: function( combo, event, eOpts ){
		if(event.getKey() == event.ENTER){
			combo.store.loadPage(1,{
				scope: this,
				callback: function(records, operation, success) {
					if(records.length>0){
						combo.expand();
					}
				}
			});
		}
	},*/
	getBeforeLoad: function(store,operation,e){
		var me = this, searchParams = operation.getParams();
		if (Ext.isEmpty(searchParams)) {
			searchParams = {};
		}
		searchParams[me.queryParam] = me.rawValue;
		Ext.apply(store.proxy.extraParams, searchParams);  
	},
    initEvents: function() {
        var me = this;
        me.callParent(arguments);
		//判断是否是回车去查询
		//if(me.isEnterQuery==true){
			//me.mon(me, 'change', me.getChange, me);
			//me.mon(me, 'keypress', me.getKeyPress, me);
		//}
		me.mon(me.store, 'beforeLoad', me.getBeforeLoad, me);
    },
	initComponent:function(){
		var me = this;
		//列表宽度自定义
		me.on('expand', function(combo, eOpts ){
				combo.getPicker().minWidth=me.listWidth;
				combo.getPicker().setWidth(me.listWidth);
		});
		//判断是否要分页
		if(me.isPaging==true){
			me.pageSize = me.store.pageSize;
		}
		//判断下拉grid显示列
		if(me.showContent==null){
			me.showContent = '{'+me.displayField+'}';
		}
		//判断是否是回车去查询
		if(me.isEnterQuery==true){
			me.enableKeyEvents = true;
			me.queryDelay = 1000000;//此处为了使回车时间生效
		}
		this.callParent(arguments);
	}
});	
/**
 * 动态下拉多选框查询
 */
Ext.define('Hoau.commonselector.DynamicMultiSelectComboSelector',{
	extend: 'Ext.form.field.ComboBox',
	alias: 'widget.dynamicmulticomboselector',
	triggerCls: Ext.baseCSSPrefix + 'form-search-trigger',
	listWidth: this.width,//下拉列表宽度，从外面传入
	isPaging: false,//是否要分页
	isEnterQuery: false,	//回车查询
	triggerOnClick: false,//得到焦点时，不进行下拉
	multiSelect: true,
	minChars:1,
	listConfig: {
		getInnerTpl: function() {
			return this.up('combo').showContent;
		}
	},
	onKeyPress:function(e, t){
		var me = this;
		if(e.getKey() == e.ENTER){
			me.doRawQuery();
		}else{
			me.collapse();
		}
	},
	initEvents: function(){
		var me = this;
        me.callParent(arguments);
        if (me.enableKeyEvents&&me.isEnterQuery) {
			me.mon(me.inputEl, 'keypress', me.onKeyPress, me);
        }
	},
	initComponent:function(){
		var me = this;
		//列表宽度自定义
		me.on('expand', function(combo, eOpts ){
				combo.getPicker().minWidth=me.listWidth;
				combo.getPicker().setWidth(me.listWidth);
		});
		//判断是否要分页
		if(me.isPaging){
			me.pageSize = me.store.pageSize;
		}
		//判断是否是回车去查询
		if(me.isEnterQuery){
			me.enableKeyEvents = true;
			//me.queryDelay = 1000000;//此处为了使回车时间生效
		}
		//判断下拉grid显示列
		if(me.showContent==null){
			me.showContent = '{'+me.displayField+'}';
		}
		this.callParent(arguments);
	}
});



/**
 * A control that allows selection of multiple items in a list.
 */
Ext.define('Ext.ux.form.MultiSelect', {
    
    extend: 'Ext.form.FieldContainer',
    
    mixins: [
        'Ext.util.StoreHolder',
        'Ext.form.field.Field'
    ],
    
    alternateClassName: 'Ext.ux.Multiselect',
    alias: ['widget.multiselectfield', 'widget.multiselect'],
    
    requires: ['Ext.panel.Panel', 'Ext.view.BoundList', 'Ext.layout.container.Fit'],
    
    uses: ['Ext.view.DragZone', 'Ext.view.DropZone'],
    
    layout: 'anchor',
    
    /**
     * @cfg {String} [dragGroup=""] The ddgroup name for the MultiSelect DragZone.
     */

    /**
     * @cfg {String} [dropGroup=""] The ddgroup name for the MultiSelect DropZone.
     */
    
    /**
     * @cfg {String} [title=""] A title for the underlying panel.
     */
    
    /**
     * @cfg {Boolean} [ddReorder=false] Whether the items in the MultiSelect list are drag/drop reorderable.
     */
    ddReorder: false,

    /**
     * @cfg {Object/Array} tbar An optional toolbar to be inserted at the top of the control's selection list.
     * This can be a {@link Ext.toolbar.Toolbar} object, a toolbar config, or an array of buttons/button configs
     * to be added to the toolbar. See {@link Ext.panel.Panel#tbar}.
     */

    /**
     * @cfg {String} [appendOnly=false] `true` if the list should only allow append drops when drag/drop is enabled.
     * This is useful for lists which are sorted.
     */
    appendOnly: false,

    /**
     * @cfg {String} [displayField="text"] Name of the desired display field in the dataset.
     */
    displayField: 'text',

    /**
     * @cfg {String} [valueField="text"] Name of the desired value field in the dataset.
     */

    /**
     * @cfg {Boolean} [allowBlank=true] `false` to require at least one item in the list to be selected, `true` to allow no
     * selection.
     */
    allowBlank: true,

    /**
     * @cfg {Number} [minSelections=0] Minimum number of selections allowed.
     */
    minSelections: 0,

    /**
     * @cfg {Number} [maxSelections=Number.MAX_VALUE] Maximum number of selections allowed.
     */
    maxSelections: Number.MAX_VALUE,

    /**
     * @cfg {String} [blankText="This field is required"] Default text displayed when the control contains no items.
     */
    blankText: 'This field is required',

    /**
     * @cfg {String} [minSelectionsText="Minimum {0}item(s) required"] 
     * Validation message displayed when {@link #minSelections} is not met. 
     * The {0} token will be replaced by the value of {@link #minSelections}.
     */
    minSelectionsText: 'Minimum {0} item(s) required',
    
    /**
     * @cfg {String} [maxSelectionsText="Maximum {0}item(s) allowed"] 
     * Validation message displayed when {@link #maxSelections} is not met
     * The {0} token will be replaced by the value of {@link #maxSelections}.
     */
    maxSelectionsText: 'Maximum {0} item(s) required',

    /**
     * @cfg {String} [delimiter=","] The string used to delimit the selected values when {@link #getSubmitValue submitting}
     * the field as part of a form. If you wish to have the selected values submitted as separate
     * parameters rather than a single delimited parameter, set this to `null`.
     */
    delimiter: ',',
    
    /**
     * @cfg {String} [dragText="{0} Item{1}"] The text to show while dragging items.
     * {0} will be replaced by the number of items. {1} will be replaced by the plural
     * form if there is more than 1 item.
     */
    dragText: '{0} Item{1}',

    /**
     * @cfg {Ext.data.Store/Array} store The data source to which this MultiSelect is bound (defaults to `undefined`).
     * Acceptable values for this property are:
     * <div class="mdetail-params"><ul>
     * <li><b>any {@link Ext.data.Store Store} subclass</b></li>
     * <li><b>an Array</b> : Arrays will be converted to a {@link Ext.data.ArrayStore} internally.
     * <div class="mdetail-params"><ul>
     * <li><b>1-dimensional array</b> : (e.g., <tt>['Foo','Bar']</tt>)<div class="sub-desc">
     * A 1-dimensional array will automatically be expanded (each array item will be the combo
     * {@link #valueField value} and {@link #displayField text})</div></li>
     * <li><b>2-dimensional array</b> : (e.g., <tt>[['f','Foo'],['b','Bar']]</tt>)<div class="sub-desc">
     * For a multi-dimensional array, the value in index 0 of each item will be assumed to be the combo
     * {@link #valueField value}, while the value at index 1 is assumed to be the combo {@link #displayField text}.
     * </div></li></ul></div></li></ul></div>
     */
    
    ignoreSelectChange: 0,

    /**
     * @cfg {Object} listConfig
     * An optional set of configuration properties that will be passed to the {@link Ext.view.BoundList}'s constructor.
     * Any configuration that is valid for BoundList can be included.
     */

    //TODO - doc me.addEvents('drop');

    initComponent: function(){
        var me = this;

        me.items = me.setupItems();

        me.bindStore(me.store, true);

        if (me.store.autoCreated) {
            me.valueField = me.displayField = 'field1';
            if (!me.store.expanded) {
                me.displayField = 'field2';
            }
        }

        if (!Ext.isDefined(me.valueField)) {
            me.valueField = me.displayField;
        }

        me.callParent();
        me.initField();
    },

    setupItems: function() {
        var me = this;

        me.boundList = Ext.create('Ext.view.BoundList', Ext.apply({
            anchor: 'none 100%',
            border: 1,
            multiSelect: true,
            store: me.store,
            displayField: me.displayField,
            disabled: me.disabled
        }, me.listConfig));

        me.boundList.getSelectionModel().on('selectionchange', me.onSelectChange, me);

        // Boundlist expects a reference to its pickerField for when an item is selected (see Boundlist#onItemClick).
        me.boundList.pickerField = me;

        // Only need to wrap the BoundList in a Panel if we have a title.
        if (!me.title) {
            return me.boundList;
        }

        // Wrap to add a title
        me.boundList.border = false;
        return {
            border: true,
            anchor: 'none 100%',
            layout: 'anchor',
            title: me.title,
            tbar: me.tbar,
            items: me.boundList
        };
    },

    onSelectChange: function(selModel, selections){
        if (!this.ignoreSelectChange) {
            this.setValue(selections);
        }    
    },
    
    getSelected: function(){
        return this.boundList.getSelectionModel().getSelection();
    },
    
    // compare array values
    isEqual: function(v1, v2) {
        var fromArray = Ext.Array.from,
            i = 0, 
            len;

        v1 = fromArray(v1);
        v2 = fromArray(v2);
        len = v1.length;

        if (len !== v2.length) {
            return false;
        }

        for(; i < len; i++) {
            if (v2[i] !== v1[i]) {
                return false;
            }
        }

        return true;
    },
    
    afterRender: function(){
        var me = this,
            records;
        
        me.callParent();
        if (me.selectOnRender) {
            records = me.getRecordsForValue(me.value);
            if (records.length) {
                ++me.ignoreSelectChange;
                me.boundList.getSelectionModel().select(records);
                --me.ignoreSelectChange;
            }
            delete me.toSelect;
        }    
        
        if (me.ddReorder && !me.dragGroup && !me.dropGroup){
            me.dragGroup = me.dropGroup = 'MultiselectDD-' + Ext.id();
        }

        if (me.draggable || me.dragGroup){
            me.dragZone = Ext.create('Ext.view.DragZone', {
                view: me.boundList,
                ddGroup: me.dragGroup,
                dragText: me.dragText
            });
        }
        if (me.droppable || me.dropGroup){
            me.dropZone = Ext.create('Ext.view.DropZone', {
                view: me.boundList,
                ddGroup: me.dropGroup,
                handleNodeDrop: function(data, dropRecord, position) {
                    var view = this.view,
                        store = view.getStore(),
                        records = data.records,
                        index;

                    // remove the Models from the source Store
                    data.view.store.remove(records);

                    index = store.indexOf(dropRecord);
                    if (position === 'after') {
                        index++;
                    }
                    store.insert(index, records);
                    view.getSelectionModel().select(records);
                    me.fireEvent('drop', me, records);
                }
            });
        }
    },
    
    isValid : function() {
        var me = this,
            disabled = me.disabled,
            validate = me.forceValidation || !disabled;
            
        
        return validate ? me.validateValue(me.value) : disabled;
    },
    
    validateValue: function(value) {
        var me = this,
            errors = me.getErrors(value),
            isValid = Ext.isEmpty(errors);
            
        if (!me.preventMark) {
            if (isValid) {
                me.clearInvalid();
            } else {
                me.markInvalid(errors);
            }
        }

        return isValid;
    },
    
    markInvalid : function(errors) {
        // Save the message and fire the 'invalid' event
        var me = this,
            oldMsg = me.getActiveError();
        me.setActiveErrors(Ext.Array.from(errors));
        if (oldMsg !== me.getActiveError()) {
            me.updateLayout();
        }
    },

    /**
     * Clear any invalid styles/messages for this field.
     *
     * __Note:__ this method does not cause the Field's {@link #validate} or {@link #isValid} methods to return `true`
     * if the value does not _pass_ validation. So simply clearing a field's errors will not necessarily allow
     * submission of forms submitted with the {@link Ext.form.action.Submit#clientValidation} option set.
     */
    clearInvalid : function() {
        // Clear the message and fire the 'valid' event
        var me = this,
            hadError = me.hasActiveError();
        me.unsetActiveError();
        if (hadError) {
            me.updateLayout();
        }
    },
    
    getSubmitData: function() {
        var me = this,
            data = null,
            val;
        if (!me.disabled && me.submitValue && !me.isFileUpload()) {
            val = me.getSubmitValue();
            if (val !== null) {
                data = {};
                data[me.getName()] = val;
            }
        }
        return data;
    },

    /**
     * Returns the value that would be included in a standard form submit for this field.
     *
     * @return {String} The value to be submitted, or `null`.
     */
    getSubmitValue: function() {
        var me = this,
            delimiter = me.delimiter,
            val = me.getValue();
        
        return Ext.isString(delimiter) ? val.join(delimiter) : val;
    },
    
    getValue: function(){
        return this.value || [];
    },
    
    getRecordsForValue: function(value){
        var me = this,
            records = [],
            all = me.store.getRange(),
            valueField = me.valueField,
            i = 0,
            allLen = all.length,
            rec,
            j,
            valueLen;
            
        for (valueLen = value.length; i < valueLen; ++i) {
            for (j = 0; j < allLen; ++j) {
                rec = all[j];   
                if (rec.get(valueField) == value[i]) {
                    records.push(rec);
                }
            }    
        }
            
        return records;
    },
    
    setupValue: function(value){
        var delimiter = this.delimiter,
            valueField = this.valueField,
            i = 0,
            out,
            len,
            item;
            
        if (Ext.isDefined(value)) {
            if (delimiter && Ext.isString(value)) {
                value = value.split(delimiter);
            } else if (!Ext.isArray(value)) {
                value = [value];
            }
        
            for (len = value.length; i < len; ++i) {
                item = value[i];
                if (item && item.isModel) {
                    value[i] = item.get(valueField);
                }
            }
            out = Ext.Array.unique(value);
        } else {
            out = [];
        }
        return out;
    },
    
    setValue: function(value){
        var me = this,
            selModel = me.boundList.getSelectionModel(),
            store = me.store;

        // Store not loaded yet - we cannot set the value
        if (!store.getCount()) {
            store.on({
                load: Ext.Function.bind(me.setValue, me, [value]),
                single: true
            });
            return;
        }

        value = me.setupValue(value);
        me.mixins.field.setValue.call(me, value);
        
        if (me.rendered) {
            ++me.ignoreSelectChange;
            selModel.deselectAll();
            if (value.length) {
                selModel.select(me.getRecordsForValue(value));
            }
            --me.ignoreSelectChange;
        } else {
            me.selectOnRender = true;
        }
    },
    
    clearValue: function(){
        this.setValue([]);    
    },
    
    onEnable: function(){
        var list = this.boundList;
        this.callParent();
        if (list) {
            list.enable();
        }
    },
    
    onDisable: function(){
        var list = this.boundList;
        this.callParent();
        if (list) {
            list.disable();
        }
    },
    
    getErrors : function(value) {
        var me = this,
            format = Ext.String.format,
            errors = [],
            numSelected;

        value = Ext.Array.from(value || me.getValue());
        numSelected = value.length;

        if (!me.allowBlank && numSelected < 1) {
            errors.push(me.blankText);
        }
        if (numSelected < me.minSelections) {
            errors.push(format(me.minSelectionsText, me.minSelections));
        }
        if (numSelected > me.maxSelections) {
            errors.push(format(me.maxSelectionsText, me.maxSelections));
        }
        return errors;
    },
    
    onDestroy: function(){
        var me = this;
        
        me.bindStore(null);
        Ext.destroy(me.dragZone, me.dropZone);
        me.callParent();
    },
    
    onBindStore: function(store){
        var boundList = this.boundList;
        
        if (boundList) {
            boundList.bindStore(store);
        }
    }
    
});


/*
 * Note that this control will most likely remain as an example, and not as a core Ext form
 * control.  However, the API will be changing in a future release and so should not yet be
 * treated as a final, stable API at this time.
 */

/**
 * A control that allows selection of between two Ext.ux.form.MultiSelect controls.
 */
Ext.define('Ext.ux.form.ItemSelector', {
    extend: 'Ext.ux.form.MultiSelect',
    alias: ['widget.itemselectorfield', 'widget.itemselector'],
    alternateClassName: ['Ext.ux.ItemSelector'],
    requires: [
        'Ext.button.Button',
        'Ext.ux.form.MultiSelect'
    ],

    /**
     * @cfg {Boolean} [hideNavIcons=false] True to hide the navigation icons
     */
    hideNavIcons:false,

    /**
     * @cfg {Array} buttons Defines the set of buttons that should be displayed in between the ItemSelector
     * fields. Defaults to <tt>['top', 'up', 'add', 'remove', 'down', 'bottom']</tt>. These names are used
     * to build the button CSS class names, and to look up the button text labels in {@link #buttonsText}.
     * This can be overridden with a custom Array to change which buttons are displayed or their order.
     */
    buttons: ['top', 'up', 'add', 'remove', 'down', 'bottom'],

    /**
     * @cfg {Object} buttonsText The tooltips for the {@link #buttons}.
     * Labels for buttons.
     */
    buttonsText: {
        top: "Move to Top",
        up: "Move Up",
        add: "Add to Selected",
        remove: "Remove from Selected",
        down: "Move Down",
        bottom: "Move to Bottom"
    },

    layout: {
        type: 'hbox',
        align: 'stretch'
    },

    initComponent: function() {
        var me = this;

        me.ddGroup = me.id + '-dd';
        me.callParent();

        // bindStore must be called after the fromField has been created because
        // it copies records from our configured Store into the fromField's Store
        me.bindStore(me.store);
    },

    createList: function(title){
        var me = this;

        return Ext.create('Ext.ux.form.MultiSelect', {
            // We don't want the multiselects themselves to act like fields,
            // so override these methods to prevent them from including
            // any of their values
            submitValue: false,
            getSubmitData: function(){
                return null;
            },
            getModelData: function(){
                return null;    
            },
            flex: 1,
            dragGroup: me.ddGroup,
            dropGroup: me.ddGroup,
            title: title,
            store: {
                model: me.store.model,
                data: []
            },
            displayField: me.displayField,
            valueField: me.valueField,
            disabled: me.disabled,
            listeners: {
                boundList: {
                    scope: me,
                    itemdblclick: me.onItemDblClick,
                    drop: me.syncValue
                }
            }
        });
    },

    setupItems: function() {
        var me = this;

        me.fromField = me.createList(me.fromTitle);
        me.toField = me.createList(me.toTitle);

        return [
            me.fromField,
            {
                xtype: 'container',
                margin: '0 4',
                layout: {
                    type: 'vbox',
                    pack: 'center'
                },
                items: me.createButtons()
            },
            me.toField
        ];
    },

    createButtons: function() {
        var me = this,
            buttons = [];

        if (!me.hideNavIcons) {
            Ext.Array.forEach(me.buttons, function(name) {
                buttons.push({
                    xtype: 'button',
                    tooltip: me.buttonsText[name],
                    handler: me['on' + Ext.String.capitalize(name) + 'BtnClick'],
                    cls: Ext.baseCSSPrefix + 'form-itemselector-btn',
                    iconCls: Ext.baseCSSPrefix + 'form-itemselector-' + name,
                    navBtn: true,
                    scope: me,
                    margin: '4 0 0 0'
                });
            });
        }
        return buttons;
    },

    /**
     * Get the selected records from the specified list.
     * 
     * Records will be returned *in store order*, not in order of selection.
     * @param {Ext.view.BoundList} list The list to read selections from.
     * @return {Ext.data.Model[]} The selected records in store order.
     * 
     */
    getSelections: function(list) {
        var store = list.getStore();

        return Ext.Array.sort(list.getSelectionModel().getSelection(), function(a, b) {
            a = store.indexOf(a);
            b = store.indexOf(b);

            if (a < b) {
                return -1;
            } else if (a > b) {
                return 1;
            }
            return 0;
        });
    },

    onTopBtnClick : function() {
        var list = this.toField.boundList,
            store = list.getStore(),
            selected = this.getSelections(list);

        store.suspendEvents();
        store.remove(selected, true);
        store.insert(0, selected);
        store.resumeEvents();
        list.refresh();
        this.syncValue(); 
        list.getSelectionModel().select(selected);
    },

    onBottomBtnClick : function() {
        var list = this.toField.boundList,
            store = list.getStore(),
            selected = this.getSelections(list);

        store.suspendEvents();
        store.remove(selected, true);
        store.add(selected);
        store.resumeEvents();
        list.refresh();
        this.syncValue();
        list.getSelectionModel().select(selected);
    },

    onUpBtnClick : function() {
        var list = this.toField.boundList,
            store = list.getStore(),
            selected = this.getSelections(list),
            rec,
            i = 0,
            len = selected.length,
            index = 0;

        // Move each selection up by one place if possible
        store.suspendEvents();
        for (; i < len; ++i, index++) {
            rec = selected[i];
            index = Math.max(index, store.indexOf(rec) - 1);
            store.remove(rec, true);
            store.insert(index, rec);
        }
        store.resumeEvents();
        list.refresh();
        this.syncValue();
        list.getSelectionModel().select(selected);
    },

    onDownBtnClick : function() {
        var list = this.toField.boundList,
            store = list.getStore(),
            selected = this.getSelections(list),
            rec,
            i = selected.length - 1,
            index = store.getCount() - 1;

        // Move each selection down by one place if possible
        store.suspendEvents();
        for (; i > -1; --i, index--) {
            rec = selected[i];
            index = Math.min(index, store.indexOf(rec) + 1);
            store.remove(rec, true);
            store.insert(index, rec);
        }
        store.resumeEvents();
        list.refresh();
        this.syncValue();
        list.getSelectionModel().select(selected);
    },

    onAddBtnClick : function() {
        var me = this,
            selected = me.getSelections(me.fromField.boundList);

        me.moveRec(true, selected);
        me.toField.boundList.getSelectionModel().select(selected);
    },

    onRemoveBtnClick : function() {
        var me = this,
            selected = me.getSelections(me.toField.boundList);

        me.moveRec(false, selected);
        me.fromField.boundList.getSelectionModel().select(selected);
    },

    moveRec: function(add, recs) {
        var me = this,
            fromField = me.fromField,
            toField   = me.toField,
            fromStore = add ? fromField.store : toField.store,
            toStore   = add ? toField.store   : fromField.store;

        fromStore.suspendEvents();
        toStore.suspendEvents();
        fromStore.remove(recs);
        toStore.add(recs);
        fromStore.resumeEvents();
        toStore.resumeEvents();

        fromField.boundList.refresh();
        toField.boundList.refresh();

        me.syncValue();
    },

    // Synchronizes the submit value with the current state of the toStore
    syncValue: function() {
        var me = this; 
        me.mixins.field.setValue.call(me, me.setupValue(me.toField.store.getRange()));
    },

    onItemDblClick: function(view, rec) {
        this.moveRec(view === this.fromField.boundList, rec);
    },

    setValue: function(value) {
        var me = this,
            fromField = me.fromField,
            toField = me.toField,
            fromStore = fromField.store,
            toStore = toField.store,
            selected;

        // Wait for from store to be loaded
        if (!me.fromStorePopulated) {
            me.fromField.store.on({
                load: Ext.Function.bind(me.setValue, me, [value]),
                single: true
            });
            return;
        }

        value = me.setupValue(value);
        me.mixins.field.setValue.call(me, value);

        selected = me.getRecordsForValue(value);

        // Clear both left and right Stores.
        // Both stores must not fire events during this process.
        fromStore.suspendEvents();
        toStore.suspendEvents();
        fromStore.removeAll();
        toStore.removeAll();

        // Reset fromStore
        me.populateFromStore(me.store);

        // Copy selection across to toStore
        Ext.Array.forEach(selected, function(rec){
            // In the from store, move it over
            if (fromStore.indexOf(rec) > -1) {
                fromStore.remove(rec);
            }
            toStore.add(rec);
        });

        // Stores may now fire events
        fromStore.resumeEvents();
        toStore.resumeEvents();

        // Refresh both sides and then update the app layout
        Ext.suspendLayouts();
        fromField.boundList.refresh();
        toField.boundList.refresh();
        Ext.resumeLayouts(true);        
    },

    onBindStore: function(store, initial) {
        var me = this;

        if (me.fromField) {
            me.fromField.store.removeAll();
            me.toField.store.removeAll();

            // Add everything to the from field as soon as the Store is loaded
            if (store.getCount()) {
                me.populateFromStore(store);
            } else {
                me.store.on('load', me.populateFromStore, me);
            }
        }
    },

    populateFromStore: function(store) {
        var fromStore = this.fromField.store;

        // Flag set when the fromStore has been loaded
        this.fromStorePopulated = true;

        fromStore.add(store.getRange());

        // setValue waits for the from Store to be loaded
        fromStore.fireEvent('load', fromStore);
    },

    onEnable: function(){
        var me = this;

        me.callParent();
        me.fromField.enable();
        me.toField.enable();

        Ext.Array.forEach(me.query('[navBtn]'), function(btn){
            btn.enable();
        });
    },

    onDisable: function(){
        var me = this;

        me.callParent();
        me.fromField.disable();
        me.toField.disable();

        Ext.Array.forEach(me.query('[navBtn]'), function(btn){
            btn.disable();
        });
    },

    onDestroy: function(){
        this.bindStore(null);
        this.callParent();
    }
});



/**
 * 设置系统中window位置默认限制在它的父元素内
 */
Ext.override(Ext.window.Window, {
    constrain: true
});
/**
 * 设置系统中表格内的内容都可选择
 */
Ext.override(Ext.view.Table, {
    enableTextSelection: true
});
/**
 * 设置只读添加红色星号
 */
Ext.override(Ext.form.field.Base, {
	initComponent:function () {
    	var me = this;
    	me.callParent();
    	if (me.allowBlank === false && !Ext.isEmpty(me.fieldLabel)) {
      		me.beforeLabelTextTpl = '<span style="color:red;font-weight:bold" data-qtip="必填选项">*</span>';
    	}
	}
});
/**
 * 设置只读添加红色星号
 */
Ext.override(Ext.form.FieldContainer, {
	initComponent:function () {
    	var me = this;
    	me.callParent();
    	if (me.allowBlank === false && !Ext.isEmpty(me.fieldLabel)) {
      		me.beforeLabelTextTpl = '<span style="color:red;font-weight:bold" data-qtip="必填选项">*</span>';
    	}
	}
});

/**
 * A ratings picker based on `Ext.Widget`.
 *
 *      @example
 *      Ext.create({
 *          xtype: 'rating',
 *          renderTo: Ext.getBody(),
 *          listeners: {
 *              change: function (picker, value) {
 *                 console.log('Rating ' + value);
 *              }
 *          }
 *      });
 */
Ext.define('Ext.ux.rating.Picker', {
    extend: 'Ext.Widget',
    xtype: 'rating',
    focusable: true,
    /*
     * The "cachedConfig" block is basically the same as "config" except that these
     * values are applied specially to the first instance of the class. After processing
     * these configs, the resulting values are stored on the class `prototype` and the
     * template DOM element also reflects these default values.
     */
    cachedConfig: {
        /**
         * @cfg {String} [family]
         * The CSS `font-family` to use for displaying the `{@link #glyphs}`.
         */
        family: 'monospace',
        /**
         * @cfg {String/String[]/Number[]} [glyphs]
         * Either a string containing the two glyph characters, or an array of two strings
         * containing the individual glyph characters or an array of two numbers with the
         * character codes for the individual glyphs.
         *
         * For example:
         *
         *      @example
         *      Ext.create({
         *          xtype: 'rating',
         *          renderTo: Ext.getBody(),
         *          glyphs: [ 9671, 9670 ], // '◇◆',
         *          listeners: {
         *              change: function (picker, value) {
         *                 console.log('Rating ' + value);
         *              }
         *          }
         *      });
         */
        glyphs: '☆★',
        /**
         * @cfg {Number} [minimum=1]
         * The minimum allowed `{@link #value}` (rating).
         */
        minimum: 1,
        /**
         * @cfg {Number} [limit=1]
         * The maximum allowed `{@link #value}` (rating).
         */
        limit: 5,
        /**
         * @cfg {String/Object} [overStyle]
         * Optional styles to apply to the rating glyphs when `{@link #trackOver}` is
         * enabled.
         */
        overStyle: null,
        /**
         * @cfg {Number} [rounding=1]
         * The rounding to apply to values. Common choices are 0.5 (for half-steps) or
         * 0.25 (for quarter steps).
         */
        rounding: 1,
        /**
         * @cfg {String} [scale="125%"]
         * The CSS `font-size` to apply to the glyphs. This value defaults to 125% because
         * glyphs in the stock font tend to be too small. When using specially designed
         * "icon fonts" you may want to set this to 100%.
         */
        scale: '125%',
        /**
         * @cfg {String/Object} [selectedStyle]
         * Optional styles to apply to the rating value glyphs.
         */
        selectedStyle: null,
        /**
         * @cfg {String/Object} [style]
         * Optional styles to apply to the top-level element.
         */
        style: null,
        /**
         * @cfg {Object/String/String[]/Ext.XTemplate/Function} tooltip
         * A template or a function that produces the tooltip text. The `Object`, `String`
         * and `String[]` forms are converted to an `Ext.XTemplate`. If a function is given,
         * it will be called with an object parameter and should return the tooltip text.
         * The object contains these properties:
         *
         *   - component: The rating component requesting the tooltip.
         *   - tracking: The current value under the mouse cursor.
         *   - trackOver: The value of the `{@link #trackOver}` config.
         *   - value: The current value.
         *
         * Templates can use these properties to generate the proper text.
         */
        tooltip: null,
        /**
         * @cfg {Boolean} [trackOver=true]
         * Determines if mouse movements should temporarily update the displayed value.
         * The actual `value` is only updated on `click` but this rather acts as the
         * "preview" of the value prior to click.
         */
        trackOver: true,
        /**
         * @cfg {Number} value
         * The rating value. This value is bounded by `minimum` and `limit` and is also
         * adjusted by the `rounding`.
         */
        value: null,
        //---------------------------------------------------------------------
        // Private configs
        /**
         * @cfg {String} tooltipText
         * The current tooltip text. This value is set into the DOM by the updater (hence
         * only when it changes). This is intended for use by the tip manager
         * (`{@link Ext.tip.QuickTipManager}`). Developers should never need to set this
         * config since it is handled by virtue of setting other configs (such as the
         * {@link #tooltip} or the {@link #value}.).
         * @private
         */
        tooltipText: null,
        /**
         * @cfg {Number} trackingValue
         * This config is used to when `trackOver` is `true` and represents the tracked
         * value. This config is maintained by our `mousemove` handler. This should not
         * need to be set directly by user code.
         * @private
         */
        trackingValue: null
    },
    config: {
        /**
         * @cfg {Boolean/Object} [animate=false]
         * Specifies an animation to use when changing the `{@link #value}`. When setting
         * this config, it is probably best to set `{@link #trackOver}` to `false`.
         */
        animate: null
    },
    // This object describes our element tree from the root.
    element: {
        cls: 'u' + Ext.baseCSSPrefix + 'rating-picker',
        // Since we are replacing the entire "element" tree, we have to assign this
        // "reference" as would our base class.
        reference: 'element',
        children: [
            {
                reference: 'innerEl',
                cls: 'u' + Ext.baseCSSPrefix + 'rating-picker-inner',
                listeners: {
                    click: 'onClick',
                    mousemove: 'onMouseMove',
                    mouseenter: 'onMouseEnter',
                    mouseleave: 'onMouseLeave'
                },
                children: [
                    {
                        reference: 'valueEl',
                        cls: 'u' + Ext.baseCSSPrefix + 'rating-picker-value'
                    },
                    {
                        reference: 'trackerEl',
                        cls: 'u' + Ext.baseCSSPrefix + 'rating-picker-tracker'
                    }
                ]
            }
        ]
    },
    // Tell the Binding system to default to our "value" config.
    defaultBindProperty: 'value',
    // Enable two-way data binding for the "value" config.
    twoWayBindable: 'value',
    overCls: 'u' + Ext.baseCSSPrefix + 'rating-picker-over',
    trackOverCls: 'u' + Ext.baseCSSPrefix + 'rating-picker-track-over',
    //-------------------------------------------------------------------------
    // Config Appliers
    applyGlyphs: function(value) {
        if (typeof value === 'string') {
            if (value.length !== 2) {
                Ext.Error.raise('Expected 2 characters for "glyphs" not "' + value + '".');
            }
            value = [
                value.charAt(0),
                value.charAt(1)
            ];
        } else if (typeof value[0] === 'number') {
            value = [
                String.fromCharCode(value[0]),
                String.fromCharCode(value[1])
            ];
        }
        return value;
    },
    applyOverStyle: function(style) {
        this.trackerEl.applyStyles(style);
    },
    applySelectedStyle: function(style) {
        this.valueEl.applyStyles(style);
    },
    applyStyle: function(style) {
        this.element.applyStyles(style);
    },
    applyTooltip: function(tip) {
        if (tip && typeof tip !== 'function') {
            if (!tip.isTemplate) {
                tip = new Ext.XTemplate(tip);
            }
            tip = tip.apply.bind(tip);
        }
        return tip;
    },
    applyTrackingValue: function(value) {
        return this.applyValue(value);
    },
    // same rounding as normal value
    applyValue: function(v) {
        if (v !== null) {
            var rounding = this.getRounding(),
                limit = this.getLimit(),
                min = this.getMinimum();
            v = Math.round(Math.round(v / rounding) * rounding * 1000) / 1000;
            v = (v < min) ? min : (v > limit ? limit : v);
        }
        return v;
    },
    //-------------------------------------------------------------------------
    // Event Handlers
    onClick: function(event) {
        var value = this.valueFromEvent(event);
        this.setValue(value);
    },
    onMouseEnter: function() {
        this.element.addCls(this.overCls);
    },
    onMouseLeave: function() {
        this.element.removeCls(this.overCls);
    },
    onMouseMove: function(event) {
        var value = this.valueFromEvent(event);
        this.setTrackingValue(value);
    },
    //-------------------------------------------------------------------------
    // Config Updaters
    updateFamily: function(family) {
        this.element.setStyle('fontFamily', "'" + family + "'");
    },
    updateGlyphs: function() {
        this.refreshGlyphs();
    },
    updateLimit: function() {
        this.refreshGlyphs();
    },
    updateScale: function(size) {
        this.element.setStyle('fontSize', size);
    },
    updateTooltip: function() {
        this.refreshTooltip();
    },
    updateTooltipText: function(text) {
        var innerEl = this.innerEl,
            QuickTips = Ext.tip && Ext.tip.QuickTipManager,
            tip = QuickTips && QuickTips.tip,
            target;
        if (QuickTips) {
            innerEl.dom.setAttribute('data-qtip', text);
            this.trackerEl.dom.setAttribute('data-qtip', text);
            // If the QuickTipManager is active over our widget, we need to update
            // the tooltip text directly.
            target = tip && tip.activeTarget;
            target = target && target.el;
            if (target && innerEl.contains(target)) {
                tip.update(text);
            }
        }
    },
    updateTrackingValue: function(value) {
        var me = this,
            trackerEl = me.trackerEl,
            newWidth = me.valueToPercent(value);
        trackerEl.setStyle('width', newWidth);
        me.refreshTooltip();
    },
    updateTrackOver: function(trackOver) {
        this.element[trackOver ? 'addCls' : 'removeCls'](this.trackOverCls);
    },
    updateValue: function(value, oldValue) {
        var me = this,
            animate = me.getAnimate(),
            valueEl = me.valueEl,
            newWidth = me.valueToPercent(value),
            column, record;
        if (me.isConfiguring || !animate) {
            valueEl.setStyle('width', newWidth);
        } else {
            valueEl.stopAnimation();
            valueEl.animate(Ext.merge({
                from: {
                    width: me.valueToPercent(oldValue)
                },
                to: {
                    width: newWidth
                }
            }, animate));
        }
        me.refreshTooltip();
        if (!me.isConfiguring) {
            // Since we are (re)configured many times as we are used in a grid cell, we
            // avoid firing the change event unless there are listeners.
            if (me.hasListeners.change) {
                me.fireEvent('change', me, value, oldValue);
            }
            column = me.getWidgetColumn && me.getWidgetColumn();
            record = column && me.getWidgetRecord && me.getWidgetRecord();
            if (record && column.dataIndex) {
                // When used in a widgetcolumn, we should update the backing field. The
                // linkages will be cleared as we are being recycled, so this will only
                // reach this line when we are properly attached to a record and the
                // change is coming from the user (or a call to setValue).
                record.set(column.dataIndex, value);
            }
        }
    },
    //-------------------------------------------------------------------------
    // Config System Optimizations
    //
    // These are to deal with configs that combine to determine what should be
    // rendered in the DOM. For example, "glyphs" and "limit" must both be known
    // to render the proper text nodes. The "tooltip" and "value" likewise are
    // used to update the tooltipText.
    //
    // To avoid multiple updates to the DOM (one for each config), we simply mark
    // the rendering as invalid and post-process these flags on the tail of any
    // bulk updates.
    afterCachedConfig: function() {
        // Now that we are done setting up the initial values we need to refresh the
        // DOM before we allow Ext.Widget's implementation to cloneNode on it.
        this.refresh();
        return this.callParent(arguments);
    },
    initConfig: function(instanceConfig) {
        this.isConfiguring = true;
        this.callParent([
            instanceConfig
        ]);
        // The firstInstance will already have refreshed the DOM (in afterCacheConfig)
        // but all instances beyond the first need to refresh if they have custom values
        // for one or more configs that affect the DOM (such as "glyphs" and "limit").
        this.refresh();
    },
    setConfig: function() {
        var me = this;
        // Since we could be updating multiple configs, save any updates that need
        // multiple values for afterwards.
        me.isReconfiguring = true;
        me.callParent(arguments);
        me.isReconfiguring = false;
        // Now that all new values are set, we can refresh the DOM.
        me.refresh();
        return me;
    },
    //-------------------------------------------------------------------------
    destroy: function() {
        var me = this,
            tip = me.tip;
        if (tip) {
            me.tip = Ext.destroy(tip);
        }
        me.callParent();
    },
    privates: {
        /**
         * This method returns the DOM text node into which glyphs are placed.
         * @param {HTMLElement} dom The DOM node parent of the text node.
         * @return {HTMLTextNode} The text node.
         * @private
         */
        getGlyphTextNode: function(dom) {
            var node = dom.lastChild;
            // We want all our text nodes to be at the end of the child list, most
            // especially the text node on the innerEl. That text node affects the
            // default left/right position of our absolutely positioned child divs
            // (trackerEl and valueEl).
            if (!node || node.nodeType !== 3) {
                node = dom.ownerDocument.createTextNode('');
                dom.appendChild(node);
            }
            return node;
        },
        getTooltipData: function() {
            var me = this;
            return {
                component: me,
                tracking: me.getTrackingValue(),
                trackOver: me.getTrackOver(),
                value: me.getValue()
            };
        },
        /**
         * Forcibly refreshes both glyph and tooltip rendering.
         * @private
         */
        refresh: function() {
            var me = this;
            if (me.invalidGlyphs) {
                me.refreshGlyphs(true);
            }
            if (me.invalidTooltip) {
                me.refreshTooltip(true);
            }
        },
        /**
         * Refreshes the glyph text rendering unless we are currently performing a
         * bulk config change (initConfig or setConfig).
         * @param {Boolean} now Pass `true` to force the refresh to happen now.
         * @private
         */
        refreshGlyphs: function(now) {
            var me = this,
                later = !now && (me.isConfiguring || me.isReconfiguring),
                el, glyphs, limit, on, off, trackerEl, valueEl;
            if (!later) {
                el = me.getGlyphTextNode(me.innerEl.dom);
                valueEl = me.getGlyphTextNode(me.valueEl.dom);
                trackerEl = me.getGlyphTextNode(me.trackerEl.dom);
                glyphs = me.getGlyphs();
                limit = me.getLimit();
                for (on = off = ''; limit--; ) {
                    off += glyphs[0];
                    on += glyphs[1];
                }
                el.nodeValue = off;
                valueEl.nodeValue = on;
                trackerEl.nodeValue = on;
            }
            me.invalidGlyphs = later;
        },
        /**
         * Refreshes the tooltip text rendering unless we are currently performing a
         * bulk config change (initConfig or setConfig).
         * @param {Boolean} now Pass `true` to force the refresh to happen now.
         * @private
         */
        refreshTooltip: function(now) {
            var me = this,
                later = !now && (me.isConfiguring || me.isReconfiguring),
                tooltip = me.getTooltip(),
                data, text;
            if (!later) {
                tooltip = me.getTooltip();
                if (tooltip) {
                    data = me.getTooltipData();
                    text = tooltip(data);
                    me.setTooltipText(text);
                }
            }
            me.invalidTooltip = later;
        },
        /**
         * Convert the coordinates of the given `Event` into a rating value.
         * @param {Ext.Event} event The event.
         * @return {Number} The rating based on the given event coordinates.
         * @private
         */
        valueFromEvent: function(event) {
            var me = this,
                el = me.innerEl,
                ex = event.getX(),
                rounding = me.getRounding(),
                cx = el.getX(),
                x = ex - cx,
                w = el.getWidth(),
                limit = me.getLimit(),
                v;
            if (me.getInherited().rtl) {
                x = w - x;
            }
            v = x / w * limit;
            // We have to round up here so that the area we are over is considered
            // the value.
            v = Math.ceil(v / rounding) * rounding;
            return v;
        },
        /**
         * Convert the given rating into a width percentage.
         * @param {Number} value The rating value to convert.
         * @return {String} The width percentage to represent the given value.
         * @private
         */
        valueToPercent: function(value) {
            value = (value / this.getLimit()) * 100;
            return value + '%';
        }
    }
});


Ext.define('Ext.ux.grid.SubTable', {
    extend: 'Ext.grid.plugin.RowExpander',

    alias: 'plugin.subtable',

    rowBodyTpl: ['<table class="' + Ext.baseCSSPrefix + 'grid-subtable"><tbody>',
        '{%',
            'this.owner.renderTable(out, values);',
        '%}',
        '</tbody></table>'
    ],

    init: function(grid) {
        var me = this,
            columns = me.columns,
            len, i, columnCfg;

        me.callParent(arguments);

        me.columns = [];
        if (columns) {
            for (i = 0, len = columns.length; i < len; ++i) {
                // Don't register with the component manager, we create them to use
                // their rendering smarts, but don't want to treat them as real components
                columnCfg = Ext.apply({
                    preventRegister: true
                }, columns[i]);
                columnCfg.xtype = columnCfg.xtype || 'gridcolumn';
                me.columns.push(Ext.widget(columnCfg));
            }
        }
    },

    destroy: function() {
        var columns = this.columns,
            len, i;

        if (columns) {
            for (i = 0, len = columns.length; i < len; ++i) {
                columns[i].destroy();
            }
        }
        this.columns = null;
        this.callParent();
    },

    getRowBodyFeatureData: function(record, idx, rowValues) {
        this.callParent(arguments);
        rowValues.rowBodyCls += ' ' + Ext.baseCSSPrefix + 'grid-subtable-row';
    },

    renderTable: function(out, rowValues) {
        var me = this,
            columns = me.columns,
            numColumns = columns.length,
            associatedRecords = me.getAssociatedRecords(rowValues.record),
            recCount = associatedRecords.length,
            rec, column, i, j, value;

        out.push('<thead>');
        for (j = 0; j < numColumns; j++) {
            out.push('<th class="' + Ext.baseCSSPrefix + 'grid-subtable-header">', columns[j].text, '</th>');
        }
        out.push('</thead>');
        for (i = 0; i < recCount; i++) {
            rec = associatedRecords[i];
            out.push('<tr>');
            for (j = 0; j < numColumns; j++) {
                column = columns[j];
                value = rec.get(column.dataIndex);
                if (column.renderer && column.renderer.call) {
                    value = column.renderer.call(column.scope || me, value, {}, rec);
                }
                out.push('<td class="' + Ext.baseCSSPrefix + 'grid-subtable-cell"');
                if (column.width != null) {
                    out.push(' style="width:' + column.width + 'px"');
                }
                out.push('><div class="' + Ext.baseCSSPrefix + 'grid-cell-inner">', value, '</div></td>');
            }
            out.push('</tr>');
        }
    },
    
    getRowBodyContentsFn: function(rowBodyTpl) {
        var me = this;
        return function (rowValues) {
            rowBodyTpl.owner = me;
            return rowBodyTpl.applyTemplate(rowValues);
        };
    },
    
    getAssociatedRecords: function(record) {
        return record[this.association]().getRange();
    }
});

/**
 * allowBlank = false 
 * 过滤空格
 */
Ext.override(Ext.form.TextField, { 
    validator:function(text){
        if(this.allowBlank==false && Ext.util.Format.trim(text).length==0)
          return false;
        else
          return true;
    }
}); 


Ext.define('Hoau.errorMessage.Window', {
	extend: 'Ext.window.Window', 
	title: '系统异常',
	eTitle: '未知异常',
	eDetailTitle: '异常详细信息',
	resizable: false,
	modal: true,
	width: 500,
	defaults: {
		anchor: '100%',
		autoScroll: true
	},
	message : null,
	buttons: [{
		text: 'OK',
		handler: function(button, even) {
			button.up('window').destroy();
		}
	}],
	constructor: function(config) {
        var me = this,
			cfg = Ext.apply({}, config);
		me.message = config.message;
		me.requestId = config.requestId;
		me.stackTrace = config.stackTrace;
		me.items = [{
			xtype: 'panel',
			frame: true,
			title: '请求id',
			width: 500,
			html: me.requestId
		},{
			xtype: 'panel',
			frame: true,
			title: me.eDetailTitle,
			collapsible: true,
			collapsed: true,
			width: 500,
			autoScroll: true,
			items: [{
				height: 200,
				width: 500,
				html: me.stackTrace
			}]
		}];
		me.callParent([cfg]);
    }
});// ------------------------------------常量----------------------------------
var oms = {};
oms.operatorCount = {
	defaultV : 0,
	successV : 1,
	failureV : -1
}; // 操作返回值 1为成功，-1为失败
oms.delAgencyType = 'MANY'; // 删除的类型，批量
oms.delType = 'MANY'; // 删除的类型，批量
oms.viewState = {
	add : 'ADD',
	update : 'UPDATE',
	view : 'VIEW'
}; // 查看状态viewState："ADD"新增,"UPDATE"修改,"VIEW"查看
oms.booleanType = {
	yes : 'Y',
	no : 'N'
}; // booleanType 对应后台常量 "布尔类型"
oms.effectiveState = {
	active : 'Y',
	inactive : 'N'
}; // booleanType 对应后台常量 "生效/未生效"
oms.booleanStr = {
	yes : 'true',
	no : 'false'
}; // booleanStr 从复选框中得到值
oms.operateType = {
	save : 'SAVE'
}; // 标识 是否 保存操作
oms.levelType = {
	p : 'PARENT',
	c : 'CHILDREN'
}; // 标识 是父容器还是子容器
// 图片glyph
oms.iconGlyph = {
	add : 0xf055,// 新增
	update : 0xf044,// 修改
	del : 0xf014,// 设置
	download : 0xf019,// 下载
	upload : 0xf093,// 上传
	settings : 0xf013,// 设置
	search : 0xf002
	// 查询
};

/**
 * <p>
 * AJAX请求<br/>
 * <p>
 * 
 * @param url
 *            url请求地址
 * @param params
 *            PARAMS参数
 * @param successFn
 *            successFn调用成功
 * @param failFn
 *            FAILFN调用失败
 * @param syncFlag
 *            是否异步
 * @author 高佳
 * @date 2015年5月13日
 * @update
 */
oms.requestJsonAjax = function(url, params, successFn, failFn, asyncFlag) {
	if (Ext.isEmpty(asyncFlag)) {
		asyncFlag = true;
	}
	// !(asyncFlag && true)
	Ext.Ajax.request({
				url : url,
				async : asyncFlag,
				jsonData : params,
				success : function(response) {
					var result = Ext.decode(response.responseText);
					// 系统异常
					if (result.isException) {
						var errorWindow = Ext.create(
								'Hoau.errorMessage.Window', {
									message : result.message,
									requestId : result.requestId,
									stackTrace : result.stackTrace
								});
						errorWindow.show();
					} else {
						if (result.success) {
							successFn(result);
						} else {
							failFn(result);
						}
					}
				},
				failure : function(response) {
					var result = Ext.decode(response.responseText);
					failFn(result);
				},
				exception : function(response) {
					var result = Ext.decode(response.responseText);
					failFn(result);
				}
			});
};

/**
 * <p>
 * AJAX请求<br/>
 * <p>
 * 
 * @param url
 *            url请求地址
 * @param params
 *            PARAMS参数
 * @param successFn
 *            successFn调用成功
 * @param failFn
 *            FAILFN调用失败
 * @param eventFn
 *            异常后触发操作
 * @param syncFlag
 *            是否异步
 * @author 高佳
 * @date 2015年5月13日
 * @update
 */
oms.requestJsonEventAjax = function(url, params, successFn, failFn, eventFn,
		asyncFlag) {
	if (Ext.isEmpty(asyncFlag)) {
		asyncFlag = true;
	}
	// !(asyncFlag && true)
	Ext.Ajax.request({
				url : url,
				async : asyncFlag,
				jsonData : params,
				success : function(response) {
					var result = Ext.decode(response.responseText);
					// 系统异常
					if (result.isException) {
						var errorWindow = Ext.create(
								'Hoau.errorMessage.Window', {
									message : result.message,
									requestId : result.requestId,
									stackTrace : result.stackTrace
								});
						eventFn();
						errorWindow.show();
					} else {
						if (result.success) {
							successFn(result);
						} else {
							failFn(result);
						}
					}
				},
				failure : function(response) {
					var result = Ext.decode(response.responseText);
					failFn(result);
				},
				exception : function(response) {
					var result = Ext.decode(response.responseText);
					failFn(result);
				}
			});
};

/**
 * <p>
 * Ajax请求--文件表单的请求<br/>
 * <p>
 * 
 * @param url
 *            url请求地址
 * @param params
 *            PARAMS参数
 * @param successFn
 *            successFn调用成功
 * @param failFn
 *            FAILFN调用失败
 * @author 高佳
 * @date 2015年5月13日
 * @update
 */
oms.requestFileUploadAjax = function(url, form, successFn, failFn) {
	Ext.Ajax.request({
				url : url,
				form : form,
				isUpload : true,
				success : function(form, action) {
					var result = action.result;
					if (result.success) {
						successFn(result);
					} else {
						failFn(result);
					}
				},
				failure : function(form, action) {
					var result = action.result;
					failFn(result);
				},
				exception : function(response) {
					var result = Ext.decode(response.responseText);
					failFn(result);
				}
			});
};

/**
 * <p>
 * Ajax请求--非json字符串<br/>
 * <p>
 * 
 * @param url
 *            url请求地址
 * @param params
 *            PARAMS参数
 * @param successFn
 *            successFn调用成功
 * @param failFn
 *            FAILFN调用失败
 * @author 高佳
 * @date 2015年5月13日
 * @update
 */
oms.requestAjax = function(url, params, successFn, failFn) {
	Ext.Ajax.request({
				url : url,
				params : params,
				success : function(response) {
					var result = Ext.decode(response.responseText);
					if (result.success) {
						successFn(result);
					} else {
						failFn(result);
					}
				},
				failure : function(response) {
					var result = Ext.decode(response.responseText);
					failFn(result);
				},
				exception : function(response) {
					var result = Ext.decode(response.responseText);
					failFn(result);
				}
			});
};

/**
 * <p>
 * 填值方法<br/>
 * <p>
 * 
 * @param form
 *            需要加载数据的form
 * @param formRecord
 *            需要加载的数据model
 * @param grid
 *            需要加载数据的grid
 * @param girdData
 *            需要加载的数据data
 * @author 高佳
 * @date 2015年5月13日
 * @update
 */
oms.formReset = function(form, formRecord, grid, girdData) {
	if (!Ext.isEmpty(form) && !Ext.isEmpty(formRecord)) {
		Ext.Array.each(form, function(name, index, countriesItSelf) {
					form[index].loadRecord(formRecord[index]);
				});
	}
	if (!Ext.isEmpty(grid)) {
		Ext.Array.each(grid, function(name, index, countriesItSelf) {
					if (Ext.isEmpty(girdData)) {
						grid[index].store.loadData([]);
					} else {
						grid[index].store.loadPage(1);
					}
				});
	}
};

/**
 * .
 * <p>
 * form表单所有元素 设置 readOnly值<br/>
 * <p>
 * 
 * @param flag
 *            需要设值的表单
 * @param form
 *            表单readOnly值
 * @author 高佳
 * @date 2015年5月13日
 * @update
 */
oms.formSetReadOnly = function(flag, form) {
	var arr = form.items.items;
	if (!Ext.isEmpty(arr)) {
		for (var i = 0; i < arr.length; i++) {
			arr[i].setReadOnly(flag);
		}
	}
};
oms.formFieldSetReadOnly = function(flag, form) {
	var arr = form.query('field');
	if (!Ext.isEmpty(arr)) {
		for (var i = 0; i < arr.length; i++) {
			arr[i].setReadOnly(flag);
		}
	}
};

/**
 * .
 * <p>
 * 消息提示框 ，无国际化<br/>
 * <p>
 * 
 * @param message
 *            提示消息
 * @param fun
 *            回调fun
 * @author 高佳
 * @date 2015年5月13日
 * @update
 */
oms.showInfoMsg = function(message, fun) {
	var len = message.length;
	Ext.Msg.show({
				title : '系统提醒您:',
				width : 110 + len * 15,
				msg : '<div id="message">' + message + '</div>',
				buttons : Ext.Msg.OK,
				icon : Ext.MessageBox.INFO,
				callback : function(e) {
					if (!Ext.isEmpty(fun)) {
						if (e == 'ok') {
							fun();
						}
					}
				}
			});

};

/**
 * .
 * <p>
 * 警告提示框<br/>
 * <p>
 * 
 * @param message
 *            提示消息
 * @param fun
 *            回调fun
 * @author 高佳
 * @date 2015年5月13日
 * @update
 */
oms.showWoringMessage = function(message, fun) {
	var len = message.length;
	Ext.Msg.show({
				title : '系统提醒您:',
				msg : message,
				// cls:'mesbox',
				width : 110 + len * 15,
				msg : '<div id="message">' + message + '</div>',
				buttons : Ext.Msg.OK,
				icon : Ext.MessageBox.WARNING,
				callback : function(e) {
					if (!Ext.isEmpty(fun)) {
						if (e == 'ok') {
							fun();
						}
					}
				}
			});

};

/**
 * .
 * <p>
 * 是和否选择提示框<br/>
 * <p>
 * 
 * @param message
 *            提示消息
 * @param fun
 *            回调fun
 * @author 高佳
 * @date 2015年5月13日
 * @update
 */
oms.showQuestionMes = function(message, fun) {
	var len = message.length;
	Ext.Msg.show({
				title : '系统提醒您:',
				width : 110 + len * 15,
				msg : '<div id="message">' + message + '</div>',
				buttons : Ext.Msg.YESNO,
				icon : Ext.MessageBox.QUESTION,
				callback : function(e) {
					if (!Ext.isEmpty(fun)) {
						fun(e);
					}
				}
			});
};

/**
 * .
 * <p>
 * 错误提示框<br/>
 * <p>
 * 
 * @param message
 *            提示消息
 * @param fun
 *            回调fun
 * @author 高佳
 * @date 2015年5月13日
 * @update
 */
oms.showErrorMes = function(message, fun) {
	var len = message.length;
	Ext.Msg.show({
				title : '系统提醒您:',
				width : 110 + len * 15,
				msg : '<div id="message">' + message + '</div>',
				buttons : Ext.Msg.OK,
				icon : Ext.MessageBox.ERROR,
				callback : function(e) {
					if (!Ext.isEmpty(fun)) {
						if (e == 'ok') {
							fun();
						}
					}
				}
			});
};

/**
 * .
 * <p>
 * 查看状态下 只有 取消按钮可用 [添加网点,取消]按钮分别占 0和1<br/>
 * <p>
 * 
 * @param win
 *            提示消息
 * @param viewState
 *            查看状态
 * @param operateType
 *            操作状态
 * @author 高佳
 * @date 2015年5月13日
 * @update
 */
oms.operateWinBtn = function(win, viewState, operateType) {
	// 查看状态下 只有 取消按钮可用 [添加,取消]按钮分别占 0和1
	if (oms.viewState.view === viewState) {
		var btnArr = win.query('button');
		for (var i = 0; i < btnArr.length; i++) {
			btnArr[i].setDisabled(i != 2);
		}
	} else if (!Ext.isEmpty(operateType)
			&& oms.operateType.save === operateType) {
		var btnArr = win.query('button');
		for (var i = 0; i < btnArr.length; i++) {
			btnArr[i].setDisabled(i > 2);
		}
	}
};

/**
 * .
 * <p>
 * 覆盖Ext.form.RadioGroup的setValue方法<br/> item.getRawValue全为false
 * <p>
 * 
 * @author 高佳
 * @date 2015年5月13日
 * @update
 */
Ext.override(Ext.form.RadioGroup, {
			setValue : function(v) {
				if (this.rendered)
					this.items.each(function(item) {
								item.setValue(item.inputValue == v);
							});
				else {
					for (var k = 0; k < this.items.items.length; k++) {
						this.items.items[k]
								.setValue(this.items.items[k].inputValue == v);
					}
				}
			}
		});

/**
 * .
 * <p>
 * 公共方法，通过storeId和model创建STORE<br/>
 * <p>
 * 
 * @param storeId
 * @param model
 *            store所用到的model名
 * @param fields
 *            store所用到的fields
 * @param data
 * @returns store 返回创建的store
 * @author 高佳
 * @date 2015年5月13日
 * @update
 */
oms.getStore = function(storeId, model, fields, data) {
	var store = null;
	if (!Ext.isEmpty(storeId)) {
		store = Ext.data.StoreManager.lookup(storeId);
	}
	if (Ext.isEmpty(data)) {
		data = [];
	}
	if (!Ext.isEmpty(model)) {
		if (Ext.isEmpty(store)) {
			store = Ext.create('Ext.data.Store', {
						storeId : storeId,
						model : model,
						data : data
					});
		}
	}
	if (!Ext.isEmpty(fields)) {
		if (Ext.isEmpty(store)) {
			store = Ext.create('Ext.data.Store', {
						storeId : storeId,
						fields : fields,
						data : data
					});
		}
	}
	return store;
};

/**
 * .
 * <p>
 * 设置元素为readOnly<br/>
 * <p>
 * 
 * @param readOnlyIdList
 *            设置为readOnly的元素ID数组
 * @author 高佳
 * @date 2015年5月13日
 * @update
 */
oms.setReadOnly = function(readOnlyIdList) {
	for (var i = 0; i < readOnlyIdList.length; i++) {
		Ext.getCmp(readOnlyIdList[i]).setReadOnly(true);
		Ext.getCmp(readOnlyIdList[i]).addCls('readonly');
	}
};

/**
 * .
 * <p>
 * 设置元素为隐藏并且销毁，使其不在校验<br/>
 * <p>
 * 
 * @param hiddenIdList
 *            设置为隐藏的元素ID数组
 * @author 高佳
 * @date 2015年5月13日
 * @update
 */
oms.setHiddenAndDestroy = function(hiddenIdList) {
	for (var i = 0; i < hiddenIdList.length; i++) {
		Ext.getCmp(hiddenIdList[i]).hide();
		Ext.getCmp(hiddenIdList[i]).destroy();
	}
};

/**
 * .
 * <p>
 * 设置元素为隐藏<br/>
 * <p>
 * 
 * @param hiddenIdList
 *            设置为隐藏的元素ID数组
 * @author 高佳
 * @date 2015年5月13日
 * @update
 */
oms.setHidden = function(hiddenIdList) {
	for (var i = 0; i < hiddenIdList.length; i++) {
		Ext.getCmp(hiddenIdList[i]).hide();
	}
};

/**
 * .
 * <p>
 * 设置元素为销毁<br/>
 * <p>
 * 
 * @param destoryIdList
 *            设置为destory的元素ID数组
 * @author 高佳
 * @date 2015年5月13日
 * @update
 */
oms.setDestroy = function(destoryIdList) {
	for (var i = 0; i < destoryIdList.length; i++) {
		Ext.getCmp(destoryIdList[i]).destroy();
	}
};

/**
 * .
 * <p>
 * 设置元素为不可用<br/>
 * <p>
 * 
 * @param disabledIdList
 *            设置为Disabled的元素ID数组
 * @author 高佳
 * @date 2015年5月13日
 * @update
 */
oms.setDisabled = function(disabledIdList) {
	for (var i = 0; i < disabledIdList.length; i++) {
		Ext.getCmp(disabledIdList[i]).setDisabled(true);
	}
};

/**
 * .
 * <p>
 * 清除事件<br/>
 * <p>
 * 
 * @param clearIdList
 *            设置为清除事件的元素ID数组
 * @author 高佳
 * @date 2015年5月13日
 * @update
 */
oms.clearListeners = function(clearIdList) {
	for (var i = 0; i < clearIdList.length; i++) {
		Ext.getCmp(clearIdList[i]).clearListeners();
	}
};

/**
 * .
 * <p>
 * 数组中是否有空值<br/>
 * <p>
 * 
 * @param array
 *            数组
 * @author 高佳
 * @date 2015年5月13日
 * @update
 */
oms.isHaveEmpty = function(array) {
	var boolen = false;
	for (var i = 0; i < array.length; i++) {
		if (Ext.isEmpty(array[i])) {
			boolen = true;
			return boolen;
		}
	}
	return boolen;
};

/**
 * .
 * <p>
 * JS日期的format方法<br/>
 * <p>
 * 
 * @param format
 *            日期格式
 * @author 高佳
 * @date 2015年5月13日
 * @update
 */
Date.prototype.format = function(format) {
	if (Ext.isEmpty(this) || this.getTime() == 0
			|| this.toString().indexOf('GMT') == -1) {
		return null;
	}
	var o = {
		"M+" : this.getMonth() + 1, // month
		"d+" : this.getDate(), // day
		"h+" : this.getHours(), // hour
		"m+" : this.getMinutes(), // minute
		"s+" : this.getSeconds(), // second
		"q+" : Math.floor((this.getMonth() + 3) / 3), // quarter
		"S" : this.getMilliseconds()
		// millisecond
	};

	if (/(y+)/.test(format)) {
		format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4
						- RegExp.$1.length));
	};

	for (var k in o) {
		if (new RegExp("(" + k + ")").test(format)) {
			format = format.replace(RegExp.$1, RegExp.$1.length == 1
							? o[k]
							: ("00" + o[k]).substr(("" + o[k]).length));
		}
	};
	return format;
};

/**
 * .
 * <p>
 * 根据传的参数生成查询条件<br/>
 * <p>
 * 
 * @param modelList
 *            要转换的Modellist
 * @returns dataList
 * @author 高佳
 * @date 2015年5月13日
 * @update
 */
oms.changeModelListToDataList = function(modelList) {
	var dataList = new Array();
	for (var i = 0; i < modelList.length; i++) {
		dataList.push(modelList[i].data);
	}
	return dataList;
};

/**
 * .
 * <p>
 * 数据的将全局变量复制出来<br/>
 * <p>
 * 
 * @param modelList
 *            要转换的Modellist
 * @returns dataList
 * @author 高佳
 * @date 2015年5月13日
 * @update
 */
oms.copyModelListToDataList = function(modelList) {
	var dataList = new Array();
	for (var i = 0; i < modelList.length; i++) {
		dataList.push(modelList[i]);
	}
	return dataList;
};

/**
 * .
 * <p>
 * 为js中的STRING加上trim方法<br/>
 * <p>
 * 
 * @author 高佳
 * @date 2015年5月13日
 * @update
 */
String.prototype.trim = function() {
	// 用正则表达式将前后空格
	// 用空字符串替代。
	return this.replace(/(^\s*)|(\s*$)/g, "");
};

/**
 * .
 * <p>
 * 为数组添加数组元素<br/>
 * <p>
 * 
 * @param list
 *            数组
 * @param all
 *            数组
 * @author 高佳
 * @date 2015年5月13日
 * @update
 */
oms.addAll = function(list, all) {
	var newlist = new Array();
	newlist.push(all);
	for (var i = 0; i < list.length; i++) {
		newlist.push(list[i]);
	}
	return newlist;
};

/**
 * .
 * <p>
 * 根据code获取name<br/>
 * <p>
 * 
 * @param list
 *            集合
 * @param code
 * @author 高佳
 * @date 2015年5月13日
 * @update
 */
oms.changeCodeToName = function(list, code) {
	var name = '';
	for (var i = 0; i < list.length; i++) {
		if (list[i].valueCode == code) {
			name = list[i].valueName;
		}
	}
	return name;
};
/**
 * .
 * <p>
 * store中根据code获取name<br/>
 * <p>
 * 
 * @param store
 * @param code
 * @author 高佳
 * @date 2015年5月13日
 * @update
 */
oms.changeCodeToNameStore = function(store, code) {
	var name = '';
	if (!Ext.isEmpty(store)) {
		store.each(function(record) {
					if (record.get('valueCode') == code) {
						name = record.get('valueName');
					}
				});
	}
	return name;
};

/**
 * 获取浏览器高度
 */
oms.getBrowserHeight = function() {
	var browserHeight = document.documentElement.clientHeight;
	return browserHeight;
};
oms.getBrowserWidth = function() {
	var browserWidth = document.documentElement.clientWidth;

	return browserWidth;
};
/**
 * 打印日志
 */
oms.log = function(message) {
	if (console) {
		console.log(message);
	}
}

var loadWin;
oms.loadMaskshow = function(text) {
	if (Ext.isEmpty(text)) {
		text = "Loading...";
	}
	if (Ext.isEmpty(loadWin)) {
		loadWin = Ext.create('Ext.window.Window', {
					height : 120,
					width : 200,
					// hideShadowOnDeactivate : true,
					plain : true,
					// bodyStyle: 'background:#ffc; padding:10px;',
					closable : false,
					modal : true,
					// frame:true,
					loadMarsk : null
				});
		var myMask = new Ext.LoadMask({
					msg : text,
					target : loadWin
				});
		// myMask.show();
		loadWin.loadMarsk = myMask;

	} else {

		loadWin.loadMarsk.msg = text;
	}

	loadWin.show();
	loadWin.loadMarsk.show();
};

oms.loadMaskClear = function() {
	loadWin.loadMarsk.hide();
	loadWin.hide();
};
// 随机数
oms.generateMixed = function(n) {
	var chars = ['0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B',
			'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O',
			'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'];
	var res = "";
	for (var i = 0; i < n; i++) {
		var id = Math.ceil(Math.random() * 35);
		res += chars[id];
	}
	return res;
}
oms.shiOrFouToYN = function(value) {
	if (value == 'Y')
		return '是';
	if (value == 'N')
		return '否';
	return '';
}
/**
 * 可控页数控件------begin
 * 用法：plugins: new Ext.ui.plugins.ComboPageSize([{pageSizes:[10, 20, 50, 100]}])
 * 默认分页为：{pageSizes:[10, 20, 50, 100]}
 */
Ext.namespace('Ext.ui.plugins');
Ext.ui.plugins.ComboPageSize = function(config) {
	Ext.apply(this, config);
};
Ext.extend(Ext.ui.plugins.ComboPageSize, Ext.util.Observable, {

	pageSizes : [10, 20, 50, 100],
	prefixText : '每页显示',
	postfixText : '条',
	addToItem : true, // true添加到items中去，配合index；false则直接添加到最后
	index : 10, // 在items中的位置
	init : function(pagingToolbar) {
		var ps = this.pageSizes;
		var combo = new Ext.form.ComboBox({
					typeAhead : true,
					triggerAction : 'all',
					lazyRender : true,
					mode : 'local',
					width : 80,
					store : ps,
					value : 20, //初始化默认值
					enableKeyEvents : true,
					editable : false,
					listeners : {
						select : function(c, r, i) {
							pagingToolbar.store.pageSize = r.data.field1;
							pagingToolbar.moveFirst();
						}
					}
				});
		if (this.addToItem) {
			var inputIndex = this.index;
			if (inputIndex > pagingToolbar.items.length)
				inputIndex = pagingToolbar.items.length;
			pagingToolbar.insert(++inputIndex, '-');
			pagingToolbar.insert(++inputIndex, this.prefixText);
			pagingToolbar.insert(++inputIndex, combo);
			pagingToolbar.insert(++inputIndex, this.postfixText);
		} else {
			pagingToolbar.add('-');
			pagingToolbar.add(this.prefixText);
			pagingToolbar.add(combo);
			pagingToolbar.add(this.postfixText);
		}
		pagingToolbar.on({
					beforedestroy : function() {
						combo.destroy();
					}
				});
	}
});
/**
 * 可控页数控件------end
 */;//下拉单选框
Ext.define('Butterfly.commonSelector.CommonCombSelector', {
	extend : 'Hoau.commonselector.DynamicComboSelector',
	minChars : 0,
	disableKeyFilter: true,
	isPaging : false,// 分页
	isEnterQuery : true,// 回车查询
	listWidth : 200,// 设置下拉框宽度
	active : null,
	myActive : 'Y',
	record : null,
	displayField : null,
	valueField : null, 
	displayField : null,// 显示名称
	valueField : null,// 值
	queryParam : null,// 查询参数
	realValue : null,
	isSearchContacts:false,
	setCombValue : function(displayText, valueText) {
		var me = this, key = me.displayField + '', value = me.valueField
				+ '';
		var m = Ext.create(me.store.getModel().getName());
		m.set(key, displayText);
		m.set(value, valueText);
		me.record = m;
		me.store.loadRecords([m]);
		me.setValue(valueText);
		me.realValue = valueText;
	},
	listeners: {
        /*change: function(comb, newValue, oldValue, eOpts){
        		if(comb.isExpanded==true){
					comb.collapse();
				}
				if(newValue != oldValue){
					comb.realValue = null;
				}
        },*/
        //失去焦点时校验是否做出选择，未做出选择清空下拉框
		blur:function(comb, the, eOpts){
			if(!comb.isSearchContacts){
				if (Ext.isEmpty(comb.realValue)) {
					comb.setRawValue(null);
					comb.setValue(null);
				}else{
					var display = comb.record.data[comb.displayField];
					if(!Ext.Object.equals(display,comb.getRawValue())){
						comb.setRawValue(null);
						comb.setValue(null);
						comb.realValue = null;
					}
				}
			}
		},
		select:function(comb, records, obs){
			comb.record = records;
			var data = records.data;
			comb.realValue = data[comb.valueField];
			//显示情况控件
			comb.getTrigger('clear').show();
		},
		keyup : function(combo, event, eOpts){
			//下拉框输入值
			var value = combo.getRawValue();
			//情况控件
			var clearTrigger = combo.getTrigger('clear');
	        if(!Ext.isEmpty(value)){
	        	//输入不为空显示情况控件
	        	clearTrigger.show();
	        }else{
	        	//隐藏情况控件
	        	clearTrigger.hide();
	        }
	        //关闭下拉框
	        //combo.collapse();
			if(combo.isEnterQuery == true && event.getKey() == event.ENTER){
				var rawValue = combo.getRawValue();
				combo.store.loadPage(1,{
					scope: this,
					callback: function(records, operation, success) {
						if(records.length>0){
							//展开下拉框
							combo.expand();
						}
						combo.setRawValue(rawValue);
					}
				});
			}
		}
    },
	
	getBeforeLoad : function(store, operation, e) {
		var me = this;
		var me = this, searchParams = operation.getParams();
		if (Ext.isEmpty(searchParams)) {
			searchParams = {};
		}
		var prefix = me.queryParam.substring(0, me.queryParam
						.lastIndexOf('.'))
				+ '.';
		var param = prefix + me.myQueryParam;
		if (!Ext.isEmpty(me.myQueryParam)) {
			searchParams[param] = me.getRawValue();
			searchParams[me.queryParam] = null;
		} else {
			searchParams[me.queryParam] = me.rawValue;
			if(!Ext.isEmpty(me.myQueryParam)){
				searchParams[param] = null;
			}
		}
		/*var activeParam = prefix + 'active';
		var act = Ext.isEmpty(me.active) ? me.myActive : me.active;
		searchParams[activeParam] = act;*/
		Ext.apply(store.proxy.extraParams, searchParams);
	},
	triggers:{
		clear : {
				cls : 'x-form-clear-trigger',
				handler : function(){
					this.setRawValue(null);
					this.setValue(null);
					this.realValue = null;
					this.getTrigger('clear').hide();
				},
				hidden : true
		}
	},
	constructor : function(config) {
		var me = this, cfg = Ext.apply({}, config);
		me.active = config.active; 
		me.store.addListener('select', function(comb, records, obs) {
			var record = records[0];
			me.record = record;
			me.realValue = record[me.valueField];
		});
		me.callParent([cfg]);
		/*me.on('blur',me.getBlur,me); 
		me.on('select',me.getSelect,me); 
		me.on('change',me.getChange,me);
		me.on('keyup',me.getKeyup,me);*/
	},
	getRecord : function() {
		var me = this;
		return me.record;
	}
});




/**
 * #########################################
 * #######      数据字典下拉框开始          #######
 * #########################################
 */
//数据字典下拉单选框
Ext.define('Butterfly.commonSelector.DataDictionaryCommonSelector', {
	extend : 'Ext.form.field.ComboBox',
	alias: 'widget.dataDictionarySelector',
    displayField: 'valueName',
    valueField: 'valueCode',
    queryMode : 'local',
    editable : false,
    setTermsCode:function(termsCode){
    	var store = getDataDictionary().getDataDictionaryStore(termsCode);
		if(!Ext.isEmpty(store)&&!Ext.isEmpty(this.anyRecords)){
			store.insert(0,this.anyRecords);
		}
    	this.setStore(store);
    },
    
    constructor : function(config) {
		var me = this, cfg = Ext.apply({}, config);
		var store = getDataDictionary().getDataDictionaryStore(config.termsCode);
		if(!Ext.isEmpty(store)&&!Ext.isEmpty(config.anyRecords)){
			//store.add(config.anyRecords);
			store.insert(0,config.anyRecords);//新增的数据在第一个位置(“全部”)
		}
		me.store = store;
		me.callParent([cfg]);
	}
});
/**
 * #########################################
 * #######      数据字典下拉框结束          #######
 * #########################################
 */

/**
 * #########################################
 * #######      是否下拉框开始          #######
 * #########################################
 */
Ext.define('Butterfly.model.baseinfo.YesNoModel',{
	extend : 'Ext.data.Model',
	fields : [{
		name : 'name',
		type : 'string'
	},{
		name : 'value',
		type : 'string'
	}]
});
Ext.define('Butterfly.commonSelector.YesNoAllStore', {
	extend : 'Ext.data.Store',
	model: 'Butterfly.model.baseinfo.YesNoModel',
    data : [{'name':'全部','value':''},{'name':'是','value':'Y'},{'name':'否','value':'N'}]
});
Ext.define('Butterfly.commonSelector.YesNoStore', {
	extend : 'Ext.data.Store',
	model: 'Butterfly.model.baseinfo.YesNoModel',
    data : [{'name':'是','value':'Y'},{'name':'否','value':'N'}]
});
Ext.define('Butterfly.commonSelector.YesOrNoSelector', {
	extend : 'Ext.form.ComboBox',
	alias : 'widget.yesnocombselector',
	listWidth : this.width,// 下拉列表宽度，从外面传入
	multiSelect : false,// 从外面传入
	displayField : 'name',// 显示名称
	valueField : 'value',// 值
	isShowAll : false,// 是否显示全部
	constructor : function(config) {
		var me = this, 
		cfg = Ext.apply({}, config); 
		if (config.isShowAll){
			me.store = Ext.create('Butterfly.commonSelector.YesNoAllStore');
		}else{
			me.store = Ext.create('Butterfly.commonSelector.YesNoStore');
		}
		me.callParent([cfg]);
	}
});
//是和否checkBox
Ext.define('Butterfly.commonCheckBox.YesOrNoCheckBox', {
	extend : 'Ext.form.field.Checkbox',
	alias : 'widget.yesnocheckbox',
	inputValue: 'Y',         //选中的值
    uncheckedValue: 'N',
    getValue : function(){
    	if(this.checked){
    		return 'Y';
    	}else{
    		return 'N';
    	}
    }
});
/**
 * #########################################
 * #######      是否下拉框结束          #######
 * #########################################
 */
/**
 * #########################################
 * #######      组织相关下拉框开始          #######
 * #########################################
 */
/**
 * 用户model
 */
Ext.define('Butterfly.baseinfo.commonSelector.UserModel', {
	extend : 'Ext.data.Model',
	fields : [{
				name : 'id'
			}, {
				name : 'userName'
				//用户名
			}, {
				name : 'empCode'
				//员工工号
			}, {
				name : 'empName'
				//员工姓名
			}, {
				name : 'password'
				//密码
			}, {
				name : 'title'
				//称谓
			}, {
				name : 'beginTime'
			}, {
				name : 'endTime'
			}, {
				name : 'isEmp'
				//是否公司员工
			}]
});
//用户下拉框store
Ext.define('Butterfly.commonUserSelector.UserCombStore', {
	extend : 'Ext.data.Store',
	model : 'Butterfly.baseinfo.commonSelector.UserModel',
	pageSize : 20,
	proxy : {
		type : 'ajax',
		url : '../baseinfo/commonUserSearchAction!seacrhUserByParam.action',
		actionMethods : 'POST',
		reader : {
			type : 'json',
			rootProperty : 'commonUserVo.commonUserEntityList',
			totalProperty : 'totalCount'
		}
	}
});
//用户单选
Ext.define('Butterfly.commonUserSelector.DynamicUserSelector', {
	extend : 'Butterfly.commonSelector.CommonCombSelector',
	alias : 'widget.dynamicusercombselector',
	displayField : 'empName',// 显示名称
	valueField : 'userName',// 值

	isEnable : null,//当前是否可用（Y，N--根据启用时间，失效时间判定）

	active : null,
	queryParam : 'commonUserVo.userSearchConditionEntity.queryParam',// 查询参数
	showContent : '{empName}&nbsp;&nbsp;&nbsp;{title}',// 显示表格列
	constructor : function(config) {
		var me = this, cfg = Ext.apply({}, config);    
		me.store = Ext.create('Butterfly.commonUserSelector.UserCombStore');
		
		me.store.addListener('beforeload', function(store, operation, eOpts) {
			var searchParams = operation.getParams();
			if (Ext.isEmpty(searchParams)) {
				searchParams = {};
			}

			if (!Ext.isEmpty(config.isEnable)) {
				searchParams['commonUserVo.userSearchConditionEntity.isEnable'] = config.isEnable;
			}
			if (!Ext.isEmpty(config.active)) {
				searchParams['commonUserVo.userSearchConditionEntity.active'] = config.active;
			}else{
				searchParams['commonUserVo.userSearchConditionEntity.active'] = 'Y';
			}
			Ext.apply(store.proxy.extraParams, searchParams);  
		})
		me.callParent([cfg]);
	}
});

/**
 * 员工model
 * 
 */
Ext.define('Butterfly.baseinfo.commonSelector.EmployeModel',{
	extend : 'Ext.data.Model',
	fields : [{
				name : 'id'
			}, {
				name : 'employeeCode'
			}, {
				name : 'employeeName'
			}, {
				name : 'pininName'
			}, {
				name : 'pinyinShortName'
			}, {
				name : 'account'
			}, {
				name : 'deptName'
			}, {
				name : 'deptCode'
			}, {
				name : 'jobName'
			}, {
				name : 'jobCode'
			}
			]
});

//员工下拉框store
Ext.define('Butterfly.commonEmployeSelector.EmployeCombStore',{
	extend : 'Ext.data.Store',
	model : 'Butterfly.baseinfo.commonSelector.EmployeModel',
	pageSize : 20,
	proxy : {
		type : 'ajax',
		url : '../baseinfo/commonEmployeeSearchAction!seacrhEmployeeByParam.action',
		actionMethods : 'POST',
		reader : {
			type : 'json',
			rootProperty : 'commonEmployeeVo.commonEmployeeList',
			totalProperty : 'totalCount'
		}
	}
});

/**
 * 员工单选
 */
Ext.define('Butterfly.commonEmpSelector.EmployeeCommonSelector',{
	extend : 'Butterfly.commonSelector.CommonCombSelector',
	alias : 'widget.dynamicemployeecombselector',
	displayField : 'employeeName',// 显示名称
	valueField : 'employeeCode',// 值
	active : null,
	queryParam : 'commonEmployeeVo.employeeSearchConditionEntity.queryParam',// 查询参数
	showContent : '{employeeName}&nbsp;&nbsp;&nbsp;{jobName}',// 显示表格列
	constructor : function(config) {
		var me = this, cfg = Ext.apply({}, config);    
		me.store = Ext.create('Butterfly.commonEmployeSelector.EmployeCombStore');
		
		me.store.addListener('beforeload', function(store, operation, eOpts) {
			var searchParams = operation.getParams();
			if (Ext.isEmpty(searchParams)) {
				searchParams = {};
			}
			if (!Ext.isEmpty(config.active)) {
				searchParams['commonEmployeeVo.employeeSearchConditionEntity.active'] = config.active;
			}else{
				searchParams['commonEmployeeVo.employeeSearchConditionEntity.active'] = 'Y';
			}
			Ext.apply(store.proxy.extraParams, searchParams);  
		})
		me.callParent([cfg]);
	}
});


/**********************司机业务员信息begin**************************/
/**
 * 司机业务员model
 * 
 */
Ext.define('Butterfly.baseinfo.commonSelector.OmsHRModel',{
	extend : 'Ext.data.Model',
	fields : [{
		name : 'empNo'
	}, {
		name : 'empName'
	}, {
		name : 'jobName'
	}, {
		name : 'logistCode'
	}, {
		name : 'empMobile'
	}
	]
});

/**
 * 司机业务员下拉框store
 */
Ext.define('Butterfly.commonOmsHRSelector.OmsHRCombStore',{
	extend : 'Ext.data.Store',
	model : 'Butterfly.baseinfo.commonSelector.OmsHRModel',
	pageSize : 20,
	proxy : {
		type : 'ajax',
		url : '../baseinfo/commonOmsHRSearchAction!seacrhOmsHRByParam.action',
		actionMethods : 'POST',
		reader : {
			type : 'json',
			rootProperty : 'commonOmsHRVo.commonOmsHRList',
			totalProperty : 'totalCount'
		}
	}
});

/**
 * 司机业务员单选
 */
Ext.define('Butterfly.commonOmsHRSelector.OmsHRCommonSelector',{
	extend : 'Butterfly.commonSelector.CommonCombSelector',
	alias : 'widget.dynaOmsHRcombselector',
	displayField : 'empName',// 显示名称
	valueField : 'empNo',// 值
	queryParam : 'commonOmsHRVo.omsHRSearchConditionEntity.queryParam',// 查询参数
	showContent : '{empName}&nbsp;&nbsp;{logistCode}&nbsp;{jobName}',// 显示表格列
	constructor : function(config) {
		var me = this, cfg = Ext.apply({}, config);    
		me.store = Ext.create('Butterfly.commonOmsHRSelector.OmsHRCombStore');
		
		me.store.addListener('beforeload', function(store, operation, eOpts) {
			var searchParams = operation.getParams();
			if (Ext.isEmpty(searchParams)) {
				searchParams = {};
			}
			Ext.apply(store.proxy.extraParams, searchParams);  
		})
		me.callParent([cfg]);
	}
});
/**********************司机业务员信息end**************************/

/**
 * 组织model
 */
Ext.define('Butterfly.baseinfo.commonSelector.OrgModel', {
	extend : 'Ext.data.Model',
	fields : [{
				name : 'id'
			}, {
				name : 'code'
			}, {
				name : 'logistCode'
			}, {
				name : 'name'
			}, {
				name : 'cityCode'
			}, {
				name : 'countyCode'
			}, {
				name : 'provinceCode'
			}, {
				name : 'active'
			}, {
				name : 'isDivision'
			}, {
				name : 'isBigRegion'
			}, {
				name : 'isRoadArea'
			}, {
				name : 'isSalesDepartment'
			}, {
				name : 'isTransferCenter'
			}, {
				name : 'isFleet'
			},{
				name : 'isPlatform'
			},{
				name : 'phone'
			}]
});
//组织下拉框store
Ext.define('Butterfly.commonOrgSelector.OrgCombStore', {
	extend : 'Ext.data.Store',
	model : 'Butterfly.baseinfo.commonSelector.OrgModel',
	pageSize : 20,
	proxy : {
		type : 'ajax',
		url : '../baseinfo/commonOrgSearchAction!seacrhOrgByParam.action',
		actionMethods : 'POST',
		reader : {
			type : 'json',
			rootProperty : 'commonOrgVo.commonOrgEntityList',
			totalProperty : 'totalCount'
		}
	}
});
//组织单选
Ext.define('Butterfly.commonOrgSelector.DynamicOrgSelector', {
	extend : 'Butterfly.commonSelector.CommonCombSelector',
	alias : 'widget.dynamicorgcombselector',
	displayField : 'name',// 显示名称
	valueField : 'code',// 值
	type : 'ORG',// 部门类型 一种部门类型，传递此值
	types : null,// 部门类型 多种部门类型传递次值 两个类型的值之间用","隔开
	isDivision : null,// 查询事业部 配置此值
	isBigRegion : null,// 查询大区 配置此值
	isRoadArea : null,// 查询路区 配置此值
	isSalesDepartment : null,// 查询门店 配置此值
	isTransferCenter : null,// 查询场站 配置此值
	isFleet : null,// 查询车队 配置此值
	isPlatform : null,// 查询平台 配置此值
	logistCode : null,//物流代码	
	arrive:null,//是否可到达
	depart:null,//是否可出发
	queryParam : 'commonOrgVo.orgSearchConditionEntity.queryParam',// 查询参数
	showContent : '{name}&nbsp;&nbsp;&nbsp;{logistCode}',// 显示表格列
	constructor : function(config) {
		var me = this, cfg = Ext.apply({}, config);    
		me.store = Ext.create('Butterfly.commonOrgSelector.OrgCombStore');
		me.store.addListener('beforeload', function(store, operation, eOpts) {
			var searchParams = operation.getParams();
			if (Ext.isEmpty(searchParams)) {
				searchParams = {};
			}
			// 传递的部门类型是多种
			var types = null;
			if (!Ext.isEmpty(config.types)) {
				types = config.types.split(',');
				searchParams['commonOrgVo.orgSearchConditionEntity.types'] = types;
			}
			if (!Ext.isEmpty(config.type)) {
				searchParams['commonOrgVo.orgSearchConditionEntity.type'] = config.type;
			}else{
				searchParams['commonOrgVo.orgSearchConditionEntity.type'] = me.type;
			}
			if (!Ext.isEmpty(config.isDivision)) {
				searchParams['commonOrgVo.orgSearchConditionEntity.isDivision'] = config.isDivision;
			}
			if (!Ext.isEmpty(config.isBigRegion)) {
				searchParams['commonOrgVo.orgSearchConditionEntity.isBigRegion'] = config.isBigRegion;
			}
			if (!Ext.isEmpty(config.isLoadArea)) {
				searchParams['commonOrgVo.orgSearchConditionEntity.isRoadArea'] = config.isRoadArea;
			}
			if (!Ext.isEmpty(config.isSalesDepartment)) {
				searchParams['commonOrgVo.orgSearchConditionEntity.isSalesDepartment'] = config.isSalesDepartment;
			}
			if (!Ext.isEmpty(config.isTransferCenter)) {
				searchParams['commonOrgVo.orgSearchConditionEntity.isTransferCenter'] = config.isTransferCenter;
			}
			if (!Ext.isEmpty(config.isFleet)) {
				searchParams['commonOrgVo.orgSearchConditionEntity.isFleet'] = config.isFleet;
			}
			if (!Ext.isEmpty(config.isPlatform)) {
				searchParams['commonOrgVo.orgSearchConditionEntity.isPlatform'] = config.isPlatform;
			}
			if (!Ext.isEmpty(config.active)) {
				searchParams['commonOrgVo.orgSearchConditionEntity.active'] = config.active;
			}else{
				searchParams['commonOrgVo.orgSearchConditionEntity.active'] = 'Y';
			}
			Ext.apply(store.proxy.extraParams, searchParams);  
		})
		me.callParent([cfg]);
	}
});
/**
 * #########################################
 * #######      组织相关下拉框结束         #######
 * #########################################
 */
/**
 * #########################################
 * #######      省市区相关下拉框开始        #######
 * #########################################
 */
//行政区域Model
Ext.define('Butterfly.baseinfo.commonSelector.DistrictModel', {
	extend : 'Ext.data.Model',
	fields : [{
				name : 'districtCode',
				type : 'string'
			}, {
				name : 'districtName',
				type : 'string'
			}, {
				name : 'districtType',
				type : 'string'
			}, {
				name : 'parentDistrictName',
				type : 'string'
			},{
				name : 'parentDistrictCode',
				type : 'string'
			}]
});
//行政区域store
Ext.define('Butterfly.baseinfo.commonSelector.DistrictStore', {
	extend : 'Ext.data.Store',
	model : 'Butterfly.baseinfo.commonSelector.DistrictModel',
	pageSize : 50,
	proxy : {
		type : 'ajax',
		url : '../baseinfo/districtAction!queryDistrictByName.action',
		actionMethods : 'POST',// 否则可能会出现中文乱码
		reader : {
			type : 'json',
			rootProperty : 'districtVo.districtList',
			totalProperty : 'totalCount'
		}
	}
});
/**
 * 国家store
 */
Ext.define('Butterfly.baseinfo.commonSelector.NationStore', {
	extend : 'Ext.data.Store',
	model : 'Butterfly.baseinfo.commonSelector.DistrictModel',
	pageSize : 50,
	proxy : {
		type : 'ajax',
		url : '../baseinfo/districtAction!queryAllNation.action',
		actionMethods : 'POST',// 否则可能会出现中文乱码
		reader : {
			type : 'json',
			rootProperty : 'districtVo.districtList',
			totalProperty : 'totalCount'
		}
	}
});
/**
 * 省份store
 */
Ext.define('Butterfly.baseinfo.commonSelector.ProvinceStore', {
	extend : 'Ext.data.Store',
	model : 'Butterfly.baseinfo.commonSelector.DistrictModel',
	pageSize : 50,
	proxy : {
		type : 'ajax',
		url : '../baseinfo/districtAction!queryProvince.action',
		actionMethods : 'POST',// 否则可能会出现中文乱码
		reader : {
			type : 'json',
			rootProperty : 'districtVo.districtList',
			totalProperty : 'totalCount'
		}
	}
});

/**
 * 城市store
 */
Ext.define('Butterfly.baseinfo.commonSelector.CityStore', {
	extend : 'Ext.data.Store',
	model : 'Butterfly.baseinfo.commonSelector.DistrictModel',
	pageSize : 50,
	proxy : {
		type : 'ajax',
		url : '../baseinfo/districtAction!queryCity.action',
		actionMethods : 'POST',// 否则可能会出现中文乱码
		reader : {
			type : 'json',
			rootProperty : 'districtVo.districtList',
			totalProperty : 'totalCount'
		}
	}
});

/**
 * 区县store
 */
Ext.define('Butterfly.baseinfo.commonSelector.AreaStore', {
	extend : 'Ext.data.Store',
	model : 'Butterfly.baseinfo.commonSelector.DistrictModel',
	pageSize : 50,
	proxy : {
		type : 'ajax',
		url : '../baseinfo/districtAction!queryArea.action',
		actionMethods : 'POST',// 否则可能会出现中文乱码
		reader : {
			type : 'json',
			rootProperty : 'districtVo.districtList',
			totalProperty : 'totalCount'
		}
	}
});
/**
 * 省市区单个下拉框
 */
Ext.define('Butterfly.commonSelector.DistrictSelector', {
	extend : 'Butterfly.commonSelector.CommonCombSelector',
	alias : 'widget.commondistrictselector',
	eventType : ['callparent'],// 一般callparent包含focus事件，如果有callparent,则focus事件可不用传递
	displayField : 'districtName',// 显示名称
	valueField : 'districtCode',
	queryParam : 'districtVo.district.districtName',// 查询参数
	districtType: null,//省市县类别
	showContent : '{districtName}',// 显示表格列
	constructor : function(config) {
		var me = this, cfg = Ext.apply({}, config);
		me.store = Ext.create('Butterfly.baseinfo.commonSelector.DistrictStore');
		me.store.addListener('beforeload', function(store, operation, eOpts) {
			var searchParams = operation.getParams();
			if (Ext.isEmpty(searchParams)) {
				searchParams = {};
			}
			if(!Ext.isEmpty(config.districtType)){
				searchParams['districtVo.district.districtType'] = config.districtType;
			} 
			Ext.apply(store.proxy.extraParams, searchParams);
		});
		me.callParent([cfg]);
	}
});

//联动container	
Ext.define('Butterfly.commonselector.LinkageContainer',{
	extend: 'Ext.form.FieldContainer',
	alias: 'widget.linkagecontainer',
	defaultType: 'linkagecomboselector',
	getItemValue: function(itemId){
		var me = this,
			item = me.items.get(itemId);
		if(item!=null){
			return item.getValue();
		}
		return null;
	},
	getValue: function(){
		var me = this,
			values = new Array();
		for(var i=0;i<me.items.length;i++){
			values[i] = me.getItemValue(me.items.items[i].itemId);
		}
		return values;
	}
});

/**
 * 联动查询组件
 */
Ext.define('Butterfly.commonselector.LinkageComboSelector',{
	extend: 'Hoau.commonselector.DynamicComboSelector',
	alias: 'widget.linkagecomboselector',
	minChars : 0,
	isPaging : false,// 分页
	isEnterQuery : true,// 回车查询
	parentParamsAndItemIds: null,//级联父组件参数
	realValue : null,
	listeners:{
        //失去焦点时校验是否做出选择，未做出选择清空下拉框
		/*blur:function(comb, the, eOpts){
			if (Ext.isEmpty(comb.realValue)) {
				comb.setRawValue(null);
				comb.setValue(null);
			}else{
				var display = comb.record.data[comb.displayField];
				if(!Ext.Object.equals(display,comb.getRawValue())){
					comb.setRawValue(null);
					comb.setValue(null);
					comb.realValue = null;
				}
			}
		},*/
		select:function(comb, records, obs){
			comb.record = records;
			var data = records.data;
			comb.realValue = data[comb.valueField];
			//显示情况控件
			comb.getTrigger('clear').show();
		},
		keyup : function(combo, event, eOpts){
			//下拉框输入值
			var value = combo.getRawValue();
			//情况控件
			var clearTrigger = combo.getTrigger('clear');
	        if(!Ext.isEmpty(value)){
	        	//输入不为空显示情况控件
	        	clearTrigger.show();
	        }else{
	        	//隐藏情况控件
	        	clearTrigger.hide();
	        }
	        //关闭下拉框
	        //combo.collapse();
			if(combo.isEnterQuery == true && event.getKey() == event.ENTER){
				var rawValue = combo.getRawValue();
				combo.store.loadPage(1,{
					scope: this,
					callback: function(records, operation, success) {
						if(records.length>0){
							//展开下拉框
							combo.expand();
						}
						combo.setRawValue(rawValue);
					}
				});
			}
		}
	},
	/*eventType: ['callparent'],//事件(传入)
	getFocus: function(){
		var me = this;
		me.on('focus',function(){
			me.setValue(null);
			me.store.loadPage(1,{
				scope: this,
				callback: function(records, operation, success) {
					if(records.length>0){
						me.expand();
					}
				}
			});
		});
	},*/
	/*getCallParent: function(){
		var me = this,
			cmp;
		if(!Ext.isEmpty(me.parentParamsAndItemIds)){
			Ext.Object.each(me.parentParamsAndItemIds, function(queryParam, itemId, parentParamsAndItemIds) {
				cmp = me.up().items.get(itemId);
				if(!cmp.hasListener('select')){
					cmp.on('select',function(combo){
						me.setValue(null);
						me.store.loadPage(1,{
							scope: this,
							callback: function(records, operation, success) {
								me.focus(false, 100);
								me.expand();
							}
						});
					});
				}
			});
		}
	},*/
	triggers:{
		clear : {
				cls : 'x-form-clear-trigger',
				handler : function(){
					this.setRawValue(null);
					this.setValue(null);
					this.realValue = null;
					this.getTrigger('clear').hide();
				},
				hidden : true
		}
	},
	initComponent:function(){
		var me = this;
		/*me.on('boxready',function(){
			var callparent = null;
			for(var i = 0;i<me.eventType.length;i++){
				if(me.eventType[i]=='focus'){
					me.getFocus();
				}else if(me.eventType[i]=='callparent'){
					callparent = 'callparent';
					me.getCallParent();
				}
			}
			//调用父类方法会自动包含focus事件
			if(callparent=='callparent'){
				me.un('focus');
			}
		});*/
		this.callParent(arguments);
		//增加级联查询条件
		me.store.on('beforeLoad', function(st,operation,e){
			var cmp=null, searchParams=operation.getParams();
			if(Ext.isEmpty(searchParams)){
				searchParams={};
			}
			searchParams[me.queryParam] = me.rawValue;
			if(!Ext.isEmpty(me.parentParamsAndItemIds)){
				Ext.Object.each(me.parentParamsAndItemIds, function(queryParam, itemId, parentParamsAndItemIds) {
					cmp = me.up().items.get(itemId);
					searchParams[queryParam] = cmp.getValue();
				});
			}
			Ext.apply(st.proxy.extraParams, searchParams);
		});
	}
});	

Ext.define('Butterfly.commonSelector.linkReginCombSelector', {
	extend : 'Butterfly.commonselector.LinkageContainer',
	alias : 'widget.linkregincombselector',
//	fieldLabel : '联动选择',
	type : 'N-P-C-C',// type ：N-P-C-C 国省市县 P-C-C :省市县 C-C:市县 P-C:省市
	width : 600,
	nationWidth : 150,// 国家长度
	nationLabel : '',// 国家label
	nationName : '',// 国家名称--对应name
	nationIsBlank : true,
	nationLabelWidth:null,
	provinceWidth : 150,// 省份长度
	provinceLabel : '',// 省份label
	provinceLabelWidth:null,
	provinceName : '',// 省份名称—对应name
	provinceIsBlank : true,
	cityWidth : 150,// 城市长度
	cityLabel : '',// 城市label
	cityName : '',// 城市name
	cityIsBlank : true,
	cityLabelWidth:null,
	areaWidth : 150,// 县长度
	areaLabel : '',// 县label
	areaName : '',// 县name
	areaIsBlank : true,
	areaLabelWidth:null,
	layout : 'column',
	labelWid : 20,
	readOnly : false,
	setReadOnly : function(flag){
		var me =this;
		me.getNation().setReadOnly(flag);
		me.getProvince().setReadOnly(flag);
		me.getCity().setReadOnly(flag);
		me.getCounty().setReadOnly(flag);
	},
	getDefults : function(config) {
		return {
			labelWidth : config.labelWid,
			minChars : 0,
			labelSeparator : ''
		}
	},
	setReginValue : function(displayText, valueText, order) {// 0：国家的值，1：省的值，2：市的值，3：县的值
		var me = this;
		var regionObj=null;
		if(!Ext.isEmpty(order)){
			if(order == '0'){
				regionObj=me.nation;
			}else if(order == '1'){
				regionObj=me.province;
			}else if(order == '2'){
				regionObj=me.city;
			}else if(order == '3'){
				regionObj=me.county;
			}
		}
		var  key = regionObj.displayField + '', value =regionObj.valueField
				+ '';
		var m = Ext.create(regionObj.store.getModel().getName());
		m.set(key, displayText);
		m.set(value, valueText);
		regionObj.store.loadRecords([m]);
		regionObj.setValue(valueText);
	},
	nation:null,
	getNation:function(nationWidth,nationLabel,nationName,nationIsBlank,nationLabelWidth,configType,provinceObj){
		if(Ext.isEmpty(this.nation)){
			this.nation=Ext.widget('linkagecomboselector',{
				configType :configType,
				provinceObj : provinceObj,
				/*editable:false,*/
				eventType : ['focus'],// 一般callparent包含focus事件，如果有callparent,则focus事件可不用传递
				name : 'province',
				itemId : 'Butterfly_baseinfo_Nation_ID',
				store : Ext.create('Butterfly.baseinfo.commonSelector.NationStore'),// 从外面传入
				listeners : {
					'change' : function(ths, the, eOpts){
						if(ths.configType == 'N-P-C-C'){
							ths.provinceObj.setReadOnly(false);
							var provObj=ths.provinceObj.getEl();
							if(!Ext.isEmpty(provObj)){
								provObj.query('input')[0].readOnly = 'readOnly';	
							}
						} 
					}
				},
				displayField : 'districtName',// 显示名称
				valueField : 'districtCode',
				width : nationWidth,
				fieldLabel : nationLabel,
				name : nationName,
				labelWidth:nationLabelWidth,
				allowBlank : nationIsBlank,
				queryParam : 'districtVo.district.queryParam'
			});
		}
		return this.nation;
	},
	province:null,
	getProvince:function(provinceWidth,provinceLabel,provinceName,provinceIsBlank,provinceLabelWidth,configType,cityObj){
		if(Ext.isEmpty(this.province)){
			this.province=Ext.widget('linkagecomboselector',{
				configType:configType,
				cityObj : cityObj,
				/*editable:false,*/
				itemId : 'Butterfly_baseinfo_Province_ID',
				eventType : ['callparent'],
				store : Ext.create('Butterfly.baseinfo.commonSelector.ProvinceStore'),// 从外面传入
				listeners : {
					'change' : function(ths, the, eOpts){
						ths.cityObj.disable();
						ths.cityObj.setValue(null);
						ths.cityObj.realValue = null;
						/*var cityObj=ths.cityObj.getEl();*/
						/*if(!Ext.isEmpty(cityObj)){
							cityObj.query('input')[0].readOnly = 'readOnly';	
						}*/
					},
					'select' : function(ths, the, eOpts){
						ths.cityObj.enable();
						ths.cityObj.getStore().load();
					}
				},
				displayField : 'districtName',// 显示名称
				valueField : 'districtCode',
				minChars : 0,
				width : provinceWidth,
				fieldLabel : provinceLabel,
				name : provinceName,
				labelWidth:provinceLabelWidth,
				allowBlank : provinceIsBlank,
				isPaging: false, 
				parentParamsAndItemIds : {
					'districtVo.district.parentDistrictCode' : 'Butterfly_baseinfo_Nation_ID'
				},// 此处城市不需要传入
				queryParam : 'districtVo.district.queryParam'
			
			});
		}
		return this.province;
	},
	city:null,
	getCity:function(cityWidth,cityLabel,cityName,cityIsBlank,cityLabelWidth,configType,countyObj){
		if(Ext.isEmpty(this.city)){
				this.city=Ext.widget('linkagecomboselector',{
					configType:configType,
					countyObj : countyObj,
					/*editable:false,*/
					itemId : 'Butterfly_baseinfo_City_ID',
					eventType : ['callparent'],// 一般callparent包含focus事件，如果有callparent,则focus事件可不用传递
					store : Ext.create('Butterfly.baseinfo.commonSelector.CityStore'),// 从外面传入
					listeners : {
						'change' : function(ths, the, eOpts){
							ths.countyObj.disable();
							ths.countyObj.setValue(null);
							ths.countyObj.realValue = null;
							/*ths.countyObj.setReadOnly(false);
							var countyObj=ths.countyObj.getEl();
							if(!Ext.isEmpty(countyObj)){
								countyObj.query('input')[0].readOnly = 'readOnly';	
							}*/
						},
						'select' : function(ths, the, eOpts){
							ths.countyObj.enable();
							ths.countyObj.getStore().load();
						}
					},
					displayField : 'districtName',// 显示名称
					valueField : 'districtCode',
					minChars : 0,
					width : cityWidth,
					fieldLabel : cityLabel,
					name : cityName,
					labelWidth:cityLabelWidth,
					allowBlank : cityIsBlank,
					isPaging: false,  
					parentParamsAndItemIds : {
						'districtVo.district.parentDistrictCode' : 'Butterfly_baseinfo_Province_ID'
					},// 此处城市不需要传入
					queryParam : 'districtVo.district.queryParam'
			});
		}
		return this.city;
	},
	county:null,
	getCounty:function(areaWidth,areaLabel,areaNames,areaIsBlank,areaLabelWidth){
		if(Ext.isEmpty(this.county)){
			this.county=Ext.widget('linkagecomboselector',{
				store : Ext.create('Butterfly.baseinfo.commonSelector.AreaStore'),// 从外面传入
				displayField : 'districtName',// 显示名称
				valueField : 'districtCode',
				minChars : 0,
				/*editable:false,*/
				width : areaWidth,
				fieldLabel : areaLabel,
				name : areaNames,
				allowBlank : areaIsBlank,
				labelWidth:areaLabelWidth,
				isPaging: false, 
				parentParamsAndItemIds : {
					'districtVo.district.parentDistrictCode' : 'Butterfly_baseinfo_City_ID'
				},// 此处区域不需要传入
				queryParam : 'districtVo.district.queryParam'
				});
			}
		return  this.county;
	},
	constructor : function(config) {
		var me = this, cfg = Ext.apply({}, config);
		me.defaults = me.getDefults(config);
		var county = me.getCounty(config.areaWidth,config.areaLabel,config.areaName,config.areaIsBlank,config.areaLabelWidth);
		var city = me.getCity(config.cityWidth,config.cityLabel,config.cityName,config.cityIsBlank,config.cityLabelWidth,config.type,county);
		var province = me.getProvince(config.provinceWidth,config.provinceLabel,config.provinceName,config.provinceIsBlank,config.provinceLabelWidth,config.type,city);
		var nation = me.getNation(config.nationWidth,config.nationLabel,config.nationName,config.nationIsBlank,config.nationLabelWidth,config.type,province);
		me.items=[nation,province,city,county];
		if (config.type == 'N-P-C-C') {
			/*province.setReadOnly(true);   
			city.setReadOnly(true);   
			county.setReadOnly(true);*/  
			/*province.cls='readonlyhaveborder'; 
			city.cls='readonlyhaveborder';
			county.cls='readonlyhaveborder';*/			
			/*me.items=[nation,province,city,county];*/
		} else if (config.type == 'P-C-C') {
			if(config.readOnly){
				/*province.disable();
				city.disable();   
				county.disable();
				province.setReadOnly(true);
				city.setReadOnly(true);
				county.setReadOnly(true);*/
			}else{
				/*city.disable();   
				county.disable();*/
			}
			/*city.cls='readonlyhaveborder';
			county.cls='readonlyhaveborder';*/
			province.eventType=['focus'];
			province.parentParamsAndItemIds = null;
			me.items=[province,city,county];
		} else if (config.type == 'P-C') {
			if(config.readOnly){
				/*province.disable();
				city.disable();*/
				province.setReadOnly(true);
				city.setReadOnly(true);
			}else{
				city.disable();
			}
			/*city.cls='readonlyhaveborder';*/
			province.eventType=['focus'];
			province.parentParamsAndItemIds = null;
			me.items=[province,city];
		} else if (config.type == 'C-C') {
			if(config.readOnly){
				/*city.disable();
				county.disable(); */
				city.setReadOnly(true);
				county.setReadOnly(true);
			}else{
				county.disable(true); 
			}
			/*county.cls='readonlyhaveborder';*/
			city.eventType=['focus'];
			city.parentParamsAndItemIds = null;
			me.items=[city,county];
		}
		me.callParent([cfg]);
	}
});
/**
 * #########################################
 * #######      省市区相关下拉框结束        #######
 * #########################################
 */
/**
 * #########################################
 * #######      异常项目下拉框开始        #######
 * #########################################
 */
/**
 * 异常项目
 */
Ext.define('Butterfly.baseinfo.commonSelector.AbnormalProjectModel',{
	extend : 'Ext.data.Model',
	fields : [ {
		name : 'id',
		//id
		type : 'string'
	},{
		name : 'abnormalName',
		//异常名称
		type : 'string'
	},{
		name : 'abnormalCode',
		//异常代号
		type : 'string'
	},{
		name : 'abnormalType',
		//异常类型
		type : 'string'
	},{
		name : 'responsibleParty',
		//责任方
		type : 'string'
	},{
		name : 'orderState',
		//订单状态
		type : 'string'
	},{
		name : 'abnormalDescribe',
		//异常描述
		type : 'string'
	},{
		name : 'abnormalReaso',
		//异常原因
		type : 'string'
	},{
		name : 'abnormalAvailable',
		//异常可用
		type : 'string'
	},{
		name : 'solution',
		//解决方案描述
		type : 'string'
	},{
		name : 'abnormalRemarks',
		//异常备注
		type : 'string'
	}]
});
Ext.define('Butterfly.baseinfo.commonEmployeSelector.AbnormalProjectStore',{
	extend : 'Ext.data.Store',
	model : 'Butterfly.baseinfo.commonSelector.AbnormalProjectModel',
	pageSize : 20,
	proxy : {
		type : 'ajax',
		actionMethods : 'post',
		url : '../baseinfo/abnormalProjectAction!queryAbnormalProject.action',
		reader : {
			type : 'json',
			rootProperty : 'abnormalProjectVo.abnormalProjectList',
			totalProperty : 'totalCount' // 总个数
		}
	}
});

/**
 * 异常项目单选
 */
Ext.define('Butterfly.commonEmpSelector.AbnormalProjectSelector',{
	extend : 'Butterfly.commonSelector.CommonCombSelector',
	alias : 'widget.abnormalProjectSelector',
	displayField : 'abnormalCode',// 显示名称
	valueField : 'abnormalCode',// 值
	active : null,
	queryParam : 'abnormalProjectVo.abnormalProjectParams.abnormalCode',// 查询参数
	showContent : '{abnormalCode}【{abnormalDescribe}】',// 显示表格列
	constructor : function(config) {
		var me = this, cfg = Ext.apply({}, config);    
		me.store = Ext.create('Butterfly.baseinfo.commonEmployeSelector.AbnormalProjectStore');
		
		me.store.addListener('beforeload', function(store, operation, eOpts) {
			var searchParams = operation.getParams();
			if (Ext.isEmpty(searchParams)) {
				searchParams = {};
			}
			if (!Ext.isEmpty(config.active)) {
				searchParams['abnormalProjectVo.abnormalProjectParams.abnormalAvailable'] = config.active;
			}else{
				searchParams['abnormalProjectVo.abnormalProjectParams.abnormalAvailable'] = 'Y';
			}
			Ext.apply(store.proxy.extraParams, searchParams);  
		})
		me.callParent([cfg]);
	}
});
/**
 * #########################################
 * #######      异常项目下拉框结束        #######
 * #########################################
 */



/*****************收发货人搜索框**************/
/**收发货 联系人公共下拉选择model**/
Ext.define('Butterfly.order.commonSelector.ContactsModel', {
	extend : 'Ext.data.Model',
	fields :[{
			name: 'id',type:'string'
		},{
			name: 'showValue',type:'string'
		}]
});
/**收发货 联系人公共下拉选择Store**/
Ext.define('Butterfly.order.commonSelector.ContactsStore',{
	extend:'Ext.data.Store',
	model: 'Butterfly.order.commonSelector.ContactsModel',
	pageSize:15,
	proxy:{
		type:"ajax",
		url:"../order/contactsAction!queryContacts.action",
		actionMethods:"POST",
		reader:{
			type:'json',
			root:'contacts',
			totalProperty : 'totalCount'
		}
	}
});
/**收发货 联系人公共下拉选择**/
Ext.define('Butterfly.order.commonContactsSelector.DynamicContactsSelector',{
	extend : 'Butterfly.commonSelector.CommonCombSelector',
	alias : 'widget.dynamiccontactcombselector',
	displayField : 'showValue',//显示的值
	valueField : 'showValue',// 值
	contactType : null, //联系人类型,
	paramType : null, //参数类型
	queryParam : 'selectParam',
	showContent : '{showValue}【{contactName}】',// 显示表格列
	isSearchContacts:true,
	constructor : function(config) {
		var me = this, cfg = Ext.apply({}, config);
		me.store = Ext.create('Butterfly.order.commonSelector.ContactsStore');
		me.store.addListener('beforeload', function(store, operation, eOpts) {
			var searchParams = operation.getParams();
			if (Ext.isEmpty(searchParams)) {
				searchParams = {};
			}
			if (!Ext.isEmpty(config.contactType)) {
				searchParams['contactType'] = config.contactType;
			}
			if (!Ext.isEmpty(config.paramType)) {
				searchParams['paramType'] = config.paramType;
			}
			Ext.apply(store.proxy.extraParams, searchParams);  
		})
		me.callParent([cfg]);
	}
	
});
/*****************收发货人搜索框**************/




/******************报表查询*********************/

/**
 * 报表sqlmodel
 * 
 */
Ext.define('Butterfly.baseinfo.commonSelector.QuerySqlModel',{
	extend : 'Ext.data.Model',
	fields : [{name: 'id'},//ID  
	          {name: 'createDate',type:'date',dateFormat:'time'},//日期
	          {name: 'number'},//SQL编号
	          {name: 'sql'},//sql
	          {name: 'tableHead'},//表头
	          {name: 'param'},//sql参数
	          {name: 'myColumn'},//表头结果集 json格式字符串
	          {name: 'remark'},//备注sql功能
	          {name: 'queryRule'} // 取数规则
			]
});

//报表sqlstore
Ext.define('Butterfly.commonEmployeSelector.QuerySqlCombStore',{
	extend : 'Ext.data.Store',
	model : 'Butterfly.baseinfo.commonSelector.QuerySqlModel',
	pageSize : 20,
	proxy : {
		type : 'ajax',
		url : '../configreport/showQuerySql.action',
		actionMethods : 'POST',
		reader : {
			type : 'json',
			rootProperty : 'sqlLists',
			totalProperty : 'totalCount'
		}
	}
});

/**
 * 报表单选
 */
Ext.define('Butterfly.commonEmpSelector.QuerySqlCommonSelector',{
	extend : 'Butterfly.commonSelector.CommonCombSelector',
	alias : 'widget.querySqlcombselector',
	displayField : 'remark',// 显示名称
	valueField : 'id',// 值
	active : null,
	queryParam : 'queryAssemble.remark',// 查询参数
//	showContent : '{remark}',// 显示表格列
	constructor : function(config) {
		var me = this, cfg = Ext.apply({}, config);    
		me.store = Ext.create('Butterfly.commonEmployeSelector.QuerySqlCombStore');
		
		me.store.addListener('beforeload', function(store, operation, eOpts) {
			var searchParams = operation.getParams();
			if (Ext.isEmpty(searchParams)) {
				searchParams = {};
			}
			if (!Ext.isEmpty(config.active)) {
				searchParams['queryAssemble.active'] = config.active;
			}else{
				searchParams['queryAssemble.active'] = 'Y';
			}
			Ext.apply(store.proxy.extraParams, searchParams);  
		})
		me.callParent([cfg]);
	}
});


/******************报表查询 end *********************/





;/**
 * 获取当前登录用户信息
 * 从父页面中获取当前登录用户信息
 * @returns
 * @author 高佳
 * @date 2015年5月15日
 * @update
 */
function getUserContext(){
	return window.parent.UserContext;
}
/**
 * 获取数据字典信息
 * 从父页面中获取数据字典信息
 * @returns
 * @author 高佳
 * @date 2015年5月15日
 * @update
 */
function getDataDictionary(){
	return window.parent.DataDictionary;
}

/**
 * 判断当前用户是否有此权限
 * 
 * @param authCode
 * @returns
 * @author 蒋落琛
 * @date 2015-6-2
 * @update
 */
function isButtonHide(functionCode){
	return null;//window.parent.UserContext.isButtonHide(functionCode);
}


/**
 * 日期类型数据转换器
 */
function dateConvert(value, record) {
	if (value != null) {
		var date = new Date(value);
		return date;
	} else {
		return null;
	}
}

/**
 * 表格日期显示
 * @param value
 * @param format
 * @returns
 */
function dateRender(value,format){
	if(Ext.isEmpty(format)){
		format = 'Y-m-d';
	}
	if(!Ext.isEmpty(value)){
		return Ext.Date.format(new Date(value), format);
	}
	return value;
}

/**
 * 是和否Render
 * @param value
 * @param format
 * @returns
 */
function booleanTypeRender(value){
	if(Ext.isEmpty(value)){
		return value;
	}
	if(oms.booleanType.yes == value){
		return '是';
	}
	if(oms.booleanType.no == value){
		return '否';
	}
	return value;
}

/**
 * 保留2位小数 四舍五入
 */
function numberRound(value){
	return value.toFixed(2);
}

/**
 * 保留2位小数下取
 */
function numberFloor(value){
	return Math.floor(value*100)/100;
}

/**
 * 保留2位小数上取
 */
function numberCeil(value){
	return Math.ceil(value*100)/100;
	
}

/**
 * 字体图标按钮定义
 */
//新增按钮
Ext.define('Butterfly.commonButton.CommonAddButton', {
	extend : 'Ext.button.Button',
	alias: 'widget.addbutton',
	glyph : 0xf055,
	cls: 'add-btn-item'
});
//删除按钮
Ext.define('Butterfly.commonButton.CommonDeleteButton', {
	extend : 'Ext.button.Button',
	alias: 'widget.deletebutton',
	glyph : 0xf014,
	cls: 'delete-btn-item'
});
//修改编辑按钮
Ext.define('Butterfly.commonButton.CommonUpdateButton', {
	extend : 'Ext.button.Button',
	alias: 'widget.updatebutton',
	glyph : 0xf044,
	cls: 'update-btn-item'
});
//设置按钮
Ext.define('Butterfly.commonButton.CommonSettingsButton', {
	extend : 'Ext.button.Button',
	alias: 'widget.settingsbutton',
	glyph : 0xf085,
	cls: 'settings-btn-item'
});
//刷新
Ext.define('Butterfly.commonButton.CommonRefreshButton', {
	extend : 'Ext.button.Button',
	alias: 'widget.refreshbutton',
	glyph : 0xf021,
	cls: 'settings-btn-item'
});
//下载按钮
Ext.define('Butterfly.commonButton.CommonDownloadButton', {
	extend : 'Ext.button.Button',
	alias: 'widget.downloadbutton',
	glyph : 0xf08e,
	cls: 'download-btn-item'
});
//上传按钮
Ext.define('Butterfly.commonButton.CommonUploadButton', {
	extend : 'Ext.button.Button',
	alias: 'widget.uploadbutton',
	glyph : 0xf093,
	cls: 'upload-btn-item'
});
//查询按钮
Ext.define('Butterfly.commonButton.CommonSearchButton', {
	extend : 'Ext.button.Button',
	alias: 'widget.searchbutton',
	glyph : 0xf00e,
	cls: 'search-btn-item'
});
//关闭按钮
Ext.define('Butterfly.commonButton.CommonCloseButton', {
	extend : 'Ext.button.Button',
	alias: 'widget.closebutton',
	glyph : 0xf00d,
	cls: 'delete-btn-item'
});

//支付按钮
Ext.define('Butterfly.commonButton.CommonPaypalButton', {
	extend : 'Ext.button.Button',
	alias: 'widget.paypalbutton',
	glyph : 0xf1ed,
	cls: 'paypal-btn-item'
});

//人民币（金额处理）按钮
Ext.define('Butterfly.commonButton.CommonRmbButton', {
	extend : 'Ext.button.Button',
	alias: 'widget.rmbbutton',
	glyph : 0xf157,
	cls: 'rmb-btn-item'
});

//打印按钮
Ext.define('Butterfly.commonButton.CommonPrintButton', {
	extend : 'Ext.button.Button',
	alias: 'widget.printbutton',
	glyph : 0xf02f,
	cls: 'print-btn-item'
});

//图表按钮
Ext.define('Butterfly.commonButton.CommonChartButton', {
	extend : 'Ext.button.Button',
	alias: 'widget.chartbutton',
	glyph : 0xf080,
	cls: 'chart-btn-item'
});

//汽车按钮
Ext.define('Butterfly.commonButton.CommonTruckButton', {
	extend : 'Ext.button.Button',
	alias: 'widget.truckbutton',
	glyph : 0xf0d1,
	cls: 'truck-btn-item'
});

//拷贝（导入）按钮
Ext.define('Butterfly.commonButton.CommonExportButton', {
	extend : 'Ext.button.Button',
	alias: 'widget.exportbutton',
	glyph : 0xf0c5,
	cls: 'export-btn-item'
});
//导入开单
Ext.define('Butterfly.commonButton.CommonLevelDownButton', {
	extend : 'Ext.button.Button',
	alias: 'widget.develdownbutton',
	glyph : 0xf149,
	cls: 'export-btn-item'
});
//用户
Ext.define('Butterfly.commonButton.CommonUserButton', {
	extend : 'Ext.button.Button',
	alias: 'widget.userbutton',
	glyph : 0xf007,
	cls: 'export-btn-item'
});

//提交
Ext.define('Butterfly.commonButton.CommonSubmitButton', {
	extend : 'Ext.button.Button',
	alias: 'widget.submitbutton',
	glyph : 0xf00c,
	cls: 'export-btn-item'
});

//审核
Ext.define('Butterfly.commonButton.CommonAuditButton', {
	extend : 'Ext.button.Button',
	alias: 'widget.auditbutton',
	glyph : 0xf14a,
	cls: 'export-btn-item'
});

Ext.override(Ext.window.Window, {
	buttonAlign  : 'center'
});


function commsubmit(btn,wintype,isopen){
	//获取表单数据
	var win = btn.up('window'),
	form = win.down('form').getForm(),orderOperateVo = {},params = {},startdate,enddate;
	orderOperateVo = form.getValues();
	if(!form.isValid()){
		oms.showWoringMessage('请完善必填字段信息');  
	}
	if(Ext.isEmpty(orderOperateVo.orderNo)){
		oms.showWoringMessage('订单号为空');
		win.close();
		return;
	}
	if(wintype=='receive'){
		url = 'orderOperateAction!receiveSuccess.action';
		orderOperateVo.waybillCount = parseFloat(orderOperateVo.waybillCount);
	}else{
		url = 'orderOperateAction!enquirySuccess.action';
		//判断是否在同一天
		if(!Ext.isEmpty(orderOperateVo.custDemandStartTime)&&!Ext.isEmpty(orderOperateVo.custDemandEndTime)){
			startdate = orderOperateVo.custDemandStartTime.split(' ')[0];
			enddate = orderOperateVo.custDemandEndTime.split(' ')[0];
			if(Date.parse(startdate)!=Date.parse(enddate)){
	    		oms.showWoringMessage('客户期望提货/送货起始时间和结束时间要选择同一天');
	    		return;
	    	}
		}
		//判断起始时间是否正确
		if(Date.parse(orderOperateVo.custDemandStartTime)>=Date.parse(orderOperateVo.custDemandEndTime)){
			oms.showWoringMessage('客户期望提货/送货起始时间不能大于结束时间');
			return;
		}
		orderOperateVo.custDemandStartTime = orderOperateVo.custDemandStartTime+':00';
		orderOperateVo.custDemandEndTime = orderOperateVo.custDemandEndTime+':00';
	}
	params.orderOperateVo = orderOperateVo;
	oms.requestJsonAjax(url,params,function(res){
		//console.log(res);
		if(res.success){
			oms.showInfoMsg('操作成功',function(){
    			win.close();
    			if(isopen){
    				var url = '/oms-web/order/toLookOrder.action?orderNo='+res.orderOperateVo.orderNo;
        			window.parent.initTabpanel('web_10901031','订单详情',url, true);
        			window.parent.delTabpanel('web_1090103');
    			}
    		}); 
		}
	},function(res){
		oms.showErrorMes(res.message)
	})
}
;/**
 * A date picker. This class is used by the Ext.form.field.Date field to allow browsing and selection of valid
 * dates in a popup next to the field, but may also be used with other components.
 *
 * Typically you will need to implement a handler function to be notified when the user chooses a date from the picker;
 * you can register the handler using the {@link #select} event, or by implementing the {@link #handler} method.
 *
 * By default the user will be allowed to pick any date; this can be changed by using the {@link #minDate},
 * {@link #maxDate}, {@link #disabledDays}, {@link #disabledDatesRE}, and/or {@link #disabledDates} configs.
 *
 * All the string values documented below may be overridden by including an Ext locale file in your page.
 *
 *     @example
 *     Ext.create('Ext.panel.Panel', {
 *         title: 'Choose a future date:',
 *         width: 200,
 *         bodyPadding: 10,
 *         renderTo: Ext.getBody(),
 *         items: [{
 *             xtype: 'datepicker',
 *             minDate: new Date(),
 *             handler: function(picker, date) {
 *                 // do something with the selected date
 *             }
 *         }]
 *     });
 */
Ext.define('baseUx.form.datetime.DateTimePicker', {
    extend: 'Ext.Component',
    alias: 'widget.datetimepicker',
    alternateClassName: 'Ext.DateTimePicker',
    requires: [
        'Ext.XTemplate',
        'Ext.button.Button',
        'Ext.button.Split',
        'Ext.util.ClickRepeater',
        'Ext.util.KeyNav',
        'Ext.fx.Manager',
        'Ext.picker.Month'
    ],
    
    isDatePicker: true,
    
    focusable: true,

    childEls: [
        'innerEl', 'eventEl', 'prevEl', 'nextEl', 'middleBtnEl', 'footerEl'
    ],
    
    border: true,

    renderTpl: [
        '<div id="{id}-innerEl" data-ref="innerEl">',
            '<div class="{baseCls}-header">',
                '<div id="{id}-prevEl" data-ref="prevEl" class="{baseCls}-prev {baseCls}-arrow" role="button" title="{prevText}"></div>',
                '<div id="{id}-middleBtnEl" data-ref="middleBtnEl" class="{baseCls}-month" role="heading">{%this.renderMonthBtn(values, out)%}</div>',
                '<div id="{id}-nextEl" data-ref="nextEl" class="{baseCls}-next {baseCls}-arrow" role="button" title="{nextText}"></div>',
            '</div>',
            '<table role="grid" id="{id}-eventEl" data-ref="eventEl" class="{baseCls}-inner" {%',
                // If the DatePicker is focusable, make its eventEl tabbable.
                'if (values.$comp.focusable) {out.push("tabindex=\\\"0\\\"");}',
            '%} cellspacing="0">',
                '<thead><tr role="row">',
                    '<tpl for="dayNames">',
                        '<th role="columnheader" class="{parent.baseCls}-column-header" aria-label="{.}">',
                            '<div role="presentation" class="{parent.baseCls}-column-header-inner">{.:this.firstInitial}</div>',
                        '</th>',
                    '</tpl>',
                '</tr></thead>',
                '<tbody><tr role="row">',
                    '<tpl for="days">',
                        '{#:this.isEndOfWeek}',
                        '<td role="gridcell" id="{[Ext.id()]}">',
                            '<div hidefocus="on" class="{parent.baseCls}-date"></div>',
                        '</td>',
                    '</tpl>',
                '</tr></tbody>',
            '</table>',
            '<tpl if="showToday">',
                '<div id="{id}-footerEl" role="presentation" style="background-color:#D9E5F3;border-top:0px solid #99BCE8;">{%this.renderHour(values, out)%}{%this.renderMinute(values, out)%}{%this.renderSecond(values, out)%}<center>{%this.renderTodayBtn(values, out)%}</center></div>',
            '</tpl>',
        '</div>',
        {
            firstInitial: function(value) {
                return Ext.picker.Date.prototype.getDayInitial(value);
            },
            isEndOfWeek: function(value) {
                // convert from 1 based index to 0 based
                // by decrementing value once.
                value--;
                var end = value % 7 === 0 && value !== 0;
                return end ? '</tr><tr role="row">' : '';
            },
			longDay: function(value){
                return Ext.Date.format(value, this.longDayFormat);
            },
            renderHour: function(values, out) {
            	out.push('<font  style="float : left;">&nbsp&nbsp</font>');
                Ext.DomHelper.generateMarkup(values.$comp.hour.getRenderTree(), out);
            },
            renderMinute: function(values, out) {
            	out.push('<font  style="float : left;font-weight:bold;">&nbsp:&nbsp&nbsp</font>');
                Ext.DomHelper.generateMarkup(values.$comp.minute.getRenderTree(), out);
            },
            renderSecond: function(values, out) {
            	out.push('<font style="float : left;font-weight:bold;">&nbsp:&nbsp&nbsp</font>');
                Ext.DomHelper.generateMarkup(values.$comp.second.getRenderTree(), out);
            },
            renderTodayBtn: function(values, out) {
                Ext.DomHelper.generateMarkup(values.$comp.todayBtn.getRenderTree(), out);
            },
            renderMonthBtn: function(values, out) {
                Ext.DomHelper.generateMarkup(values.$comp.monthBtn.getRenderTree(), out);
            }
        }
    ],

    //<locale>
    /**
     * @cfg {String} todayText
     * The text to display on the button that selects the current date
     */
    todayText: '今天',
    //</locale>
    
    //<locale>
    /**
     * @cfg {String} ariaTitle
     * The text to display for the aria title
     */
    ariaTitle: 'Date Picker: {0}',
    //</locale>
    
    //<locale>
    /**
     * @cfg {String} ariaTitleDateFormat
     * The date format to display for the current value in the {@link #ariaTitle}
     */
    ariaTitleDateFormat: 'F d',
    //</locale>

    /**
     * @cfg {Function} handler
     * Optional. A function that will handle the select event of this picker. The handler is passed the following
     * parameters:
     *
     *   - `picker` : Ext.picker.Date
     *
     * This Date picker.
     *
     *   - `date` : Date
     *
     * The selected date.
     */

    /**
     * @cfg {Object} scope
     * The scope (`this` reference) in which the `{@link #handler}` function will be called.
     *
     * Defaults to this DatePicker instance.
     */

    //<locale>
    /**
     * @cfg {String} todayTip
     * A string used to format the message for displaying in a tooltip over the button that selects the current date.
     * The `{0}` token in string is replaced by today's date.
     */
    todayTip: '{0} (Spacebar)',
    //</locale>

    //<locale>
    /**
     * @cfg {String} minText
     * The error text to display if the minDate validation fails.
     */
    minText: 'This date is before the minimum date',
    //</locale>

    //<locale>
    /**
     * @cfg {String} maxText
     * The error text to display if the maxDate validation fails.
     */
    maxText: 'This date is after the maximum date',
    //</locale>

    /**
     * @cfg {String} format
     * The default date format string which can be overriden for localization support. The format must be valid
     * according to {@link Ext.Date#parse} (defaults to {@link Ext.Date#defaultFormat}).
     */

    //<locale>
    /**
     * @cfg {String} disabledDaysText
     * The tooltip to display when the date falls on a disabled day.
     */
    disabledDaysText: 'Disabled',
    //</locale>

    //<locale>
    /**
     * @cfg {String} disabledDatesText
     * The tooltip text to display when the date falls on a disabled date.
     */
    disabledDatesText: 'Disabled',
    //</locale>

    /**
     * @cfg {String[]} monthNames
     * An array of textual month names which can be overriden for localization support (defaults to Ext.Date.monthNames)
     * @deprecated This config is deprecated. In future the month names will be retrieved from {@link Ext.Date}
     */

    /**
     * @cfg {String[]} dayNames
     * An array of textual day names which can be overriden for localization support (defaults to Ext.Date.dayNames)
     * @deprecated This config is deprecated. In future the day names will be retrieved from {@link Ext.Date}
     */

    //<locale>
    /**
     * @cfg {String} nextText
     * The next month navigation button tooltip
     */
    nextText: 'Next Month (Control+Right)',
    //</locale>

    //<locale>
    /**
     * @cfg {String} prevText
     * The previous month navigation button tooltip
     */
    prevText: 'Previous Month (Control+Left)',
    //</locale>

    //<locale>
    /**
     * @cfg {String} monthYearText
     * The header month selector tooltip
     */
    monthYearText: 'Choose a month (Control+Up/Down to move years)',
    //</locale>
    
    //<locale>
    /**
     * @cfg {String} monthYearFormat
     * The date format for the header month
     */
    monthYearFormat: 'F Y',
    //</locale>

    //<locale>
    /**
     * @cfg {Number} [startDay=undefined]
     * Day index at which the week should begin, 0-based.
     *
     * Defaults to `0` (Sunday).
     */
    startDay: 0,
    //</locale>

    //<locale>
    /**
     * @cfg {Boolean} showToday
     * False to hide the footer area containing the Today button and disable the keyboard handler for spacebar that
     * selects the current date.
     */
    showToday: true,
    //</locale>

    /**
     * @cfg {Date} [minDate=null]
     * Minimum allowable date (JavaScript date object)
     */

    /**
     * @cfg {Date} [maxDate=null]
     * Maximum allowable date (JavaScript date object)
     */

    /**
     * @cfg {Number[]} [disabledDays=null]
     * An array of days to disable, 0-based. For example, [0, 6] disables Sunday and Saturday.
     */

    /**
     * @cfg {RegExp} [disabledDatesRE=null]
     * JavaScript regular expression used to disable a pattern of dates. The {@link #disabledDates}
     * config will generate this regex internally, but if you specify disabledDatesRE it will take precedence over the
     * disabledDates value.
     */

    /**
     * @cfg {String[]} disabledDates
     * An array of 'dates' to disable, as strings. These strings will be used to build a dynamic regular expression so
     * they are very powerful. Some examples:
     *
     *   - ['03/08/2003', '09/16/2003'] would disable those exact dates
     *   - ['03/08', '09/16'] would disable those days for every year
     *   - ['^03/08'] would only match the beginning (useful if you are using short years)
     *   - ['03/../2006'] would disable every day in March 2006
     *   - ['^03'] would disable every day in every March
     *
     * Note that the format of the dates included in the array should exactly match the {@link #format} config. In order
     * to support regular expressions, if you are using a date format that has '.' in it, you will have to escape the
     * dot when restricting dates. For example: ['03\\.08\\.03'].
     */

    /**
     * @cfg {Boolean} disableAnim
     * True to disable animations when showing the month picker.
     */
    disableAnim: false,

    /**
     * @cfg {String} [baseCls='x-datepicker']
     * The base CSS class to apply to this components element.
     */
    baseCls: Ext.baseCSSPrefix + 'datepicker',

    /**
     * @cfg {String} [selectedCls='x-datepicker-selected']
     * The class to apply to the selected cell.
     */

    /**
     * @cfg {String} [disabledCellCls='x-datepicker-disabled']
     * The class to apply to disabled cells.
     */

    //<locale>
    /**
     * @cfg {String} longDayFormat
     * The format for displaying a date in a longer format.
     */
    longDayFormat: 'F d, Y',
    //</locale>

    /**
     * @cfg {Object} keyNavConfig
     * Specifies optional custom key event handlers for the {@link Ext.util.KeyNav} attached to this date picker. Must
     * conform to the config format recognized by the {@link Ext.util.KeyNav} constructor. Handlers specified in this
     * object will replace default handlers of the same name.
     */

    // Default value used to initialise each date in the DatePicker.
    // __Note:__ 12 noon was chosen because it steers well clear of all DST timezone changes.
    initHour: 12, // 24-hour format

    numDays: 42,

    /**
     * @event select
     * Fires when a date is selected
     * @param {Ext.picker.Date} this DatePicker
     * @param {Date} date The selected date
     */

    // private, inherit docs
    initComponent: function() {
        var me = this,
            clearTime = Ext.Date.clearTime;

        me.selectedCls = me.baseCls + '-selected';
        me.disabledCellCls = me.baseCls + '-disabled';
        me.prevCls = me.baseCls + '-prevday';
        me.activeCls = me.baseCls + '-active';
        me.cellCls = me.baseCls + '-cell';
        me.nextCls = me.baseCls + '-prevday';
        me.todayCls = me.baseCls + '-today';
        
        
        if (!me.format) {
            me.format = Ext.Date.defaultFormat;
        }
        if (!me.dayNames) {
            me.dayNames = Ext.Date.dayNames;
        }
        me.dayNames = me.dayNames.slice(me.startDay).concat(me.dayNames.slice(0, me.startDay));

        me.callParent();

        me.value = me.value ? clearTime(me.value, true) : clearTime(new Date());

        me.initDisabledDays();
    },

    // Keep the tree structure correct for Ext.form.field.Picker input fields which poke a 'pickerField' reference down into their pop-up pickers.
    getRefOwner: function() {
        return this.pickerField || this.callParent();
    },

    getRefItems: function() {
        var results = [],
            monthBtn = this.monthBtn,
            todayBtn = this.todayBtn;

        if (monthBtn) {
            results.push(monthBtn);
        }

        if (todayBtn) {
            results.push(todayBtn);
        }
        return results;
    },

    beforeRender: function() {
        /*
         * days array for looping through 6 full weeks (6 weeks * 7 days)
         * Note that we explicitly force the size here so the template creates
         * all the appropriate cells.
         */
        var me = this,
            days = new Array(me.numDays),
            today = Ext.Date.format(new Date(), me.format);

        if (me.padding && !me.width) {
            me.cacheWidth();
        }

        me.monthBtn = new Ext.button.Split({
            ownerCt: me,
            ownerLayout: me.getComponentLayout(),
            text: '',
            tooltip: me.monthYearText,
            listeners: {
                click: me.doShowMonthPicker,
                arrowclick: me.doShowMonthPicker,
                scope: me
            }
        });
		
		me.hour = Ext.create('Ext.form.field.Number', {
            scope: me,
            ownerCt: me,
            editable : false,
            ownerLayout: me.getComponentLayout(),
           	minValue: 0,
           	maxValue: 23,
           	width: 50,
           	style : {float:"left"},
           	enableKeyEvents: true,
           	listeners: {
                 keyup: function(field, e){
                     if (field.getValue() > 23){
                         e.stopEvent();
                         field.setValue(23);
                     }
                 }
             }
        });
        
        me.minute = Ext.create('Ext.form.field.Number', {
            scope: me,
            ownerCt: me,
           	style : {float:"left"},
            ownerLayout: me.getComponentLayout(),
        	minValue: 0,
        	maxValue: 59,
            editable : false,
        	width: 50,
        	enableKeyEvents: true,
        	listeners: {
                keyup: function(field, e){
                    if (field.getValue() > 59){
                        e.stopEvent();
                        field.setValue(59);
                    }
                }
            }
        });
        
      me.second = Ext.create('Ext.form.field.Number', {
            scope: me,
            ownerCt: me,
            editable : false,
           	style : {float:"left"},
            ownerLayout: me.getComponentLayout(),
        	minValue: 0,
        	maxValue: 59,
        	width: 50,
        	enableKeyEvents: true,
        	listeners: {
                keyup: function(field, e){
                    if (field.getValue() > 59){
                        e.stopEvent();
                        field.setValue(59);
                    }
                }
            }
        });

        if (me.showToday) {
            me.todayBtn = new Ext.button.Button({
                ownerCt: me,
                ownerLayout: me.getComponentLayout(),
                text: Ext.String.format(me.todayText, today),
                tooltip: Ext.String.format(me.todayTip, today),
                tooltipType: 'title',
                handler: me.selectToday,
                scope: me
            });
        }

        me.callParent();

        Ext.applyIf(me, {
            renderData: {}
        });

        Ext.apply(me.renderData, {
            dayNames: me.dayNames,
            showToday: me.showToday,
            prevText: me.prevText,
            nextText: me.nextText,
            days: days
        });

        me.protoEl.unselectable();
    },

    cacheWidth: function() {
        var me = this,
            padding = me.parseBox(me.padding),
            widthEl = Ext.getBody().createChild({
                cls: me.baseCls + ' ' + me.borderBoxCls,
                style: 'position:absolute;top:-1000px;left:-1000px;'
            });

        me.self.prototype.width = widthEl.getWidth() + padding.left + padding.right;
        widthEl.destroy();
    },

    // @private
    // @inheritdoc
    onRender: function(container, position) {
        var me = this;

        me.callParent(arguments);

        me.cells = me.eventEl.select('tbody td');
        me.textNodes = me.eventEl.query('tbody td div');
        
        me.eventEl.set({ 'aria-labelledby': me.monthBtn.id });

        me.mon(me.eventEl, {
            scope: me,
            mousewheel: me.handleMouseWheel,
            click: {
                fn: me.handleDateClick,
                delegate: 'div.' + me.baseCls + '-date'
            }
        });
        
    },

    // @private
    // @inheritdoc
    initEvents: function(){
        var me = this,
            pickerField = me.pickerField,
            eDate = Ext.Date,
            day = eDate.DAY;

        me.callParent();

        // If this is not focusable (eg being used as the picker of a DateField)
        // then prevent mousedown from blurring the input field.
        if (!me.focusable) {
            me.el.on({
                mousedown: me.onMouseDown
            });
        }

        me.prevRepeater = new Ext.util.ClickRepeater(me.prevEl, {
            handler: me.showPrevMonth,
            scope: me,
            preventDefault: true,
            stopDefault: true
        });

        me.nextRepeater = new Ext.util.ClickRepeater(me.nextEl, {
            handler: me.showNextMonth,
            scope: me,
            preventDefault: true,
            stopDefault: true
        });

        // Read key events through our pickerField if we are bound to one
        me.keyNav = new Ext.util.KeyNav(pickerField ? pickerField.inputEl : me.eventEl, Ext.apply({
            scope: me,

            // Must capture event so that the Picker sees it before the Field.
            capture: true,

            left: function(e) {
                if (e.ctrlKey) {
                    me.showPrevMonth();
                } else {
                    me.update(eDate.add(me.activeDate, day, -1));
                }
            },

            right: function(e){
                if (e.ctrlKey) {
                    me.showNextMonth();
                } else {
                    me.update(eDate.add(me.activeDate, day, 1));
                }
            },

            up: function(e) {
                if (e.ctrlKey) {
                    me.showNextYear();
                } else {
                    me.update(eDate.add(me.activeDate, day, -7));
                }
            },

            down: function(e) {
                if (e.ctrlKey) {
                    me.showPrevYear();
                } else {
                    me.update(eDate.add(me.activeDate, day, 7));
                }
            },

            pageUp: function(e) {
                if (e.ctrlKey) {
                    me.showPrevYear();
                } else {
                    me.showPrevMonth();
                }
            },

            pageDown: function(e) {
                if (e.ctrlKey) {
                    me.showNextYear();
                } else {
                    me.showNextMonth();
                }
            },

            tab: function (e) {
                me.handleTabClick(e);
                
                // Allow default behaviour of TAB - it MUST be allowed to navigate.
                return true;
            },

            enter: function(e) {
                me.handleDateClick(e, me.activeCell.firstChild);
            },

            space: function() {
                me.setValue(new Date(me.activeCell.firstChild.dateValue));
                var startValue = me.startValue,
                    value = me.value,
                    pickerValue;

                if (pickerField) {
                    pickerValue = pickerField.getValue();
                    if (pickerValue && startValue && pickerValue.getTime() === value.getTime()) {
                        pickerField.setValue(startValue);
                    } else {
                        pickerField.setValue(value);
                    }
                }
            },

            home: function(e) {
                me.update(eDate.getFirstDateOfMonth(me.activeDate));
            },

            end: function(e) {
                me.update(eDate.getLastDateOfMonth(me.activeDate));
            }
        }, me.keyNavConfig));

        if (me.disabled) {
            me.syncDisabled(true);
        }
        me.update(me.value);
    },

    onMouseDown: function(e) {
        e.preventDefault();
    },

    handleTabClick: function (e) {
        var me = this,
            t = me.getSelectedDate(me.activeDate),
            handler = me.handler;

        // The following code is like handleDateClick without the e.stopEvent()
        if (!me.disabled && t.dateValue && !Ext.fly(t.parentNode).hasCls(me.disabledCellCls)) {
            me.setValue(new Date(t.dateValue));
            me.fireEvent('select', me, me.value);
            if (handler) {
                handler.call(me.scope || me, me, me.value);
            }
            me.onSelect();
        }
    },

    getSelectedDate: function (date) {
        var me = this,
            t = date.getTime(),
            cells = me.cells,
            cls = me.selectedCls,
            cellItems = cells.elements,
            c,
            cLen = cellItems.length,
            cell;

        cells.removeCls(cls);

        for (c = 0; c < cLen; c++) {
            cell = Ext.fly(cellItems[c]);

            if (cell.dom.firstChild.dateValue == t) {
                return cell.dom.firstChild;
            }
        }
        return null;
    },

    /**
     * Setup the disabled dates regex based on config options
     * @private
     */
    initDisabledDays: function(){
        var me = this,
            dd = me.disabledDates,
            re = '(?:',
            len,
            d, dLen, dI;

        if(!me.disabledDatesRE && dd){
                len = dd.length - 1;

            dLen = dd.length;

            for (d = 0; d < dLen; d++) {
                dI = dd[d];

                re += Ext.isDate(dI) ? '^' + Ext.String.escapeRegex(Ext.Date.dateFormat(dI, me.format)) + '$' : dI;
                if (d !== len) {
                    re += '|';
                }
            }

            me.disabledDatesRE = new RegExp(re + ')');
        }
    },

    /**
     * Replaces any existing disabled dates with new values and refreshes the DatePicker.
     * @param {String[]/RegExp} disabledDates An array of date strings (see the {@link #disabledDates} config for
     * details on supported values), or a JavaScript regular expression used to disable a pattern of dates.
     * @return {Ext.picker.Date} this
     */
    setDisabledDates: function(dd){
        var me = this;

        if (Ext.isArray(dd)) {
            me.disabledDates = dd;
            me.disabledDatesRE = null;
        } else {
            me.disabledDatesRE = dd;
        }
        me.initDisabledDays();
        me.update(me.value, true);
        return me;
    },

    /**
     * Replaces any existing disabled days (by index, 0-6) with new values and refreshes the DatePicker.
     * @param {Number[]} disabledDays An array of disabled day indexes. See the {@link #disabledDays} config for details
     * on supported values.
     * @return {Ext.picker.Date} this
     */
    setDisabledDays: function(dd){
        this.disabledDays = dd;
        return this.update(this.value, true);
    },

    /**
     * Replaces any existing {@link #minDate} with the new value and refreshes the DatePicker.
     * @param {Date} value The minimum date that can be selected
     * @return {Ext.picker.Date} this
     */
    setMinDate: function(dt){
        this.minDate = dt;
        return this.update(this.value, true);
    },

    /**
     * Replaces any existing {@link #maxDate} with the new value and refreshes the DatePicker.
     * @param {Date} value The maximum date that can be selected
     * @return {Ext.picker.Date} this
     */
    setMaxDate: function(dt){
        this.maxDate = dt;
        return this.update(this.value, true);
    },

    /**
     * Sets the value of the date field
     * @param {Date} value The date to set
     * @return {Ext.picker.Date} this
     */
    setValue: function(value){
        // If passed a null value just pass in a new date object.
        this.value = Ext.Date.clearTime(value || new Date(), true);
        return this.update(this.value);
    },

    /**
     * Gets the current selected value of the date field
     * @return {Date} The selected date
     */
    getValue: function(){
        return this.value;
    },

    //<locale type="function">
    /**
     * Gets a single character to represent the day of the week
     * @return {String} The character
     */
    getDayInitial: function(value){
        return value.substr(0,1);
    },
    //</locale>

    // @private
    // @inheritdoc
    onEnable: function(){
        this.callParent();
        this.syncDisabled(false);
        this.update(this.activeDate);

    },

    // @private
    // @inheritdoc
    onShow: function(){
        this.callParent();
        this.syncDisabled(false);
        if (this.pickerField) {
            this.startValue = this.pickerField.getValue();
        }
    },
    
    // @private
    // @inheritdoc
    onHide: function(){
        this.callParent();
        this.syncDisabled(true);
    },

    // @private
    // @inheritdoc
    onDisable: function(){
        this.callParent();
        this.syncDisabled(true);
    },

    /**
     * Get the current active date.
     * @private
     * @return {Date} The active date
     */
    getActive: function(){
        return this.activeDate || this.value;
    },

    /**
     * Run any animation required to hide/show the month picker.
     * @private
     * @param {Boolean} isHide True if it's a hide operation
     */
    runAnimation: function(isHide){
        var picker = this.monthPicker,
            options = {
                duration: 200,
                callback: function() {
                    picker.setVisible(!isHide);
                }
            };

        if (isHide) {
            picker.el.slideOut('t', options);
        } else {
            picker.el.slideIn('t', options);
        }
    },

    /**
     * Hides the month picker, if it's visible.
     * @param {Boolean} [animate] Indicates whether to animate this action. If the animate
     * parameter is not specified, the behavior will use {@link #disableAnim} to determine
     * whether to animate or not.
     * @return {Ext.picker.Date} this
     */
    hideMonthPicker: function(animate){
        var me = this,
            picker = me.monthPicker;

        if (picker && picker.isVisible()) {
            if (me.shouldAnimate(animate)) {
                me.runAnimation(true);
            } else {
                picker.hide();
            }
        }
        return me;
    },
    
    doShowMonthPicker: function(){
        // Wrap in an extra call so we can prevent the button
        // being passed as an animation parameter.
        this.showMonthPicker();
    },
    
    doHideMonthPicker: function() {
        // Wrap in an extra call so we can prevent this
        // being passed as an animation parameter
        this.hideMonthPicker();
    },

    /**
     * Show the month picker
     * @param {Boolean} [animate] Indicates whether to animate this action. If the animate
     * parameter is not specified, the behavior will use {@link #disableAnim} to determine
     * whether to animate or not.
     * @return {Ext.picker.Date} this
     */
    showMonthPicker: function(animate) {
        var me = this,
            el = me.el,
            picker;
        
        if (me.rendered && !me.disabled) {
            picker = me.createMonthPicker();            
            if (!picker.isVisible()) {
                picker.setValue(me.getActive());
                picker.setSize(el.getSize());
                picker.setPosition(-el.getBorderWidth('l'), -el.getBorderWidth('t'));
                if (me.shouldAnimate(animate)) {
                    me.runAnimation(false);
                } else {
                    picker.show();
                }
            }
        }
        return me;
    },
    
    /**
     * Checks whether a hide/show action should animate
     * @private
     * @param {Boolean} [animate] A possible animation value
     * @return {Boolean} Whether to animate the action
     */
    shouldAnimate: function(animate) {
        return Ext.isDefined(animate) ? animate : !this.disableAnim;
    },

    /**
     * Create the month picker instance
     * @private
     * @return {Ext.picker.Month} picker
     */
    createMonthPicker: function() {
        var me = this,
            picker = me.monthPicker;

        if (!picker) {
            me.monthPicker = picker = new Ext.picker.Month({
                renderTo: me.el,
                floating: true,
                padding: me.padding,
                shadow: false,
                small: me.showToday === false,
                listeners: {
                    scope: me,
                    cancelclick: me.onCancelClick,
                    okclick: me.onOkClick,
                    yeardblclick: me.onOkClick,
                    monthdblclick: me.onOkClick
                }
            });
            if (!me.disableAnim) {
                // hide the element if we're animating to prevent an initial flicker
                picker.el.setStyle('display', 'none');
            }
            picker.hide();
            me.on('beforehide', me.doHideMonthPicker, me);
        }
        return picker;
    },

    /**
     * Respond to an ok click on the month picker
     * @private
     */
    onOkClick: function(picker, value) {
        var me = this,
            month = value[0],
            year = value[1],
            date = new Date(year, month, me.getActive().getDate());

        if (date.getMonth() !== month) {
            // 'fix' the JS rolling date conversion if needed
            date = Ext.Date.getLastDateOfMonth(new Date(year, month, 1));
        }
        me.setValue(date);
        me.hideMonthPicker();
    },

    /**
     * Respond to a cancel click on the month picker
     * @private
     */
    onCancelClick: function() {
        this.selectedUpdate(this.activeDate);
        this.hideMonthPicker();
    },

    /**
     * Show the previous month.
     * @param {Object} e
     * @return {Ext.picker.Date} this
     */
    showPrevMonth: function(e) {
        return this.setValue(Ext.Date.add(this.activeDate, Ext.Date.MONTH, -1));
    },

    /**
     * Show the next month.
     * @param {Object} e
     * @return {Ext.picker.Date} this
     */
    showNextMonth: function(e) {
        return this.setValue(Ext.Date.add(this.activeDate, Ext.Date.MONTH, 1));
    },

    /**
     * Show the previous year.
     * @return {Ext.picker.Date} this
     */
    showPrevYear: function() {
        return this.setValue(Ext.Date.add(this.activeDate, Ext.Date.YEAR, -1));
    },

    /**
     * Show the next year.
     * @return {Ext.picker.Date} this
     */
    showNextYear: function() {
        return this.setValue(Ext.Date.add(this.activeDate, Ext.Date.YEAR, 1));
    },

    /**
     * Respond to the mouse wheel event
     * @private
     * @param {Ext.event.Event} e
     */
    handleMouseWheel: function(e) {
        e.stopEvent();
        if(!this.disabled){
            var delta = e.getWheelDelta();
            if(delta > 0){
                this.showPrevMonth();
            } else if(delta < 0){
                this.showNextMonth();
            }
        }
    },

    /**
     * Respond to a date being clicked in the picker
     * @private
     * @param {Ext.event.Event} e
     * @param {HTMLElement} t
     */
    handleDateClick: function(e, t) {
        var me = this,
            handler = me.handler;

        e.stopEvent();
        if(!me.disabled && t.dateValue && !Ext.fly(t.parentNode).hasCls(me.disabledCellCls)){
            me.setValue(new Date(t.dateValue));
            me.fireEvent('select', me, me.value);
            if (handler) {
                handler.call(me.scope || me, me, me.value);
            }
            // event handling is turned off on hide
            // when we are using the picker in a field
            // therefore onSelect comes AFTER the select
            // event.
            me.onSelect();
        }
    },

    /**
     * Perform any post-select actions
     * @private
     */
    onSelect: function() {
        if (this.hideOnSelect) {
             this.hide();
         }
    },

    /**
     * Sets the current value to today.
     * @return {Ext.picker.Date} this
     */
    selectToday: function() {
        var me = this,
            btn = me.todayBtn,
            handler = me.handler;

        if (btn && !btn.disabled) {
            me.setValue(Ext.Date.clearTime(new Date()));
            me.fireEvent('select', me, me.value);
            if (handler) {
                handler.call(me.scope || me, me, me.value);
            }
            me.onSelect();
        }
        return me;
    },

    /**
     * Update the selected cell
     * @private
     * @param {Date} date The new date
     */
    selectedUpdate: function(date) {
        var me        = this,
            t         = date.getTime(),
            cells     = me.cells,
            cls       = me.selectedCls,
            c,
            cLen      = cells.getCount(),
            cell;

        cell = me.activeCell;
        if (cell) {
            Ext.fly(cell).removeCls(cls);
            cell.setAttribute('aria-selected', false);
        }

        for (c = 0; c < cLen; c++) {
            cell = cells.item(c);

            if (me.textNodes[c].dateValue == t) {
                me.activeCell = cell.dom;
                me.eventEl.dom.setAttribute('aria-activedescendant', cell.dom.id);
                cell.dom.setAttribute('aria-selected', true);
                cell.addCls(cls);
                me.fireEvent('highlightitem', me, cell);
                break;
            }
        }
    },

    /**
     * Update the contents of the picker for a new month
     * @private
     * @param {Date} date The new date
     */
    fullUpdate: function(date) {
        var me = this,
            cells = me.cells.elements,
            textNodes = me.textNodes,
            disabledCls = me.disabledCellCls,
            eDate = Ext.Date,
            i = 0,
            extraDays = 0,
            newDate = +eDate.clearTime(date, true),
            today = +eDate.clearTime(new Date()),
            min = me.minDate ? eDate.clearTime(me.minDate, true) : Number.NEGATIVE_INFINITY,
            max = me.maxDate ? eDate.clearTime(me.maxDate, true) : Number.POSITIVE_INFINITY,
            ddMatch = me.disabledDatesRE,
            ddText = me.disabledDatesText,
            ddays = me.disabledDays ? me.disabledDays.join('') : false,
            ddaysText = me.disabledDaysText,
            format = me.format,
            days = eDate.getDaysInMonth(date),
            firstOfMonth = eDate.getFirstDateOfMonth(date),
            startingPos = firstOfMonth.getDay() - me.startDay,
            previousMonth = eDate.add(date, eDate.MONTH, -1),
            ariaTitleDateFormat = me.ariaTitleDateFormat,
            prevStart, current, disableToday, tempDate, setCellClass, html, cls,
            formatValue, value;

        if (startingPos < 0) {
            startingPos += 7;
        }

        days += startingPos;
        prevStart = eDate.getDaysInMonth(previousMonth) - startingPos;
        current = new Date(previousMonth.getFullYear(), previousMonth.getMonth(), prevStart, me.initHour);

        if (me.showToday) {
            tempDate = eDate.clearTime(new Date());
            disableToday = (tempDate < min || tempDate > max ||
                (ddMatch && format && ddMatch.test(eDate.dateFormat(tempDate, format))) ||
                (ddays && ddays.indexOf(tempDate.getDay()) != -1));

            if (!me.disabled) {
                me.todayBtn.setDisabled(disableToday);
            }
        }

        setCellClass = function(cellIndex, cls){
            var cell = cells[cellIndex];
            
            value = +eDate.clearTime(current, true);
            cell.setAttribute('aria-label', eDate.format(current, ariaTitleDateFormat));
            // store dateValue number as an expando
            cell.firstChild.dateValue = value;
            if (value == today) {
                cls += ' ' + me.todayCls;
                cell.firstChild.title = me.todayText;
                
                // Extra element for ARIA purposes
                me.todayElSpan = Ext.DomHelper.append(cell.firstChild, {
                    tag: 'span',
                    cls: Ext.baseCSSPrefix + 'hidden-clip',
                    html: me.todayText
                }, true);
            }
            if (value == newDate) {
                me.activeCell = cell;
                me.eventEl.dom.setAttribute('aria-activedescendant', cell.id);
                cell.setAttribute('aria-selected', true);
                cls += ' ' + me.selectedCls;
                me.fireEvent('highlightitem', me, cell);
            } else {
                cell.setAttribute('aria-selected', false);
            }

            if (value < min) {
                cls += ' ' + disabledCls;
                cell.setAttribute('aria-label', me.minText);
            }
            else if (value > max) {
                cls += ' ' + disabledCls;
                cell.setAttribute('aria-label', me.maxText);
            }
            else if (ddays && ddays.indexOf(current.getDay()) !== -1){
                cell.setAttribute('aria-label', ddaysText);
                cls += ' ' + disabledCls;
            }
            else if (ddMatch && format){
                formatValue = eDate.dateFormat(current, format);
                if(ddMatch.test(formatValue)){
                    cell.setAttribute('aria-label', ddText.replace('%0', formatValue));
                    cls += ' ' + disabledCls;
                }
            }
            cell.className = cls + ' ' + me.cellCls;
        };

        for(; i < me.numDays; ++i) {
            if (i < startingPos) {
                html = (++prevStart);
                cls = me.prevCls;
            } else if (i >= days) {
                html = (++extraDays);
                cls = me.nextCls;
            } else {
                html = i - startingPos + 1;
                cls = me.activeCls;
            }
            textNodes[i].innerHTML = html;
            current.setDate(current.getDate() + 1);
            setCellClass(i, cls);
        }

        me.monthBtn.setText(Ext.Date.format(date, me.monthYearFormat));
    },

    /**
     * Update the contents of the picker
     * @private
     * @param {Date} date The new date
     * @param {Boolean} forceRefresh True to force a full refresh
     */
    update : function(date, forceRefresh) {
        var me = this,
            active = me.activeDate;

		date.setHours(me.hour.getValue());
    	date.setMinutes(me.minute.getValue());
    	date.setSeconds(me.second.getValue());
		
        if (me.rendered) {
            me.activeDate = date;
            if(!forceRefresh && active && me.el && active.getMonth() == date.getMonth() && active.getFullYear() == date.getFullYear()){
                me.selectedUpdate(date, active);
            } else {
                me.fullUpdate(date, active);
            }
        }
        return me;
    },

    // @private
    // @inheritdoc
    beforeDestroy: function() {
        var me = this;

        if (me.rendered) {
            Ext.destroy(
                me.keyNav,
                me.monthPicker,
                me.monthBtn,
                me.nextRepeater,
                me.prevRepeater,
                me.todayBtn
            );
            delete me.textNodes;
            delete me.cells.elements;
        }
        me.callParent();
    },

    privates: {
        // Do the job of a container layout at this point even though we are not a Container.
        // TODO: Refactor as a Container.
        finishRenderChildren: function () {
            var me = this;

            me.callParent();
            me.monthBtn.finishRender();
            if (me.showToday) {
                me.todayBtn.finishRender();
            }
			
			this.hour.finishRender();
			this.minute.finishRender();
			this.second.finishRender();
        },
        
        getFocusEl: function() {
            return this.eventEl;
        },

        /**
         * Set the disabled state of various internal components
         * @param {Boolean} disabled
         * @private
         */
        syncDisabled: function (disabled) {
            var me = this,
                keyNav = me.keyNav;

            // If we have one, we have all
            if (keyNav) {
                keyNav.setDisabled(disabled);
                me.prevRepeater.setDisabled(disabled);
                me.nextRepeater.setDisabled(disabled);
                if (me.todayBtn) {
                    me.todayBtn.setDisabled(disabled);
                }
            }
        }
    }
});/**
 * @docauthor Jason Johnston <jason@sencha.com>
 *
 * Provides a date input field with a {@link Ext.picker.Date date picker} dropdown and automatic date
 * validation.
 *
 * This field recognizes and uses the JavaScript Date object as its main {@link #value} type. In addition,
 * it recognizes string values which are parsed according to the {@link #format} and/or {@link #altFormats}
 * configs. These may be reconfigured to use date formats appropriate for the user's locale.
 *
 * The field may be limited to a certain range of dates by using the {@link #minValue}, {@link #maxValue},
 * {@link #disabledDays}, and {@link #disabledDates} config parameters. These configurations will be used both
 * in the field's validation, and in the date picker dropdown by preventing invalid dates from being selected.
 *
 * # Example usage
 *
 *     @example
 *     Ext.create('Ext.form.Panel', {
 *         renderTo: Ext.getBody(),
 *         width: 300,
 *         bodyPadding: 10,
 *         title: 'Dates',
 *         items: [{
 *             xtype: 'datefield',
 *             anchor: '100%',
 *             fieldLabel: 'From',
 *             name: 'from_date',
 *             maxValue: new Date()  // limited to the current date or prior
 *         }, {
 *             xtype: 'datefield',
 *             anchor: '100%',
 *             fieldLabel: 'To',
 *             name: 'to_date',
 *             value: new Date()  // defaults to today
 *         }]
 *     });
 *
 * # Date Formats Examples
 *
 * This example shows a couple of different date format parsing scenarios. Both use custom date format
 * configurations; the first one matches the configured `format` while the second matches the `altFormats`.
 *
 *     @example
 *     Ext.create('Ext.form.Panel', {
 *         renderTo: Ext.getBody(),
 *         width: 300,
 *         bodyPadding: 10,
 *         title: 'Dates',
 *         items: [{
 *             xtype: 'datefield',
 *             anchor: '100%',
 *             fieldLabel: 'Date',
 *             name: 'date',
 *             // The value matches the format; will be parsed and displayed using that format.
 *             format: 'm d Y',
 *             value: '2 4 1978'
 *         }, {
 *             xtype: 'datefield',
 *             anchor: '100%',
 *             fieldLabel: 'Date',
 *             name: 'date',
 *             // The value does not match the format, but does match an altFormat; will be parsed
 *             // using the altFormat and displayed using the format.
 *             format: 'm d Y',
 *             altFormats: 'm,d,Y|m.d.Y',
 *             value: '2.4.1978'
 *         }]
 *     });
 */
Ext.define('baseUx.form.datetime.DateTime', {
    extend:'Ext.form.field.Picker',
    alias: 'widget.datetimefield',
    requires: ['baseUx.form.datetime.DateTimePicker'],

    //<locale>
    /**
     * @cfg {String} format
     * The default date format string which can be overriden for localization support. The format must be valid
     * according to {@link Ext.Date#parse}.
     */
    format : "m/d/Y",
    //</locale>
    //<locale>
    /**
     * @cfg {String} altFormats
     * Multiple date formats separated by "|" to try when parsing a user input value and it does not match the defined
     * format.
     */
    altFormats : "m/d/Y|n/j/Y|n/j/y|m/j/y|n/d/y|m/j/Y|n/d/Y|m-d-y|m-d-Y|m/d|m-d|md|mdy|mdY|d|Y-m-d|n-j|n/j",
    //</locale>
    //<locale>
    /**
     * @cfg {String} disabledDaysText
     * The tooltip to display when the date falls on a disabled day.
     */
    disabledDaysText : "Disabled",
    //</locale>
    //<locale>
    /**
     * @cfg {String} disabledDatesText
     * The tooltip text to display when the date falls on a disabled date.
     */
    disabledDatesText : "Disabled",
    //</locale>
    //<locale>
    /**
     * @cfg {String} minText
     * The error text to display when the date in the cell is before {@link #minValue}.
     */
    minText : "The date in this field must be equal to or after {0}",
    //</locale>
    //<locale>
    /**
     * @cfg {String} maxText
     * The error text to display when the date in the cell is after {@link #maxValue}.
     */
    maxText : "The date in this field must be equal to or before {0}",
    //</locale>
    //<locale>
    /**
     * @cfg {String} invalidText
     * The error text to display when the date in the field is invalid.
     */
    invalidText : "{0} 请填写正确的时间格式 {1}",
    //</locale>
    /**
     * @cfg {String} [triggerCls='x-form-date-trigger']
     * An additional CSS class used to style the trigger button. The trigger will always get the class 'x-form-trigger'
     * and triggerCls will be **appended** if specified (default class displays a calendar icon).
     */
    triggerCls : Ext.baseCSSPrefix + 'form-date-trigger',
    /**
     * @cfg {Boolean} showToday
     * false to hide the footer area of the Date picker containing the Today button and disable the keyboard handler for
     * spacebar that selects the current date.
     */
    showToday : true,
    /**
     * @cfg {Date/String} minValue
     * The minimum allowed date. Can be either a Javascript date object or a string date in a valid format.
     */
    /**
     * @cfg {Date/String} maxValue
     * The maximum allowed date. Can be either a Javascript date object or a string date in a valid format.
     */
    /**
     * @cfg {Number[]} disabledDays
     * An array of days to disable, 0 based. Some examples:
     *
     *     // disable Sunday and Saturday:
     *     disabledDays:  [0, 6]
     *     // disable weekdays:
     *     disabledDays: [1,2,3,4,5]
     */
    /**
     * @cfg {String[]} disabledDates
     * An array of "dates" to disable, as strings. These strings will be used to build a dynamic regular expression so
     * they are very powerful. Some examples:
     *
     *     // disable these exact dates:
     *     disabledDates: ["03/08/2003", "09/16/2003"]
     *     // disable these days for every year:
     *     disabledDates: ["03/08", "09/16"]
     *     // only match the beginning (useful if you are using short years):
     *     disabledDates: ["^03/08"]
     *     // disable every day in March 2006:
     *     disabledDates: ["03/../2006"]
     *     // disable every day in every March:
     *     disabledDates: ["^03"]
     *
     * Note that the format of the dates included in the array should exactly match the {@link #format} config. In order
     * to support regular expressions, if you are using a {@link #format date format} that has "." in it, you will have
     * to escape the dot when restricting dates. For example: `["03\\.08\\.03"]`.
     */

    /**
     * @cfg {String} submitFormat
     * The date format string which will be submitted to the server. The format must be valid according to
     * {@link Ext.Date#parse}.
     *
     * Defaults to {@link #format}.
     */

    /**
     * @cfg {Boolean} useStrict
     * True to enforce strict date parsing to prevent the default Javascript "date rollover".
     * Defaults to the useStrict parameter set on Ext.Date
     * See {@link Ext.Date#parse}.
     */
    useStrict: undefined,

    // in the absence of a time value, a default value of 12 noon will be used
    // (note: 12 noon was chosen because it steers well clear of all DST timezone changes)
    initTime: '12', // 24 hour format

    initTimeFormat: 'H',

    matchFieldWidth: false,
    //<locale>
    /**
     * @cfg {Number} [startDay=undefined]
     * Day index at which the week should begin, 0-based.
     *
     * Defaults to `0` (Sunday).
     */
    startDay: 0,
    //</locale>

    /**
     * @inheritdoc
     */
    valuePublishEvent: ['select', 'blur'],

    initComponent : function(){
        var me = this,
            isString = Ext.isString,
            min, max;

        min = me.minValue;
        max = me.maxValue;
        if(isString(min)){
            me.minValue = me.parseDate(min);
        }
        if(isString(max)){
            me.maxValue = me.parseDate(max);
        }
        me.disabledDatesRE = null;
        me.initDisabledDays();

        me.callParent();
    },

    initValue: function() {
        var me = this,
            value = me.value;

        // If a String value was supplied, try to convert it to a proper Date
        if (Ext.isString(value)) {
            me.value = me.rawToValue(value);
        }

        me.callParent();
    },

    // private
    initDisabledDays : function(){
        if(this.disabledDates){
            var dd   = this.disabledDates,
                len  = dd.length - 1,
                re   = "(?:",
                d,
                dLen = dd.length,
                date;

            for (d = 0; d < dLen; d++) {
                date = dd[d];

                re += Ext.isDate(date) ? '^' + Ext.String.escapeRegex(date.dateFormat(this.format)) + '$' : date;
                if (d !== len) {
                    re += '|';
                }
            }

            this.disabledDatesRE = new RegExp(re + ')');
        }
    },

    /**
     * Replaces any existing disabled dates with new values and refreshes the Date picker.
     * @param {String[]} disabledDates An array of date strings (see the {@link #disabledDates} config for details on
     * supported values) used to disable a pattern of dates.
     */
    setDisabledDates : function(disabledDates){
        var me = this,
            picker = me.picker;

        me.disabledDates = disabledDates;
        me.initDisabledDays();
        if (picker) {
            picker.setDisabledDates(me.disabledDatesRE);
        }
    },

    /**
     * Replaces any existing disabled days (by index, 0-6) with new values and refreshes the Date picker.
     * @param {Number[]} disabledDays An array of disabled day indexes. See the {@link #disabledDays} config for details on
     * supported values.
     */
    setDisabledDays : function(disabledDays){
        var picker = this.picker;

        this.disabledDays = disabledDays;
        if (picker) {
            picker.setDisabledDays(disabledDays);
        }
    },

    /**
     * Replaces any existing {@link #minValue} with the new value and refreshes the Date picker.
     * @param {Date} value The minimum date that can be selected
     */
    setMinValue : function(value){
        var me = this,
            picker = me.picker,
            minValue = (Ext.isString(value) ? me.parseDate(value) : value);

        me.minValue = minValue;
        if (picker) {
            picker.minText = Ext.String.format(me.minText, me.formatDate(me.minValue));
            picker.setMinDate(minValue);
        }
    },

    /**
     * Replaces any existing {@link #maxValue} with the new value and refreshes the Date picker.
     * @param {Date} value The maximum date that can be selected
     */
    setMaxValue : function(value){
        var me = this,
            picker = me.picker,
            maxValue = (Ext.isString(value) ? me.parseDate(value) : value);

        me.maxValue = maxValue;
        if (picker) {
            picker.maxText = Ext.String.format(me.maxText, me.formatDate(me.maxValue));
            picker.setMaxDate(maxValue);
        }
    },

    /**
     * Runs all of Date's validations and returns an array of any errors. Note that this first runs Text's validations,
     * so the returned array is an amalgamation of all field errors. The additional validation checks are testing that
     * the date format is valid, that the chosen date is within the min and max date constraints set, that the date
     * chosen is not in the disabledDates regex and that the day chosed is not one of the disabledDays.
     * @param {Object} [value] The value to get errors for (defaults to the current field value)
     * @return {String[]} All validation errors for this field
     */
    getErrors: function(value) {
        value = arguments.length > 0 ? value : this.formatDate(this.processRawValue(this.getRawValue()));

        var me = this,
            format = Ext.String.format,
            clearTime = Ext.Date.clearTime,
            errors = me.callParent([value]),
            disabledDays = me.disabledDays,
            disabledDatesRE = me.disabledDatesRE,
            minValue = me.minValue,
            maxValue = me.maxValue,
            len = disabledDays ? disabledDays.length : 0,
            i = 0,
            svalue,
            fvalue,
            day,
            time;

        

        if (value === null || value.length < 1) { // if it's blank and textfield didn't flag it then it's valid
             return errors;
        }

        svalue = value;
        value = me.parseDate(value);
        if (!value) {
            errors.push(format(me.invalidText, svalue, Ext.Date.unescapeFormat(me.format)));
            return errors;
        }

        time = value.getTime();
        if (minValue && time < clearTime(minValue).getTime()) {
            errors.push(format(me.minText, me.formatDate(minValue)));
        }

        if (maxValue && time > clearTime(maxValue).getTime()) {
            errors.push(format(me.maxText, me.formatDate(maxValue)));
        }

        if (disabledDays) {
            day = value.getDay();

            for(; i < len; i++) {
                if (day === disabledDays[i]) {
                    errors.push(me.disabledDaysText);
                    break;
                }
            }
        }

        fvalue = me.formatDate(value);
        if (disabledDatesRE && disabledDatesRE.test(fvalue)) {
            errors.push(format(me.disabledDatesText, fvalue));
        }

        return errors;
    },

    rawToValue: function(rawValue) {
        return this.parseDate(rawValue) || rawValue || null;
    },

    valueToRaw: function(value) {
        return this.formatDate(this.parseDate(value));
    },

    /**
     * @method setValue
     * Sets the value of the date field. You can pass a date object or any string that can be parsed into a valid date,
     * using {@link #format} as the date format, according to the same rules as {@link Ext.Date#parse} (the default
     * format used is "m/d/Y").
     *
     * Usage:
     *
     *     //All of these calls set the same date value (May 4, 2006)
     *
     *     //Pass a date object:
     *     var dt = new Date('5/4/2006');
     *     dateField.setValue(dt);
     *
     *     //Pass a date string (default format):
     *     dateField.setValue('05/04/2006');
     *
     *     //Pass a date string (custom format):
     *     dateField.format = 'Y-m-d';
     *     dateField.setValue('2006-05-04');
     *
     * @param {String/Date} date The date or valid date string
     * @return {Ext.form.field.Date} this
     */

    /**
     * Attempts to parse a given string value using a given {@link Ext.Date#parse date format}.
     * @param {String} value The value to attempt to parse
     * @param {String} format A valid date format (see {@link Ext.Date#parse})
     * @return {Date} The parsed Date object, or null if the value could not be successfully parsed.
     */
    safeParse : function(value, format) {
        var me = this,
            utilDate = Ext.Date,
            result = null,
            strict = me.useStrict,
            parsedDate;

        if (utilDate.formatContainsHourInfo(format)) {
            // if parse format contains hour information, no DST adjustment is necessary
            result = utilDate.parse(value, format, strict);
        } else {
            // set time to 12 noon, then clear the time
            parsedDate = utilDate.parse(value + ' ' + me.initTime, format + ' ' + me.initTimeFormat, strict);
            if (parsedDate) {
                result = utilDate.clearTime(parsedDate);
            }
        }
        return result;
    },

    // @private
    getSubmitValue: function() {
        var format = this.submitFormat || this.format,
            value = this.getValue();

        return value ? Ext.Date.format(value, format) : '';
    },

    /**
     * @private
     */
    parseDate : function(value) {
        if(!value || Ext.isDate(value)){
            return value;
        }

        var me = this,
            val = me.safeParse(value, me.format),
            altFormats = me.altFormats,
            altFormatsArray = me.altFormatsArray,
            i = 0,
            len;

        if (!val && altFormats) {
            altFormatsArray = altFormatsArray || altFormats.split('|');
            len = altFormatsArray.length;
            for (; i < len && !val; ++i) {
                val = me.safeParse(value, altFormatsArray[i]);
            }
        }
        return val;
    },

    // private
    formatDate: function(date){
        return Ext.isDate(date) ? Ext.Date.dateFormat(date, this.format) : date;
    },

    createPicker: function() {
        var me = this,
            format = Ext.String.format;

        // Create floating Picker BoundList. It will acquire a floatParent by looking up
        // its ancestor hierarchy (Pickers use their pickerField property as an upward link)
        // for a floating component.
        return new baseUx.form.datetime.DateTimePicker({
            pickerField: me,
            floating: true,
            focusable: false, // Key events are listened from the input field which is never blurred
            hidden: true,
            minDate: me.minValue,
            maxDate: me.maxValue,
            disabledDatesRE: me.disabledDatesRE,
            disabledDatesText: me.disabledDatesText,
            disabledDays: me.disabledDays,
            disabledDaysText: me.disabledDaysText,
            format: me.format,
            showToday: me.showToday,
            startDay: me.startDay,
            minText: format(me.minText, me.formatDate(me.minValue)),
            maxText: format(me.maxText, me.formatDate(me.maxValue)),
            listeners: {
                scope: me,
                select: me.onSelect
            },
            keyNavConfig: {
                esc: function() {
                    me.collapse();
                }
            }
        });
    },

    onSelect: function(m, d) {
        var me = this;

        me.setValue(d);
        me.fireEvent('select', me, d);
        me.collapse();
    },

    /**
     * @private
     * Sets the Date picker's value to match the current field value when expanding.
     */
    onExpand: function() {
		var me = this,
            value = me.getValue() instanceof Date ? me.getValue() : new Date();
        me.picker.setValue(value);        
        me.picker.hour.setValue(value.getHours());
        me.picker.minute.setValue(value.getMinutes());
        me.picker.second.setValue(value.getSeconds());
    },

    // private
    onBlur: function(e) {
        var me = this,
            v = me.rawToValue(me.getRawValue());

        if (Ext.isDate(v)) {
            me.setValue(v);
        }
        me.callParent([e]);
    }

    /**
     * @cfg {Boolean} grow
     * @private
     */
    /**
     * @cfg {Number} growMin
     * @private
     */
    /**
     * @cfg {Number} growMax
     * @private
     */
    /**
     * @method autoSize
     * @private
     */
});