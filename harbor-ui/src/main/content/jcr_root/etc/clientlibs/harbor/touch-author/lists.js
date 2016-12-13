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
         * @param options.listName The name of the child resource representing the list.  If not provided list items will be created as a direct child of the editable
         * @param options.listResourceType The sling:ResourceType of the list container Resource.  Defaults to none
         * @param options.listPrimaryType The jcr:primaryType of the list container Resource.  Defaults to "nt:unstructured"
         * @param options.listItemName The name of the new list item to create.  Defaults to "item" + Date.now()
         * @param options.listItemNameBase The root name of a list item to be used if uniqueness wants to be left to this function but the name is intended to be semantic
         */
        this.addListItem = function (editable, item, options) {

            var listNamePath = options.listName ? options.listName + '/' : '';
            var itemName = options.listItemName || ( options.listItemNameBase || 'item' ) + Date.now();

            var postData = {
                "./sling:resourceType": editable.type, //TODO: not sure if this is where this is contained
                "./jcr:primaryType": editable.primaryType //TODO: it seems unlikely that we have this data
            };

            if ( options.listName ) {
                postData["./" + listNamePath + "jcr:primaryType"] = options.listPrimaryType || 'nt:unstructured';
                if (options.listResourceType) {
                    postData["./" + listNamePath + "sling:resourceType"] = options.listResourceType;
                }
            }

            for (var propertyName in item) {
                if (item.hasOwnProperty(propertyName)) {
                    postData["./" + listNamePath + itemName + "/" + propertyName] = item[propertyName];
                }
            }

            if (!item['jcr:primaryType']) {
                postData["./" + listNamePath + itemName + "/jcr:primaryType"] = 'nt:unstructured';
            }

            autopopulatedProperties.forEach(function (currentProperty) {
                postData["./" + currentProperty] = '';
                if ( options.listName ) {
                    postData["./" + options.listName + "/" + currentProperty] = '';
                }
                if (!item[currentProperty]) {
                    postData["./" + listNamePath + itemName + "/" + currentProperty] = '';
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

        /**
         * Looks up the Path of the nearest sibling Resource to the editable provided.
         *
         * @param editable
         * @param direction 1 for next sibling, -1 for prior sibling
         * @return jQuery Deferred promise of sibling path.  Promise will resolve to null if no such sibling exists
         */
        this.findSiblingPath = function( editable, direction ) {
            return ns.persistence.readParagraphContent( { path: editable.getParentPath() + '.1' } )
                .then( function( paragraph ) {
                    var paragraphObject = JSON.parse( paragraph );
                    var siblings = [];

                    for ( var key in paragraphObject ) {
                        if ( paragraphObject.hasOwnProperty( key ) ) {
                            if ( typeof paragraphObject[ key ] === 'object' ) {
                                siblings.push( editable.getParentPath() + '/' + key );
                            }
                        }
                    }

                    var siblingIndex = siblings.indexOf( editable.path ) + direction;

                    if ( siblingIndex < 0 || siblingIndex >= siblings.length ) {
                        return null;
                    }

                    return siblings[ siblingIndex ];
                } );
        };

        this.findPriorSiblingPath = function( editable ) {
            return this.findSiblingPath( editable, -1 );
        };

        this.findNextSiblingPath = function( editable ) {
            return this.findSiblingPath( editable, 1 );
        };

        /**
         * Sends a request to Sling to reorder the editable amongst its siblings, advancing it by one amongst its siblings.
         *
         * @param editable
         * @returns A jQuery promise of the move
         */
        this.moveForward = function( editable ) {
            return this.findNextSiblingPath( editable )
                .then( function( path ) {
                    if ( path ) {
                        return $.ajax( {
                            type: "POST",
                            url: editable.path,
                            data: {
                                ":order": "after " + path.substring( path.lastIndexOf( '/' ) + 1 )
                            }
                        } );
                    }
                } );
        };

        /**
         * Sends a request to Sling to reorder the editable amongst its siblings, moving it back by one amongst its siblings.
         *
         * @param editable
         * @returns A jQuery promise of the move
         */
        this.moveBackward = function( editable ) {
            return this.findPriorSiblingPath( editable )
                .then( function( path ) {
                    if ( path ) {
                        return $.ajax( {
                            type: "POST",
                            url: editable.path,
                            data: {
                                ":order": "before " + path.substring( path.lastIndexOf( '/' ) + 1 )
                            }
                        } );
                    }
                } );
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