package com.icfolson.aem.harbor.core.components.content.columnrow.v1;

import com.icfolson.aem.harbor.api.components.content.columnrow.Column;
import com.icfolson.aem.harbor.api.components.content.columnrow.ParagraphSystemColumn;
import com.icfolson.aem.harbor.api.components.mixins.paragraphsystem.ParagraphSystemContainer;
import com.icfolson.aem.library.api.node.BasicNode;
import com.icfolson.aem.library.api.node.ComponentNode;
import com.icfolson.aem.library.models.annotations.InheritInject;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.Self;

import javax.inject.Inject;

@Model(adaptables = Resource.class,
        adapters = {Column.class, ParagraphSystemColumn.class, ParagraphSystemContainer.class},
        resourceType = InheritingColumn.RESOURCE_TYPE)
public class InheritingColumn extends DefaultColumn {

    public static final String RESOURCE_TYPE = DefaultColumn.RESOURCE_TYPE + "/inheriting";

    public static final String DEFAULT_VALUE = "default";

    @InheritInject @Default(values = DEFAULT_VALUE)
    private String xsSize;

    @InheritInject @Default(values = DEFAULT_VALUE)
    private String smSize;

    @InheritInject @Default(values = DEFAULT_VALUE)
    private String mdSize;

    @InheritInject @Default(values = DEFAULT_VALUE)
    private String lgSize;

    @InheritInject @Default(values = DEFAULT_VALUE)
    private String mdOrdering;

    @Inject @Self
    private Resource resource;

    public Resource getResource() {
        return resource.adaptTo(ComponentNode.class).getNodeInherited(".").transform(BasicNode::getResource).or(resource);
    }

    @Override
    public String getParagraphSystemType() {
        return ParagraphSystemContainer.I_PARSYS;
    }

    public String getXsSize() {
        return xsSize;
    }

    public String getSmSize() {
        return smSize;
    }

    public String getMdSize() {
        return mdSize;
    }

    public String getLgSize() {
        return lgSize;
    }

    public String getMdOrdering() {
        return mdOrdering;
    }

}
