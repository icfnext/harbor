<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="/libs/harbor/components/global.jsp"%>

<ct:component className="com.citytechinc.cq.harbor.components.content.columns.ColumnRow" name="ColumnRow"/>

<c:set var="curRow" scope="page" value="${ColumnRow}" />
<div class="row" <c:if test="${ColumnRow.classification.hasClassification}">class="${ColumnRow.classification.classificationName}"</c:if>>
    <c:forEach var="curcolumn" items="${curRow.columns}" varStatus="status">
        <c:choose>
            <c:when test="${ColumnRow.classification.hasClassification}">
                <div class="${curRow.gridSize}${curcolumn.colClass} ${curcolumn.classification.classificationName}" name="${curcolumn.name}">
                    <cq:include path="${curcolumn.name}" resourceType="harbor/components/content/column" />
                </div>
            </c:when>
            <c:otherwise>
                <div class="${curRow.gridSize}${curcolumn.colClass}" name="${curcolumn.name}">
                    <cq:include path="${curcolumn.name}" resourceType="harbor/components/content/column" />
                </div>
            </c:otherwise>
        </c:choose>
    </c:forEach>
</div>