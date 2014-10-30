<%@include file="/libs/harbor/components/global.jsp"%>

<c:set var="curRow" scope="page" value="${ColumnRow}" />
<div class="${ColumnRow.cssClass}">
    <c:forEach var="curcolumn" items="${curRow.columns}" varStatus="status">
        <c:choose>
            <c:when test="${curcolumn.classification.hasClassification}">
                <div class="${curRow.gridSize}${curcolumn.colSize} ${curcolumn.classification.classificationName}" name="${curcolumn.name}">
                    <cq:include path="${curcolumn.name}" resourceType="harbor/components/content/column" />
                </div>
            </c:when>
            <c:otherwise>
                <div class="${curRow.gridSize}${curcolumn.colSize}" name="${curcolumn.name}">
                    <cq:include path="${curcolumn.name}" resourceType="harbor/components/content/column" />
                </div>
            </c:otherwise>
        </c:choose>
    </c:forEach>
</div>