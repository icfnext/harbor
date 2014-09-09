<%@include file="/libs/harbor/components/global.jsp" %>

<li>
    <c:choose>
        <c:when test="${renderableItem.isArticle}">
            <article>
                <header>
                    <c:if test="${renderableItem.renderTitle}">
                        <c:choose>
                            <c:when test="${renderableItem.renderAsLink}">
                                <${renderableItem.titleHeadingType}><a href="${renderableItem.href}">${renderableItem.title}</a></${renderableItem.titleHeadingType}>
                            </c:when>
                            <c:otherwise>
                                <${renderableItem.titleHeadingType}>${renderableItem.title}</${renderableItem.titleHeadingType}>
                            </c:otherwise>
                        </c:choose>
                    </c:if>
                    <c:if test="${renderableItem.renderSubtitle}"><p class="subtitle">${renderableItem.subtitle}</p></c:if>
                </header>
                <c:if test="${renderableItem.renderImage}"><img src="${renderableItem.imageSource}" /></c:if>
                <c:if test="${renderableItem.renderDescription}"><p>${renderableItem.description}</p></c:if>
            </article>
        </c:when>
        <c:otherwise>
            <c:choose>
                <c:when test="${renderableItem.renderTitle}">
                    <c:choose>
                        <c:when test="${renderableItem.renderAsLink}">
                            <${renderableItem.titleHeadingType}><a href="${renderableItem.href}">${renderableItem.title}</a></${renderableItem.titleHeadingType}>
                        </c:when>
                        <c:otherwise>
                            <${renderableItem.titleHeadingType}>${renderableItem.title}</${renderableItem.titleHeadingType}>
                        </c:otherwise>
                    </c:choose>
                </c:when>
                <c:otherwise>
                    Warning! Nothing has been selected to be presented for this page list.
                </c:otherwise>
            </c:choose>
        </c:otherwise>
    </c:choose>
</li>
