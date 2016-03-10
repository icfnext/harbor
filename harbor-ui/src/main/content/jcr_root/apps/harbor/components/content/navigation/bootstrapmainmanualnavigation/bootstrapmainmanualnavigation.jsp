<%@include file="/apps/harbor/components/global.jsp" %>

<c:set var="noStickAuthor" value="false" />
<c:if test="${isAuthor && isEditMode}">
    <c:set var="noStickAuthor" value="true" />
</c:if>



<c:choose>
    <c:when test="${bootstrapMainManualNavigation.isSticky && !noStickAuthor}">
        <div class="navbar navbar-default navbar-fixed-top" role="navigation">
    </c:when>
    <c:otherwise>
        <div class="navbar navbar-default" role="navigation">
    </c:otherwise>
</c:choose>
    <div class="${bootstrapMainManualNavigation.tree.containerClass}">
        <cq:include script="navbarheader.jsp" />
        <cq:include script="navbarcontent.jsp" />
    </div>
</div>