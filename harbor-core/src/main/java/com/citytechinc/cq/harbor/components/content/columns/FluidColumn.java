package com.citytechinc.cq.harbor.components.content.columns;

import com.citytechinc.cq.component.annotations.*;
import com.citytechinc.cq.component.annotations.editconfig.ActionConfig;
import com.citytechinc.cq.component.annotations.widgets.Selection;
import com.citytechinc.cq.library.components.AbstractComponent;
import com.citytechinc.cq.library.content.node.ComponentNode;
import com.citytechinc.cq.library.content.request.ComponentRequest;
import com.citytechinc.cq.component.annotations.Component;


@Component(value = "Fluid Column",
        actions = {"text:Fluid Column", "edit", "delete"},
        contentAdditionalProperties = {@ContentProperty(name="dependencies", value="harbor.components.content.fluidcolumnrow")},
        actionConfigs = {
                @ActionConfig(xtype = "tbseparator"),
                @ActionConfig(text="Move Left", handler="Harbor.Components.FluidColumnRow.moveColumnLeft"),
                @ActionConfig(text="Move Right", handler="Harbor.Components.FluidColumnRow.moveColumnRight")
        },
        listeners = {
                @Listener(name = "afterdelete", value = "REFRESH_PARENT"),
                @Listener(name = "afteredit", value = "REFRESH_PARENT"),
        },
        group = ".hidden"
)
public class FluidColumn extends AbstractComponent implements AbstractColumn{

    public FluidColumn(ComponentRequest request) {
        super(request);
    }

    public FluidColumn(ComponentNode node) {
        super(node);
    }

    @DialogField(fieldLabel = "Span", fieldDescription="The amount of the space in which the column row is contained which this column will occupy.")
    @Selection(type = Selection.SELECT, options = {
            @Option(text = "1/12", value = "span1"),
            @Option(text = "2/12", value = "span2"),
            @Option(text = "3/12", value = "span3"),
            @Option(text = "4/12", value = "span4"),
            @Option(text = "5/12", value = "span5"),
            @Option(text = "6/12", value = "span6"),
            @Option(text = "7/12", value = "span7"),
            @Option(text = "8/12", value = "span8"),
            @Option(text = "9/12", value = "span9"),
            @Option(text = "10/12", value = "span10"),
            @Option(text = "11/12", value = "span11"),
            @Option(text = "12/12", value = "span12")
    })
    public String getSpanClass() {
        return get("span", "span1");
    }

    @DialogField(fieldLabel = "Offset", fieldDescription="The amount of the space in which the column row is contained which will be used as a column offset.")
    @Selection(type = Selection.SELECT, options = {
            @Option(text = "--", value = "offset0"),
            @Option(text = "1/12", value = "offset1"),
            @Option(text = "2/12", value = "offset2"),
            @Option(text = "3/12", value = "offset3"),
            @Option(text = "4/12", value = "offset4"),
            @Option(text = "5/12", value = "offset5"),
            @Option(text = "6/12", value = "offset6"),
            @Option(text = "7/12", value = "offset7"),
            @Option(text = "8/12", value = "offset8"),
            @Option(text = "9/12", value = "offset9"),
            @Option(text = "10/12", value = "offset10"),
            @Option(text = "11/12", value = "offset11")
    })
    public String getOffsetClass() {
        return get("offset", "");
    }

    public String getName() {
        return this.getResource().getName();
    }
}