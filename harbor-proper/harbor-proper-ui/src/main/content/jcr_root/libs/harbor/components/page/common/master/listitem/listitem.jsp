<%@include file="/libs/harbor/components/global.jsp"%>
<ct:component className="com.citytechinc.cq.harbor.components.page.master.listitem.PageListItem" name="pageListItem"/>

<c:out value="${pageListItem.listItemTitle}" />
<c:if test="${not empty pageListItem.page.description}">
    <p>
        <c:out value="${pageListItem.page.description}" />
    </p>
</c:if>
