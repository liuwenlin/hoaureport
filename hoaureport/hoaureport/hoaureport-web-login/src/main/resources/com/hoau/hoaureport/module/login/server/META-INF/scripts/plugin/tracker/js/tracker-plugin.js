var _paq = _paq || [];
_paq.push(['setUserId', TRACKER_CONFIG.visitor]);
_paq.push(["setDomains", [TRACKER_CONFIG.domain]]);
_paq.push(['trackPageView']);
_paq.push(['enableLinkTracking']);
(function() {
    var u= TRACKER_CONFIG.remote;
    _paq.push(['setTrackerUrl', u+'piwik.php']);
    _paq.push(['setSiteId', TRACKER_CONFIG.siteId]);
    var d=document, g=d.createElement('script'), s=d.getElementsByTagName('script')[0];
    g.type='text/javascript'; g.async=true; g.defer=true; g.src='../scripts/login/plugin/tracker/js/piwik.js'; s.parentNode.insertBefore(g,s);
})();
document.write("<noscript><p><img src='" + TRACKER_CONFIG.remote + "piwik.php?idsite='" + TRACKER_CONFIG.siteId + " style='border:0;' alt='' /></p></noscript>");

var PiwikTracker = {
    EVENT : {
        MENU_CLICK : "点击菜单",
        BUTTON_CLICK : "点击按钮"
    },
    /**
     * @param category 事件分类
     * record.data.id(record.data.text)
     * e.g. "接收上转移(web_105002)"
     *
     * @param action 事件行为
     * record.data.uri
     * e.g. "/ty-tfr-web/load/receptTransferAction!index.action"
     *
     * @param name 事件名字
     *
     * e.g. 点击菜单
     *
     * @param value 事件值
     *
     *
     */
    record : function(category, action, name, value) {
        var tracker = Piwik.getAsyncTracker();
        tracker.setTrackerUrl(TRACKER_CONFIG.remote + 'piwik.php');
        tracker.setSiteId(TRACKER_CONFIG.siteId);
        tracker.setUserId(TRACKER_CONFIG.visitor);
        tracker.setDomains([TRACKER_CONFIG.domain]);
        tracker.trackEvent(category, action, name, value);
    }
}