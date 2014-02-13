//Todo: move this clientlib into it's own folder, so it is only included in author
Harbor.Components.Tabs = function (jQuery) {
    return {
        addTab: function (component) {
            var currentEditable = component;
            jQuery.post(
                currentEditable.path + '/*',
                {
                    'sling:resourceType': 'harbor/components/content/tabs/tab',
                    'jcr:primaryType': 'nt:unstructured',
                    ':nameHint': 'tab'
                },
                function (data) {
                    currentEditable.refreshSelf();
                }
            );
        }
    }
}(jQuery)

$(document)