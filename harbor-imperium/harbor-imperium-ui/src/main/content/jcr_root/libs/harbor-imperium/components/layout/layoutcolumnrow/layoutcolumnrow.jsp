<%@include file="/libs/harbor-imperium/components/global.jsp" %>

<c:set var="curRow" scope="page" value="${ColumnRow}" />
<c:if test="${curRow.isLayoutMode}">
    <div class="imperium-layout-section-container">
        <span class="imperium-layout-section-name">
            Layout Column Row
        </span>
</c:if>
<div class="row" <c:if test="${ColumnRow.classification.hasClassification}">class="${ColumnRow.classification.classificationName}"</c:if>>
    <c:forEach var="curcolumn" items="${curRow.columns}" varStatus="status">
        <c:choose>
            <c:when test="${ColumnRow.classification.hasClassification}">
                <div class="${curRow.gridSize}${curcolumn.colClass} ${curcolumn.classification.classificationName}" name="${curcolumn.name}">
            </c:when>
            <c:otherwise>
                <div class="${curRow.gridSize}${curcolumn.colClass}" name="${curcolumn.name}">
            </c:otherwise>
        </c:choose>
            <imperium:includeLayoutElement path="${curcolumn.name}" resourceType="harbor-imperium/components/layout/layoutcolumn" />
        </div>
    </c:forEach>
</div>
<c:if test="${curRow.isLayoutMode}">
    </div>
</c:if>