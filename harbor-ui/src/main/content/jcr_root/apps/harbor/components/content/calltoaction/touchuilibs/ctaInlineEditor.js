( function() {

    var disabledEditables = [],
        closeButton = null;

    var CTAEditor = function() {

    };

    CTAEditor.prototype.setUp = function( editable ) {

        var currentEditor = this;

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

        closeButton = jQuery( "<button id=\"Harbor-CTAEditorCloseButton\" class=\"coral-MinimalButton cq-editable-action\" type=\"button\" title=\"Close\"><i class=\"coral-Icon coral-Icon--close coral-Icon--sizeS\" title=\"Close\"></i></button>");

        //TODO: Setup position tracking based on the position of the editable sitting inside
        jQuery( "#OverlayWrapper" ).append( closeButton );

        closeButton.click( function() {
            currentEditor.tearDown( editable );
        } );

        editable.setInactive();
        jQuery( "#EditableToolbar" ).hide();

        /*
        editable.setInactive();
        editable.setDisabled( true );
        editable.overlay.setVisible( false );
        disabledEditables.push( editable );
        */

        window.theEditableInQuestion = editable;

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

        closeButton.remove();

    };

    var theEditor = new CTAEditor();

    Granite.author.editor.register( 'harborcta', theEditor );

    window.theEditorInQuestion = theEditor;

} )();