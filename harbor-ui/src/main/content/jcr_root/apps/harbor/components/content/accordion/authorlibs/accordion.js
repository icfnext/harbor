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
        },
        //move Accordion Item up
        moveUp: function (component) {
            var currentEditable = component;
            var $currentAccordionItem = $('#' + currentEditable.element.id);
            console.log('moving up item with id ' + currentEditable.element.id);
            var prevNodeName = '';
            if ($currentAccordionItem.prevAll('.accordionitem').size() > 0) {
                prevNodeName = 'before ' + $currentAccordionItem.prevAll('.accordionitem').first().find('.panel.panel-default').data('node-name');
                jQuery.post(
                    currentEditable.path,
                    {
                        ':order': prevNodeName
                    },
                    function (data) {
                        currentEditable.refreshParent();
                    }
                );
            } else {
                //alert to let the author know that the operation was not possible?
            }
        },
        //move Accordion Item down
        moveDown: function (component) {
            var currentEditable = component;
            var $currentAccordionItem = $('#' + currentEditable.element.id);
            console.log('moving down item with id ' + currentEditable.element.id);
            var nextNodeName = '';
            if ($currentAccordionItem.nextAll('.accordionitem').size() > 0) {
                nextNodeName = 'after ' + $currentAccordionItem.nextAll('.accordionitem').first().find('.panel.panel-default').data('node-name');
                jQuery.post(
                    currentEditable.path,
                    {
                        ':order': nextNodeName
                    },
                    function (data) {
                        currentEditable.refreshParent();
                    }
                );
            } else {
                //alert to let the author know that the operation was not possible?
            }
        }
    }
}(jQuery);
