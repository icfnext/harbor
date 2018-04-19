package com.icfolson.aem.harbor.core.components.content.heading.v1;

import com.adobe.cq.export.json.ExporterConstants;
import com.icfolson.aem.harbor.api.components.content.heading.Heading;
import com.icfolson.aem.harbor.api.constants.dom.Headings;
import com.icfolson.aem.library.models.annotations.InheritInject;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;

@Model(adaptables = Resource.class, adapters = Heading.class, resourceType = InheritingHeading.RESOURCE_TYPE)
@Exporter(name = ExporterConstants.SLING_MODEL_EXPORTER_NAME, extensions = ExporterConstants.SLING_MODEL_EXTENSION)
public class InheritingHeading extends DefaultHeading {

    public static final String RESOURCE_TYPE = DefaultHeading.RESOURCE_TYPE + "/inheriting";

    @InheritInject @Optional
    private String text;

    @InheritInject @Default(values = Headings.H2)
    private String size;

    @Override
    public String getText() {
        return text;
    }

    @Override
    public String getSize() {
        return size;
    }

}
