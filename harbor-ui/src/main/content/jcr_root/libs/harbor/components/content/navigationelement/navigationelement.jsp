<%@include file="/libs/harbor/components/global.jsp" %>

<ct:component className="com.citytechinc.cq.harbor.components.content.navigation.globalnavigation.NavigationElement" name="navElement"/>


<c:choose>
    <%-- Insert a dropdown if configured for the nav element --%>
    <c:when test="${navElement.hasDropdown}">
        <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">${navElement.elementTitle}  <b class="caret"></b></a>
            <ul class="dropdown-menu">
                <li><cq:include path="navpar" resourceType="foundation/components/parsys" /></li>
            </ul>
        </li>
    </c:when>
    <c:otherwise>
        <li>
            <a href="${navElement.elementLinkTarget}">${navElement.elementTitle}</a>
        </li>
    </c:otherwise>
</c:choose>