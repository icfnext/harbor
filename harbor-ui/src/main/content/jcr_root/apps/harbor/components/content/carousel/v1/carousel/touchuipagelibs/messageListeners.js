( function dynamicCarouselMessageListenerInitialization ( jQuery ) {

    if ( !jQuery ) {
        return;
    }

    jQuery( document ).ready ( function() {
        if ( typeof Granite !== 'undefined' && Granite.author && Granite.author.inner && Granite.author.inner.EditorFrame ) {

            Granite.author.inner.EditorFrame.subscribeRequestMessage( 'harbor-Carousel-nextSlide', function( r ) {
                console.log( r );
                jQuery( '.carousel[data-path="' + r.data.path + '"]' ).carousel( 'next' );
            } );

            Granite.author.inner.EditorFrame.subscribeRequestMessage( 'harbor-Carousel-previousSlide', function( r ) {
                console.log( r );
                jQuery( '.carousel[data-path="' + r.data.path + '"]' ).carousel( 'next' );
            } );

            /*
            aem.authoring.command[ 'harbor-DynamicCarousel-nextSlide' ] = function( path, message ) {
                console.log( path );
                console.log( message );

                jQuery( '.carousel[data-path="' + path + '"]' ).carousel( 'next' );
            };

            aem.authoring.command[ 'harbor-DynamicCarousel-previousSlide' ] = function( path, message ) {
                console.log( path );
                console.log( message );

                jQuery( '.carousel[data-path="' + path + '"]' ).carousel( 'prev' );
            };
            */

        }
    } ) } )( window.jQuery );