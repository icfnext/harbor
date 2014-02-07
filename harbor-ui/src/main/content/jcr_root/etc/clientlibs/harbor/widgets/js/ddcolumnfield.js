Harbor.Widgets.DdColumnField = CQ.Ext.extend ( CQ.CustomContentPanel , {

    columnCount : 0,


    initComponent: function() {

        Harbor.Widgets.DdColumnField.superclass.initComponent.call(this);
        var parentContext = this;

        console.log(this);

        this.buttonBar = new function(){

            this.addColumn = function( test ){


                if( parentContext.columnCount != 12 ){
                    var parentContainer = $('#'+test.ownerCt.ownerCt.id);
                    var container       = parentContainer.find(".CQAuthorColumnContainer");
                    var parentWidth     = parentContainer.innerWidth();
                    var colWidth        = (parentWidth - 120) / 13;

                    parentContext.columnCount++;

                    var col             = $("<div class='well'>" + parentContext.columnCount + "</div>").width(colWidth);

                    container.append(col);

                    console.log( colWidth );
                }

            }

        };


        this.containerPanel = new CQ.Ext.Panel({
            title: 'Author Columns',
            layout:'card',
            activeItem: 0, // make sure the active item is set on the container config!
            bodyStyle: 'padding:15px',
            defaults: {
                // applied to each contained panel
                border:false
            },
            // just an example of one possible navigation scheme, using buttons
            bbar: [

                '->', // greedy spacer so that the buttons are aligned to each side
                {
                    id: 'add-column',
                    text: 'Add Column',
                    handler: this.buttonBar.addColumn
                }
            ],
            // the panels (or "cards") within the layout
            html: '<div class="testContainer CQAuthorColumnContainer"></div>'
        });

        this.add(this.containerPanel);




        this.doLayout();

    },


    onRender: function(ct, position){
        Harbor.Widgets.DdColumnField.superclass.onRender.call(this, ct, position);


        this.containerPanel.toolbars[0]

        console.log("render");
    }


} );


CQ.Ext.reg('ddcolumnfield', Harbor.Widgets.DdColumnField);