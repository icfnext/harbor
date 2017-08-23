Harbor.Components.Carousel = Harbor.Components.Carousel || {};
Harbor.Components.Carousel.v1 = {};
Harbor.Components.Carousel.v1.Carousel = function (ns, channel) {

    var CarouselEditor = function () {

        this.addSlide = function (component) {
            Harbor.Lists.v1.ListsEditor.addListItem(component, {
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