package com.citytechinc.cq.harbor.components.content.columns;

import com.citytechinc.cq.component.annotations.*;
import com.citytechinc.cq.component.annotations.widgets.DialogFieldSet;
import com.citytechinc.cq.component.annotations.widgets.Selection;
import com.citytechinc.cq.harbor.components.mixins.classifiable.Classification;
import com.citytechinc.cq.library.components.AbstractComponent;
import com.citytechinc.cq.library.content.node.ComponentNode;
import com.citytechinc.cq.library.content.request.ComponentRequest;

@Component(value = "Column",
        actions = {"text:Column", "edit", "delete"},
        listeners = {
                @Listener(name = "afterdelete", value = "REFRESH_PARENT"),
                @Listener(name = "afteredit", value = "REFRESH_PARENT"),
        },
        group = ".hidden",
        tabs = { @Tab(title = "Column Row"),
            @Tab(title = "Advanced Configuration") }
)
public class Column extends AbstractComponent{
    public Column(ComponentRequest req) {
        super(req);
    }
    public Column(ComponentNode node) {
        super(node);
    }

    public String getName() {
        return this.getResource().getName();
    }

    public String getColSize(){
        return get("colSize", "1");
    }

    @DialogField(tab = 1)
    @DialogFieldSet
    public Classification getClassification() {
        return new Classification(this.request);
    }

    @DialogField(fieldLabel = "Inherit Content?",
            fieldDescription = "Inherit column content from parent page. The component layout of the child page must exactly match that of the parent page. This Column looks along the same content path in the parent's tree, and will display content from a Column in the parent page at the same content location. If the component structure does not match, paragraph inheritance will not function.",
            tab = 2
    )
    @Selection(type=Selection.CHECKBOX, options = {
            @Option(text="", value = "true")
    })
    public Boolean getIsInherited(){
        return get("isInherited", "").equals("true");
    }
}