Harbor.Components.DynamicTabs = Harbor.Components.DynamicTabs || {};
Harbor.Components.DynamicTabs.v1 = {};
Harbor.Components.DynamicTabs.v1.DynamicTabs = function ( ns, channel ) {

    var DynamicTabsEditor = function() {

        this.addTab = function( component, dialogPath ) {
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

        this.nextTab = function( component ) {
            console.log( 'GOTO Next Tab for ' + component.path );
            ns.ContentFrame.postMessage( 'harbor-DynamicTabs-v1-nextTab', { path: component.path } );
            Harbor.Lists.v1.removeSelection();
        };

        this.previousTab = function( component ) {
            console.log( 'GOTO Previous Tab for ' + component.path );
            ns.ContentFrame.postMessage( 'harbor-DynamicTabs-v1-previousTab', { path: component.path } );
            Harbor.Lists.v1.removeSelection();
        };

    };

    return new DynamicTabsEditor();

}( Granite.author, jQuery( document ) );


