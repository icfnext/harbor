<%@include file="/apps/harbor/components/global.jsp" %>
<c:forEach items="${breadcrumb.iterator}" var="curBreadcrumbItem" varStatus="status">
    <c:if test="${!status.first}">
        <c:if test="${breadcrumb.items.hasHtmlDelimiter}">
            ${breadcrumb.items.htmlDelimiter}
        </c:if>
        <c:if test="${breadcrumb.items.hasIconDelimiter}">
            <i class='fa ${breadcrumb.items.iconDelimiter}'></i>
        </c:if>
    </c:if>
    <c:if test="${breadcrumb.items.renderAsLink}">
        <a href="${curBreadcrumbItem.href}">
    </c:if>
    <c:if test="${!curBreadcrumbItem.hideIcon}">
        ${curBreadcrumbItem.pageIcon}
    </c:if>
    <c:if test="${!curBreadcrumbItem.hideTitle}">
        ${curBreadcrumbItem.title}
    </c:if>
    <c:if test="${breadcrumb.items.renderAsLink}">
        </a>
    </c:if>
</c:forEach>
