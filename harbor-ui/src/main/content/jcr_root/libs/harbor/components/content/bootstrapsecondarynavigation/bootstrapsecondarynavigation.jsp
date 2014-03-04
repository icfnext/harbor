<%@include file="/libs/harbor/components/global.jsp" %>

<c:if test="${secondaryNavigation.hasRootNode}">
   <%-- ${secondaryNavigation.renderableRootNode.renderedTreeNodeValue}--%>
    ${secondaryNavigation.renderableRootNode.renderedTreeNodeChildren}

<%--    <c:forEach var="currentNode" items="${secondaryNavigation.rootChildrenAsRenderable}" varStatus="status">
        ${currentNode.renderedTreeNodeValue}
    </c:forEach>--%>
</c:if>