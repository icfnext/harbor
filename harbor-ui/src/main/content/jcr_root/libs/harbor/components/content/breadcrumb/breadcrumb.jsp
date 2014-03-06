<%@include file="/libs/harbor/components/global.jsp" %>
<ct:component className="com.citytechinc.cq.harbor.components.content.breadcrumb.Breadcrumb" name="breadcrumb"/>
<ol class="harbor-breadcrumb breadcrumb">
    <c:forEach var="curBreadcrumbItem" items="${breadcrumb.trail}" varStatus="loop">
        <c:set var='href' value="${curBreadcrumbItem.href}"></c:set>
        <c:set var='iconDelimiter' value="${curBreadcrumbItem.iconDelimiter}"></c:set>
        <c:set var='htmlDelimiter'
               value="<span class='breadcrumb-html-delimiter'>${curBreadcrumbItem.htmlDelimiter}</span>"></c:set>
        <c:set var='title' value="${curBreadcrumbItem.title}"></c:set>
        <c:set var='isDelimiterTypeIcon' value="${curBreadcrumbItem.delimiterType == true}"></c:set>
        <li>
            <a href="${href}">
                ${!curBreadcrumbItem.hideIcon && !isDelimiterTypeIcon ? htmlDelimiter : ''}
                <i class="fa ${!curBreadcrumbItem.hideIcon && isDelimiterTypeIcon ? iconDelimiter : ''}"></i>${curBreadcrumbItem.hideTitle ?  '' : title}
            </a>
        </li>
    </c:forEach>
</ol>
