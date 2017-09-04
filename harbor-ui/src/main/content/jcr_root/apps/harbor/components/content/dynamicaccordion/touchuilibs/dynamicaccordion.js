Harbor.Components.DynamicAccordion = function ( ns, channel ) {

    var DynamicAccordionEditor = function() {

        this.addItem = function( component, dialogPath ) {
            Harbor.Lists.ListsEditor.addDynamicListItem( component, dialogPath );
        };

        this.moveUp = function( component ) {

            Harbor.Lists.ListsEditor.moveBackward( component )
                .then( function() {
                    ns.edit.actions.doRefresh( component.getParent() );
                    Harbor.Lists.removeSelection();
                } );

        };

        this.moveDown = function( component ) {

            Harbor.Lists.ListsEditor.moveForward( component )
                .then( function() {
                    ns.edit.actions.doRefresh( component.getParent() );
                    Harbor.Lists.removeSelection();
                } );

        };

    };

    return new DynamicAccordionEditor();

}( Granite.author, jQuery( document ) );


