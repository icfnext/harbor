<%@include file="/libs/harbor/components/global.jsp" %>

<div class="navbar-header">
    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#${bootstrapMainAutoNavigation.id}">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
    </button>
    <c:if test="${bootstrapMainAutoNavigation.tree.showBrandLink}">
        <a class="navbar-brand" href="${bootstrapMainAutoNavigation.tree.href}">${bootstrapMainAutoNavigation.tree.brandLinkText}<c:if test="${bootstrapMainAutoNavigation.tree.hasBrandLinkImage}"><img src="${bootstrapMainAutoNavigation.tree.brandLinkImage}"/></c:if></a>
    </c:if>
</div>