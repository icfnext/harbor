package com.citytechinc.cq.harbor.components.content.columns;



import com.citytechinc.cq.component.annotations.*;
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
                @ActionConfig(text = "Add Column", handler = "function(){ Harbor.Components.FluidColumnRow.addColumn(this) }")
        },
        allowedParents = "*/parsys",
        resourceSuperType = "foundation/components/parbase"
)
public class FluidColumnRow  extends AbstractComponent {
    private final List<AbstractColumn> columns;
    private final String uniqueId;
    private final String name;

    public FluidColumnRow(ComponentRequest request) {
        super(request);

        this.columns = new ArrayList<AbstractColumn>();

        Iterator<Resource> columnResourceIterator = request.getResource().listChildren();

        while (columnResourceIterator.hasNext()) {
            this.columns.add(new FluidColumn(columnResourceIterator.next().adaptTo(ComponentNode.class)));
        }

        this.name = request.getResource().getName();
        this.uniqueId = request.getResource().getPath().replace("/", "__").replace(":", "___");
    }

    @DialogField(xtype="selection", fieldLabel="Layout", fieldDescription="The layout to apply to the columns of this column row.&lt;br&gt;In a fixed layout, each column takes up a set number of pixels.&lt;br&gt;In a fluid layout, each column takes up a set percentage of the total available space.")
    @Selection(type = Selection.SELECT, options = { @Option(text = "Fixed", value = "fixed"),
        @Option(text = "Fluid", value = "fluid")} )
    public String getLayout() {
        return get("layout", "fluid");
    }

    public Boolean getIsFluid() {
        return get("layout", "fixed").equals("fluid");
    }

    public String getUniqueId(){
        return this.uniqueId;
    }

    public List<AbstractColumn> getColumns(){
        return this.columns;
    }

    public String getName(){
        return this.getName();
    }
}