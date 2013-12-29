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

    public String getUniqueId(){
        return this.uniqueId;
    }

    public List<FluidColumn> getColumns(){
        return this.columns;
    }
}