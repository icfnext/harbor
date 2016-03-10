Harbor.Components.Accordion = function ( ns, channel ) {

    var AccordionEditor = function() {

        this.addItem = function ( component, itemResourceType ) {

            Harbor.Lists.ListsEditor.addListItem(component, {
                "sling:resourceType": itemResourceType
            }, {
                listItemNameBase: "item"
            })
                .done(function () {
                    //TODO: Consider following the model of manual injection of the content so as to not necessitate a refresh of the entire accordion group
                    ns.edit.actions.doRefresh(component);
                });

        };

        this.moveUp = function( component ) {

            console.log( 'Move Up Called' );

        };

        this.moveDown = function( component ) {

            console.log( 'Move Down Called' );

        };

    };

    return new AccordionEditor();

}( Granite.author, jQuery( document ) );


/*
function tacos() {
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
*/
