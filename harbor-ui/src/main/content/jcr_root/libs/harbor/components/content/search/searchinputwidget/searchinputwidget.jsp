<%@include file="/libs/harbor/components/global.jsp" %>

<c:choose>
    <c:when test="${searchinputwidget.allowInPlaceSearch}">
        <form data-form-purpose="search" data-submission-type="${searchinputwidget.submissionType}" action="${searchinputwidget.searchPageHref}">

            <input type="text" name="query">
            <button type="submit"><c:out value="${searchinputwidget.searchButtonText}" escapeXml="false" /></button>

        </form>
    </c:when>
    <c:otherwise>
        <a data-button-purpose="search" href="${searchinputwidget.searchPageHref}"><c:out value="${searchinputwidget.searchButtonText}" escapeXml="false" /></a>
    </c:otherwise>
</c:choose>