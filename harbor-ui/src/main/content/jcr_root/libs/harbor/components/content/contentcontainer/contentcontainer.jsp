<%@include file="/libs/harbor/components/global.jsp"%>
<ct:component className="com.citytechinc.cq.harbor.components.content.container.Container" name="contentContainer"/>

<${contentContainer.containerElement} class="${contentContainer.containerClass}" <c:if test="${contentContainer.hasRole}">data-role="${contentContainer.role}"</c:if>>
    <cq:include path="container-par" resourceType="foundation/components/parsys" />
</${contentContainer.containerElement}>