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
        },

        //TODO: I'm being lazy here and putting this function here during development. It will be moved to the appropriate file in the future.
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