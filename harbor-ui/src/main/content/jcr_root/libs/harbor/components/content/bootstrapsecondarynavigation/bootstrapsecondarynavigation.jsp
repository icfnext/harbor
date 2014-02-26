<%@include file="/libs/harbor/components/global.jsp" %>

<c:if test="${secondaryNavigation.hasRootNode}">
    <%--    ${treeComponent.renderableRootNode.renderedTreeNodeValue}--%>
    ${secondaryNavigation.renderableRootNode.renderedTreeNodeChildren}
</c:if>