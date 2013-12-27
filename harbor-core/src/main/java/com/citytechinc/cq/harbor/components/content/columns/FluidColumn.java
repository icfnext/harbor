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

    @DialogField(fieldLabel = "Column Width", fieldDescription="The amount of the space in which the column row is contained which this column will occupy.")
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
    public String getColClass() {
        return get("colClass", "1");
    }

    @DialogField(fieldLabel = "Offset", fieldDescription="The amount of the space in which the column row is contained which will be used as a column offset.")
    @Selection(type = Selection.SELECT, options = {
            @Option(text = "--", value = "offset-0"),
            @Option(text = "1/12", value = "offset-1"),
            @Option(text = "2/12", value = "offset-2"),
            @Option(text = "3/12", value = "offset-3"),
            @Option(text = "4/12", value = "offset-4"),
            @Option(text = "5/12", value = "offset-5"),
            @Option(text = "6/12", value = "offset-6"),
            @Option(text = "7/12", value = "offset-7"),
            @Option(text = "8/12", value = "offset-8"),
            @Option(text = "9/12", value = "offset-9"),
            @Option(text = "10/12", value = "offset-10"),
            @Option(text = "11/12", value = "offset-11")
    })
    public String getOffsetClass() {
        return get("offsetClass", "");
    }

    public String getName() {
        return this.getResource().getName();
    }
}