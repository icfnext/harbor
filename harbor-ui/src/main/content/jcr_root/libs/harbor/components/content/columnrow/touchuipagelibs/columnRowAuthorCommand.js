window.Granite = window.Granite || {};
window.Granite.author = window.Granite.author || {};
window.Granite.author.command = window.Granite.author.command || {};

Granite.author.command[ 'harbor-columnrow-update-column-size' ] = function( path, msg ) {

    var column = Granite.author.getEditableNode( path );
    var bootstrapColumn = column.parentNode;
    var newBootstrapClasses = JSON.parse( msg.data );

    var classesToRemove = [];

    bootstrapColumn.classList.forEach( function( currentClass ) {
        if ( currentClass.indexOf( 'col-' ) === 0 ) {
            classesToRemove.push( currentClass );
        }
    } );

    classesToRemove.forEach( function( currentClassToRemove ) {
        bootstrapColumn.classList.remove( currentClassToRemove );
    } );

    newBootstrapClasses.forEach( function( currentClassToAdd ) {
        bootstrapColumn.classList.add( currentClassToAdd );
    } );

};