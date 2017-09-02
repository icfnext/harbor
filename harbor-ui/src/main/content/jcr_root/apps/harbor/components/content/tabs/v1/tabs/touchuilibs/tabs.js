Harbor.Components.Tabs = Harbor.Components.Tabs || {};
Harbor.Components.Tabs.v1 = {};
Harbor.Components.Tabs.v1.Tabs = function (ns, channel) {

    var TabsEditor = function () {

        this.addTab = function ( component, itemResourceType ) {
            Harbor.Lists.v1.ListsEditor.addListItem( component, {
                "sling:resourceType": itemResourceType
            }, {
                listItemNameBase: "item"
            } )
                .done(function () {
                    ns.edit.actions.doRefresh( component );
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

        this.nextTab = function( component ) {
            ns.ContentFrame.postMessage( 'harbor-Tabs-v1-nextTab', { path: component.path } );
            Harbor.Lists.v1.removeSelection();
        };

        this.previousTab = function( component ) {
            ns.ContentFrame.postMessage( 'harbor-Tabs-v1-previousTab', { path: component.path } );
            Harbor.Lists.v1.removeSelection();
        };
    };

    return new TabsEditor();
}(Granite.author, jQuery(document));

