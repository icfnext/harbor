package com.citytechinc.cq.harbor.imperium.components.layout.columns;

import com.citytechinc.aem.imperium.proper.core.components.layout.AbstractLayoutComponent;
import com.citytechinc.cq.component.annotations.*;
import com.citytechinc.cq.component.annotations.editconfig.ActionConfig;
import com.citytechinc.cq.component.annotations.widgets.DialogFieldSet;
import com.citytechinc.cq.component.annotations.widgets.Selection;
import com.citytechinc.cq.harbor.proper.api.constants.bootstrap.Bootstrap;
import com.citytechinc.cq.harbor.proper.core.components.mixins.classifiable.Classification;
import com.citytechinc.cq.library.components.annotations.AutoInstantiate;
import com.citytechinc.cq.library.content.node.ComponentNode;
import com.citytechinc.cq.library.content.request.ComponentRequest;
import com.google.common.collect.Lists;

import java.util.List;

/**
 *
 * Note: Most of the properties germane to the .content.xml are excluded as a hand-crafted .content.xml exists under the component definition
 */
@Component(value = "Layout Column Row",
        actions = {"text: Column Row", "-", "edit", "-", "copymove", "delete", "-", "insert"},
        actionConfigs = {
                @ActionConfig(xtype = "tbseparator"),
                @ActionConfig(text = "Add Column", handler = "function(){ Harbor.Imperium.Components.Layout.ColumnRow.addColumn(this) }")
        },
        listeners = {
                @Listener(name = "afterinsert", value = "REFRESH_PAGE")
        },
        allowedParents = "*/parsys",
        name = "layoutcolumnrow",
        path = "layout",
        layout = "rollover"
)
@AutoInstantiate(instanceName = "ColumnRow")
public class ColumnRow  extends AbstractLayoutComponent {

    private List<Column> columns;
    private Classification classification;
    private String gridSize;

    public ColumnRow(ComponentRequest request) {
        super(request);
    }

    @DialogField(xtype="selection",
            fieldLabel="Grid Options",
            fieldDescription="Bootstrap Grid Options")
    @Selection(type = Selection.SELECT, options = {
            @Option(text = "Extra Small Devices (Phones)", value = Bootstrap.GRID_EXTRA_SMALL, qtip = "Never Stacked"),
            @Option(text = "Small Devices (Tablets)", value = Bootstrap.GRID_SMALL, qtip = "From Stacked to Horizontal at the @screen-sm-min bootstrap breakpoint"),
            @Option(text = "Medium Devices (Desktops)", value = Bootstrap.GRID_MEDIUM, qtip = "From Stacked to Horizontal at the @screen-md-min bootstrap breakpoint"),
            @Option(text = "Large Devices (Desktops)", value = Bootstrap.GRID_LARGE, qtip = "From Stacked to Horizontal at the @screen-lg-min bootstrap breakpoint")
    })
    public String getGridSize() {

        if (gridSize == null) {
            gridSize = get("gridSize", Bootstrap.GRID_MEDIUM);
        }

        return gridSize;

    }

    public List<Column> getColumns(){
        if (columns == null) {
            columns = Lists.newArrayList();

            for (ComponentNode currentLayoutColumnComponentNode : getComponentNodes()) {
                this.columns.add(new Column(currentLayoutColumnComponentNode, isLayoutMode()));
            }
        }

        return columns;
    }

    @DialogField
    @DialogFieldSet
    public Classification getClassification() {
        if (classification == null) {
            classification = new Classification(getResource().adaptTo(ComponentNode.class));
        }

        return classification;
    }

}