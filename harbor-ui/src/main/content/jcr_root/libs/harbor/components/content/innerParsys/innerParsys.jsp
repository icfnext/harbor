<%@include file="/libs/harbor/components/global.jsp"%>
<%@ page import="com.day.cq.wcm.api.WCMMode" %>

<% WCMMode wcmMode = WCMMode.fromRequest(slingRequest); %>

<c:if test="${isDesignMode}"><%
	WCMMode.DISABLED.toRequest(slingRequest);
%></c:if>
	<cq:include path="innerParsys" resourceType="harbor/components/content/innerParsys/parsys" />
<% wcmMode.toRequest(slingRequest); %>