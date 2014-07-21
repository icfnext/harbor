<%@include file="/libs/harbor-imperium/components/global.jsp"%>

<c:choose>
    <c:when test="${column.isLayoutMode}">
        <div class="imperium-layout-section-container">
            <span class="imperium-layout-section-name">
                ${column.resource.name}
            </span>
            <imperium:includeLayoutElement path="column-content" resourceType="imperium/components/layout/layoutcontainer" />
        </div>
    </c:when>
    <c:otherwise>
        <imperium:includeLayoutElement path="column-content" resourceType="imperium/components/layout/layoutcontainer" />
    </c:otherwise>
</c:choose>
