<%@include file="/libs/harbor/components/global.jsp"%>
<!-- TODO: is this import acceptable? Should this code be refactored? The parsys node under this folder is a little questionable. -->
<%@ page import="com.day.cq.wcm.api.WCMMode" %>

<% WCMMode wcmMode = WCMMode.fromRequest(slingRequest); %>

<c:if test="${isDesignMode}"><%
	WCMMode.DISABLED.toRequest(slingRequest);
%></c:if>
	<cq:include path="innerParsys" resourceType="harbor/components/content/innerParsys/parsys" />
<% wcmMode.toRequest(slingRequest); %>