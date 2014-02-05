<%@include file="/libs/harbor/components/global.jsp"%>
<ct:component className="com.citytechinc.cq.harbor.components.content.demobreadcrumb.DemoBreadcrumb" name="breadcrumb"/>

<ul class="harbor-breadcrumb">
  <c:forEach var="curBreadcrumbItem" items="${breadcrumb.trail}" varStatus="loop">
    <li ${loop.last ? "class='active'" : ''}><a href="${curBreadcrumbItem.href}">${curBreadcrumbItem.path} : ${curBreadcrumbItem.title}/></a></li>
  </c:forEach>
</ul>