<%@include file="/libs/harbor/components/global.jsp" %>

<div <c:choose><c:when test="${bootstrapMainAutoNavigation.root.isSticky}">class="navbar navbar-default navbar-fixed-top"</c:when><c:otherwise>class="navbar navbar-default"</c:otherwise></c:choose> role="navigation">
    <c:if test="${bootstrapMainAutoNavigation.hasRoot}">
        <div class="container-fluid">
            <cq:include script="navbarheader.jsp" />
            <cq:include script="navbarcontent.jsp" />
        </div>
    </c:if>
</div>