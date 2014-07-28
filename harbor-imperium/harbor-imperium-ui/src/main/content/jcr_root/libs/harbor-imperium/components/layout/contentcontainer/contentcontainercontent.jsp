<%@include file="/libs/harbor-imperium/components/global.jsp"%>

<${contentContainer.containerElement} class="${contentContainer.containerClass} clearfix" <c:if test="${contentContainer.hasRole}">data-role="${contentContainer.role}"</c:if>>
    <cq:include path="container-content" resourceType="imperium/components/layout/layoutcontainer" />
</${contentContainer.containerElement}>
