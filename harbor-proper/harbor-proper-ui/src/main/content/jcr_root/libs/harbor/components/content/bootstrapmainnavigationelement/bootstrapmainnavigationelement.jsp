<%@include file="/libs/harbor/components/global.jsp" %>

<c:choose>
    <%-- Insert a dropdown if configured for the nav element --%>
    <c:when test="${navElement.hasDropdown}">
        <a href="#" class="dropdown-toggle" data-toggle="dropdown">${navElement.elementTitle}  <b class="caret"></b></a>
        <ul class="dropdown-menu">
            <li><cq:include path="navpar" resourceType="foundation/components/parsys" /></li>
        </ul>
    </c:when>
    <c:otherwise>
            <a href="${navElement.elementLinkTarget}">${navElement.elementTitle}</a>
    </c:otherwise>
</c:choose>