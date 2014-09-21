<%@include file="/libs/harbor/components/global.jsp" %>

<div class="navbar-header">
    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#${bootstrapMainManualNavigation.id}">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
    </button>
    <c:if test="${bootstrapMainManualNavigation.showBrandLink}">
        <a class="navbar-brand" href="${bootstrapMainManualNavigation.brandLink.href}">${bootstrapMainManualNavigation.brandLinkText}<c:if test="${bootstrapMainManualNavigation.hasBrandLinkImage}"><img src="${bootstrapMainAutoNavigation.brandLinkImage}"/></c:if></a>
    </c:if>
</div>