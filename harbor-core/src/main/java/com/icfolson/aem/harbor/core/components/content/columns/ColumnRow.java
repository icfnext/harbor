package com.icfolson.aem.harbor.core.components.content.columns;

import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.Tab;
import com.citytechinc.cq.component.annotations.editconfig.ActionConfig;
import com.citytechinc.cq.component.annotations.editconfig.ActionConfigProperty;
import com.citytechinc.cq.component.annotations.widgets.DialogFieldSet;
import com.citytechinc.cq.component.annotations.widgets.TextField;
import com.icfolson.aem.harbor.api.constants.bootstrap.Bootstrap;
import com.icfolson.aem.harbor.core.components.mixins.classifiable.Classification;
import com.icfolson.aem.harbor.core.constants.groups.ComponentGroups;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.Self;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * The authoring of content in a flexible set of columns is achieved by the Column Row component.  Each instance of the
 * component represents a "row" and each "row" may contain any number of Column components.  Column are added to the
 * Column Row instance by clicking the "+" button in the instance's edit bar.  Once added each Column is configured
 * individually and can be removed via deletion.
 * <p>
 * This component leverages the Bootstrap grid system which breaks the page into a 12 column grid.  Each column can be
 * configured to span 1-12 parts of the grid.  Further the amount a given column spans may differ by viewport size. If
 * the total number of columns spanned by the Column components making up a row is greater than 12 then columns beyond
 * the 12th column spanned will flow into the next row.
 * <p>
 * For more information on authoring individual columns refer to the com.icfolson.aem.harbor.core.components.content.columns.Column
 * documentation.
 * <p>
 * This component is Classifiable
 */
@Component(
    value = "Column Row",
    group = ComponentGroups.HARBOR_SCAFFOLDING,
    actions = { "text: Column Row", "-", "edit", "-", "copymove", "delete", "-", "insert" },
    actionConfigs = {
        @ActionConfig(
            handler = "function() { Harbor.Components.ColumnRow.addColumn( this, 'harbor/components/content/column' ); }",
            text = "Add Column",
            additionalProperties = { @ActionConfigProperty(name = "icon", value = "coral-Icon--experienceAdd") }
        )
    },
    allowedParents = "*/parsys",
    resourceSuperType = "foundation/components/parbase",
    tabs = {
        @Tab(title = "Column Row")
    })
@Model(adaptables = Resource.class)
public class ColumnRow {

    @Inject
    @Optional
    @Named(".")
    @ChildResource
    private List<Column> columns;

    @DialogField(ranking = 3)
    @DialogFieldSet
    @Self
    private Classification classification;

    @DialogField(
        fieldLabel = "ID",
        fieldDescription = "A unique identifier to apply to the Row element rendered in the page DOM.  If left blank, no id attribute will be applied to the rendered element.",
        tab = 1)
    @TextField
    @Inject
    @Optional
    private String domId;

    public List<Column> getColumns() {
        return columns;
    }

    public Classification getClassification() {
        return classification;
    }

    public String getCssClass() {
        final StringBuilder builder = new StringBuilder(Bootstrap.GRID_ROW_CLASS);

        if (getClassification().isHasClassifications()) {
            builder.append(" ");
            builder.append(getClassification().getClassNames());
        }

        return builder.toString();
    }

    public String getDomId() {
        return domId;
    }

    public boolean isHasDomId() {
        return domId != null;
    }
}