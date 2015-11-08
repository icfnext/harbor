<%@include file="/libs/harbor/components/global.jsp"%>

<c:choose>
    <c:when test="${contentContainer.section}">
        <section <c:if test="${contentContainer.classification.hasClassifications}">class="${contentContainer.sectionClass}"</c:if> <c:if test="${contentContainer.hasDomId}">id="${contentContainer.domId}"</c:if>>
            <${contentContainer.containerElement} class="${contentContainer.containerClass}" <c:if test="${contentContainer.hasRole}">data-role="${contentContainer.role}"</c:if>>
                <cq:include script="innercontentcontainer.jsp"/>
            </${contentContainer.containerElement}>
        </section>
    </c:when>
    <c:otherwise>
        <${contentContainer.containerElement} class="${contentContainer.containerClass}" <c:if test="${contentContainer.hasRole}">data-role="${contentContainer.role}"</c:if> <c:if test="${contentContainer.hasDomId}">id="${contentContainer.domId}"</c:if>>
            <cq:include script="innercontentcontainer.jsp"/>
        </${contentContainer.containerElement}>
    </c:otherwise>
</c:choose>
