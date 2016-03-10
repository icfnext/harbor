Harbor.Components.LinkList = function ( ns, channel ) {

    return {
        addLink: function ( component ) {

            console.log( component );

            var args = [ component, 'last', null ];

            channel.trigger("cq-persistence-before-create", args);

            return (
                ns.persistence.crud
                    .sendCreateParagraph(
                        'harbor/components/content/lists/linklist/listablelink', //resource type
                        component.path, //path to parent
                        'last', //location relative to neighbor
                        null, //name of neighbor,
                        null, //config parameters
                        {
                            "parentResourceType" : 'harbor/components/content/lists/linklist' //additional parameters
                        },
                        null, //template path
                        'listablelink' //name hint
                    )
                    .done(function (data, textStatus, jqXHR) {
                        channel.trigger("cq-persistence-after-create", args);

                        //TODO: Refresh is problamatic as it refreshes the editability from local config and not from filtered config
                        component.refresh().done( function() {
                            ns.edit.findEditables( component.dom).forEach( function( currentEditable ) {
                                if ( ns.store.find( currentEditable.path).length === 0 ) {
                                    ns.overlayManager.recreate( currentEditable );
                                    ns.store.add( currentEditable );
                                }
                            } );
                        } );

                        //TODO: This is getting the component onto the page and it's editable but I don't think the edit config is being initialized appropriately.  Once the new instance is added and made editable, if you try to open the dialog, it opens and works but won't close.  If you refresh the page the problem goes away.

                    })
                    /*error handling*/
                    .fail(function (/*jqXHR, textStatus, errorThrown*/) {
                        ns.ui.helpers.notify(Granite.I18n.get("Paragraph create operation failed."));
                    })
            );

        }
    };

}( Granite.author, jQuery( document ) );


