Harbor.Components.DynamicTabs = function ( ns, channel ) {

    var DynamicTabsEditor = function() {

        this.addTab = function( component, dialogPath ) {
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

        this.nextTab = function( component ) {
            console.log( 'GOTO Next Tab for ' + component.path );
            ns.ContentFrame.postMessage( 'harbor-DynamicTabs-nextTab', { path: component.path } );
            Harbor.Lists.removeSelection();
        };

        this.previousTab = function( component ) {
            console.log( 'GOTO Previous Tab for ' + component.path );
            ns.ContentFrame.postMessage( 'harbor-DynamicTabs-previousTab', { path: component.path } );
            Harbor.Lists.removeSelection();
        };

    };

    return new DynamicTabsEditor();

}( Granite.author, jQuery( document ) );


