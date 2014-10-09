<%@include file="/libs/harbor/components/global.jsp" %>

<div class="carousel-inner">
    <c:forEach var="currentSlide" items="${carousel.slides}" varStatus="status">
        <c:choose>
            <c:when test="${status.first}">
                <div class="item active">
                    <cq:include path="${currentSlide.path}" resourceType="${currentSlide.resource.resourceType}" />
                </div>
            </c:when>
            <c:otherwise>
                <div class="item">
                    <cq:include path="${currentSlide.path}" resourceType="${currentSlide.resource.resourceType}" />
                </div>
            </c:otherwise>
        </c:choose>


    </c:forEach>
</div>