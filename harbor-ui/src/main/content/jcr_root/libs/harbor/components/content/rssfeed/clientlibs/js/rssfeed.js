Harbor.Components.RSSFeed = function (jQuery) {
    //TODO rename
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
        updateRSSFeed: function (currentRSSFeedDiv) {
            var currentRSSFeedPath = currentRSSFeedDiv.data("currentrssfeedpath");

            $.ajax({
                    url: currentRSSFeedPath + "." + "rssfeed" + ".json",
                    dataType: "json",
                    type: "GET",
                    cache: false,
                    success: function(data){
                        refreshRSSFeed(data, currentRSSFeedDiv)
                    }
                }
            );
        }
    }
}(jQuery);


$(document).ready(function () {

    $(".list-group.rssfeed").each(function () {
        console.time("UPDATE RSS FEED");
        Harbor.Components.RSSFeed.updateRSSFeed($(this));
        console.timeEnd("UPDATE RSS FEED");
    });

    $(".list-group.rssfeed").each(function () {

        var currentRSSFeedDiv = $(this);
        setInterval(function () {
            console.time("UPDATE RSS FEED");
            Harbor.Components.RSSFeed.updateRSSFeed(currentRSSFeedDiv);
            console.timeEnd("UPDATE RSS FEED");
        }, currentRSSFeedDiv.data("updateinterval"));
    });
});
