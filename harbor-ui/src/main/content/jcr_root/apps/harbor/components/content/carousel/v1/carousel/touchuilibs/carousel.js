Harbor.Components.Carousel = Harbor.Components.Carousel || {};
Harbor.Components.Carousel.v1 = {};
Harbor.Components.Carousel.v1.Carousel = function (ns, channel) {

    var CarouselEditor = function () {

        this.addSlide = function ( component, slideType ) {
            Harbor.Lists.v1.ListsEditor.addListItem(component, {
                'sling:resourceType': slideType
            }, {
                listItemNameBase: 'slide-'
            }).done(function () {
                ns.edit.actions.doRefresh(component);
            });
        };
    };

    return new CarouselEditor();
}(Granite.author, jQuery(document));