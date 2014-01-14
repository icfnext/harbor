<%@include file="/libs/harbor/components/global.jsp"%>
<ct:component className="com.citytechinc.cq.harbor.components.content.demobreadcrumb.DemoBreadcrumb" name="breadcrumb"/>

<ul class="harbor-breadcrumb">
  <c:forEach var="curBreadcrumbItem" items="${breadcrumb.trail}">
    <li><c:out value="${curBreadcrumbItem.navigationTitle}" /></li>
  </c:forEach>
</ul>