package com.icfolson.aem.harbor.core.components.content.subtitle.v1;

import com.icfolson.aem.harbor.api.components.content.heading.Heading;
import com.icfolson.aem.library.models.annotations.InheritInject;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;

@Model(adaptables = Resource.class, adapters = Heading.class, resourceType = InheritingSubtitle.RESOURCE_TYPE)
public class InheritingSubtitle extends Subtitle {

    public static final String RESOURCE_TYPE = Subtitle.RESOURCE_TYPE + "/inheriting";

    @InheritInject @Optional
    private String text;

    protected String getRawText() {
        return text;
    }

}
