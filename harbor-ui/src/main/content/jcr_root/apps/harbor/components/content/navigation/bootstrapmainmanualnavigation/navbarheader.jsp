<%@include file="/apps/harbor/components/global.jsp" %>

<div class="navbar-header">
    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#${bootstrapMainManualNavigation.id}">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
    </button>
    <c:if test="${bootstrapMainManualNavigation.showBrandLink}">
        <a class="navbar-brand" href="${bootstrapMainManualNavigation.brandLink.href}">
            <c:choose>
                <c:when test="${bootstrapMainManualNavigation.hasBrandLinkImage}">
                    <img src="${bootstrapMainManualNavigation.brandLinkImageSrc}" title="${bootstrapMainManualNavigation.brandLinkText}"/>
                </c:when>
                <c:otherwise>
                    ${bootstrapMainManualNavigation.brandLinkText}
                </c:otherwise>
            </c:choose>
        </a>
    </c:if>
</div>