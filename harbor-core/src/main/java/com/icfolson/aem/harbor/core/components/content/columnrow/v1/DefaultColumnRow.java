package com.icfolson.aem.harbor.core.components.content.columnrow.v1;

import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.editconfig.ActionConfig;
import com.citytechinc.cq.component.annotations.editconfig.ActionConfigProperty;
import com.citytechinc.cq.component.annotations.widgets.DialogFieldSet;
import com.google.common.collect.Lists;
import com.icfolson.aem.harbor.api.components.content.columnrow.Column;
import com.icfolson.aem.harbor.api.components.content.columnrow.ColumnRow;
import com.icfolson.aem.harbor.api.components.mixins.classifiable.Classification;
import com.icfolson.aem.harbor.core.constants.groups.ComponentGroups;
import com.icfolson.aem.harbor.core.util.ComponentUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;
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
    value = "Column Row (v1)",
    group = ComponentGroups.HARBOR_SCAFFOLDING,
    actions = { "text: Column Row (v1)", "-", "edit", "-", "copymove", "delete", "-", "insert" },
    name = "columnrow/v1/columnrow",
    actionConfigs = {
        @ActionConfig(
            handler = "function() { Harbor.Components.ColumnRow.v1.ColumnRow.addColumn( this, '" + DefaultColumn.RESOURCE_TYPE + "' ); }",
            text = "Add Column",
            additionalProperties = { @ActionConfigProperty(name = "icon", value = "coral-Icon--experienceAdd") }
        )
    },
    isContainer = true
)
@Model(adaptables = Resource.class, adapters = ColumnRow.class, resourceType = DefaultColumnRow.RESOURCE_TYPE)
public class DefaultColumnRow implements ColumnRow<Column> {

    public static final String RESOURCE_TYPE = "harbor/components/content/columnrow/v1/columnrow";

    private List<Column> columns;

    @Inject @Self
    private Resource resource;

    @DialogField(ranking = 3) @DialogFieldSet @Self
    private Classification classification;

    public List<Column> getColumns() {
        if (columns == null) {
            columns = Lists.newArrayList();
            resource.listChildren().forEachRemaining(currentResource -> {
                Column currentColumn = currentResource.adaptTo(Column.class);
                if (currentColumn != null) {
                    columns.add(currentColumn);
                }
            });
        }
        return columns;
    }

    public Classification getClassification() {
        return classification;
    }

    @Override
    public String getId() {
        return ComponentUtils.DomIdForResourcePath(resource.getPath());
    }

}