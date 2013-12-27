package com.citytechinc.cq.harbor.components.content.columns;

import com.citytechinc.cq.component.annotations.*;
import com.citytechinc.cq.component.annotations.editconfig.ActionConfig;
import com.citytechinc.cq.component.annotations.widgets.Selection;
import com.citytechinc.cq.library.components.AbstractComponent;
import com.citytechinc.cq.library.content.node.ComponentNode;
import com.citytechinc.cq.library.content.request.ComponentRequest;
import com.citytechinc.cq.component.annotations.Component;


@Component(value = "Column",
        actions = {"text:Column", "edit", "delete"},
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
public class Column extends AbstractComponent implements AbstractColumn{

    public Column(final ComponentRequest request) {
        super(request);
    }

    public Column(ComponentNode node) {
        super(node);
    }

    @DialogField(fieldLabel="Span", fieldDescription="The width which the column will span over - NOTE: The total of all offsets and spans of a given row should not be greater than 12")
    @Selection(type = Selection.SELECT, options = {
            @Option(text = "1", value = "span1"),
            @Option(text = "2", value = "span2"),
            @Option(text = "3", value = "span3"),
            @Option(text = "4", value = "span4"),
            @Option(text = "5", value = "span5"),
            @Option(text = "6", value = "span6"),
            @Option(text = "7", value = "span7"),
            @Option(text = "8", value = "span8"),
            @Option(text = "9", value = "span9"),
            @Option(text = "10", value = "span10"),
            @Option(text = "11", value = "span11"),
            @Option(text = "12", value = "span12")
    })
    public String getSpanClass() {
        return get("span", "span1");
    }

    @DialogField(fieldLabel="Offset", fieldDescription="The offset of the Column - NOTE: The total of all offsets and spans of a given row should not be greater than 12")
    @Selection(type = Selection.SELECT, options = {
            @Option(text = "--", value = "offset0"),
            @Option(text = "1", value = "offset1"),
            @Option(text = "2", value = "offset2"),
            @Option(text = "3", value = "offset3"),
            @Option(text = "4", value = "offset4"),
            @Option(text = "5", value = "offset5"),
            @Option(text = "6", value = "offset6"),
            @Option(text = "7", value = "offset7"),
            @Option(text = "8", value = "offset8"),
            @Option(text = "9", value = "offset9"),
            @Option(text = "10", value = "offset10"),
            @Option(text = "11", value = "offset11")
    })
    public String getOffsetClass() {
        return get("offset", "");
    }

    public String getName() {
        return this.getResource().getName();
    }
}