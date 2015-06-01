if ( typeof aem !== 'undefined' && aem.authoring && aem.authoring.command ) {

    aem.authoring.command[ 'harbor-openAccordion' ] = function( path, message ) {
        console.log( path );
        console.log( message );

        jQuery( '.accordion-item[data-path="' + path + '"]' ).modal( 'show' );
    };

}