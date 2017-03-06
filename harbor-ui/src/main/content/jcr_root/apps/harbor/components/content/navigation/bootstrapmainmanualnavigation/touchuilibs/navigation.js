Harbor.Components.GlobalNavigation = function (ns, channel) {

    var NavigationEditor = function () {

        this.addNavigationElement = function (component) {
            Harbor.Lists.ListsEditor.addListItem(component, {
                'sling:resourceType': 'harbor/components/content/bootstrapmainnavigationelement'
            }, {
                listName: 'elements',
                listItemNameBase: 'nav-element-'
            }).done(function () {
                ns.edit.actions.doRefresh(component);
            });
        };
    };

    return new NavigationEditor();
}(Granite.author, jQuery(document));