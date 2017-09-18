package com.icfolson.aem.harbor.core.components.content.text.v1;

import com.icfolson.aem.harbor.api.components.content.text.Text;
import com.icfolson.aem.harbor.api.components.mixins.classifiable.Classification;
import com.icfolson.aem.harbor.core.components.mixins.classifiable.InheritedTagBasedClassification;
import com.icfolson.aem.library.models.annotations.InheritInject;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.Model;

@Model(adaptables = Resource.class, adapters = Text.class, resourceType = InheritingText.RESOURCE_TYPE)
public class InheritingText extends DefaultText {

    public static final String RESOURCE_TYPE = DefaultText.RESOURCE_TYPE + "/inheriting";

    @InheritInject @Default(values = "")
    private String text;

    @Override
    public String getContent() {
        return text;
    }

    @Override
    public Classification getClassification() {
        return getResource().adaptTo(InheritedTagBasedClassification.class);
    }

}
