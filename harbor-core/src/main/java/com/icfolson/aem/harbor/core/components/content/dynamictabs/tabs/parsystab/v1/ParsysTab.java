package com.icfolson.aem.harbor.core.components.content.dynamictabs.tabs.parsystab.v1;

import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.widgets.TextField;
import com.icfolson.aem.harbor.api.components.content.dynamictabs.DynamicTab;
import com.icfolson.aem.harbor.api.components.mixins.classifiable.Classification;
import com.icfolson.aem.harbor.api.components.mixins.paragraphsystem.ParagraphSystemContainer;
import com.icfolson.aem.harbor.core.components.mixins.classifiable.TagBasedClassification;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;

import javax.inject.Inject;

@Model(adaptables = Resource.class, adapters = {DynamicTab.class, ParagraphSystemContainer.class}, resourceType = ParsysTab.RESOURCE_TYPE )
public class ParsysTab implements DynamicTab, ParagraphSystemContainer {

    public static final String RESOURCE_TYPE = "harbor/components/content/dynamictabs/tabs/parsystab/v1/parsystab";

    @Inject @Optional
    private String label;

    @Inject
    private Resource resource;

    @DialogField(fieldLabel = "Tab Label") @TextField
    @Override
    public String getLabel() {
        return this.label;
    }

    @Override
    public String getId() {
        return resource.getPath().replaceAll("/", "_").replaceAll(":", "__"); //TODO: Use MD5 Hash
    }

    @Override
    public String getType() {
        return ParsysTab.RESOURCE_TYPE;
    }

    @Override
    public String getPath() {
        return this.resource.getPath();
    }

    public Classification getClassification() {
        return resource.adaptTo(TagBasedClassification.class);
    }

    @Override
    public String getParagraphSystemType() {
        return ParagraphSystemContainer.PARSYS;
    }
}
