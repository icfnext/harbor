<%@include file="/apps/harbor/components/global.jsp" %>

<div <c:if test="${!isEditMode}">class="${responsiveContainer.responsiveClasses}"</c:if> <c:if test="${isEditMode}">style="position : relative;"</c:if>>
    <cq:include path="container-par" resourceType="foundation/components/parsys" />
    <c:if test="${isEditMode && responsiveContainer.hiddenInSomeDevices}">
        <div class="${responsiveContainer.inverseResponsiveClasses} harbor-responsive-container-overlay"></div>
    </c:if>
</div>