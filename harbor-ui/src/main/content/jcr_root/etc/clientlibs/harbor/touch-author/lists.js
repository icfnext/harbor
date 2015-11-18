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

                    alert("the post happened");

                })
                .fail(function (e) {
                    ns.ui.helpers.notify(Granite.I18n.get('Error during creation of a new list item'));
                    console.log(e);
                });

        };

    };

    return new ListsEditor();

}( Granite.author, jQuery( document ) );