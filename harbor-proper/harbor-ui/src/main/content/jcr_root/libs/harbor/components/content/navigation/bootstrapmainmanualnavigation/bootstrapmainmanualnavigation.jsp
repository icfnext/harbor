<%@include file="/libs/harbor/components/global.jsp" %>

<c:choose>
    <c:when test="${bootstrapMainManualNavigation.isSticky}">
        <div class="navbar navbar-default navbar-fixed-top" role="navigation">
    </c:when>
    <c:otherwise>
        <div class="navbar navbar-default" role="navigation">
    </c:otherwise>
</c:choose>
    <div class="container-fluid">
        <cq:include script="navbarheader.jsp" />
        <cq:include script="navbarcontent.jsp" />
    </div>
</div>