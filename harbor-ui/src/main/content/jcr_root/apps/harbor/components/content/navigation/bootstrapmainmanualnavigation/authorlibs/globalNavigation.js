Harbor.Components.GlobalNavigation = function ($) {

    var navigationElement = {
        'sling:resourceType': '/harbor/components/content/bootstrapmainnavigationelement',
        'jcr:primaryType': 'nt:unstructured',
        ':nameHint': 'nav_element'
    };

    var sendNavElementAddPost = function (path, data, success) {
        return $.post(
            path, data, success
        ).then(function (data) {
            return data;
        });
    };

    return {
        addNavigationElement: function (editableContext) {
            sendNavElementAddPost(editableContext.path + '/elements/*', navigationElement,
                function (data) {
                    editableContext.refreshSelf();
                });
        }
    };
}(jQuery);