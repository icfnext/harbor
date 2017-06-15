Harbor.Components.DynamicCarousel = function ( ns, channel ) {

    var DynamicCarouselEditor = function() {

        this.addSlide = function( component, dialogPath ) {
            Harbor.Lists.ListsEditor.addDynamicListItem( component, dialogPath );
        };

        this.moveUp = function( component ) {

            Harbor.Lists.ListsEditor.moveBackward( component )
                .then( function() {
                    ns.edit.actions.doRefresh( component.getParent() );
                } );

        };

        this.moveDown = function( component ) {

            Harbor.Lists.ListsEditor.moveForward( component )
                .then( function() {
                    ns.edit.actions.doRefresh( component.getParent() );
                } );

        };

        this.nextSlide = function( component ) {
            console.log( 'GOTO Next Slide for ' + component.path );
            ns.ContentFrame.postMessage( 'harbor-DynamicCarousel-nextSlide', { path: component.path } );
        };

        this.previousSlide = function( component ) {
            console.log( 'GOTO Previous Slide for ' + component.path );
            ns.ContentFrame.postMessage( 'harbor-DynamicCarousel-previousSlide', { path: component.path } );
        };

    };

    return new DynamicCarouselEditor();

}( Granite.author, jQuery( document ) );


