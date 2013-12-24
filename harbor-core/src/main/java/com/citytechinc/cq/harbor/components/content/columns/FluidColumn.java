package com.citytechinc.cq.harbor.components.content.columns;

import com.citytechinc.cq.component.annotations.*;
import com.citytechinc.cq.component.annotations.widgets.Selection;
import com.citytechinc.cq.library.components.AbstractComponent;
import com.citytechinc.cq.library.content.node.ComponentNode;
import com.citytechinc.cq.library.content.request.ComponentRequest;
import com.citytechinc.cq.component.annotations.Component;


@Component(value = "Fluid Column",
        contentAdditionalProperties = {@ContentProperty(name="dependencies", value="harbor.components.content.fluidcolumnrow")},
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