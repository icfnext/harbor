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

        this.gotoNextSlide = function() {
            console.log( 'GOTO Next Slide' );
        };

        this.gotoPreviousSlide = function() {
            console.log( 'GOTO Previous Slide' );
        };

    };

    return new DynamicCarouselEditor();

}( Granite.author, jQuery( document ) );


