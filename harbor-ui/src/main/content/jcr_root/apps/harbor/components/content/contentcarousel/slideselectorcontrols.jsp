<%@include file="/apps/harbor/components/global.jsp" %>

<ol class="${carousel.indicatorsCssClass}">
    <c:forEach var="currentSlide" items="${carousel.slides}" varStatus="status">
        <c:choose>
            <c:when test="${status.first}">
                <li data-target="#${carousel.uniqueIdentifier}-carousel" data-slide-to="${status.index}" class="active"></li>
            </c:when>
            <c:otherwise>
                <li data-target="#${carousel.uniqueIdentifier}-carousel" data-slide-to="${status.index}"></li>
            </c:otherwise>
        </c:choose>
    </c:forEach>
</ol>