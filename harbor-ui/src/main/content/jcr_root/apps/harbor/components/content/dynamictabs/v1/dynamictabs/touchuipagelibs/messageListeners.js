( function dynamicCarouselMessageListenerInitialization ( jQuery ) {

    if ( !jQuery ) {
        return;
    }

    jQuery( document ).ready ( function() {
        if ( typeof Granite !== 'undefined' && Granite.author && Granite.author.inner && Granite.author.inner.EditorFrame ) {

            Granite.author.inner.EditorFrame.subscribeRequestMessage( 'harbor-DynamicTabs-v1-nextTab', function( r ) {
                var nextTab = jQuery( 'ul[role="tablist"][data-path="' + r.data.path + '"] > li.active' ).next();

                if ( nextTab.length === 0 ) {
                    nextTab = jQuery( 'ul[role="tablist"][data-path="' + r.data.path + '"] > li:first' );
                }

                nextTab.find( 'a[data-toggle="tab"]' ).tab( 'show' );
            } );

            Granite.author.inner.EditorFrame.subscribeRequestMessage( 'harbor-DynamicTabs-v1-previousTab', function( r ) {
                var previousTab = jQuery( 'ul[role="tablist"][data-path="' + r.data.path + '"] > li.active' ).prev();

                if ( previousTab.length === 0 ) {
                    previousTab = jQuery( 'ul[role="tablist"][data-path="' + r.data.path + '"] > li:last' );
                }

                previousTab.find( 'a[data-toggle="tab"]' ).tab( 'show' );
            } );
        }
    } )
} )( window.jQuery );
