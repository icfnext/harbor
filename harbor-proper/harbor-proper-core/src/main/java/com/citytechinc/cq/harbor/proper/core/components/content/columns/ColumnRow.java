package com.citytechinc.cq.harbor.proper.core.components.content.columns;

import com.citytechinc.cq.component.annotations.*;
import com.citytechinc.cq.component.annotations.widgets.DialogFieldSet;
import com.citytechinc.cq.component.annotations.widgets.Selection;
import com.citytechinc.cq.harbor.proper.api.constants.bootstrap.Bootstrap;
import com.citytechinc.cq.harbor.proper.core.components.mixins.classifiable.Classification;
import com.citytechinc.cq.harbor.proper.core.constants.groups.ComponentGroups;
import com.citytechinc.cq.library.components.AbstractComponent;
import com.citytechinc.cq.library.components.annotations.AutoInstantiate;
import com.citytechinc.cq.library.content.node.ComponentNode;
import com.citytechinc.cq.library.content.request.ComponentRequest;
import org.apache.sling.api.resource.Resource;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component(value = "Column Row",
        group = ComponentGroups.HARBOR_SCAFFOLDING,
        actions = {"text: Column Row", "-", "edit", "-", "copymove", "delete", "-", "insert"},
        contentAdditionalProperties = {
                @ContentProperty(name="dependencies", value="harbor.components.content.columnrow")
        },
        listeners = {
                @Listener(name = "afterinsert", value = "REFRESH_PAGE")
        },
        allowedParents = "*/parsys",
        resourceSuperType = "foundation/components/parbase"
)
@AutoInstantiate( instanceName = "ColumnRow" )
public class ColumnRow  extends AbstractComponent {
    private final List<Column> columns;

    @DialogField(xtype="ddcolumnfield", ranking = 1)
    private String placeholderColumnConfiguration;

    public ColumnRow(ComponentRequest request) {
        super(request);

        this.columns = new ArrayList<Column>();

        Iterator<Resource> columnResourceIterator = request.getResource().listChildren();

        while (columnResourceIterator.hasNext()) {
            this.columns.add(new Column(columnResourceIterator.next().adaptTo(ComponentNode.class)));
        }
    }

    @DialogField(xtype="selection",
            fieldLabel="Grid Options",
            fieldDescription="Bootstrap Grid Options",
            ranking = 2
    )
    @Selection(type = Selection.SELECT, options = {
            @Option(text = "Extra Small Devices (Phones)", value = Bootstrap.GRID_EXTRA_SMALL, qtip = "Never Stacked"),
            @Option(text = "Small Devices (Tablets)", value = Bootstrap.GRID_SMALL, qtip = "From Stacked to Horizontal at the @screen-sm-min bootstrap breakpoint"),
            @Option(text = "Medium Devices (Desktops)", value = Bootstrap.GRID_MEDIUM, qtip = "From Stacked to Horizontal at the @screen-md-min bootstrap breakpoint"),
            @Option(text = "Large Devices (Desktops)", value = Bootstrap.GRID_LARGE, qtip = "From Stacked to Horizontal at the @screen-lg-min bootstrap breakpoint")
    })
    public String getGridSize() {
        return get("gridSize", Bootstrap.GRID_MEDIUM);
    }

    public List<Column> getColumns(){
        return this.columns;
    }

    @DialogField(ranking = 3 )
    @DialogFieldSet
    public Classification getClassification() {
        return new Classification(this.request);
    }

}