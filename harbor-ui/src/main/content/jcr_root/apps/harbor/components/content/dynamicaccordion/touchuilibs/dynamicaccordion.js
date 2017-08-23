Harbor.Components.DynamicAccordion = Harbor.Components.DynamicAccordion || {};
Harbor.Components.DynamicAccordion.v1 = {};
Harbor.Components.DynamicAccordion.v1.DynamicAccordion = function ( ns, channel ) {

    var DynamicAccordionEditor = function() {

        this.addItem = function( component, dialogPath ) {
            Harbor.Lists.v1.ListsEditor.addDynamicListItem( component, dialogPath );
        };

        this.moveUp = function( component ) {

            Harbor.Lists.v1.ListsEditor.moveBackward( component )
                .then( function() {
                    ns.edit.actions.doRefresh( component.getParent() );
                    Harbor.Lists.v1.removeSelection();
                } );

        };

        this.moveDown = function( component ) {

            Harbor.Lists.v1.ListsEditor.moveForward( component )
                .then( function() {
                    ns.edit.actions.doRefresh( component.getParent() );
                    Harbor.Lists.v1.removeSelection();
                } );

        };

    };

    return new DynamicAccordionEditor();

}( Granite.author, jQuery( document ) );


