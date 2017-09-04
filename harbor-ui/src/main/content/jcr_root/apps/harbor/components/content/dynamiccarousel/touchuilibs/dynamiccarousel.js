Harbor.Components.DynamicCarousel = function ( ns, channel ) {

    var DynamicCarouselEditor = function() {

        this.addSlide = function( component, dialogPath ) {
            Harbor.Lists.ListsEditor.addDynamicListItem( component, dialogPath );
        };

        this.moveUp = function( component ) {

            Harbor.Lists.ListsEditor.moveBackward( component )
                .then( function() {
                    ns.edit.actions.doRefresh( component.getParent() );
                    Harbor.Lists.removeSelection();
                } );

        };

        this.moveDown = function( component ) {

            Harbor.Lists.ListsEditor.moveForward( component )
                .then( function() {
                    ns.edit.actions.doRefresh( component.getParent() );
                    Harbor.Lists.removeSelection();
                } );

        };

        this.nextSlide = function( component ) {
            ns.ContentFrame.postMessage( 'harbor-DynamicCarousel-nextSlide', { path: component.path } );
            Harbor.Lists.removeSelection();

        };

        this.previousSlide = function( component ) {
            ns.ContentFrame.postMessage( 'harbor-DynamicCarousel-previousSlide', { path: component.path } );
            Harbor.Lists.removeSelection();
        };

    };

    return new DynamicCarouselEditor();

}( Granite.author, jQuery( document ) );


