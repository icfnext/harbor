Harbor.Components.DynamicCarousel = Harbor.Components.DynamicCarousel || {};
Harbor.Components.DynamicCarousel.v1 = {};
Harbor.Components.DynamicCarousel.v1.DynamicCarousel = function ( ns, channel ) {

    var DynamicCarouselEditor = function() {

        this.addSlide = function( component, dialogPath ) {
            Harbor.Lists.v1.ListsEditor.addDynamicListItem( component, dialogPath );
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
            ns.ContentFrame.postMessage( 'harbor-DynamicCarousel-nextSlide', { path: component.path } );
            Harbor.Lists.v1.removeSelection();

        };

        this.previousSlide = function( component ) {
            ns.ContentFrame.postMessage( 'harbor-DynamicCarousel-previousSlide', { path: component.path } );
            Harbor.Lists.v1.removeSelection();
        };

    };

    return new DynamicCarouselEditor();

}( Granite.author, jQuery( document ) );


