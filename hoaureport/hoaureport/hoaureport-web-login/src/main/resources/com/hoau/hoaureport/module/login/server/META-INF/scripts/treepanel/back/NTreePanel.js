Ext.define('baseUx.tree.NTreePanel', {
    extend: 'Ext.panel.Table',
    alias: 'widget.ntreepanel',
    alternateClassName: ['baseUx.tree.NTreePanel'],
    requires: [
        'baseUx.view.NTreeView',
        'Ext.selection.TreeModel',
        'baseUx.tree.NTreeColumn',
        'Ext.data.TreeStore',
        'baseUx.tree.NTreeNavigationModel'
    ],
    viewType: 'ntreeview',
    selType: 'treemodel',

    treeCls: Ext.baseCSSPrefix + 'tree-panel',

    /**
     * @cfg {Boolean} [rowLines=false]
     * Configure as true to separate rows with visible horizontal lines (depends on theme).
     */
    rowLines: false,

    /**
     * @cfg {Boolean} [lines=true]
     * False to disable tree lines.
     */
    lines: true,

    /**
     * @cfg {Boolean} [useArrows=false]
     * True to use Vista-style arrows in the tree.
     */
    useArrows: false,

    /**
     * @cfg {Boolean} [singleExpand=false]
     * True if only 1 node per branch may be expanded.
     */
    singleExpand: false,

    ddConfig: {
        enableDrag: true,
        enableDrop: true
    },

    /**
     * @cfg {Boolean} animate
     * True to enable animated expand/collapse. Defaults to the value of {@link Ext#enableFx}.
     */

    /**
     * @cfg {Boolean} [rootVisible=true]
     * False to hide the root node.
     *
     * Note that trees *always* have a root node. If you do not specify a {@link #cfg-root} node, one will be created.
     *
     * If the root node is not visible, then in order for a tree to appear to the end user, the root node is autoloaded with its child nodes.
     */
    rootVisible: true,

    /**
     * @cfg {String} [displayField=text]
     * The field inside the model that will be used as the node's text.
     */
    displayField: 'text',

    /**
     * @cfg {Ext.data.Model/Ext.data.TreeModel/Object} root
     * Allows you to not specify a store on this TreePanel. This is useful for creating a simple tree with preloaded
     * data without having to specify a TreeStore and Model. A store and model will be created and root will be passed
     * to that store. For example:
     *
     *     Ext.create('Ext.tree.Panel', {
     *         title: 'Simple Tree',
     *         root: {
     *             text: "Root node",
     *             expanded: true,
     *             children: [
     *                 { text: "Child 1", leaf: true },
     *                 { text: "Child 2", leaf: true }
     *             ]
     *         },
     *         renderTo: Ext.getBody()
     *     });
     */
    root: null,

    // Required for the Lockable Mixin. These are the configurations which will be copied to the
    // normal and locked sub tablepanels
    normalCfgCopy: ['displayField', 'root', 'singleExpand', 'useArrows', 'lines', 'rootVisible', 'scroll'],
    lockedCfgCopy: ['displayField', 'root', 'singleExpand', 'useArrows', 'lines', 'rootVisible'],
    
    isTree: true,

    /**
     * @cfg {Boolean} hideHeaders
     * True to hide the headers.
     */

    /**
     * @cfg {Boolean} folderSort
     * True to automatically prepend a leaf sorter to the store.
     */
     
    /**
     * @cfg {Ext.data.TreeStore} store (required)
     * The {@link Ext.data.TreeStore Store} the tree should use as its data source.
     */
    
    arrowCls: Ext.baseCSSPrefix + 'tree-arrows',
    linesCls: Ext.baseCSSPrefix + 'tree-lines',
    noLinesCls: Ext.baseCSSPrefix + 'tree-no-lines',
    autoWidthCls: Ext.baseCSSPrefix + 'autowidth-table',

    constructor: function(config) {
        config = config || {};
        if (config.animate === undefined) {
            config.animate = Ext.isBoolean(this.animate) ? this.animate : Ext.enableFx;
        }
        this.enableAnimations = config.animate;
        delete config.animate;

        this.callParent([config]);
    },

    initComponent: function() {
        var me = this,
            cls = [me.treeCls],
            store = me.store,
            view;
        alert("["+me.getNavigationModel+"]");
        if (me.useArrows) {
            cls.push(me.arrowCls);
            me.lines = false;
        }

        if (me.lines) {
            cls.push(me.linesCls);
        } else if (!me.useArrows) {
            cls.push(me.noLinesCls);
        }

        if (Ext.isString(store)) {
            store = me.store = Ext.StoreMgr.lookup(store);
        } else if (!store || !store.isStore) {
            store = Ext.apply({
                type: 'tree',
                root: me.root,
                fields: me.fields,
                model: me.model,
                proxy: 'memory',
                folderSort: me.folderSort
            }, store);
            store = me.store = Ext.StoreMgr.lookup(store);
        } else if (me.root) {
            store = me.store = Ext.data.StoreManager.lookup(store);
            store.setRoot(me.root);
            if (me.folderSort !== undefined) {
                store.folderSort = me.folderSort;
                store.sort();
            }
        }

        // Store must have the same idea about root visibility as us BEFORE callParent binds it.
        store.setRootVisible(me.rootVisible);

        // If there is no rootnode defined, then create one.
        if (!store.getRoot()) {
            store.setRoot({});
        }

        me.viewConfig = Ext.apply({
            rootVisible: me.rootVisible,
            animate: me.enableAnimations,
            singleExpand: me.singleExpand,
            node: store.getRoot(),
            hideHeaders: me.hideHeaders,
            navigationModel: 'tree'
        }, me.viewConfig);

        // If the user specifies the headers collection manually then dont inject our own
        if (!me.columns) {
            if (me.initialConfig.hideHeaders === undefined) {
                me.hideHeaders = true;
            }
            me.addCls(me.autoWidthCls);
            me.columns = [{
                xtype    : 'ntreecolumn',
                text     : 'Name',
                flex     : 1,
                dataIndex: me.displayField         
            }];
        }

        if (me.cls) {
            cls.push(me.cls);
        }
        me.cls = cls.join(' ');

        me.callParent();

        view = me.getView();

        // Relay events from the TreeView.
        // An injected LockingView relays events from its locked side's View
        me.relayEvents(view, [
            /**
            * @event checkchange
            * Fires when a node with a checkbox's checked property changes
            * @param {Ext.data.TreeModel} node The node who's checked property was changed
            * @param {Boolean} checked The node's new checked state
            */
            'checkchange',
            /**
            * @event afteritemexpand
            * @inheritdoc Ext.tree.View#afteritemexpand
            */
            'afteritemexpand',
            /**
            * @event afteritemcollapse
            * @inheritdoc Ext.tree.View#afteritemcollapse
            */
            'afteritemcollapse'
        ]);
    },

    getSelectionModel: function() {
        var result = this.callParent();
        result.treeStore = this.getStore();
        return result;
    },

    // @private
    // Hook into the TreeStore.
    bindStore: function(store, initial) {
        var me = this,
            root = store.getRoot(),
            bufferedRenderer = me.bufferedRenderer;

        // Bind to store, and autocreate the BufferedRenderer.
        me.callParent(arguments);

        // If we're in a reconfigure (we already have a BufferedRenderer which is bound to our old store),
        // rebind the BufferedRenderer
        if (bufferedRenderer) {
            if (bufferedRenderer.store) {
                bufferedRenderer.bindStore(store);
            }
        }
        // Create a BufferedRenderer as a plugin if we have not already configured with one.
        else {
            bufferedRenderer = {
                xclass: 'Ext.grid.plugin.BufferedRenderer'
            };
            Ext.copyTo(bufferedRenderer, me, 'variableRowHeight,numFromEdge,trailingBufferZone,leadingBufferZone,scrollToLoadBuffer');
            me.bufferedRenderer = bufferedRenderer = me.addPlugin(bufferedRenderer);
        }

        // The TreeStore needs to know about this TreePanel's singleExpand constraint so that
        // it can ensure the compliance of NodeInterface.expandAll.
        store.singleExpand = me.singleExpand;

        // Monitor the TreeStore for the root node being changed. Return a Destroyable object
        me.storeListeners = me.mon(store, {
            destroyable: true,
            rootchange: me.onRootChange,
            scope: me
        });

        // Relay store events. relayEvents always returns a Destroyable object.
        me.storeRelayers = me.relayEvents(store, [
            /**
             * @event beforeload
             * @inheritdoc Ext.data.TreeStore#beforeload
             */
            'beforeload',

            /**
             * @event load
             * @inheritdoc Ext.data.TreeStore#load
             */
            'load'
        ]);

        // Relay store events with prefix. Return a Destroyable object
        me.rootRelayers = me.mon(root, {
            destroyable: true,

            /**
             * @event itemappend
             * @inheritdoc Ext.data.TreeStore#nodeappend
             */
            append: me.createRelayer('itemappend'),

            /**
             * @event itemremove
             * @inheritdoc Ext.data.TreeStore#noderemove
             */
            remove: me.createRelayer('itemremove'),

            /**
             * @event itemmove
             * @inheritdoc Ext.data.TreeStore#nodemove
             */
            move: me.createRelayer('itemmove', [0, 4]),

            /**
             * @event iteminsert
             * @inheritdoc Ext.data.TreeStore#nodeinsert
             */
            insert: me.createRelayer('iteminsert'),

            /**
             * @event beforeitemappend
             * @inheritdoc Ext.data.TreeStore#nodebeforeappend
             */
            beforeappend: me.createRelayer('beforeitemappend'),

            /**
             * @event beforeitemremove
             * @inheritdoc Ext.data.TreeStore#nodebeforeremove
             */
            beforeremove: me.createRelayer('beforeitemremove'),

            /**
             * @event beforeitemmove
             * @inheritdoc Ext.data.TreeStore#nodebeforemove
             */
            beforemove: me.createRelayer('beforeitemmove'),

            /**
             * @event beforeiteminsert
             * @inheritdoc Ext.data.TreeStore#nodebeforeinsert
             */
            beforeinsert: me.createRelayer('beforeiteminsert'),

            /**
             * @event itemexpand
             * @inheritdoc Ext.data.TreeStore#nodeexpand
             */
            expand: me.createRelayer('itemexpand', [0, 1]),

            /**
             * @event itemcollapse
             * @inheritdoc Ext.data.TreeStore#nodecollapse
             */
            collapse: me.createRelayer('itemcollapse', [0, 1]),

            /**
             * @event beforeitemexpand
             * @inheritdoc Ext.data.TreeStore#nodebeforeexpand
             */
            beforeexpand: me.createRelayer('beforeitemexpand', [0, 1]),

            /**
             * @event beforeitemcollapse
             * @inheritdoc Ext.data.TreeStore#nodebeforecollapse
             */
            beforecollapse: me.createRelayer('beforeitemcollapse', [0, 1])
        });

        // If rootVisible is false, we *might* need to expand the node.
        // If store is autoLoad, that will already have been kicked off.
        // If its already expanded, or in the process of loading, the TreeStore
        // has started that at the end of updateRoot 
        if (!me.rootVisible && !store.autoLoad && !(root.isExpanded() || root.isLoading())) {
            // A hidden root must be expanded.
            // If it's loaded, set its expanded field (silently), and skip ahead to the onNodeExpand callback.
            if (root.isLoaded()) {
                root.data.expanded = true;
                store.onNodeExpand(root, root.childNodes);
            }
            // Root is not loaded; go through the expand mechanism to force a load
            else {
                root.data.expanded = false;
                root.expand();
            }
        }

        // TreeStore must have an upward link to the TreePanel so that nodes can find their owning tree in NodeInterface.getOwnerTree
        store.ownerTree = me;

        if (!initial) {
            me.view.setRootNode(root, true);
        }
    },

    // @private
    unbindStore: function() {
        var me = this,
            store = me.store;

        if (store) {
            me.callParent();
            Ext.destroy(me.storeListeners, me.storeRelayers, me.rootRelayers);
            delete store.ownerTree;
            store.singleExpand = null;
        }
    },

    /**
     * Sets root node of this tree. All trees *always* have a root node. It may be {@link #rootVisible hidden}.
     *
     * If the passed node has not already been loaded with child nodes, and has its expanded field set, this triggers the {@link #cfg-store} to load the child nodes of the root.
     * @param {Ext.data.TreeModel/Object} root
     * @return {Ext.data.TreeModel} The new root
     */
    setRootNode: function() {
        return this.store.setRoot.apply(this.store, arguments);
    },

    /**
     * Returns the root node for this tree.
     * @return {Ext.data.TreeModel}
     */
    getRootNode: function() {
        return this.store.getRoot();
    },

    onRootChange: function(root) {
        this.view.setRootNode(root);
    },

    /**
     * Retrieve an array of checked records.
     * @return {Ext.data.TreeModel[]} An array containing the checked records
     */
    getChecked: function() {
        return this.getView().getChecked();
    },

    isItemChecked: function(rec) {
        return rec.get('checked');
    },
    
    /**
     * Expands a record that is loaded in the tree.
     * @param {Ext.data.Model} record The record to expand
     * @param {Boolean} [deep] True to expand nodes all the way down the tree hierarchy.
     * @param {Function} [callback] The function to run after the expand is completed
     * @param {Object} [scope] The scope of the callback function.
     */
    expandNode: function(record, deep, callback, scope) {
        return this.getView().expand(record, deep, callback, scope || this);
    },

    /**
     * Collapses a record that is loaded in the tree.
     * @param {Ext.data.Model} record The record to collapse
     * @param {Boolean} [deep] True to collapse nodes all the way up the tree hierarchy.
     * @param {Function} [callback] The function to run after the collapse is completed
     * @param {Object} [scope] The scope of the callback function.
     */
    collapseNode: function(record, deep, callback, scope) {
        return this.getView().collapse(record, deep, callback, scope || this);
    },

    /**
     * Expand all nodes
     * @param {Function} [callback] A function to execute when the expand finishes.
     * @param {Object} [scope] The scope of the callback function
     */
    expandAll : function(callback, scope) {
        var me = this,
            root = me.getRootNode(),
            animate = me.enableAnimations;
        if (root) {
            if (!animate) {
                Ext.suspendLayouts();
            }
            root.expand(true, callback, scope || me);
            if (!animate) {
                Ext.resumeLayouts(true);
            }
        }
    },

    /**
     * Collapse all nodes
     * @param {Function} [callback] A function to execute when the collapse finishes.
     * @param {Object} [scope] The scope of the callback function
     */
    collapseAll : function(callback, scope) {
        var me = this,
            root = me.getRootNode(),
            animate = me.enableAnimations,
            view = me.getView();

        if (root) {
            if (!animate) {
                Ext.suspendLayouts();
            }
            scope = scope || me;
            if (view.rootVisible) {
                root.collapse(true, callback, scope);
            } else {
                root.collapseChildren(true, callback, scope);
            }
            if (!animate) {
                Ext.resumeLayouts(true);
            }
        }
    },

    /**
     * Expand the tree to the path of a particular node.
     * @param {String} path The path to expand. The path should include a leading separator.
     * @param {String} [field] The field to get the data from. Defaults to the model idProperty.
     * @param {String} [separator='/'] A separator to use.
     * @param {Function} [callback] A function to execute when the expand finishes. The callback will be called with
     * (success, lastNode) where success is if the expand was successful and lastNode is the last node that was expanded.
     * @param {Object} [scope] The scope of the callback function
     */
    expandPath: function(path, field, separator, callback, scope) {
        var me = this,
            current = me.getRootNode(),
            index = 1,
            keys,
            expander;

        field = field || me.getRootNode().idProperty;
        separator = separator || '/';

        if (Ext.isEmpty(path)) {
            Ext.callback(callback, scope || me, [false, null]);
            return;
        }

        keys = path.split(separator);
        if (current.get(field) != keys[1]) {
            // invalid root
            Ext.callback(callback, scope || me, [false, current]);
            return;
        }

        expander = function(){
            if (++index === keys.length) {
                Ext.callback(callback, scope || me, [true, current]);
                return;
            }
            var node = current.findChild(field, keys[index]);
            if (!node) {
                Ext.callback(callback, scope || me, [false, current]);
                return;
            }
            current = node;
            current.expand(false, expander);
        };
        current.expand(false, expander);
    },

    /**
     * Expand the tree to the path of a particular node, then select it.
     * @param {String} path The path to select; A string of separated node IDs.
     * 
     * The path should include a leading separator. eg `'/root/usermanagement/users'`
     * @param {String} [field] The field to get the data from. Defaults to the model idProperty.
     * @param {String} [separator='/'] A separator to use.
     * @param {Function} [callback] A function to execute when the select finishes. The callback will be called with
     * (bSuccess, oLastNode) where bSuccess is if the select was successful and oLastNode is the last node that was expanded.
     * @param {Object} [scope] The scope of the callback function
     */
    selectPath: function(path, field, separator, callback, scope) {
        var me = this,
            root,
            keys,
            last;

        field = field || me.getRootNode().idProperty;
        separator = separator || '/';

        keys = path.split(separator);
        last = keys.pop();
        if (keys.length > 1) {
            me.expandPath(keys.join(separator), field, separator, function(success, node){
                var lastNode = node;
                if (success && node) {
                    node = node.findChild(field, last);
                    if (node) {
                        me.getSelectionModel().select(node);
                        Ext.callback(callback, scope || me, [true, node]);
                        return;
                    }
                }
                Ext.callback(callback, scope || me, [false, lastNode]);
            }, me);
        } else {
            root = me.getRootNode();
            if (root.getId() === last) {
                me.getSelectionModel().select(root);
                Ext.callback(callback, scope || me, [true, root]);
            } else {
                Ext.callback(callback, scope || me, [false, null]);
            }
        }
    }
});