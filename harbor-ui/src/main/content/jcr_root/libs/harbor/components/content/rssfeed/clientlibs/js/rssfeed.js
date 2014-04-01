Harbor.Components.RSSFeed = function (jQuery) {
    function refreshRSSFeed(rssFeedJSON) {
        var currentRSSDiv = Harbor.Components.RSSFeed.currentRSSDiv;
        var currentRSSDivChildrenNames = [];
        currentRSSDiv.children().each(function () {
            var currentTitle = $(this).data("title");
            currentRSSDivChildrenNames.push(currentTitle)

        });
        $.each(rssFeedJSON, function () {
            var currentTitle = this.title;
            if ($.each(currentRSSDivChildrenNames, function () {
                if (this === currentTitle) {
                    return true;
                } else {
                    return false;
                }
            })) {
                currentRSSDiv.prepend(this.HTML);
            }
        });

    }

    return {
        updateRSSFeeds: function () {
            $(".list-group.rssfeed").each(function () {
                    Harbor.Components.RSSFeed.currentRSSDiv = $(this);
                    var currentRSSFeedPath = $(this).data("currentrssfeedpath");
                    $.ajax({
                            url: currentRSSFeedPath + "." + "rssfeed" + ".json",
                            dataType: "json",
                            type: "GET",
                            cache: false,
                            success: refreshRSSFeed
                        }
                    );
                }
            )
        }
    }
}(jQuery);


$(document).ready(function () {
    Harbor.Components.RSSFeed.updateRSSFeeds();
    setInterval(function () {
        Harbor.Components.RSSFeed.updateRSSFeeds();
    }, 10000);
})
