<%@include file="/libs/harbor/components/global.jsp" %>

<c:if test="${treeComponent.hasRootNode}">
<%--    ${treeComponent.renderableRootNode.renderedTreeNodeValue}--%>
    ${treeComponent.renderableRootNode.renderedTreeNodeChildren}
</c:if>