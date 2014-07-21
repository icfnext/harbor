<%@include file="/libs/harbor-imperium/components/global.jsp"%>

<c:choose>
    <c:when test="${contentContainer.isLayoutMode}">
        <div class="imperium-layout-section-container">
            <span class="imperium-layout-section-name">
                ${contentContainer.containerName} <c:if test="${contentContainer.hasRole}">${contentContainer.role}</c:if>
            </span>
            <cq:include script="contentcontainercontent.jsp" />
        </div>
    </c:when>
    <c:otherwise>
        <cq:include script="contentcontainercontent.jsp" />
    </c:otherwise>
</c:choose>
