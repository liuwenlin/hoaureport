Ext.define('baseUx.tree.NTreeColumn', {
    extend: 'Ext.grid.column.Column',
    alias: 'widget.treecolumn',
    tdCls: Ext.baseCSSPrefix + 'grid-cell-treecolumn',

    autoLock: true,
    lockable: false,
    draggable: false,
    hideable: false,

    iconCls: Ext.baseCSSPrefix + 'tree-icon',
    checkboxCls: Ext.baseCSSPrefix + 'tree-checkbox',
    elbowCls: Ext.baseCSSPrefix + 'tree-elbow',
    expanderCls: Ext.baseCSSPrefix + 'tree-expander',
    textCls: Ext.baseCSSPrefix + 'tree-node-text',
    innerCls: Ext.baseCSSPrefix + 'grid-cell-inner-treecolumn',
    parentIconCls: 'ui_main_left_tree_left_iconCls',
    isTreeColumn: true,
    
    cellTpl: [
        '<table style="margin-left:30px;height:100%;" border=0 cellpadding=0 cellspacing=0>',
        	'<tr>',
        		'<td style="width:22px;">',
			        '<tpl for="lines">',
			            '<img src="{parent.blankUrl}" class="{parent.childCls} {parent.elbowCls}-img',
			            '{parent.elbowCls}-<tpl if=".">line<tpl else>empty</tpl>" role="presentation"/>',
			        '</tpl>',
			        '<tpl if="checked !== null">',
			            '<input type="button" {ariaCellCheckboxAttr}',
			                ' class="{childCls} {checkboxCls}<tpl if="checked"> {checkboxCls}-checked</tpl>"/>',
			        '</tpl>',
			        '<img src="{blankUrl}" role="presentation" class="{childCls} {baseIconCls} ',
			            '<tpl if="leaf">{parentIconCls}</tpl> {iconCls}"',
			            '<tpl if="icon">style="background-image:url({icon})"</tpl>/>',
	            '</td>',
	            '<td style="width:115px;text-align:left;padding-left:17px;">',
			        '<tpl if="href">',
			            '<a href="{href}" role="link" target="{hrefTarget}" class="{textCls} {childCls}">{value}</a>',
			        '<tpl else>',
			            '<span class="{textCls} {childCls}">{value}</span>',
			        '</tpl>',
			    '</td>',
		        '<td style="width:22px;text-align:left;">',
			        '<img src="{blankUrl}" class="{childCls} {elbowCls}-img {elbowCls}',
		            '<tpl if="isLast">-end</tpl><tpl if="expandable">-plus {expanderCls}</tpl>" role="presentation"/>',
		        '</td>',
        	'</tr>',
        '</table>'
    ],

    initComponent: function() {
        var me = this;

        me.setupRenderer();
        me.origRenderer = me.renderer;
        me.origScope = me.scope || window;

        me.renderer = me.treeRenderer;
        me.scope = me;

        me.callParent();
    },

    treeRenderer: function(value, metaData, record, rowIdx, colIdx, store, view){
        var me = this,
            cls = record.get('cls'),
            rendererData;

        if (cls) {
            metaData.tdCls += ' ' + cls;
        }

        rendererData = me.initTemplateRendererData(value, metaData, record, rowIdx, colIdx, store, view);
        
        return me.getTpl('cellTpl').apply(rendererData);
    },
    
    initTemplateRendererData: function(value, metaData, record, rowIdx, colIdx, store, view) {
        var me = this,
            renderer = me.origRenderer,
            data = record.data,
            parent = record.parentNode,
            rootVisible = view.rootVisible,
            lines = [],
            parentData;
        
        while (parent && (rootVisible || parent.data.depth > 0)) {
            parentData = parent.data;
            lines[rootVisible ? parentData.depth : parentData.depth - 1] =
                    parentData.isLast ? 0 : 1;
            parent = parent.parentNode;
        }
        
        return {
            record: record,
            baseIconCls: me.iconCls,
            iconCls: data.iconCls,
            icon: data.icon,
            checkboxCls: me.checkboxCls,
            checked: data.checked,
            elbowCls: me.elbowCls,
            expanderCls: me.expanderCls,
            textCls: me.textCls,
            parentIconCls: me.parentIconCls,
            expanderOverCls: me.expanderOverCls,
            leaf: data.leaf,
            expandable: record.isExpandable(),
            isLast: record.isLastVisible(),
            blankUrl: Ext.BLANK_IMAGE_URL,
            href: data.href,
            hrefTarget: data.hrefTarget,
            lines: lines,
            metaData: metaData,
            // subclasses or overrides can implement a getChildCls() method, which can
            // return an extra class to add to all of the cell's child elements (icon,
            // expander, elbow, checkbox).  This is used by the rtl override to add the
            // "x-rtl" class to these elements.
            childCls: me.getChildCls ? me.getChildCls() + ' ' : '',
            value: renderer ? renderer.apply(me.origScope, arguments) : value
        };
    }
});