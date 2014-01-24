Harbor.Widgets.DdColumnField = CQ.Ext.extend ( CQ.form.CompositeField , {

    initComponent: function() {

        alert('dd component is here!')
        console.log(this);


        Harbor.Widgets.DdColumnField.superclass.initComponent.call(this);
        var parentScope = this;

        // Container panel for original SmartFile panels
        this.containerPanel = new CQ.Ext.Panel({
            "region": "center",
            "ctCls": CQ.DOM.encodeClass("ddcolumnfield-" + this.name),
            "activeItem": 0,
            "autoScroll": false,
            "bodyStyle": "padding: 0px; overflow: hidden;",
            "layout": "card"
        });
        this.add(this.containerPanel);


    }

} );


CQ.Ext.reg('ddcolumnfield', Harbor.Widgets.DdColumnField);