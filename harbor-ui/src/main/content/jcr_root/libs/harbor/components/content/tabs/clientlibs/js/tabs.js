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

        initTabsSorting: function(uniqueId){
            if (CQ.WCM.isEditMode()) {
                // make tabs sortable in edit mode
                $('#'+ uniqueId + '-tabs-container').children('ul.nav-tabs').each(function () {
                    var $this = $(this);

                    $this.sortable({
                        axis: 'x',
                        update: function (event, ui) {
                            // reorder tabs on back end on update
                            var $item = ui.item;
                            var name = $item.data('name');

                            if (name) {
                                var path = $item.data('path') + '/' + name;
                                $.post(path, { ':order': $item.index() });
                            }
                        }
                    });
                });
            }
        }
    }
}(jQuery)

