<%@include file="/libs/harbor/components/global.jsp" %>
<ct:component className="com.citytechinc.cq.harbor.components.content.breadcrumb.Breadcrumb" name="breadcrumb"/>

<ol class="harbor-breadcrumb breadcrumb">
    <c:forEach var="curBreadcrumbItem" items="${breadcrumb.trail}" varStatus="loop">
        <c:set var='href' value="${curBreadcrumbItem.href}"></c:set>
        <c:set var='iconDelimiter' value="${breadcrumb.delimiterIcon}"></c:set>
        <c:set var='htmlDelimiter' value="<span class='breadcrumb-html-delimiter'>${breadcrumb.delimiterHtml}</span>"></c:set>
        <c:set var='title' value="${curBreadcrumbItem.title}"></c:set>
        <c:set var='delimiterTypeIcon' value="${breadcrumb.delimiterType == 'fontawesomeicon'}"></c:set>
        <li>${!curBreadcrumbItem.hideIcon && !delimiterTypeIcon ? htmlDelimiter : ''}<a href="${href}"><i
                class="fa ${!curBreadcrumbItem.hideIcon && delimiterTypeIcon ? iconDelimiter : ''}"></i>${curBreadcrumbItem.hideTitle ?  '' : title}
        </a></li>
    </c:forEach>
</ol>
