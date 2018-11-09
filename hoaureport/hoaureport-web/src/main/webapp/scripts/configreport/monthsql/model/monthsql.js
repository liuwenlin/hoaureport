Ext.define('report.model.monthsql', {
    extend: 'Ext.data.Model',
    fields: [
             { name: 'id'},//id
             { name: 'sqlId'},//报表id
             { name: 'sqlName'},//标题名
             { name: 'sqlSeq'},//顺序
             { name: 'active'},
             { name: 'creator'},//创建人
             { name: 'createDate',type : 'date', dateFormat:'time'},//创建时间
             { name: 'modifier'},//修改人
             { name: 'modifyDate',type : 'date', dateFormat:'time'},//修改时间
             { name: 'sqlType'},//类型
             { name: 'sqlBodyStr'},//sql
             { name: 'charCode'},//编码
             { name: 'remark'}//描述
         ]
     });