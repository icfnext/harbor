Harbor.Components.ColumnRow = function( ns, channel ) {

    var ColumnRowEditor = function() {

        this.addColumn = function (component, columnResourceType) {

            Harbor.Lists.addListItem(component, {
                "sling:resourceType": columnResourceType
            }, {
                listName: "columns",
                listItemNameBase: "column"
            });

        };

    };

    return new ColumnRowEditor();

}( Granite.author, jQuery( document ) );