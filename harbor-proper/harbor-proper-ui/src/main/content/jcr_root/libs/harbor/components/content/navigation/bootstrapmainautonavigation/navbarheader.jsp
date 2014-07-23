<%@include file="/libs/harbor/components/global.jsp" %>

<div class="navbar-header">
    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#${bootstrapMainAutoNavigation.id}">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
    </button>
    <c:if test="${bootstrapMainAutoNavigation.root.showBrandLink}">
        <a class="navbar-brand" href="${bootstrapMainAutoNavigation.root.href}">${bootstrapMainAutoNavigation.root.brandLinkText}</a>
    </c:if>
</div>