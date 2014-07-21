Harbor.Widgets.DdColumnField = CQ.Ext.extend ( CQ.Ext.Panel , {

    columnCount          : 0,
    canAddAnotherColumn  : true,
    colWidth             : 0,
    containerWidth       : 0,
    columnContainerClass : 'CQAuthorColumnContainer',
    columnClassPrefix    : 'col-xs-',
    columnRequestFactory : {},
    columnManifest       : {}, //stores col objects by data-column-id - will be serialized
    columnPathNameBase   : "column-id-",
    hiddenFields: [],

    initComponent: function() {

        Harbor.Widgets.DdColumnField.superclass.initComponent.call(this);
        var parentContext = this;

        var parentDialog = this.findParentByType("dialog");

        this.buttonBar = new function(){

            this.addColumn = function( element ){

                parentContext.addColumn( parentContext );

            }

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


        parentDialog.on("loadcontent", function(dialog, records, opts, success){
            var _this = this;
            var d = dialog;
            var r = records;
            var o = opts;

            var response = r[0].json;
            //Zero out the manifest
            parentContext.columnManifest = {};
            //Zero out the column count
            parentContext.columnCount = 0;
            //zero out the can add another column field
            parentContext.canAddAnotherColumn = true;
            //zero out column container
            var col_container = $("#"+parentContext.containerPanel.id).find("." + parentContext.columnContainerClass);
            $(col_container).html("");

            var col_list = [];
            var tcolumnData;
            var tcolumnName;
            var tid;

            /*
                Columns start with "column", lets filter them
                out of the data object, and into a list
            */
            for (var prop in response){
                if (prop.search("column") != -1){
                    tcolumnName = prop;
                    tcolumnData = response[prop];
                    tid = parentContext.columnCount.toString();

                    var col_hash = parentContext.createColData(tid, {
                        colSize: parseInt(tcolumnData["colSize"]) || 1,
                        maxColSize: parseInt(tcolumnData["maxColSize"]) || null,
                        class: tcolumnData["class"] || null,
                        canAddColumn: tcolumnData["canAddColumn"] || null,
                        name: tcolumnName
                    }, null);

                    col_list.push(col_hash);
                }
            }

            //if non-empty,
            if(col_list.length > 0){

                for(var i = 0; i < col_list.length; i++){
                    var col = col_list[i];

                    /*
                        Invoke add column with the column data that was returned via ajax.
                    */
                    parentContext.addColumn(parentContext, {
                        data: col.data
                    });

                }

            }
            else{
                //otherwise, Leave the lone column there
                if( parentContext.columnCount == 0 ){
                    parentContext.addColumn( parentContext );
                }

            }
        });

        this.doLayout();

        parentDialog.on("beforesubmit", function(e){

            parentContext.serializeToDialogForm();

        })

    },

    columnResized : function( ){

        var totalColumnsSpanned = 0;

        var parentContext = this;

        $.each( this.columnManifest , function( index , col ) {

            var colWidth = parseInt(col.data.colSize);
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

    addColumn : function( parentContext , options ){
        options = options || {};

        if( this.columnCount < 12 && this.canAddAnotherColumn ){

            this.columnCount++;

            var parentPanel  = $( "#" + this.containerPanel.id );
            var colContainer = parentPanel.find( "." + this.columnContainerClass);
            var col          = this.getColHtmlString( this.columnCount, options );

            col.find(".more").click( function( e ){

                var id = $(this).parent().parent().parent().data("column-id");

                parentContext.expandColumn( id );

                e.preventDefault();
            } );

            col.find(".less").click( function( e ){

                var id = $(this).parent().parent().parent().data("column-id");

                parentContext.reduceColumn( id );

                e.preventDefault();
            } );

            colContainer.append( col );

            var colData =   {
                colSize      : options.data.colSize || 1 ,
                class        : options.data.class || this.columnClassPrefix + (options.width || 1) ,
                maxColSize   : options.data.maxColSize || this.getMaxColSize( this.columnCount ),
                canAddColumn : options.data.canAddColumn || true,
                name         : this.columnPathNameBase + this.columnCount.toString()
            };

            //This will execute if a column name was pulled from the JCR
            if(options.data.name){
                colData.name = options.data.name;
            }
            /*
                If this else branch executes, then we can flag this column as "new"
                aka: "added via the dialog". We do this by setting the name to something
                false-ish
            */
            else{
                colData.isNewColumn = true;
            }

            this.columnManifest[ this.columnCount ] = this.createColData(
                this.columnCount ,
                colData,
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
                canAddColumn : false,
                name         : this.columnManifest[ id ].data.name
            }

            lastCol.remove();
            this.columnCount --;
        }
    },


    getColHtmlString: function(id, options){
        options.data             = options.data || {};
        var colWidth             = options.data.colSize || 1;
        var colClass             = this.columnClassPrefix + colWidth;
        var buttonContainerClass = "x-small";

        if( colWidth != 1 ){

            if( colWidth >=2 && colWidth <= 4 ){
                buttonContainerClass = 'small';
            }else if( colWidth > 4 ){
                buttonContainerClass = 'regular';
            }

        }

        var tmp = $("<div class='" + colClass + " col' data-column-id='" + id + "'>"+
                        "<div class='well'>" +
                            "<h3>" + id + "</h3> " +
                            "<div class='btn-group " + buttonContainerClass + "'>" +
                                "<button class='btn btn-default less'>" +
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


        if(col.data.canAddColumn && col.data.colSize != 12){
            col.data.colSize = col.data.colSize + 1;
            col.data.class   = this.columnClassPrefix + col.data.colSize;


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


        if(col.data.colSize != 1){
            col.data.colSize =  col.data.colSize - 1;
            col.data.class   = this.columnClassPrefix + col.data.colSize;


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

    serializeToDialogForm: function(){

        var manifest           = this.columnManifest;
        var hiddenFieldsObject = {};
        var dialog             = this.findParentByType("dialog");


        /*
         Delete fields for the current Column Name
         */
        var columnPredicate = function(item){
            return item.name.indexOf("column-id-") != -1;
        }

        dialog.formPanel.items.each(function(item/*, index, length*/) {
            if(item.xtype == "hidden"){
                if (columnPredicate(item)) {
                    dialog.formPanel.remove(item);
                }
            }
        });


        for( var key in manifest ){

            var columnObject = manifest[key];

            var hiddenFieldObject = Harbor.Components.ColumnRow.manifestUtil.generateHiddenFieldObject(columnObject.data);

            //Add all elements in the hidden field object created for the column into the hidden field object we add to the dialog

            for(var prop in hiddenFieldObject){
                if (hiddenFieldObject.hasOwnProperty(prop)){
                    hiddenFieldsObject[prop] = hiddenFieldObject[prop];
                }
            }
        }

        dialog.addHidden( hiddenFieldsObject );

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