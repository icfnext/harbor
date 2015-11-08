<%@include file="/libs/harbor/components/global.jsp"%>
<c:if test="${!isPublish && !isPreviewMode}">
    <div class="author-help-message">
        <p>${contentContainer.authorHelpMessage}</p>
    </div>
</c:if>
<cq:include path="container-par" resourceType="foundation/components/parsys" />