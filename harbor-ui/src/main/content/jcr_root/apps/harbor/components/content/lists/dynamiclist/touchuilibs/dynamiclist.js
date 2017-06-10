Harbor.Components.DynamicList = function ( ns, channel ) {

    var AddItemDialog = function Dialog( listEditable, dialogPath ) {
        this.listEditable = listEditable;
        this.dialogPath = dialogPath;
    };

    ns.util.inherits( AddItemDialog, ns.edit.Dialog );

    AddItemDialog.prototype.getConfig = function () {
        return {
            dialog: this.dialogPath,
            src: "/mnt/override" + this.dialogPath + ".html" + this.listEditable.path,
            loadingMode: 'auto',
            layout: 'auto'
        };
    };

    AddItemDialog.prototype.getRequestData = function () {
        return {};
    };

    AddItemDialog.prototype.onReady = function() {
        console.log( 'READY' );
        var $form = ns.DialogFrame.currentFloatingDialog.find( 'form' );
        $form.attr( 'action', $form.attr( 'action' ) + '/*' );
    };

    AddItemDialog.prototype.onSuccess = function ( currentDialog, currentFloatingDialog ) {

        ns.edit.actions.doRefresh( this.listEditable );

        /*
        var self = this;
        var properties = {};

        if (currentFloatingDialog) {
            var propertiesArray = currentFloatingDialog.serializeArray();

            propertiesArray.forEach(function (propertyNameValue) {
                properties[propertyNameValue.name] = propertyNameValue.value;
            });
        }  else {
            // CQ-75906: not fully impl. for compatibility dialogs
        }

        // CQ-75906: "cq-persistence-before-update" not impl. yet
        //channel.trigger("cq-persistence-after-update", [ this.listEditable,  properties ]); //TODO: What?  Does anything use these events?
        Harbor.Lists.ListsEditor.addListItem( this.listEditable, properties, {
            listItemNameBase: "item"
        } )
            .done( function() {
                ns.edit.actions.doRefresh( self.listEditable );
            } );

        */

        // CQ-74871: dialog undo/redo not implemented, we therefore clear the history for a successful dialog edit
        var history = ns.history.Manager.getHistory();
        history.clear(); //TODO: Do I need to retain this in any way or should I be adding the insert to history?

    };


    var DynamicListEditor = function() {

        this.addItem = function( component, dialogPath ) {
            ns.DialogFrame.openDialog( new AddItemDialog( component, dialogPath ) );
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

    return new DynamicListEditor();

}( Granite.author, jQuery( document ) );


