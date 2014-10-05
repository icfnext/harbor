<%@include file="/libs/harbor-imperium/components/global.jsp"%>

<c:choose>
    <c:when test="${contentContainer.section}">
        <section <c:if test="${contentContainer.classification.hasClassification}">class="${contentContainer.sectionClass}"</c:if>>
            <${contentContainer.containerElement} class="${contentContainer.containerClass} clearfix" <c:if test="${contentContainer.hasRole}">data-role="${contentContainer.role}"</c:if>>
                <cq:include path="container-content" resourceType="imperium/components/layout/layoutcontainer" />
            </${contentContainer.containerElement}>
        </section>
    </c:when>
    <c:otherwise>
        <${contentContainer.containerElement} class="${contentContainer.containerClass} clearfix" <c:if test="${contentContainer.hasRole}">data-role="${contentContainer.role}"</c:if>>
            <cq:include path="container-content" resourceType="imperium/components/layout/layoutcontainer" />
        </${contentContainer.containerElement}>
    </c:otherwise>
</c:choose>

