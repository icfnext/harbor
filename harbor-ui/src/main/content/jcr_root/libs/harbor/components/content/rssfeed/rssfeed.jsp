<%@include file="/libs/harbor/components/global.jsp" %>
<div id="${rssFeed.RSSFeedUniqueId}" class="list-group rssfeed" data-currentRSSFeedPath="${rssFeed.currentRSSFeedPath}">
</div>

<script type="text/javascript">
    $(document).ready(function () {
       Harbor.Components.RSSFeed.initRSSFeed("${rssFeed.RSSFeedUniqueId}", ${rssFeed.updateInterval});
    });
</script>