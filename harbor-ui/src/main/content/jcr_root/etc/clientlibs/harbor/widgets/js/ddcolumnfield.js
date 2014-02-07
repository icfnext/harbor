Harbor.Widgets.DdColumnField = CQ.Ext.extend ( CQ.form.CompositeField , {




    initComponent: function() {

        Harbor.Widgets.DdColumnField.superclass.initComponent.call(this);


        var navHandler = function(direction){
            // This routine could contain business logic required to manage the navigation steps.
            // It would call setActiveItem as needed, manage navigation button state, handle any
            // branching logic that might be required, handle alternate actions like cancellation
            // or finalization, etc.  A complete wizard implementation could get pretty
            // sophisticated depending on the complexity required, and should probably be
            // done as a subclass of CardLayout in a real-world implementation.
        };

        this.containerPanel = new CQ.Ext.Panel({
            title: 'Example Wizard',
            layout:'card',
            activeItem: 0, // make sure the active item is set on the container config!
            bodyStyle: 'padding:15px',
            defaults: {
                // applied to each contained panel
                border:false
            },
            // just an example of one possible navigation scheme, using buttons
            bbar: [
                {
                    id: 'move-prev',
                    text: 'Back',
                    handler: navHandler.createDelegate(this, [-1]),
                    disabled: true
                },
                '->', // greedy spacer so that the buttons are aligned to each side
                {
                    id: 'move-next',
                    text: 'Next',
                    handler: navHandler.createDelegate(this, [1])
                }
            ],
            // the panels (or "cards") within the layout
            items: [{
                id: 'card-0',
                html: '<h1>Welcome to the Wizard!</h1><p>Step 1 of 3</p>'
            },{
                id: 'card-1',
                html: '<p>Step 2 of 3</p>'
            },{
                id: 'card-2',
                html: '<h1>Congratulations!</h1><p>Step 3 of 3 - Complete</p>'
            }]
        });

        this.add(this.containerPanel);

        this.doLayout();

    },


    onRender: function(ct, position){
        Harbor.Widgets.DdColumnField.superclass.onRender.call(this, ct, position);

    }


} );


CQ.Ext.reg('ddcolumnfield', Harbor.Widgets.DdColumnField);