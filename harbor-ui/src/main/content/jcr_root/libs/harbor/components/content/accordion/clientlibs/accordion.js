Harbor.Components.Accordion = function (jQuery) {
    return {
        addItem: function (component) {
            var currentEditable = component;
            jQuery.post(
                currentEditable.path + '/*',
                {
                    'sling:resourceType': 'harbor/components/content/accordion/accordionitem',
                    'jcr:primaryType': 'nt:unstructured',
                    ':nameHint': 'item'
                },
                function (data) {
                    currentEditable.refreshSelf();
                }
            );
        }
    /*
    ,

        initTabsSorting: function (uniqueId) {
            // make tabs sortable in edit mode
            $('#' + uniqueId + '-tabs-container').children('ul.nav-tabs').each(function () {
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
    */
    }
}(jQuery);


