<%@include file="/apps/harbor/components/global.jsp" %>

<c:if test="${rssFeed.hasRssChannel}">
    <a href="${rssFeed.rssChannel.link.href}" target="_blank">${rssFeed.rssChannel.title}</a>
    <p>${rssFeed.rssChannel.description}</p>

    <ul>
        <c:forEach var="curRssItem" items="${rssFeed.rssChannel.items}">
            <li><a href="${curRssItem.link.href}" target="_blank">${curRssItem.title}</a><p>${curRssItem.description}</p></li>
        </c:forEach>
    </ul>
</c:if>