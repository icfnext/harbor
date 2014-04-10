<%@include file="/libs/harbor/components/global.jsp"%>

<c:set var="curRow" scope="page" value="${ColumnRow}" />
<div class="row" <c:if test="${ColumnRow.classification.hasClassification}">class="${ColumnRow.classification.classificationName}"</c:if>>
    <c:forEach var="curcolumn" items="${curRow.columns}" varStatus="status">
    <c:choose>
    <c:when test="${ColumnRow.classification.hasClassification}">
    <div class="${curRow.gridSize}${curcolumn.colSize} ${curcolumn.classification.classificationName}" name="${curcolumn.name}">
        </c:when>
        <c:otherwise>
        <div class="${curRow.gridSize}${curcolumn.colSize}" name="${curcolumn.name}">
            </c:otherwise>
            </c:choose>
            <cq:include path="${curcolumn.name}" resourceType="harbor/components/content/column" />
        </div>
        </c:forEach>
    </div>