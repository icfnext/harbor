Harbor.Components.LinkList = function ( ns, channel ) {

    var LinkListEditor = function() {

        this.addLink = function( component, linkResourceType ) {
            Harbor.Lists.ListsEditor.addListItem(component, {
                "sling:resourceType": linkResourceType
            }, {
                //listName: "columns",
                listItemNameBase: "link"
            } )
                .done( function() {
                    ns.edit.actions.doRefresh( component );
                } );
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

    };

    return new LinkListEditor();

}( Granite.author, jQuery( document ) );


