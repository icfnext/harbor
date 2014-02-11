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
        },

        cellSearchPathModifier: function (componentConstructor) {

            var oldSearchPath = componentConstructor.cellSearchPath;

            var split = oldSearchPath.split('/');
            if (split.length >= 2) {
                var newSearchPath = split[0] + '/' + split[1];
                componentConstructor.cellSearchPath = newSearchPath;
            }

        }

    }
}(jQuery)