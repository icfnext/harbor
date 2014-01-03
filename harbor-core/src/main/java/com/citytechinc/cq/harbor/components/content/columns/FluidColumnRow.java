package com.citytechinc.cq.harbor.components.content.columns;

import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.ContentProperty;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.Option;
import com.citytechinc.cq.component.annotations.editconfig.ActionConfig;
import com.citytechinc.cq.component.annotations.widgets.Selection;
import com.citytechinc.cq.library.components.AbstractComponent;
import com.citytechinc.cq.library.content.node.ComponentNode;
import com.citytechinc.cq.library.content.request.ComponentRequest;
import org.apache.sling.api.resource.Resource;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component(value = "Fluid Column Row",
        actions = {"text: Fluid Column Row", "-", "edit", "-", "copymove", "delete", "-", "insert"},
        contentAdditionalProperties = {@ContentProperty(name="dependencies", value="harbor.components.content.fluidcolumnrow")},
        actionConfigs = {
                @ActionConfig(xtype = "tbseparator"),
                @ActionConfig(text = "Add Column", handler = "function(){ Harbor.Components.FluidColumnRow.addColumn(this) }"),
                @ActionConfig(text = "Add n Columns", handler = "function(){ Harbor.Components.FluidColumnRow.addMultipleColumns(this) }")
        },
        allowedParents = "*/parsys",
        resourceSuperType = "foundation/components/parbase"
)
public class FluidColumnRow  extends AbstractComponent {
    private final List<FluidColumn> columns;
    private final String uniqueId;
    private static final String GRID_EXTRA_SMALL = "col-xs-";
    private static final String GRID_SMALL = "col-sm-";
    private static final String GRID_MEDIUM = "col-md-";
    private static final String GRID_LARGE = "col-lg-";

    public FluidColumnRow(ComponentRequest request) {
        super(request);

        this.columns = new ArrayList<FluidColumn>();

        Iterator<Resource> columnResourceIterator = request.getResource().listChildren();

        while (columnResourceIterator.hasNext()) {
            this.columns.add(new FluidColumn(columnResourceIterator.next().adaptTo(ComponentNode.class)));
        }

        this.uniqueId = request.getResource().getPath().replace("/", "__").replace(":", "___");
    }

    @DialogField(fieldLabel = "Is Full Width?",
        fieldDescription = "Does the Row span the full width of the entire container?")
    @Selection(type=Selection.CHECKBOX, options = {
            @Option(text="Full Width", value = "padding: 0 15px;")
    })
    public String getRowWidthPadding(){
        return get("rowWidthPadding", "");
    }

    @DialogField(xtype="selection", fieldLabel="Grid Options",
            fieldDescription="Bootstrap Grid Options")
    @Selection(type = Selection.SELECT, options = {
            @Option(text = "Extra Small Devices (Always Horizontal)", value = GRID_EXTRA_SMALL, qtip = "Never Stacked"),
            @Option(text = "Small Devices (Horizontal at 768px)", value = GRID_SMALL, qtip = "From Stacked to Horizontal at 768px"),
            @Option(text = "Medium Devices (Horizontal at 992px)", value = GRID_MEDIUM, qtip = "From Stacked to Horizontal at 992px"),
            @Option(text = "Large Devices (Horizontal at 1200px)", value = GRID_LARGE, qtip = "From Stacked to Horizontal at 1200px")
    })
    public String getGridSize() {
        return get("gridSize", GRID_MEDIUM);
    }

    public String getUniqueId(){
        return this.uniqueId;
    }

    public List<FluidColumn> getColumns(){
        return this.columns;
    }
}