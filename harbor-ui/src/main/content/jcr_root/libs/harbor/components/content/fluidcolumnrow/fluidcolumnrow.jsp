<%@include file="/libs/harbor/components/global.jsp"%>

<ct:component className="com.citytechinc.cq.harbor.components.content.columns.FluidColumnRow" name="fluidColumnRow"/>


<c:set var="curfluidrow" scope="page" value="${fluidColumnRow}" />

<div class="row<c:if test="${fluidColumnRow.isFluid}">-fluid</c:if>" id="${curfluidrow.uniqueId}-column-row">
<c:forEach var="curcolumn" items="${curfluidrow.columns}" varStatus="status">
    <div class="${curcolumn.spanClass} ${curcolumn.offsetClass} fluidColumn" name="${curcolumn.name}">
        <div>
            <c:if test="${fluidcolumnrow.isFluid}">
                <cq:include path="${curcolumn.name}" resourceType="harbor/components/content/fluidcolumn" />
            </c:if>
            <c:if test="${not fluidcolumnrow.isFluid}">
                <cq:include path="${curcolumn.name}" resourceType="harbor/components/content/column" />
            </c:if>
        </div>
    </div>
</c:forEach>
</div>