Harbor.Components.LinkList = function ( jQuery ) {

    return {
        addLink: function ( component ) {
            var currentEditable = component;
            jQuery.post(
                currentEditable.path + '/*',
                {
                    'sling:resourceType': 'harbor/components/content/lists/linklist/listablelink',
                    'jcr:primaryType': 'nt:unstructured',
                    ':nameHint': 'listitem'
                },
                function ( data ) {
                    currentEditable.refreshSelf();
                }
            );
        }
    };

}( jQuery );


