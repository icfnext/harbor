Harbor.Lists = function( ns, channel ) {

    var autopopulatedProperties = [
        "jcr:created",
        "jcr:createdBy",
        "jcr:lastModified",
        "jcr:lastModifiedBy"
    ];

    var ListsEditor = function() {

        /**
         * Modelled after Granite.author.edit.actions.doInsert but without the requirement of an editable neighbor
         * and allowing for deep creation of resources where intermediary resources might not exist.
         *
         * @param editable
         * @param item A JSON object representing the list item
         * @param item.sling:resourceType The sling:resourceType of the list item Resource.  Defaults to none
         * @param item.jcr:primaryType The jcr:primaryType of the list item Resource.  Defaults to "nt:unstructured"
         * @param options.listName The name of the child resource representing the list.  Defaults to "list"
         * @param options.listResourceType The sling:ResourceType of the list container Resource.  Defaults to none
         * @param options.listPrimaryType The jcr:primaryType of the list container Resource.  Defaults to "nt:unstructured"
         * @param options.listItemName The name of the new list item to create.  Defaults to "item" + Date.now()
         * @param options.listItemNameBase The root name of a list item to be used if uniqueness wants to be left to this function but the name is intended to be semantic
         */
        this.addListItem = function (editable, item, options) {

            var listName = options.listName || 'list';
            var itemName = options.listItemName || ( options.listItemNameBase || 'item' ) + Date.now();

            var postData = {
                "./sling:resourceType": editable.type, //TODO: not sure if this is where this is contained
                "./jcr:primaryType": editable.primaryType //TODO: it seems unlikely that we have this data
            };

            postData["./" + listName + "/jcr:primaryType"] = options.listPrimaryType || 'nt:unstructured';
            if (options.listResourceType) {
                postData["./" + listName + "/sling:resourceType"] = options.listResourceType;
            }

            for (var propertyName in item) {
                if (item.hasOwnProperty(propertyName)) {
                    postData["./" + listName + "/" + itemName + "/" + propertyName] = item[propertyName];
                }
            }

            if (!item['jcr:primaryType']) {
                postData["./" + listName + "/" + itemName + "/jcr:primaryType"] = 'nt:unstructured';
            }

            autopopulatedProperties.forEach(function (currentProperty) {
                postData["./" + currentProperty] = '';
                postData["./" + listName + "/" + currentProperty] = '';
                if (!item[currentProperty]) {
                    postData["./" + listName + "/" + itemName + "/" + currentProperty] = '';
                }
            });


            return $.ajax({
                type: "POST",
                url: editable.path,
                data: postData,
                async: true
            })
                .done(function () {

                    console.log("the post happened");

                })
                .fail(function (e) {
                    ns.ui.helpers.notify(Granite.I18n.get('Error during creation of a new list item'));
                    console.log(e);
                });

        };

    };

    /**
     * To be called as a component listener.  The context of the function will be the component editable itself.
     * Given a component structure
     *
     * + Component
     *   + List
     *     + List Item
     *     + List Item
     *
     * this function is appropriate for attribution to actions taken on the individual List Items and will call
     * for a refresh of the Component.  This is to compensate for the fact that the function which is called when
     * an edit config listener value is "REFRESH_PARENT" only goes up a single resource and attempts to find an
     * editable at that path to refresh.
     *
     * TODO: Consider whether there is a way to do this without necessitating a refresh of the entire row
     */
    var refreshListParent = function() {
        var parentPath = this.getParentPath();
        var listParentPath = parentPath.substr( 0, parentPath.lastIndexOf( '/' ) );
        var listParentInspectable = ns.store.find( listParentPath );

        if ( listParentInspectable[ 0 ] ) {
            ns.edit.actions.doRefresh( listParentInspectable[ 0 ] )
                .done( function() {
                    /*
                     * This additional find is required, because the doRefresh method relies on the editable's
                     * getChildren method to find child editables to refresh.  This in turn relies on ns.store.find
                     * to look up any editables at a path one level deeper than the path of the current editable.
                     * For listed editables such as these this obviously does not work.
                     */
                    ns.store.find( {
                        path: new RegExp( parentPath + '/[^/]+$' )
                    } ).forEach( function( currentEditable ) {
                        ns.edit.actions.doRefresh( currentEditable );
                    } );
                } );
        }
    };

    return {
        ListsEditor: new ListsEditor(),
        refreshListParent: refreshListParent
    };

}( Granite.author, jQuery( document ) );