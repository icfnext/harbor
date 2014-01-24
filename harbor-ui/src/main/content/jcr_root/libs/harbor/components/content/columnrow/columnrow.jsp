<%@include file="/libs/harbor/components/global.jsp"%>

<ct:component className="com.citytechinc.cq.harbor.components.content.columns.ColumnRow" name="columnRow"/>

<c:set var="curRow" scope="page" value="${columnRow}" />
<div class="row">
    <c:if test="${curfliudrow.isFullWidth}">
        <div class = "rowContainer">
    </c:if>
    <c:forEach var="curcolumn" items="${curRow.columns}" varStatus="status">
        <div class="${curRow.gridSize}${curcolumn.colClass}">
            <cq:include path="${curcolumn.name}" resourceType="harbor/components/content/column" />
        </div>
    </c:forEach>
    <c:if test="${curfliudrow.isFullWidth}">
        </div>
    </c:if>
</div>
