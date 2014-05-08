package com.citytechinc.cq.harbor.proper.util;

import com.day.cq.wcm.api.Page;
import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.resource.Resource;

public final class ComponentUtils {

    private ComponentUtils() {

    }

    public static String getComponentId(final Page currentPage, final Resource resource) {
        final String pageResourcePath = currentPage.getContentResource().getPath();

        return StringUtils.removeStart(resource.getPath(), pageResourcePath).substring(1)
                .replaceAll("/", "-");
    }
}
