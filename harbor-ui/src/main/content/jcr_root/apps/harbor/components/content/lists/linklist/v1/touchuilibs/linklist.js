Harbor.Components.LinkList = Harbor.Components.LinkList || {};
Harbor.Components.LinkList.v1 = {};
Harbor.Components.LinkList.v1.LinkList = function ( ns, channel ) {

    var LinkListEditor = function() {

        this.addLink = function( component, linkResourceType ) {
            Harbor.Lists.v1.ListsEditor.addListItem(component, {
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

            Harbor.Lists.v1.ListsEditor.moveBackward( component )
                .then( function() {
                    ns.edit.actions.doRefresh( component.getParent() );
                } );

        };

        this.moveDown = function( component ) {

            Harbor.Lists.v1.ListsEditor.moveForward( component )
                .then( function() {
                    ns.edit.actions.doRefresh( component.getParent() );
                } );

        };

    };

    return new LinkListEditor();

}( Granite.author, jQuery( document ) );


