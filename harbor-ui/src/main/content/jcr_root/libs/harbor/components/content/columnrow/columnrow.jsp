<%@include file="/libs/harbor/components/global.jsp"%>

<ct:component className="com.citytechinc.cq.harbor.components.content.columns.ColumnRow" name="ColumnRow"/>

<c:set var="curRow" scope="page" value="${ColumnRow}" />
<div class="row" <c:if test="${ColumnRow.classification.hasClassification}">class="${ColumnRow.classification.classificationName}"</c:if>>

<c:set var="curRow" scope="page" value="${columnRow}" />
<div class="row">
    <c:forEach var="curcolumn" items="${curRow.columns}" varStatus="status">
            <div class="${curRow.gridSize}${curcolumn.colClass} ${curcolumn.classification.classificationName}" name="${curcolumn.name}">
            <div>
                <cq:include path="${curcolumn.name}" resourceType="harbor/components/content/column" />
            </div>
        </div>
    </c:forEach>
</div>
