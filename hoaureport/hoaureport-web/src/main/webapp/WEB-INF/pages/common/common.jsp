<link rel="stylesheet" type="text/css"
	href="${resource}/ext/build/packages/ext-theme-crisp/build/resources/ext-theme-crisp-all.css">
<link href="../styles/common/common.css" rel="stylesheet" type="text/css" /> 
<script type="text/javascript" src="${resource}/ext/build/ext-all.js"></script>
<script type="text/javascript"
	src="${resource}/ext/build/packages/ext-theme-crisp/build/ext-theme-crisp.js"></script>
<script type="text/javascript"
	src="${resource}/ext/build/packages/ext-locale/build/ext-locale-zh_CN.js"></script>
<style>
	/* 
	 *Chrome bug fix 
	 */
	.x-form-text { display: inline; }
</style>

<script type="text/javascript">
	function onkeydown() {
		if ((event.keyCode == 8))
		{
			var se = window.event.srcElement;
			var tName = se.tagName;
			if (tName.toUpperCase() != "INPUT"
					&& tName.toUpperCase() != "TEXTAREA"
					&& tName.toUpperCase() != "TEXT") {
				event.keyCode = 0;
				event.returnValue = false;
			} else if(se.readOnly){
				event.keyCode = 0;
				event.returnValue = false;
			}
		}
	}
	document.onkeydown = onkeydown;
</script>