Harbor.Components.RSSFeed = function (jQuery) {

    function refreshRSSFeed(rssFeedJSON, currentRSSFeedDiv) {
        $.each(rssFeedJSON, function () {
            if (!isCurrentItemInRSSDiv(this, currentRSSFeedDiv)) {
                currentRSSFeedDiv.prepend(this.HTML);
            }
        });

    }

    function isCurrentItemInRSSDiv(currentRSSJsonItem, currentRSSFeedDiv) {
        var currentTitle = currentRSSJsonItem.title;
        var isCurrentItemInRSSDivFlag = false;
        currentRSSFeedDiv.children().each(function () {
            if ($(this).data("title") === currentTitle) {
                isCurrentItemInRSSDivFlag = true;
            }
        });

        return isCurrentItemInRSSDivFlag;
    }

    return {
        intervalIDs: {},
        updateRSSFeed: function (currentRSSFeedDiv) {
            var currentRSSFeedPath = currentRSSFeedDiv.data("currentrssfeedpath");

            $.ajax({
                    url: currentRSSFeedPath + "." + "rssfeed" + ".json",
                    dataType: "json",
                    type: "GET",
                    cache: false,
                    success: function (data) {
                        refreshRSSFeed(data, currentRSSFeedDiv)
                    }
                }
            );
        },
        initRSSFeed: function (currentRSSFeedUniqueId) {
            var previousIntervalId = Harbor.Components.RSSFeed.intervalIDs[currentRSSFeedUniqueId];
            if (previousIntervalId != undefined && previousIntervalId != null) {
                clearInterval(previousIntervalId);
            }
            var currentRSSFeedDiv = $("#" + currentRSSFeedUniqueId);
            Harbor.Components.RSSFeed.updateRSSFeed(currentRSSFeedDiv);
            var intervalId = setInterval(function () {
                console.time("UPDATE RSS FEED" + currentRSSFeedUniqueId);
                Harbor.Components.RSSFeed.updateRSSFeed(currentRSSFeedDiv);
                console.timeEnd("UPDATE RSS FEED" +  currentRSSFeedUniqueId);
            }, currentRSSFeedDiv.data("updateinterval"));
            Harbor.Components.RSSFeed.intervalIDs[currentRSSFeedUniqueId] = intervalId;
        }
    }
}(jQuery);



