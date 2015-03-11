<%@include file="/libs/harbor/components/global.jsp" %>
<bedrock:component className="com.citytechinc.aem.designmanager.core.components.page.design.DesignedPage" name="designedPage" />
<c:if test="${designedPage.hasDesign}">
    <cq:includeClientLib categories="${designedPage.designClientLibraryCategory}"/>
</c:if>