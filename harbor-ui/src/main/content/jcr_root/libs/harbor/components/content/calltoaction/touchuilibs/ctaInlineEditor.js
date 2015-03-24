( function() {

    var disabledEditables = [];

    var CTAEditor = function() {

    };

    CTAEditor.prototype.setUp = function( editable ) {
        console.log( 'setup' );
        console.log( editable );

        //Reinitialize the disabled editables array
        disabledEditables = [];

        Granite.author.ContentFrame.executeCommand( editable.path, 'harbor-openModal', {} );

        //Disable all editable overlays which do not start with the path of the modal - ie - which are not contained in the modal
        Granite.author.store.forEach( function( currentEditable ) {
            if ( currentEditable.path.indexOf( editable.path ) !== 0 && !currentEditable.config.isContainer ) {
                currentEditable.setDisabled( true );
                currentEditable.overlay.setVisible( false );
                disabledEditables.push( currentEditable );

                console.log( 'Disabling ' + currentEditable.path );
            }
        } );

    };

    CTAEditor.prototype.tearDown = function( editable ) {
        console.log( 'teardown' );
        console.log( editable );
        Granite.author.ContentFrame.executeCommand( editable.path, 'harbor-closeModal', {} );

        //Reenable previously disabled editables
        disabledEditables.forEach( function( currentEditable ) {
            currentEditable.overlay.setVisible( true );
            currentEditable.setDisabled( false );
        } );

        disabledEditables = [];

    };

    Granite.author.editor.register( 'harborcta', new CTAEditor() );
} )();