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

        this.moveUp = function( component ) {

            Harbor.Lists.v1.ListsEditor.moveBackward( component )
                .then( function() {
                    ns.edit.actions.doRefresh( component.getParent() );
                    Harbor.Lists.v1.removeSelection();
                } );

        };

        this.moveDown = function( component ) {

            Harbor.Lists.v1.ListsEditor.moveForward( component )
                .then( function() {
                    ns.edit.actions.doRefresh( component.getParent() );
                    Harbor.Lists.v1.removeSelection();
                } );

        };

        this.nextSlide = function( component ) {
            ns.ContentFrame.postMessage( 'harbor-Carousel-nextSlide', { path: component.path } );
            Harbor.Lists.v1.removeSelection();

        };

        this.previousSlide = function( component ) {
            ns.ContentFrame.postMessage( 'harbor-Carousel-previousSlide', { path: component.path } );
            Harbor.Lists.v1.removeSelection();
        };

    };

    return new CarouselEditor();
}(Granite.author, jQuery(document));