<%@ page import="com.hoau.hbdp.framework.server.context.UserContext" %>
<%@ page import="java.util.Properties" %>
<%@ page import="java.io.InputStream" %>

<%--Piwik--%>
<script type="text/javascript" src="../scripts/common/plugins/tracker/tracker-config.js"></script>
<script>
    <%
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Properties props = new Properties();
        InputStream resourceStream = loader.getResourceAsStream("config.properties");
        props.load(resourceStream);
        // 网站跟踪
        String trackerDomain = props.getProperty("tracker.domain");
        String trackerRemote = props.getProperty("tracker.remote");
        String trackerSiteId = props.getProperty("tracker.siteid");
    %>
    TRACKER_CONFIG.domain = '<%=trackerDomain%>';
    TRACKER_CONFIG.remote = '<%=trackerRemote%>';
    TRACKER_CONFIG.siteId = '<%=trackerSiteId%>';
    TRACKER_CONFIG.on = true;
    TRACKER_CONFIG.visitor = window.parent.UserContext.getCurrentUser().userName + "-" + window.parent.UserContext.getCurrentUser().empName;
//     TRACKER_CONFIG.visitor = window.parent.UserContext.getCurrentUser().userName + "-" + window.parent.UserContext.getCurrentUserDept().code;
    //    ButterflyUserContext
</script>
<script type="text/javascript" src="../scripts/common/plugins/tracker/tracker-plugin.js"></script>
<script type="text/javascript" src="../scripts/common/plugins/tracker/tracker-ext-ajax-interceptor.js"></script>
<%--End Piwik--%>