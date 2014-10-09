Harbor.Components.Carousel = function ( jQuery ) {

    return {
        addSlide: function ( component ) {
            var currentEditable = component;
            jQuery.post(
                currentEditable.path + '/*',
                {
                    'sling:resourceType': 'harbor/components/content/carousel/carouselslide',
                    'jcr:primaryType': 'nt:unstructured',
                    ':nameHint': 'slide'
                },
                function ( data ) {
                    currentEditable.refreshSelf();
                }
            );
        }
    };

}( jQuery );


