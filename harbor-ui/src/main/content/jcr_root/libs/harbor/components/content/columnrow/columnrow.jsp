<%@include file="/libs/harbor/components/global.jsp"%>

<ct:component className="com.citytechinc.cq.harbor.components.content.columns.ColumnRow" name="ColumnRow"/>

<c:set var="curRow" scope="page" value="${ColumnRow}" />

<div class="row" id="${curRow.uniqueId}-column-row">

    <c:if test="${curfliudrow.isFullWidth}">
        <div class = "rowContainer" style="${curRow.rowWidthPadding}">
    </c:if>

    <c:forEach var="curcolumn" items="${curRow.columns}" varStatus="status">
            <div class="${curRow.gridSize}${curcolumn.colClass}" name="${curcolumn.name}">
            <div>
                <cq:include path="${curcolumn.name}" resourceType="harbor/components/content/column" />
            </div>
        </div>
    </c:forEach>
    <c:if test="${curfliudrow.isFullWidth}">
        </div>
    </c:if>
</div>
