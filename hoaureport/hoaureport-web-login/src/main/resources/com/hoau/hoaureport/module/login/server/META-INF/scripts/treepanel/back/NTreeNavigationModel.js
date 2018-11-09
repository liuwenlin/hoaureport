Ext.define('baseUx.tree.NTreeNavigationModel', {
    extend: 'baseUx.grid.NGridNavigationModel',
    
    alias: 'view.ntreenavigation.ntree',
    
    initKeyNav: function(view) {
        var me = this;

        me.callParent([view]);
        me.keyNav.map.addBinding([{
            key: '8',
            shift: true,
            handler: me.onAsterisk,
            scope: me
        }, {
            key: Ext.event.Event.NUM_MULTIPLY,
            handler: me.onAsterisk,
            scope: me
        }]);
    },

    onKeyLeft: function(keyEvent) {
        var me = this,
            view = me.view,
            record = me.record;

        // Left arrow key on an expanded node closes the node.
        if (!record.isLeaf() && record.isExpanded()) {
            view.collapse(record);
        }
        // Left arrow key on a closed or end node moves focus to the node's parent (don't attempt to focus hidden root).
        else {
            record = record.parentNode;
            if (record && !(record.isRoot() && !view.rootVisible)) {
                me.setPosition(record, null, keyEvent);
            }
        }
    },

    onKeyRight: function(keyEvent) {
        var me = this,
            record = me.record;

        // Right arrow key expands a closed node, moves to the first child of an open node, or does nothing on an end node.
        if (!record.isLeaf()) {
            if (!record.isExpanded()) {
                me.view.expand(record);
            } else {
                record = record.childNodes[0];
                if (record) {
                    me.setPosition(record);
                }
            }
        }
    },

    onKeyEnter: function(keyEvent) {
        if (this.record.data.checked != null) {
            this.toggleCheck(keyEvent);
        } else {
            this.callParent([keyEvent]);
        }
    },

    onKeySpace: function(keyEvent) {
        if (this.record.data.checked != null) {
            this.toggleCheck(keyEvent);
        } else {
            this.callParent([keyEvent]);
        }
    },

    toggleCheck: function(keyEvent) {
        this.view.onCheckChange(this.record);
    },

    // (asterisk) on keypad expands all nodes.
    onAsterisk: function(keyEvent) {
        this.view.ownerCt.expandAll();
    }
});