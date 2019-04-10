/**
 * 主界面菜单Store
 */
Ext.define('Butterfly.store.login.MenuStore', {
	extend: 'Ext.data.TreeStore',
    root: {
		text:'WEB系统',
		id : 'web_1'//,
		//expanded: true
	},
	proxy:{
		type:'ajax',
		url:'menuAction!loadTree.action',
		actionMethods:'POST',
		reader:{
			type:'json',
			rootProperty:'nodes',
		},
		default:{expanded : true }
	},
	/*autoLoad:true,*/
	folderSort: true
});