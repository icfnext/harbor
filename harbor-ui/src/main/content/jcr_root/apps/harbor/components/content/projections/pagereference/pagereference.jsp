<%@include file="/apps/harbor/components/global.jsp" %>

<c:choose>
    <c:when test="${pagereference.page.isArticle}">
        <article>
            <header>
                <c:if test="${pagereference.page.renderTitle}">
                    <c:choose>
                        <c:when test="${pagereference.page.renderAsLink}">
                            <${pagereference.page.titleHeadingType}><a href="${pagereference.page.href}">${pagereference.page.title}</a></${pagereference.page.titleHeadingType}>
                        </c:when>
                        <c:otherwise>
                            <${pagereference.page.titleHeadingType}>${pagereference.page.title}</${pagereference.page.titleHeadingType}>
                        </c:otherwise>
                    </c:choose>
                </c:if>
                <c:if test="${pagereference.page.renderSubtitle}"><p class="subtitle">${pagereference.page.subtitle}</p></c:if>
            </header>
            <c:if test="${pagereference.page.renderImage}"><img src="${pagereference.page.imageSource}" /></c:if>
            <c:if test="${pagereference.page.renderDescription}"><p>${pagereference.page.description}</p></c:if>
        </article>
    </c:when>
    <c:otherwise>
        <c:choose>
            <c:when test="${pagereference.page.renderTitle}">
                <c:choose>
                    <c:when test="${pagereference.page.renderAsLink}">
                        <${pagereference.page.titleHeadingType}><a href="${pagereference.page.href}">${pagereference.page.title}</a></${pagereference.page.titleHeadingType}>
                    </c:when>
                    <c:otherwise>
                        <${pagereference.page.titleHeadingType}>${pagereference.page.title}</${pagereference.page.titleHeadingType}>
                    </c:otherwise>
                </c:choose>
            </c:when>
            <c:otherwise>
                Warning! Nothing has been selected to be presented for this page.
            </c:otherwise>
        </c:choose>
    </c:otherwise>
</c:choose>