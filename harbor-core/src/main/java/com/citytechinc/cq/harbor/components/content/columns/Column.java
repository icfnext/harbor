package com.citytechinc.cq.harbor.components.content.columns;

import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.ContentProperty;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.Listener;
import com.citytechinc.cq.component.annotations.Option;
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
        group = ".hidden"
)
public class Column extends AbstractComponent{
    public Column(ComponentRequest req) {
        super(req);
    }
    public Column(ComponentNode node) {
        super(node);
    }

    @DialogField(fieldLabel = "Inherit Content?",
            fieldDescription = "Inherit column content from parent page.")
    @Selection(type=Selection.CHECKBOX, options = {
            @Option(text="", value = "true")
    })
    public Boolean getIsInherited(){
        return get("isInherited", "").equals("true");
    }

    @DialogField(fieldLabel = "Column Width",
            fieldDescription="The amount of the space in which the column row is contained which this column will occupy.")
    @Selection(type = Selection.SELECT, options = {
            @Option(text = "1/12", value = "1"),
            @Option(text = "2/12", value = "2"),
            @Option(text = "3/12", value = "3"),
            @Option(text = "4/12", value = "4"),
            @Option(text = "5/12", value = "5"),
            @Option(text = "6/12", value = "6"),
            @Option(text = "7/12", value = "7"),
            @Option(text = "8/12", value = "8"),
            @Option(text = "9/12", value = "9"),
            @Option(text = "10/12", value = "10"),
            @Option(text = "11/12", value = "11"),
            @Option(text = "12/12", value = "12")
    })
    public String getColSize() {
        return get("colSize", "1");
    }

    public String getName() {
        return this.getResource().getName();
    }

    @DialogField
    @DialogFieldSet
    public Classification getClassification() {
        return new Classification(this.request);
    }
}