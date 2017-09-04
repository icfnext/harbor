package com.icfolson.aem.harbor.core.components.content.heading.v1;

import com.icfolson.aem.harbor.api.components.content.heading.Heading;
import com.icfolson.aem.harbor.core.util.ComponentUtils;

public abstract class AbstractHeading implements Heading {

    public static final String RESOURCE_TYPE = "harbor/components/content/heading/v1/abstractheading";

    public String getId() {
        return ComponentUtils.sanitizeTextAsDomId(getText());
    }

}
