<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="/libs/harbor/components/global.jsp"%>

<ct:component className="com.citytechinc.cq.harbor.components.content.columns.FluidColumnRow" name="fluidColumnRow"/>

<c:set var="curfluidrow" scope="page" value="${fluidColumnRow}" />

<div class = "row" id="${curfluidrow.uniqueId}-column-row">
    <c:forEach var="curcolumn" items="${curfluidrow.columns}" varStatus="status">
        <!-- Inserts the offset class if there is one. Avoids inserting incomplete offset-->
        <c:choose>
            <c:when test="${empty curcolumn.offsetClass}">
                <div class="${fluidColumnRow.gridSize}${curcolumn.colClass} fluidColumn" name="${curcolumn.name}">
            </c:when>
            <c:otherwise>
                <div class="${fluidColumnRow.gridSize}${curcolumn.colClass} ${fluidColumnRow.gridSize}${curcolumn.offsetClass} fluidColumn" name="${curcolumn.name}">
            </c:otherwise>
        </c:choose>
                <div>
                    <cq:include path="${curcolumn.name}" resourceType="harbor/components/content/fluidcolumn" />
                </div>
            </div>
    </c:forEach>
</div>
