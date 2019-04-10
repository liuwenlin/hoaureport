Ext.define('baseUx.tree.NTreePanel', {
    extend: 'Ext.tree.Panel',
    alias: 'widget.treepanel',
    requires: [
       'baseUx.view.NTreeView',
       'baseUx.tree.NTreeColumn'
   ],
   viewType: 'treeview'
});