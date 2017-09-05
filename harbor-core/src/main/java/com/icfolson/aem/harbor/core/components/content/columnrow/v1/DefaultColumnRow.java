package com.icfolson.aem.harbor.core.components.content.columnrow.v1;

import com.google.common.collect.Lists;
import com.icfolson.aem.harbor.api.components.content.columnrow.Column;
import com.icfolson.aem.harbor.api.components.content.columnrow.ColumnRow;
import com.icfolson.aem.harbor.api.components.mixins.classifiable.Classification;
import com.icfolson.aem.harbor.core.components.mixins.classifiable.TagBasedClassification;
import com.icfolson.aem.harbor.core.util.ComponentUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.Self;

import javax.inject.Inject;
import java.util.List;

@Model(adaptables = Resource.class, adapters = ColumnRow.class, resourceType = DefaultColumnRow.RESOURCE_TYPE)
public class DefaultColumnRow implements ColumnRow<Column> {

    public static final String RESOURCE_TYPE = "harbor/components/content/columnrow/v1/columnrow";

    private List<Column> columns;

    @Inject @Self
    private Resource resource;

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
        return resource.adaptTo(TagBasedClassification.class);
    }

    @Override
    public String getId() {
        return ComponentUtils.DomIdForResourcePath(resource.getPath());
    }

}