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
                            var name = $item.attr('name');

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

