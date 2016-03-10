<%@include file="/apps/harbor/components/global.jsp" %>

<ul <c:if test="${linklist.classification.hasClassifications}">class="${linklist.classification.classNames}"</c:if>>
    <c:forEach var="currentListableLink" items="${linklist.links}">
        <cq:include path="${currentListableLink.path}" resourceType="${currentListableLink.resource.resourceType}" />
    </c:forEach>
</ul>