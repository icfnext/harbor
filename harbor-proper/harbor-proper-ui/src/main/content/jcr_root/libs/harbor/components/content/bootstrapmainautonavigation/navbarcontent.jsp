<%@include file="/libs/harbor/components/global.jsp" %>

<div class="navbar-collapse collapse" id="${bootstrapMainAutoNavigation.id}">
    <ul class="nav navbar-nav">
        <c:forEach var="currentNode" items="${bootstrapMainAutoNavigation.root.itemsIterator}" varStatus="status">
            <li><a href="${currentNode.href}">${currentNode.navigationTitle}</a></li>
        </c:forEach>
    </ul>
</div>