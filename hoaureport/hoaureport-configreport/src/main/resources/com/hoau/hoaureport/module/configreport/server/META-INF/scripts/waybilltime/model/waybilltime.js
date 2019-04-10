Ext.define('report.model.waybilltime', {
    extend: 'Ext.data.Model',
    fields: [
             { name: 'id'},//id
             { name: 'zipNum'},//包编号
             { name: 'zipPath'},//路径
             { name: 'zipName'},//包名
             { name: 'active'},
             { name: 'creator'},//创建人
             { name: 'createDate',type : 'date', dateFormat:'time'}//创建时间
         
             
         ]
     });