Harbor.Components.ColumnRow = function(jQuery){
    var columnWidthProperty = "colClass";
    var nameHint = "column";
    var column = {
        'sling:resourceType' : 'harbor/components/content/column',
        'jcr:primaryType' : 'nt:unstructured',
        ':nameHint' : nameHint
    };

    return {
        ColumnManager: function ColumnManager(rowPath){
            if(! (this instanceof ColumnManager))
                return new ColumnManager(rowPath);

            var rowPath = rowPath;
            var requestFactory = Harbor.Components.ColumnRow.getRequestFactoryForEditable(rowPath);

            //initializes a list of columns to add
            var toBeAdded = [];
            var toBeRemoved = [];
            var toBeModified = [];

            /*
             Creates a list of column objects,
             {name: x. data: column info}
             */
            var toList = function(hash){
                var list = [];
                for (var prop in hash){
                    if (hash.hasOwnProperty(prop)) {
                        var name = prop;
                        var tempObj = {name:prop, data: hash[prop]}
                        list.push(tempObj);
                    }
                }
                return list;
            }


            //initializes existing columns
            var existingColumnDict = {};
            requestFactory.getColumnsInRow().done(function(data){
                /*
                 Columns start with "column", lets filter them
                 out of data, and into a list
                 */
                //build a hash of columns
                for (var prop in data){
                    if (prop.search(nameHint) != -1){
                        existingColumnDict[prop] = data[prop];
                    }
                }

            });
            this.existingColumnDict = existingColumnDict;

            /*
             utility methods
             */

            //creates a column object that can be later added via POST
            this.addColumn = function(size){
                //extend basic column with a size
                var col = column;
                col[columnWidthProperty] = size || 1;

                //add column to the toBeAddedList
                toBeAdded.push(col);
            }

            this.modifyColumn = function(name, data){
                //We ignore adding these to a list, because that is done automatically in the serialization phase
                for (var item in data){
                    if (data.hasOwnProperty(item)){
                        //index into the existing dict hash and change fields
                        existingColumnDict[name][item] = data[item]; //bam
                    }
                }
            }

            this.removeColumn = function(name){
                /*
                 Adds column name to the "to Remove" list,
                 IF that column is in the existing column dictionary
                 */
                if (this.existingColumnDict.hasOwnProperty(name)){
                    toBeRemoved.push(name);
                }
            }


            this.serializeColumnsToJCR = function(){
                toBeModified = toList(this.existingColumnDict);
                var reqFactory = Harbor.Components.ColumnRow.getRequestFactoryForEditable(rowPath);
                //execute ajax requests

                //1. Run Modifies
                reqFactory.modifyColumnList(toBeModified);

                //2. Run Adds
                reqFactory.addColumnList(toBeAdded);

                //3. Run Removals
                //reqFactory.removeColumnList(toBeRemoved);
            }
        },

        getRequestFactoryForEditable: function(path){
            var path = path;

            var writeQueryParams = function(dict){

            }

            var addColumnToRow = function(col){
                return $.ajax({
                    type: "POST",
                    data: col,
                    url: path + '/*'
                }).then(function(data){
                        return data;
                    })
            };

            var modifyColumnInRow = function(columnName, data){
                $.ajax({
                    type: "POST",
                    contentType: "multipart/form-data",
                    url: path + "/" + columnName + "?" + $.param(data)
                }).then(function(data){
                        return data;
                    });
            };

            var removeColumnFromRow = function(name){
                $.ajax({
                    data: ":operation=delete",
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
                    })
                },

                removeColumnList: function(nameList){
                    var def = $.Deferred();
                    var def_promise = def.promise();

                    $.each(nameList, function(i, name){
                        //build a .then chain with the promise
                        def_promise = def_promise.then(function(){
                            return removeColumnFromRow(name);
                        });
                    });

                    //final then
                    def_promise.then(function(){
                        //editableContext.refreshSelf();
                    })

                    def.resolve();
                },

                modifyColumnList: function(list){
                    var def = $.Deferred();
                    var def_promise = def.promise();

                    /*
                     Current assumption here is that a during a modify, I can just repost what
                     was returned already placed into the existingColumnDict with getColumnsInRow
                     */

                    $.each(list, function(i, postData){
                        //build a .then chain with the promise
                        def_promise = def_promise.then(function(){
                            return modifyColumnInRow(postData.name, postData.data);
                        });
                    });

                    //final then
                    def_promise.then(function(){
                        //editableContext.refreshSelf();
                    })

                    def.resolve();
                },

                addColumnList: function(list){
                    var def = $.Deferred();
                    var def_promise = def.promise();

                    $.each(list, function(i, postData){
                        //build a .then chain with the promise
                        def_promise = def_promise.then(function(){
                            return addColumnToRow(postData.data);
                        });
                    });

                    //final then
                    def_promise.then(function(){
                        //editableContext.refreshSelf();
                    })

                    def.resolve();
                }

            }
        },

        getBaseColumnData: function(){
            return column;
        }
    }
}(jQuery);