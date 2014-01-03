Harbor = {Components:{}};


Harbor.Components.FluidColumnRow = function(jQuery){
    var column = {
        'sling:resourceType' : 'harbor/components/content/fluidcolumn',
        'jcr:primaryType' : 'nt:unstructured',
        ':nameHint' : 'column'
    };

    var sendColumnAddPost = function(path, data, success){
        return jQuery.post(
            path, column, success
        ).then(function(data){
            return data;
        });
    }


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
            sendColumnAddPost(editableContext.path + '/*', column,
                function( data ) { editableContext.refreshSelf() });
        },

        addMultipleColumns: function(editableContext){
            //Prompt User for value.
            var value = prompt("Enter the number of columns to add.", "1");
            value = parseInt(value, 10); //value, radix

            if (value > 12 || isNaN(value)){
                value = 1;
            }

            //init column array for the promise .then chain loop
            var postData = [];
            for(var i = 0; i < value; i++){
                postData.push(column);
            }

            var def = $.Deferred();
            var def_promise = def.promise();

            $.each(postData, function(i, post){
                //build a .then chain with the promise
                def_promise = def_promise.then(function(){
                    return sendColumnAddPost(editableContext.path + '/*', post);
                });
            });

            //final then
            def_promise.then(function(){
                editableContext.refreshSelf();
            })

            def.resolve();
        }
    }
}(jQuery);