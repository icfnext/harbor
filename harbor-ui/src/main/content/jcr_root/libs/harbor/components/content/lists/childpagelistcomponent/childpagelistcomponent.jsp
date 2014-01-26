<%@include file="/libs/harbor/components/global.jsp"%>
<ct:component className="com.citytechinc.cq.harbor.components.content.list.page.ChildPageListComponent" name="childPageList"/>

<ul>
    <c:forEach items="${childPageList.content}" var="curPage">
        <li><sling:include path="${curPage.contentResource.path}" resourceType="${curPage.contentResource.resourceType}/listitem" addSelectors="listitemview" /></li>
    </c:forEach>
</ul>