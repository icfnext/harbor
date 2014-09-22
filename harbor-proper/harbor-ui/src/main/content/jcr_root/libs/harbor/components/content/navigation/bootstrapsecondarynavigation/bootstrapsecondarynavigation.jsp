<%@include file="/libs/harbor/components/global.jsp" %>

<c:if test="${secondaryNavigation.hasRoot && secondaryNavigation.tree.root.hasChildNodes}">
    <ul class="nav nav-pills nav-stacked">
        <harbor:includeListItems items="${secondaryNavigation.tree.root.childNodes}" itemVar="currentNavigationItem" script="navigationitem.jsp" />
    </ul>
</c:if>