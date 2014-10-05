<%@include file="/libs/harbor/components/global.jsp" %>

<div class="navbar-collapse collapse" id="${bootstrapMainManualNavigation.id}">
    <ul class="nav navbar-nav manual-navigation">
        <%--li is inserted due to CQ's insertion of divs for editing the nav elements. This breaks the navbar.--%>
        <c:forEach var="currentElement" items="${bootstrapMainManualNavigation.bootstrapMainNavigationElementList}" varStatus="status">
            <c:choose>
                <c:when test="${currentElement.hasDropdown}">
                    <cq:include path="${currentElement.name}" resourceType="harbor/components/content/navigation/bootstrapmainmanualnavigation/bootstrapmainnavigationelementwithdropdown" />
                </c:when>
                <c:otherwise>
                    <cq:include path="${currentElement.name}" resourceType="harbor/components/content/navigation/bootstrapmainmanualnavigation/bootstrapmainnavigationelement" />
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </ul>
</div>