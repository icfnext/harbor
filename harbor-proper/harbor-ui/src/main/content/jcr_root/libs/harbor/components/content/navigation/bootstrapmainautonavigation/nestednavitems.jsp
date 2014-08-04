<li><a href="${nestedNavigablePage.href}">${nestedNavigablePage.navigationLink.title}</a>
    <c:if test="${nestedNavigablePage.hasItems}">
        <harbor:includeListItems items="${nestedNavigablePage.items}" itemVar="nestedNavigablePage" script="nestednavitems.jsp"/>
    </c:if>
</li>