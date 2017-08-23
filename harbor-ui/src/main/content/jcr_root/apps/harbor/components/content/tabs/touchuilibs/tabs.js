Harbor.Components.Tabs = Harbor.Components.Tabs || {};
Harbor.Components.Tabs.v1 = {};
Harbor.Components.Tabs.v1.Tabs = function (ns, channel) {

    var TabsEditor = function () {

        this.addTab = function (component, itemResourceType) {
            Harbor.Lists.v1.ListsEditor.addListItem(component, {
                "sling:resourceType": itemResourceType
            }, {
                listItemNameBase: "item"
            }).done(function () {
                location.reload();
            });
        };

        this.moveUp = function (component) {
            Harbor.Lists.v1.ListsEditor.moveBackward(component).then(function () {
                ns.edit.actions.doRefresh(component.getParent());
            });
        };

        this.moveDown = function (component) {
            Harbor.Lists.v1.ListsEditor.moveForward(component).then(function () {
                ns.edit.actions.doRefresh(component.getParent());
            });
        };
    };

    return new TabsEditor();
}(Granite.author, jQuery(document));

