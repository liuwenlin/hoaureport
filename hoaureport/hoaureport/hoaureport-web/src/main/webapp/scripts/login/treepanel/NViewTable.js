Ext.define('baseUx.view.NViewTable', {
    extend: 'Ext.view.Table',
    alias: 'widget.tableview',
    outerRowTpl: [
          '<table id="{rowId}" ',
              'data-boundView="{view.id}" ',
              'data-recordId="{record.internalId}" ',
              'data-recordIndex="{recordIndex}" ',

              // Table width must fill the item container (which is explicitly sized during rendering, and during layout)
              'class="{[values.itemClasses.join(" ")]}" cellPadding="0" cellSpacing="0" {ariaTableAttr} style="font-family:\'微软雅黑\';font-size:16px;color:#ffffff;width:100%;{itemStyle}">',

                  // Do NOT emit a <TBODY> tag in case the nextTpl has to emit a <COLGROUP> column sizer element.
                  // Browser will create a tbody tag when it encounters the first <TR>
                  '{%',
                      'this.nextTpl.applyOut(values, out, parent)',
                  '%}',
          '</table>', {
              priority: 9999
          }
      ],
      cellTpl: [
          '<td class="{tdCls}" {tdAttr} {[Ext.aria ? "id=\\"" + Ext.id() + "\\"" : ""]} style="padding-top:5px;padding-bottom:5px;width:{column.cellWidth}px;<tpl if="tdStyle">{tdStyle}</tpl>" tabindex="-1" {ariaCellAttr} data-columnid="{[values.column.getItemId()]}">',
              '<div {unselectableAttr} class="' + Ext.baseCSSPrefix + 'grid-cell-inner {innerCls}" ',
                  'style="text-align:{align};<tpl if="style">{style}</tpl>" {ariaCellInnerAttr}>{value}</div>',
          '</td>', {
              priority: 0
          }
      ]
});