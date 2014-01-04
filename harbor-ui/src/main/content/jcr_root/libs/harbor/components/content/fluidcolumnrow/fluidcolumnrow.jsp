<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="/libs/harbor/components/global.jsp"%>

<ct:component className="com.citytechinc.cq.harbor.components.content.columns.FluidColumnRow" name="fluidColumnRow"/>

<c:set var="curfluidrow" scope="page" value="${fluidColumnRow}" />

<div class = "row ${curfluidrow.classification.classificationName}" id="${curfluidrow.uniqueId}-column-row">
    <div class = "rowContainer" style="${curfluidrow.rowWidthPadding}">
    <c:forEach var="curcolumn" items="${curfluidrow.columns}" varStatus="status">
        <!-- Inserts the offset class if there is one. Avoids inserting incomplete offset-->
            <div class="${curfluidrow.gridSize}${curcolumn.colClass} fluidColumn" name="${curcolumn.name}">
            <div>
                <cq:include path="${curcolumn.name}" resourceType="harbor/components/content/fluidcolumn" />
            </div>
        </div>
    </c:forEach>
    </div>
</div>
