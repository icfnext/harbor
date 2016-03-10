<%@include file="/apps/harbor/components/global.jsp" %>


    <form action="${searchinputwidget.searchPageHref}">
        <input type="text" name="${searchinputwidget.queryParameterName}">
        <button type="submit"><c:out value="${searchinputwidget.searchButtonText}" escapeXml="false" /></button>
    </form>

