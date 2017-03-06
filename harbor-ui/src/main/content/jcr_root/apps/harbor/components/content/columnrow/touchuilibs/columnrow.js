Harbor.Components.ColumnRow = function (ns, channel) {

    var bootstrapSizeProperties = [
        "xsSize",
        "smSize",
        "mdSize",
        "lgSize"
    ];

    var ColumnRowEditor = function () {

        this.addColumn = function (component, columnResourceType) {

            Harbor.Lists.ListsEditor.addListItem(component, {
                "sling:resourceType": columnResourceType
            }, {
                //listName: "columns",
                listItemNameBase: "column"
            }).done(function () {
                //TODO: Consider following the model of manual injection of the content so as to not necessitate a refresh of the entire row
                ns.edit.actions.doRefresh(component);
            });

        };

        this.updateColumn = function () {
            var editable = this;

            ns.persistence.readParagraphContent({path: editable.path})
                .done(function (content) {

                    var contentObject = JSON.parse(content);
                    var bootstrapSizeArray = [];

                    bootstrapSizeProperties.forEach(function (currentSizeProperty) {
                        if (contentObject[currentSizeProperty] && contentObject[currentSizeProperty] !== 'default') {
                            bootstrapSizeArray.push(contentObject[currentSizeProperty]);
                        }
                    });

                    ns.ContentFrame.executeCommand(editable.path, 'harbor-columnrow-update-column-size', JSON.stringify(bootstrapSizeArray));
                });
        };

    };

    return new ColumnRowEditor();

}(Granite.author, jQuery(document));