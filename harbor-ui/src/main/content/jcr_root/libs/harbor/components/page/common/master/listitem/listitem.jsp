<%@include file="/libs/harbor/components/global.jsp"%>
<bedrock:component className="com.citytechinc.aem.harbor.components.page.master.listitem.PageListItem" name="pageListItem"/>

<c:out value="${pageListItem.listItemTitle}" />
<c:if test="${not empty pageListItem.page.description}">
    <p>
        <c:out value="${pageListItem.page.description}" />
    </p>
</c:if>
