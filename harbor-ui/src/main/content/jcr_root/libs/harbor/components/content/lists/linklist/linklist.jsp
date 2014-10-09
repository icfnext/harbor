<%@include file="/libs/harbor/components/global.jsp" %>

<ul <c:if test="${linklist.classification.hasClassification}">class="${linklist.classification.classificationName}"</c:if>>
    <c:forEach var="currentListableLink" items="${linklist.links}">
        <cq:include path="${currentListableLink.path}" resourceType="${currentListableLink.resource.resourceType}" />
    </c:forEach>
</ul>