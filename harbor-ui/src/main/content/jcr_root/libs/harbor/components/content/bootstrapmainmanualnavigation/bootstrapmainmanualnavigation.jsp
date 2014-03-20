<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="/libs/harbor/components/global.jsp" %>


<c:choose>
    <c:when test="${bootstrapMainManualNavigation.stickyNavigationEnabled}">
        <div class="navbar navbar-default navbar-fixed-top" role="navigation">
    </c:when>
    <c:otherwise>
        <div class="navbar navbar-default" role="navigation">
    </c:otherwise>
</c:choose>
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
        </div>

        <div class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <%--li is inserted due to CQ's insertion of divs for editing the nav elements. This breaks the navbar.--%>
                <c:forEach var="currentElement" items="${bootstrapMainManualNavigation.bootstrapMainNavigationElementList}" varStatus="status">
                    <li <c:if test="${currentElement.hasDropdown}">class="dropdown"</c:if>>
                         <cq:include path="${currentElement.name}" resourceType="harbor/components/content/bootstrapmainnavigationelement" />
                    </li>
                </c:forEach>
            </ul>
        </div>
    </div>
</div>