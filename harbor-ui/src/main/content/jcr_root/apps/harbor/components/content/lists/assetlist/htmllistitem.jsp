<%@include file="/apps/harbor/components/global.jsp" %>

<li>
    <c:choose>
        <c:when test="${renderableItem.isArticle}">
            <article>
                <header>
                    <c:if test="${renderableItem.renderTitle}">
                        <c:choose>
                            <c:when test="${renderableItem.renderAsLink}">
                                <${renderableItem.titleHeadingType}><a href="${renderableItem.imageSource}">${renderableItem.title}</a></${renderableItem.titleHeadingType}>
                            </c:when>
                            <c:otherwise>
                                <${renderableItem.titleHeadingType}>${renderableItem.title}</${renderableItem.titleHeadingType}>
                            </c:otherwise>
                        </c:choose>
                    </c:if>
                    <c:if test="${renderableItem.renderCreator}">
                        <span class="creator">${renderableItem.createdByLabel}${renderableItem.creator}</span>
                    </c:if>
                    <c:if test="${renderableItem.renderFormat}">
                        <span class="format">${renderableItem.formatLabel}${renderableItem.format}</span>
                    </c:if>
                </header>
                <c:if test="${renderableItem.renderImage}">
                    <c:choose>
                        <c:when test="${renderableItem.renderAsLink}">
                            <a href="${renderableItem.imageSource}"><img src="${renderableItem.imageSourceRendition}" /></a>
                        </c:when>
                        <c:otherwise>
                            <img src="${renderableItem.imageSourceRendition}" />
                        </c:otherwise>
                    </c:choose>
                </c:if>
                <c:if test="${renderableItem.renderDescription}">
                    <p>${renderableItem.description}</p>
                </c:if>
            </article>
        </c:when>
        <c:otherwise>
            <c:if test="${renderableItem.renderTitle}">
                <c:choose>
                    <c:when test="${renderableItem.renderAsLink}">
                        <a href="${renderableItem.imageSource}"><${renderableItem.titleHeadingType}>${renderableItem.title}</${renderableItem.titleHeadingType}></a>
                    </c:when>
                    <c:otherwise>
                        <${renderableItem.titleHeadingType}>${renderableItem.title}</${renderableItem.titleHeadingType}>
                    </c:otherwise>
                </c:choose>
            </c:if>
            <c:if test="${renderableItem.renderImage}">
                <c:choose>
                    <c:when test="${renderableItem.renderAsLink}">
                        <a href="${renderableItem.imageSource}"><img src="${renderableItem.imageSourceRendition}" /></a>
                    </c:when>
                    <c:otherwise>
                        <img src="${renderableItem.imageSourceRendition}" />
                    </c:otherwise>
                </c:choose>
            </c:if>
        </c:otherwise>
    </c:choose>
</li>

