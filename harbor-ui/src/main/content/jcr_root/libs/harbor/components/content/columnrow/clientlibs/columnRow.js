Harbor.Components.ColumnRow = function(jQuery){
    var columnWidthProperty = "colClass";
    var nameHint = "column";
    var column = {
        'sling:resourceType' : 'harbor/components/content/column',
        'jcr:primaryType' : 'nt:unstructured'
    };

    return {
        manifestUtil: function(){

            return {
                predicates: {
                    isToBeAdded: function(data){
                        if(data.isNewColumn && data.colSize != 0){
                            return true;
                        }
                        return false;
                    },
                    isToBeRemoved: function(data){
                        return data.colSize == 0 && data.name; //data.name is undefined for newly added cols

                    },
                    isToBeModified: function(data){
                        return data.colSize != 0 && this.isToBeAdded(data) == false;
                    }
                },

                generateHiddenFieldObject: function(colData){
                    /*
                        Determine if colData is to be added,
                        modified, or removed, and create the appropriate
                        data dictionary that can be inserted as a hidden field
                    */
                    var dataPrefix = './' + colData.name + '/';

                    if (this.predicates.isToBeAdded(colData)){
                        //sling type, and node type
                        var resourceTypeKey = dataPrefix + 'sling:resourceType';
                        var primaryTypeKey = dataPrefix + 'jcr:primaryType';
                        var addColData = {};
                        addColData[resourceTypeKey] = 'harbor/components/content/column';
                        addColData[primaryTypeKey]  = 'nt:unstructured';

                        //Adds the colData passed to the function into the addColData object
                        for (var prop in colData) {
                            if (colData.hasOwnProperty(prop)){
                                //Add props from data to newColumnBase
                                if(prop != "isNewColumn"){   //we cn skip serializing this
                                    addColData[dataPrefix + prop] = colData[prop];
                                }
                            }
                        }
                        return addColData;
                    }

                    else if(this.predicates.isToBeModified(colData)){
                        var newData = {};
                        for (var prop in colData) {
                            if (colData.hasOwnProperty(prop)){
                                //Add props from data to newColumnBase
                                newData[dataPrefix + prop] = colData[prop];
                            }
                        }
                        return newData;
                    }

                    else if(this.predicates.isToBeRemoved(colData)){

                        var colName = './' + colData.name + "@Delete";
                        var obj = {}
                        obj[colName] = true;
                        return obj;
                    }
                },
                createAddColumnPayload: function(colData){
                    //extend column data with sling stuff

                },

                createModifyColumnPayload: function(colData){

                }
            }
        }(),

        getBaseColumnData: function(){
            return column;
        },

        defaultNewColumnName: ":" + nameHint

    }
}(jQuery);