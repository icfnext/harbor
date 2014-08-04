<%@include file="/libs/harbor/components/global.jsp"%>

<${contentContainer.containerElement} class="${contentContainer.containerClass}" <c:if test="${contentContainer.hasRole}">data-role="${contentContainer.role}"</c:if>>
    <cq:include path="container-par" resourceType="foundation/components/parsys" />
</${contentContainer.containerElement}>