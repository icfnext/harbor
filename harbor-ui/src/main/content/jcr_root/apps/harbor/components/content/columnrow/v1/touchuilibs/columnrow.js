Harbor.Components.ColumnRow = Harbor.Components.ColumnRow || {};
Harbor.Components.ColumnRow.v1 = {};
Harbor.Components.ColumnRow.v1.ColumnRow = function (ns, channel) {

    var ColumnRowEditor = function () {

        this.addColumn = function (component, columnResourceType) {

            Harbor.Lists.v1.ListsEditor.addListItem(component, {
                "sling:resourceType": columnResourceType
            }, {
                listItemNameBase: "column"
            }).done(function () {
                //TODO: Consider following the model of manual injection of the content so as to not necessitate a refresh of the entire row
                ns.edit.actions.doRefresh(component);
            });

        };

    };

    return new ColumnRowEditor();

}(Granite.author, jQuery(document));