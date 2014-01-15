<%@include file="/libs/harbor/components/global.jsp"%>
<ct:component className="com.citytechinc.cq.harbor.components.content.demobreadcrumb.DemoBreadcrumb" name="breadcrumb"/>

<ul class="harbor-breadcrumb">
  <c:forEach var="curBreadcrumbItem" items="${breadcrumb.trail}">
    <li><a href="${curBreadcrumbItem.href}"><c:out value="${curBreadcrumbItem.path}" /> : <c:out value="${curBreadcrumbItem.title}" /></a></li>
  </c:forEach>
</ul>