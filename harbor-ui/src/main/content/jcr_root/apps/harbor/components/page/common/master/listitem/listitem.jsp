<%@include file="/apps/harbor/components/global.jsp"%>
<aem-library:component className="com.icfolson.aem.harbor.components.page.master.listitem.PageListItem" name="pageListItem"/>

<c:out value="${pageListItem.listItemTitle}" />
<c:if test="${not empty pageListItem.page.description}">
    <p>
        <c:out value="${pageListItem.page.description}" />
    </p>
</c:if>
