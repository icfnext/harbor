<%@include file="/libs/harbor/components/global.jsp"%>
<ct:component className="com.citytechinc.cq.harbor.components.content.demobreadcrumb.DemoBreadcrumb" name="breadcrumb"/>

<ol class="harbor-breadcrumb breadcrumb">
    <c:forEach var="curBreadcrumbItem" items="${breadcrumb.trail}" varStatus="loop">
        <c:choose>
            <c:when test="${!loop.last}">
                <li><a href="${curBreadcrumbItem.href}">${curBreadcrumbItem.title}</a></li>
            </c:when>
            <c:otherwise>
                <li class="active">${curBreadcrumbItem.title}</li>
            </c:otherwise>
        </c:choose>
    </c:forEach>
</ol>