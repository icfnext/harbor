Harbor.Widgets.DdColumnField = CQ.Ext.extend ( CQ.CustomContentPanel , {

    columnCount         : 1,
    canAddAnotherColumn : true,
    colWidth            : 0,
    containerWidth      : 0,
    colArray            : [],

    initComponent: function() {

        Harbor.Widgets.DdColumnField.superclass.initComponent.call(this);
        var parentContext = this;


        this.buttonBar = new function(){

            this.addColumn = function( element ){



                if( parentContext.columnCount != 12 && parentContext.canAddAnotherColumn ){

                    var parentContainer    = $('#'+element.ownerCt.ownerCt.id);
                    var container          = parentContainer.find(".CQAuthorColumnContainer"); //TODO: make this class an attribute of the component
                    var parentWidth        = parentContainer.innerWidth();
                    var colWidth           = (parentWidth - 120) / 13;
                    parentContext.colWidth = colWidth;

                    parentContext.columnCount++;

                    var col = $("<div class='well col' data-column-id='" + parentContext.columnCount + "'> <h3>" + parentContext.columnCount + "</h3></div>").width(colWidth);

                    col.resizable({
                        grid: colWidth - (parentContext.columnCount > 1) ? 10 : 0,
                        containment: "parent",
                        handles: "e",
                        create: function(event, ui){
                            parentContext.columnAdded( parentContext , event , ui );
                        }
                    });

                    col.on('resizestart', function(event , ui){

                        parentContext.columnResize( parentContext , event , ui );

                    });
                    col.on('resize', function(event , ui){

                        parentContext.columnResize( parentContext , event , ui );

                    });
                    col.on('resizestop', function(event , ui){

                        parentContext.columnResize( parentContext , event , ui );

                    });
                    
                    container.append(col);

                    parentContext.colArray.push( col );



                }

            },

            this.removeColumn = function(){

                if( parentContext.columnCount != 1 ){
                    $(".col:last").remove();

                    parentContext.columnCount --;
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
                {
                    id: 'remove-column',
                    text: 'Remove Column',
                    handler: this.buttonBar.removeColumn
                },
                '->', // greedy spacer so that the buttons are aligned to each side
                {
                    id: 'add-column',
                    text: 'Add Column',
                    handler: this.buttonBar.addColumn
                }
            ],
            // the panels (or "cards") within the layout
            html: function(){
                var wrapper   = $('<div/>');
                var container = $('<div class="CQAuthorColumnContainer">');
                var col       = $("<div class='well col' data-column-id='" + parentContext.columnCount + "'><h3>" + parentContext.columnCount + "</h3></div>");


                container.append(col);
                wrapper.append(container);

                parentContext.colArray.push(col);

                return wrapper.html();
            }()
        });





        this.add(this.containerPanel);

        this.containerPanel.on("afterlayout", function( t ){

            var parentContainer          = $(t.body.dom.children[0]).parent();
            var container                = parentContainer.find(".CQAuthorColumnContainer");
            var parentWidth              = parentContainer.innerWidth() - 33;
            var colWidth                 = (parentWidth - 120) / 13;
            parentContext.containerWidth = parentWidth;
            parentContext.colWidth       = colWidth;

            container.find('.col').each(function(){
                $(this).width(colWidth);
                $(this).resizable({
                    grid: colWidth,
                    containment: "parent",
                    handles: "e"
                });

                $(this).on('resizestart', function(event , ui){

                    parentContext.columnResize( parentContext , event , ui );

                });
                $(this).on('resize', function(event , ui){

                    parentContext.columnResize( parentContext , event , ui );

                });
                $(this).on('resizestop', function(event , ui){

                    parentContext.columnResize( parentContext , event , ui );

                });

            });

            //console.log(container.find('.col'));

        });

        this.doLayout();

    },

    columnResize : function( parentContext , event , ui ){

        var currentCol     = $(event.currentTarget);
        var containerWidth = parentContext.containerWidth;
        var maxWidth       = 0;

        if( event.type == 'resizestart' ){

            var tWidth = 0;

            $('.CQAuthorColumnContainer .col').each(function( index , col ){

                tWidth = tWidth + $(this).width();

            });

            var gutterToAdd = ( 10 * ( parentContext.columnCount - 1 ) );

            tWidth = tWidth + gutterToAdd - currentCol.width() ;

            maxWidth = containerWidth - tWidth;

            currentCol.css('maxWidth' , maxWidth);

        }


        if( event.type == "resizestop" ){
            var tWidth = 0;

            $('.CQAuthorColumnContainer .col').each(function( index , col ){

                tWidth = tWidth + $(this).width();

            });

            var gutterToAdd = ( 10 * ( parentContext.columnCount - 1 ) );
            tWidth = tWidth + gutterToAdd;

            parentContext.canAddAnotherColumn = ( (containerWidth - tWidth) >= parentContext.colWidth );

        }


    },

    columnAdded : function( parentContext , event , ui ){

        console.log(event);
        
        var colContainer = $('.CQAuthorColumnContainer');
        var cols         = colContainer.find('.col');

        parentContext.colArray.push($(event.target));


    }

} );


CQ.Ext.reg('ddcolumnfield', Harbor.Widgets.DdColumnField);