Harbor.Components.Tabs = function (ns, channel) {

    var TabsEditor = function () {

        this.addTab = function (component, itemResourceType) {
            Harbor.Lists.ListsEditor.addListItem(component, {
                "sling:resourceType": itemResourceType
            }, {
                listItemNameBase: "item"
            }).done(function () {
                location.reload();
            });
        };

        this.moveUp = function (component) {
            Harbor.Lists.ListsEditor.moveBackward(component).then(function () {
                ns.edit.actions.doRefresh(component.getParent());
            });
        };

        this.moveDown = function (component) {
            Harbor.Lists.ListsEditor.moveForward(component).then(function () {
                ns.edit.actions.doRefresh(component.getParent());
            });
        };
    };

    return new TabsEditor();
}(Granite.author, jQuery(document));

