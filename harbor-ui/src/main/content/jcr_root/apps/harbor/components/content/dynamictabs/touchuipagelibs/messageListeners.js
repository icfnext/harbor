( function dynamicCarouselMessageListenerInitialization ( jQuery ) {

    if ( !jQuery ) {
        return;
    }

    jQuery( document ).ready ( function() {
        if ( typeof Granite !== 'undefined' && Granite.author && Granite.author.inner && Granite.author.inner.EditorFrame ) {

            Granite.author.inner.EditorFrame.subscribeRequestMessage( 'harbor-DynamicTabs-nextTab', function( r ) {
                console.log( r );
                var nextTab = jQuery( 'ul[role="tablist"][data-path="' + r.data.path + '"] > li.active' ).next() ||
                                jQuery( 'ul[role="tablist"][data-path="' + r.data.path + '"] > li:first' );
                nextTab.tab( 'show' );
            } );

            Granite.author.inner.EditorFrame.subscribeRequestMessage( 'harbor-DynamicTabs-previousTab', function( r ) {
                console.log( r );
                var previousTab = jQuery( 'ul[role="tablist"][data-path="' + r.data.path + '"] > li.active' ).prev() ||
                    jQuery( 'ul[role="tablist"][data-path="' + r.data.path + '"] > li:last' );
                previousTab.tab( 'show' );
            } );
        }
    } )
} )( window.jQuery );
