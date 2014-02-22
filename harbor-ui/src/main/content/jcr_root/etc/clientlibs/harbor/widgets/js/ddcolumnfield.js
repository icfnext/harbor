Harbor.Widgets.DdColumnField = CQ.Ext.extend ( CQ.CustomContentPanel , {

    columnCount         : 0,
    canAddAnotherColumn : true,
    colWidth            : 0,
    containerWidth      : 0,
    colArray            : [],
    columnRequestFactory: {},
    columnManifest: {}, //stores col objects by data-column-id - will be serialized

    initComponent: function() {

        Harbor.Widgets.DdColumnField.superclass.initComponent.call(this);
        var parentContext = this;

        /*
            Set up request factory, using the content path of the component.
         */
        var parentDialog = this.findParentByType("dialog");
        var contentPath = parentDialog.responseScope.path;
        parentContext.columnRequestFactory = Harbor.Components.ColumnRow.getRequestFactoryForEditable(contentPath);

        this.buttonBar = new function(){

            this.addColumn = function( element ){



                if( parentContext.columnCount < 12 && parentContext.canAddAnotherColumn ){

                    var parentContainer    = $('#'+element.ownerCt.ownerCt.id);
                    var container          = parentContainer.find(".CQAuthorColumnContainer"); //TODO: make this class an attribute of the component
                    var parentWidth        = parentContainer.innerWidth();
                    var colWidth           = (parentWidth - 120) / 13;
                    parentContext.colWidth = colWidth;

                    parentContext.columnCount++;
                    //var col = parentContext.getColHtmlString(parentContext.columnCount, {width: colWidth});
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

                if( parentContext.columnCount != 0 ){
                    $(".col:last").remove();

                    parentContext.columnCount --;
                }

                //TODO: Set flag on this column in the manifest
                //make sure it can't be ID'd in normal use (think resize, etc)




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
                parentContext.columnCount += 1;
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

        this.ownerCt.ownerCt.ownerCt.on("show", function(){
            parentContext.columnRequestFactory.getColumnsInRow().then(function(data){
                var col_list = [];
                var tcolumnData;
                var tcolumnName;
                var tid;

                /*
                 Columns start with "column", lets filter them
                 out of data, and into a list
                 */
                //build a hash of columns
                for (var prop in data){
                    if (prop.search("column") != -1){
                        tcolumnName = prop;
                        tcolumnData = data[prop];
                        tid = parentContext.columnCount.toString();

                        var col_hash = {
                            "id": tid,
                            "data": tcolumnData,
                            "colHtml": parentContext.getColHtmlString(tid, {"colClass":parentContext.getSizeForColumn(tcolumnData)})
                        };

                        col_list.push(col_hash);
                        //increase col count
                        parentContext.columnCount += 1;
                    }
                }

                //if non-empty,
                if(col_list){

                    //zero out column container to replace the placeholder with the actual columns
                    var col_container = $("#"+parentContext.containerPanel.id).find(".CQAuthorColumnContainer");
                    $(col_container).html("");

                    for(var i = 0; i < col_list.length; i++){
                        var col = col_list[i];

                        //append column html to column container
                        $(col_container).append(col.colHtml);
                        parentContext.colArray.push(col.colHtml);

                        //Add column obj to "manifest"
                        parentContext.columnManifest[col.id] = col;
                    }
                }
                else{
                    //otherwise, Leave the lone column there
                }
            });
        });

        this.ownerCt.ownerCt.ownerCt.on("close", function(){
            //TODO: serialize manifest to JCR


        }

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

        //TODO: Resize column in the manifest


    },

    columnAdded : function( parentContext , event , ui ){

        console.log(event);
        
        var colContainer = $('.CQAuthorColumnContainer');
        var cols         = colContainer.find('.col');

        parentContext.colArray.push($(event.target));

        //TODO create column object for the manifest.

    },

    getSizeForColumn: function(columnData){
        if(columnData.hasOwnProperty("colClass") && columnData.colClass){
            return columnData.colClass;
        }
        else{
            return 1;
        }
    },

    getColHtmlString: function(id, options){
        var colClass = options.colClass || null;
        var width = options.width || null;

        var tmp = $("<div class='well col' data-column-id='" + id + "'><h3>" + id + "</h3></div>");

        if(colClass){
            var pixels = this.getDefaultColWidth() * parseInt(colClass);
            return tmp.width(pixels);
        }
        else if(width){
            return tmp.width(width);
        }
        else{
            return tmp.width(this.getDefaultColWidth());
        }
    },

    getDefaultColWidth: function(){
        //return (this.containerWidth - 120) / 13;
        var col_container_width = $("#"+this.containerPanel.id).find(".CQAuthorColumnContainer").innerWidth();
        return (col_container_width - 120) / 13;
    },

    createColData: function(id, data, html){

    }

} );


CQ.Ext.reg('ddcolumnfield', Harbor.Widgets.DdColumnField);