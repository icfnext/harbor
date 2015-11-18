package com.citytechinc.aem.harbor.core.components.content.columns;

import java.util.List;

import com.citytechinc.cq.component.annotations.*;
import com.citytechinc.cq.component.annotations.editconfig.ActionConfig;
import com.citytechinc.cq.component.annotations.editconfig.ActionConfigProperty;
import com.citytechinc.cq.component.annotations.widgets.TextField;

import com.citytechinc.aem.bedrock.api.components.annotations.AutoInstantiate;
import com.citytechinc.cq.component.annotations.widgets.DialogFieldSet;
import com.citytechinc.aem.harbor.api.constants.bootstrap.Bootstrap;
import com.citytechinc.aem.harbor.core.components.mixins.classifiable.Classification;
import com.citytechinc.aem.harbor.core.constants.groups.ComponentGroups;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;
import org.apache.sling.models.annotations.injectorspecific.Self;

import javax.inject.Inject;

@Component(
		value = "Column Row",
		group = ComponentGroups.HARBOR_SCAFFOLDING,
		actions = { "text: Column Row", "-", "edit", "-", "copymove", "delete", "-", "insert" },
		actionConfigs = {
				@ActionConfig(
						handler = "function() { Harbor.Components.ColumnRow.addColumn( this, 'harbor/components/content/column' ); }",
						text = "Add Column",
						additionalProperties = {@ActionConfigProperty(name = "icon", value = "coral-Icon--add")}
				)
		},
		allowedParents = "*/parsys",
		resourceSuperType = "foundation/components/parbase",
		tabs = {
				@Tab(title = "Column Row"),
				@Tab(title = "Advanced")
		})
@AutoInstantiate(instanceName = "columnrow")
@Model(adaptables = Resource.class)
public class ColumnRow {

	@Inject @Optional
	private List<Column> columns;

	@DialogField(ranking = 3)
	@DialogFieldSet
	@Inject @Self
	private Classification classification;

	@DialogField(
			fieldLabel = "ID",
			fieldDescription = "A unique identifier to apply to the Row element rendered in the page DOM.  If left blank, no id attribute will be applied to the rendered element.",
			tab = 2)
	@TextField @Optional
	private String domId;

	public List<Column> getColumns() {
        return columns;
	}

	public Classification getClassification() {
        return classification;
	}

    public String getCssClass() {
        if (getClassification().getHasClassifications()) {
            return Bootstrap.GRID_ROW_CLASS + " " + getClassification().getClassNames();
        }

        return Bootstrap.GRID_ROW_CLASS;
    }

	public String getDomId() {
		return domId;
	}

	public boolean isHasDomId() {
		return getDomId() != null;
	}

}