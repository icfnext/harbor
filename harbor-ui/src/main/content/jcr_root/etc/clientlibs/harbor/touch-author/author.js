(function (ns, channel) {
    if (Granite && Granite.Util) {
        var loadedTime = new Date();

        channel.on('cq-layer-activated', function (event) {
            var eventTime;

            if (event.prevLayer && event.layer !== event.prevLayer) {
                eventTime = new Date();

                if (event.prevLayer !== 'Annotate' && event.layer !== 'Annotate' && eventTime - loadedTime > 1500) {
                    location.reload();
                }
            }
        });

        channel.on('inline-edit-cancel', function () {
            location.reload();
        });
    }
}(Granite.author, jQuery(document)));