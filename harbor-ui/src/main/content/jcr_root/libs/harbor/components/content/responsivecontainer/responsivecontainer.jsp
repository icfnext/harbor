<%@include file="/libs/harbor/components/global.jsp" %>
<ct:component className="com.citytechinc.cq.harbor.components.content.responsivecontainer.ResponsiveContainer" name="responsiveContainer"/>

<div <c:if test="${!isEditMode}">class="${responsiveContainer.responsiveClasses}"</c:if> <c:if test="${isEditMode}">style="position : relative;"</c:if>>
    <cq:include path="container-par" resourceType="foundation/components/parsys" />
    <c:if test="${isEditMode && responsiveContainer.hiddenInSomeDevices}">
        <div class="${responsiveContainer.inverseResponsiveClasses} harbor-responsive-container-overlay"></div>
    </c:if>
</div>