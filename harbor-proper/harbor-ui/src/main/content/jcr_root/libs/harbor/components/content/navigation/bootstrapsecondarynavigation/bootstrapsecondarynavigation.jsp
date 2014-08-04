<%@include file="/libs/harbor/components/global.jsp" %>

<c:if test="${secondaryNavigation.hasRoot && secondaryNavigation.root.hasItems}">
    <ul class="nav nav-pills nav-stacked">
        <harbor:includeListItems items="${secondaryNavigation.root.items}" itemVar="currentNavigationItem" script="navigationitem.jsp" />
    </ul>
</c:if>