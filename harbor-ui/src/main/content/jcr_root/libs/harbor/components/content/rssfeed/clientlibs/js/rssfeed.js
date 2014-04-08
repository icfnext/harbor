Harbor.Components.RSSFeed = function (jQuery) {

    function refreshRSSFeed(rssFeedJSON, currentRSSFeedDiv) {
        //We reverse the array because we prepend the items one after the other.
        // Since we receive the list in order, we have to reverse it to render it in order.
        rssFeedJSON = rssFeedJSON.reverse();
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
                return false;
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
        initRSSFeed: function (currentRSSFeedUniqueId, updateInterval) {
            var previousIntervalId = Harbor.Components.RSSFeed.intervalIDs[currentRSSFeedUniqueId];
            if (previousIntervalId != undefined && previousIntervalId != null) {
                clearInterval(previousIntervalId);
            }
            var currentRSSFeedDiv = $("#" + currentRSSFeedUniqueId);
            Harbor.Components.RSSFeed.updateRSSFeed(currentRSSFeedDiv);
            var intervalId = setInterval(function () {
                Harbor.Components.RSSFeed.updateRSSFeed(currentRSSFeedDiv);
            }, updateInterval);
            Harbor.Components.RSSFeed.intervalIDs[currentRSSFeedUniqueId] = intervalId;
        }
    }
}(jQuery);



