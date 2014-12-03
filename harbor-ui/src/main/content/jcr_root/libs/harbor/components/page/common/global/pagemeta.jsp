<%@include file="/libs/harbor/components/global.jsp" %>
<bedrock:component className="com.citytechinc.aem.harbor.core.components.content.page.meta.MetaPage" name="metaPage" />

<c:if test="${metaPage.hiddenFromRobots}">
    <meta name="robots" content="noindex">
</c:if>