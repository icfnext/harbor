Harbor.Components.Carousel = function (ns, channel) {

    var CarouselEditor = function () {

        this.addSlide = function (component) {
            Harbor.Lists.ListsEditor.addListItem(component, {
                'sling:resourceType': 'harbor/components/content/carousel/carouselslide'
            }, {
                listItemNameBase: 'slide-'
            }).done(function () {
                ns.edit.actions.doRefresh(component);
            });
        };
    };

    return new CarouselEditor();
}(Granite.author, jQuery(document));