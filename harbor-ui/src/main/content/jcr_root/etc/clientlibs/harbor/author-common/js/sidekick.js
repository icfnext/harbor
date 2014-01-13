$(function () {
    var top = CQ.WCM.getTopWindow();

    if (top.CQ.WCM.isSidekickReady()) {
        top.CQ.WCM.getSidekick().previewReload = true;
    } else {
        top.CQ.WCM.on('sidekickready', function(sidekick) {
            sidekick.previewReload = true;
        });
    }
});