<%@include file="/libs/harbor/components/global.jsp" %>
<ct:component className="com.citytechinc.cq.harbor.components.content.breadcrumb.Breadcrumb" name="breadcrumb"/>
<ol class="harbor-breadcrumb breadcrumb">
    <c:forEach var="curBreadcrumbItem" items="${breadcrumb.trail}" varStatus="loop">
        <c:set var='href' value="${curBreadcrumbItem.href}"></c:set>
        <c:set var='title' value="${curBreadcrumbItem.title}"></c:set>
        <li> ${loop.first ? '' : breadcrumb.delimiter}
            <a href="${href}">
                <i class="fa ${curBreadcrumbItem.hideIcon ? '' : curBreadcrumbItem.pageIcon}"></i>${curBreadcrumbItem.hideTitle ?  '' : title}
            </a>
        </li>
    </c:forEach>
</ol>
