package com.citytechinc.aem.harbor.core.components.content.columns;

import java.util.List;

import com.google.common.collect.Lists;

import com.citytechinc.aem.bedrock.api.components.annotations.AutoInstantiate;
import com.citytechinc.aem.bedrock.api.node.ComponentNode;
import com.citytechinc.aem.bedrock.core.components.AbstractComponent;
import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.ContentProperty;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.Listener;
import com.citytechinc.cq.component.annotations.Option;
import com.citytechinc.cq.component.annotations.widgets.DialogFieldSet;
import com.citytechinc.cq.component.annotations.widgets.Selection;
import com.citytechinc.aem.harbor.api.constants.bootstrap.Bootstrap;
import com.citytechinc.aem.harbor.core.components.mixins.classifiable.Classification;
import com.citytechinc.aem.harbor.core.constants.groups.ComponentGroups;

@Component(value = "Column Row", group = ComponentGroups.HARBOR_SCAFFOLDING, actions = { "text: Column Row", "-",
	"edit", "-", "copymove", "delete", "-", "insert" }, contentAdditionalProperties = { @ContentProperty(name = "dependencies", value = "harbor.components.content.columnrow") }, listeners = { @Listener(name = "afterinsert", value = "REFRESH_PAGE") }, allowedParents = "*/parsys", resourceSuperType = "foundation/components/parbase")
@AutoInstantiate(instanceName = "ColumnRow")
public class ColumnRow extends AbstractComponent {
	private List<Column> columns;

	@DialogField(xtype = "ddcolumnfield", ranking = 1)
	private String placeholderColumnConfiguration;

	@DialogField(xtype = "selection", fieldLabel = "Grid Options", fieldDescription = "Bootstrap Grid Options", ranking = 2)
	@Selection(type = Selection.SELECT, options = {
		@Option(text = "Extra Small Devices (Phones)", value = Bootstrap.GRID_EXTRA_SMALL, qtip = "Never Stacked"),
		@Option(text = "Small Devices (Tablets)", value = Bootstrap.GRID_SMALL, qtip = "From Stacked to Horizontal at the @screen-sm-min bootstrap breakpoint"),
		@Option(text = "Medium Devices (Desktops)", value = Bootstrap.GRID_MEDIUM, qtip = "From Stacked to Horizontal at the @screen-md-min bootstrap breakpoint"),
		@Option(text = "Large Devices (Desktops)", value = Bootstrap.GRID_LARGE, qtip = "From Stacked to Horizontal at the @screen-lg-min bootstrap breakpoint") })
	public String getGridSize() {
		return get("gridSize", Bootstrap.GRID_MEDIUM);
	}

	public List<Column> getColumns() {
        if (columns == null) {
            columns = Lists.newArrayList();

            for (ComponentNode currentColumnComponentNode : getComponentNodes()) {
                columns.add(getComponent(currentColumnComponentNode, Column.class));
            }
        }

		return this.columns;
	}

	@DialogField(ranking = 3)
	@DialogFieldSet
	public Classification getClassification() {
        return getComponent(this, Classification.class);
	}

}