package com.icfolson.aem.harbor.core.components.content.text.v1;

import com.icfolson.aem.harbor.api.components.content.text.Text;
import com.icfolson.aem.harbor.api.components.mixins.classifiable.Classification;
import com.icfolson.aem.harbor.core.components.mixins.classifiable.TagBasedClassification;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;
import javax.inject.Named;

@Model(adaptables = Resource.class, adapters = Text.class, resourceType = DefaultText.RESOURCE_TYPE)
public class DefaultText implements Text {

    public static final String RESOURCE_TYPE = "harbor/components/content/text/v1/text";

    @Inject
    private Resource resource;

    @Inject @Named("text") @Default(values = "")
    private String content;

    public String getContent() {
        return content;
    }

    public Classification getClassification() {
        return resource.adaptTo(TagBasedClassification.class);
    }
}
