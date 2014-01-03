<%@include file="/libs/harbor/components/global.jsp" %>

<ct:component className="com.citytechinc.cq.harbor.components.content.columns.FluidColumn" name="fluidColumn"/>

<c:choose>
    <c:when test="${fluidColumn.isInherited}">
        <cq:include path="fluid-column-par-inherited" resourceType="foundation/components/iparsys" />
    </c:when>
    <c:otherwise>
        <cq:include path="fluid-column-par" resourceType="foundation/components/parsys" />
    </c:otherwise>

</c:choose>