package com.icfolson.aem.harbor.core.components.content.columnrow.v1;

import com.icfolson.aem.harbor.api.components.content.columnrow.Column;
import com.icfolson.aem.harbor.api.components.content.columnrow.ColumnRow;
import com.icfolson.aem.library.api.node.BasicNode;
import com.icfolson.aem.library.api.node.ComponentNode;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.Self;

import javax.inject.Inject;
import java.util.List;

@Model(adaptables = Resource.class, adapters = ColumnRow.class, resourceType = InheritingColumnRow.RESOURCE_TYPE)
public class InheritingColumnRow extends DefaultColumnRow {

    public static final String RESOURCE_TYPE = DefaultColumnRow.RESOURCE_TYPE + "/inheriting";

    private List<Column> columns;

    @Inject @Self
    private Resource resource;

    public Resource getResource() {
        return resource.adaptTo(ComponentNode.class).getNodeInherited(".").transform(BasicNode::getResource).or(resource);
    }

}
