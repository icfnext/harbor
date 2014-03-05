Harbor.Widgets.DdColumnField = CQ.Ext.extend ( CQ.CustomContentPanel , {

    columnCount          : 0,
    canAddAnotherColumn  : true,
    colWidth             : 0,
    containerWidth       : 0,
    columnContainerClass : 'CQAuthorColumnContainer',
    columnClassPrefix    : 'col-xs-',
    columnRequestFactory : {},
    columnManifest       : {}, //stores col objects by data-column-id - will be serialized

    initComponent: function() {

        Harbor.Widgets.DdColumnField.superclass.initComponent.call(this);
        var parentContext = this;

        /*
            Set up request factory, using the content path of the component.
         */
        var parentDialog = this.findParentByType("dialog");
        var contentPath  = parentDialog.responseScope.path;

        parentContext.columnRequestFactory = Harbor.Components.ColumnRow.getRequestFactoryForEditable(contentPath);

        this.buttonBar = new function(){

            this.addColumn = function( element ){

                parentContext.addColumn( parentContext );

            },

            this.removeColumn = function(){

                parentContext.removeColumn();

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
                var wrapper   = $('<div class="row"/>');
                var container = $('<div class="row ' + parentContext.columnContainerClass + '"/>');
                wrapper.append(container);

                return wrapper.html();
            }()
        });

        this.add(this.containerPanel);


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

                        var html = parentContext.getColHtmlString(tid, {"colClass":parentContext.getSizeForColumn(tcolumnData)})
                        var col_hash = parentContext.createColData(tid, tcolumnData, html);

                        col_list.push(col_hash);
                        //increase col count

                    }
                }

                //if non-empty,
                if(col_list.length > 0){

                    //zero out column container to replace the placeholder with the actual columns
                    var col_container = $("#"+parentContext.containerPanel.id).find("." + parentContext.columnContainerClass);
                    $(col_container).html("");

                    for(var i = 0; i < col_list.length; i++){
                        var col = col_list[i];

                        //append column html to column container
                        $(col_container).append(col.colHtml);
                        parentContext.colArray.push(col.colHtml);
                        parentContext.columnCount += 1;

                        //Add column obj to "manifest"
                        parentContext.columnManifest[col.id] = col;
                    }

                }
                else{
                    //otherwise, Leave the lone column there
                    if( parentContext.columnCount == 0 ){
                        parentContext.addColumn( parentContext );
                    }

                }
            });
        });

        //TODO: find out why button event isn't accessible from this.ownerCt.ownerCt.ownerCt.buttons[0]
        this.ownerCt.ownerCt.ownerCt.on("hide", function(t){
            console.log(t);
            console.log("dialog hidden ")
            //TODO: serialize manifest to JCR
        });

        this.doLayout();

    },

    columnResized : function( ){

        var totalColumnsSpanned = 0;

        var parentContext = this;

        $.each( this.columnManifest , function( index , col ) {

            var colWidth = col.data.colSize;
            totalColumnsSpanned = totalColumnsSpanned + colWidth;

        } );



        $.each( this.columnManifest , function( index , col ) {

            if( totalColumnsSpanned < 12 ){
                col.data.maxColSize   = parentContext.getMaxColSize( col.id );
                col.data.canAddColumn = true;
                parentContext.canAddAnotherColumn = true;
            }else{
                col.data.maxColSize   = col.data.colSize;
                col.data.canAddColumn = false;
                parentContext.canAddAnotherColumn = false;
            }


        } )




    },

    addColumn : function( parentContext ){

        if( this.columnCount < 12 && this.canAddAnotherColumn ){

            this.columnCount++;

            var parentPanel  = $( "#" + this.containerPanel.id );
            var colContainer = parentPanel.find( "." + this.columnContainerClass);
            var col          = this.getColHtmlString( this.columnCount, {} );

            col.find(".more").click( function( e ){

                var id = $(this).parent().parent().parent().data("column-id");

                parentContext.expandColumn( id );

                console.log(id);

                e.preventDefault();
            } );

            col.find(".less").click( function( e ){

                var id = $(this).parent().parent().parent().data("column-id");

                parentContext.reduceColumn( id );

                console.log(id);

                e.preventDefault();
            } )


            colContainer.append( col );

            this.columnManifest[ this.columnCount ] = this.createColData(
                this.columnCount ,
                {
                    colSize      : 1 ,
                    class        : this.columnClassPrefix + 1,
                    maxColSize   : this.getMaxColSize( this.columnCount ),
                    canAddColumn : true
                } ,
                col
            );

            this.columnResized();

        }

    },

    removeColumn : function( ){

        var lastCol = $(".col:last");

        if( this.columnCount != 1 ){


            var id = lastCol.data("column-id");
            this.columnManifest[ id ].data = {
                colSize      : 0,
                maxColSize   : 0,
                class        : '',
                canAddColumn : false
            }


            lastCol.remove();
            this.columnCount --;
        }

        //TODO: Set flag on this column in the manifest
        //make sure it can't be ID'd in normal use (think resize, etc)

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
        var colWidth = options.width || '1';
        var colClass = this.columnClassPrefix + colWidth;

        var tmp = $("<div class='" + colClass + " col' data-column-id='" + id + "'>"+
                        "<div class='well'>" +
                            "<h3>" + id + "</h3> " +
                            "<div class='x-small'>" +
                                "<button class='btn btn-default less disabled'>" +
                                    "<i class='fa fa-step-backward'></i>" +
                                "</button>" +
                                "<button class='btn btn-default more'>" +
                                    "<i class='fa fa-step-forward'></i>" +
                                "</button>" +
                            "</div>" +
                        "</div>" +
                    "</div>");

        return tmp;

    },

    expandColumn: function( columnID ){

        var col         = this.columnManifest[ parseInt(columnID) ];
        var oldColClass = col.data.class;

        console.log(col.data);

        if(col.data.canAddColumn && col.data.colSize != 12){
            col.data.colSize = col.data.colSize + 1;
            col.data.class   = this.columnClassPrefix + col.data.colSize;

            console.log(col.data);

            if( col.data.colSize > 1 && col.data.colSize < 4 ){
                col.colHtml.removeClass( oldColClass ).addClass( col.data.class ).find( ".x-small" ).removeClass( "x-small" ).addClass( "small" ).addClass( "btn-group" );
                col.colHtml.find(".less").removeClass("disabled");
            }else{
                col.colHtml.removeClass( oldColClass ).addClass( col.data.class ).find( ".small" ).removeClass( "small" ).addClass( "regular" );
            }

            this.columnResized( );
        }


    },

    reduceColumn: function( columnID ){

        var col         = this.columnManifest[ parseInt(columnID) ];
        var oldColClass = col.data.class;

        console.log(col.data);

        if(col.data.colSize != 1){
            col.data.colSize = col.data.colSize - 1;
            col.data.class   = this.columnClassPrefix + col.data.colSize;

            console.log(col.data);

            col.colHtml.removeClass( oldColClass ).addClass( col.data.class )

            if( col.data.colSize <= 3 && col.data.colSize > 1){
                col.colHtml.removeClass( oldColClass ).addClass( col.data.class).find(".regular").removeClass("regular").addClass("small");
            }else if( col.data.colSize == 1 ){
                col.colHtml.removeClass( oldColClass ).addClass( col.data.class).find(".small").removeClass("small").addClass("x-small");
                col.colHtml.find(".less").addClass("disabled");
            }else{
                col.colHtml.removeClass( oldColClass ).addClass( col.data.class);
            }

            this.columnResized();
        }


    },

    getMaxColSize : function( columnID ){

        var totalColumnsSpanned = 0;

        $.each( this.columnManifest , function( index , col ) {

            var colWidth = col.data.colSize;
            totalColumnsSpanned = totalColumnsSpanned + colWidth;

        } );

        if( totalColumnsSpanned < 12 ){

            var colsLeft = 12 - totalColumnsSpanned;

            return colsLeft;

        }

    },

    /**
     *
     * @param id
     * @param data = {
     *                  colSize      : integer,
     *                  maxColSize   : integer,
     *                  class        : string,
     *                  canAddColumn : boolean
     *               }
     * @param html
     * @returns {{id: *, data: *, colHtml: *}}
     */

    createColData: function(id, data, html){
        return {
            "id": id,
            "data": data,
            "colHtml": html
        }
    }

} );


CQ.Ext.reg('ddcolumnfield', Harbor.Widgets.DdColumnField);