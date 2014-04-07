<%@include file="/libs/harbor/components/global.jsp" %>
<%--
    This resource rendering mechanism is not intended to be instantiated or used directly
    but should be extended by extending the com.citytechinc.cq.harbor.components.content.list.AbstractListComponent
    creating a concrete list implementation and indicating in said implementation that the resource super type of
    the component should be com.citytechinc.cq.harbor.components.content.list.AbstractListComponent.RESOURCE_TYPE and
    that the component should be auto instantiated under the name 'listComponent' using the
    com.citytechinc.cq.library.components.annotations.AutoInstantiate annotation.
--%>
<c:if test="${listComponent.hasListElement}"><${listComponent.listElement} <c:if test="${listComponent.isOrderedList}"><c:if test="${listComponent.hasStart}">start="${listComponent.start}"</c:if> <c:if test="${listComponent.isReversed}">reversed="true"</c:if></c:if>></c:if>

<c:choose>

    <c:when test="${listComponent.isHtmlList}">

        <ctharbor:includeListItems items="${listComponent.renderableListItems}" script="/libs/harbor/components/content/lists/abstractlist/htmllist.jsp" />

    </c:when>
    <c:otherwise>

        <ctharbor:includeListItems items="${listComponent.renderableListItems}" script="/libs/harbor/components/content/lists/abstractlist/nonhtmllist.jsp" />

    </c:otherwise>

</c:choose>

<c:if test="${listComponent.hasListElement}"></${listComponent.listElement}></c:if>
