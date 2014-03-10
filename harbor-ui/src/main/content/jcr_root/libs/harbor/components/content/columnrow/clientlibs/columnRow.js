Harbor.Components.ColumnRow = function(jQuery){
    var columnWidthProperty = "colClass";
    var nameHint = "column";
    var column = {
        'sling:resourceType' : 'harbor/components/content/column',
        'jcr:primaryType' : 'nt:unstructured',
        ':nameHint' : nameHint
    };

    return {
        manifestUtil: function(){
            var predicates = {
                isToBeAdded: function(data){
                    if(!data.name){
                        return true;
                    }
                    return false;
                },
                isToBeRemoved: function(data){
                    return data.colSize == 0 && data.name != (":" + nameHint);

                },
                isToBeModified: function(data){
                    return data.colSize != 0 && this.isToBeAdded(data) == false;
                }
            }

            /*
                These filters build an aggregation of AJAX payload objects.
                The lists here will be used for that purpose only.
             */

            return {
                filterColumnDataToAdd: function(manifest){
                    var buildAddToPayload = function(data){
                        var newColumnBase = column;

                        for (prop in data) {
                            if (data.hasOwnProperty(prop)){
                                //Add props from data to newColumnBase
                                newColumnBase[prop] = data[prop];
                            }
                        }

                        return newColumnBase;

                    }

                    /*
                        Filters columns that have a name of ':column'

                        This is inserted as a name for columns added using the add button.
                        This won't actually be the column's name, but serves as a way to fetch it.
                        New columns use the nameHint defined in this class to have their names
                        auto-generated when plopped into the jcr.
                     */
                    var filtered = [];

                    for(var prop in manifest){
                        if(manifest.hasOwnProperty(prop)){
                            //BAM DATA TOWN

                            //We check colSize here also, because we don't want to add something that
                            //had been added, but removed from the column builder
                            var tmp = manifest[prop];
                            if(predicates.isToBeAdded(tmp.data)){
                                //Add this to our list of filtered items
                                filtered.push(buildAddToPayload(tmp.data));
                            }
                        }
                    }

                    return filtered;
                },

                filterColumnDataToRemove: function(manifest){
                    /*
                        Filters columns with a 'colSize' that has been zero'd out.
                     */

                    var filtered = [];
                    for(var prop in manifest){
                        if(manifest.hasOwnProperty(prop)){
                            //Check for the name, because we don't want to send for a removal of something that wasn't
                            //put into the jcr yet. (the :nameHint is given to NEW columns added via button)
                            var tmp = manifest[prop];
                            if(predicates.isToBeRemoved(tmp.data)){
                                filtered.push({
                                    name: tmp.data.name,
                                    data: tmp.data
                                });
                            }
                        }
                    }

                    return filtered;
                },

                filterColumnDataToModify: function(manifest){
                    /*
                        Filters columns with a non-zero colSize,
                        and columns with names that are not ":column" (well, ':' + nameHint)
                     */
                    var filtered = [];
                    for(var prop in manifest){
                        if(manifest.hasOwnProperty(prop)){
                            var tmp = manifest[prop];
                            if(predicates.isToBeModified(tmp.data)){
                                filtered.push({
                                    name: tmp.data.name,
                                    data: tmp.data
                                });
                            }
                        }
                    }

                    return filtered;
                }
            }
        }(),

        getRequestFactoryForEditable: function(path){
            var path = path;

            var addColumnToRow = function(col){
                return $.ajax({
                    type: "POST",
                    data: col,
                    url: path + '/*'
                }).then(function(data){
                    return data;
                });
            };

            var modifyColumnInRow = function(columnName, col){
                return $.ajax({
                    type: "POST",
                    data: col,
                    url: path + "/" + columnName
                }).then(function(data){
                    return data;
                });
            };

            var removeColumnFromRow = function(name){
                return $.ajax({
                    type: "POST",
                    data: {":operation": "delete"},
                    url: path + "/" + name
                }).then(function(data){
                    return data;
                });
            };

            return {
                getColumnsInRow: function(){
                    return $.ajax({
                        type: "GET",
                        url: path + ".1.json"
                    });
                },

                removeColumnList: function(list){
                    var def = $.Deferred();
                    var def_promise = def.promise();

                    $.each(list, function(i, postData){
                        //build a .then chain with the promise
                        def_promise = def_promise.then(function(){
                            return removeColumnFromRow(postData.name);
                        });
                    });

                    //final then
                    def_promise.then(function(){
                        //editableContext.refreshSelf();
                    });

                    def.resolve();
                },
                modifyColumnList: function(list){
                    var def = $.Deferred();
                    var def_promise = def.promise();

                    $.each(list, function(i, postData){
                        //build a .then chain with the promise
                        def_promise = def_promise.then(function(){
                            return modifyColumnInRow(postData.name, postData.data);
                        });
                    });

                    //final then
                    def_promise.then(function(){
                        //editableContext.refreshSelf();
                    });

                    def.resolve();
                },

                addColumnList: function(list){
                    var def = $.Deferred();
                    var def_promise = def.promise();

                    $.each(list, function(i, postData){
                        //build a .then chain with the promise
                        def_promise = def_promise.then(function(){
                            return addColumnToRow(postData);
                        });
                    });

                    //final then
                    def_promise.then(function(){
                        //editableContext.refreshSelf();
                        console.log("ADD COLUMN LIST PROMISE THEN")
                    });

                    def.resolve();
                }

            }
        },

        getBaseColumnData: function(){
            return column;
        },

        defaultNewColumnName: ":" + nameHint

    }
}(jQuery);