<%@include file="/libs/harbor/components/global.jsp" %>
<div id="${rssFeed.RSSFeedUniqueID}" class="list-group rssfeed" data-currentRSSFeedPath="${rssFeed.currentRSSFeedPath}"
     data-updateinterval=${rssFeed.updateInterval}>
</div>

<script type="text/javascript">
    $(document).ready(function () {
       Harbor.Components.RSSFeed.initRSSFeed("${rssFeed.RSSFeedUniqueID}");
    });
</script>