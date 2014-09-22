<%@include file="/libs/harbor/components/global.jsp" %>

<li <c:if test="${currentNavigationItem.navigationLink.active}">class="active"</c:if>>
    <a href="${currentNavigationItem.navigationLink.href}">${currentNavigationItem.navigationLink.title}</a>
    <c:if test="${currentNavigationItem.navigationLink.active && currentNavigationItem.hasChildNodes}">
        <ul class="nav nav-pills nav-stacked">
            <harbor:includeListItems items="${currentNavigationItem.childNodes}" itemVar="currentNavigationItem" script="navigationitem.jsp" />
        </ul>
    </c:if>
</li>