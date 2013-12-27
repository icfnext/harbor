Harbor = {Components:{}};


Harbor.Components.FluidColumnRow = function(jQuery){
    return {
        moveColumnLeft: function() {
            Harbor.Components.FluidColumnRow.moveColumn.call( this, -1 );
        },

        moveColumnRight: function() {
            Harbor.Components.FluidColumnRow.moveColumn.call( this, 1 );
        },

        moveColumn: function( direction ) {
            var curIndex = jQuery( this.element.dom.parentElement.parentElement ).index();

            var newIndex = curIndex + ( 1 * direction );

            var childCount = jQuery( this.element.dom.parentElement.parentElement.parentElement ).children().length;

            if ( newIndex < 0 ) {
                newIndex = 0;
            }
            if ( newIndex >= childCount ) {
                newIndex = childCount - 1;
            }

            if ( curIndex != newIndex ) {
                var itemPath = this.path;
                var currentEditable = this;

                jQuery.post(
                    this.path,
                    {
                        ":order" : newIndex
                    },
                    function( data ) {
                        console.log ( itemPath + " :: Order Updated" );
                        currentEditable.refreshParent();
                    }
                );
            }
        },

        addColumn: function(editableContext){
            jQuery.post(
                editableContext.path + '/*',
                {
                    'sling:resourceType' : 'harbor/components/content/fluidcolumn',
                    'jcr:primaryType' : 'nt:unstructured',
                    ':nameHint' : 'column'
                },
                function( data ) { editableContext.refreshSelf(); }
            );
        }
    }
}(jQuery);