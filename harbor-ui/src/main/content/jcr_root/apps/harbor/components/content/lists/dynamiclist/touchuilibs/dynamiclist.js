Harbor.Components.DynamicList = Harbor.Components.DynamicList || {};
Harbor.Components.DynamicList.v1 = {};
Harbor.Components.DynamicList.v1.DynamicList = function ( ns, channel ) {

    var DynamicListEditor = function() {

        this.addItem = function( component, dialogPath ) {
            Harbor.Lists.v1.ListsEditor.addDynamicListItem( component, dialogPath );
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

    return new DynamicListEditor();

}( Granite.author, jQuery( document ) );


