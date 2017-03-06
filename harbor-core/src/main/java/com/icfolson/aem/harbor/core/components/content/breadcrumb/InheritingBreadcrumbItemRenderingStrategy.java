package com.icfolson.aem.harbor.core.components.content.breadcrumb;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

@Model(adaptables = Resource.class)
public class InheritingBreadcrumbItemRenderingStrategy extends BreadcrumbItemRenderingStrategy {

    @Override
    protected boolean isInherits() {
        return true;
    }
}