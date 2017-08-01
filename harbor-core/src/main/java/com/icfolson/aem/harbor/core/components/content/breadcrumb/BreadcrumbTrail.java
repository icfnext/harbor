package com.icfolson.aem.harbor.core.components.content.breadcrumb;

import javax.annotation.Nonnull;
import java.util.Iterator;
import java.util.List;

public class BreadcrumbTrail implements Iterable<BreadcrumbItem> {

    private final Boolean renderAsLink;

    private final BreadcrumbItemConfiguration rootItemConfiguration;

    private final BreadcrumbItemConfiguration intermediateItemConfiguration;

    private final BreadcrumbItemConfiguration currentItemConfiguration;

    private final List<BreadcrumbItem> renderableList;

    public BreadcrumbTrail(Boolean renderAsLink, BreadcrumbItemConfiguration rootItemConfiguration,
        BreadcrumbItemConfiguration intermediateItemConfiguration, BreadcrumbItemConfiguration currentItemConfiguration,
        List<BreadcrumbItem> renderableList) {
        this.renderAsLink = renderAsLink;
        this.rootItemConfiguration = rootItemConfiguration;
        this.intermediateItemConfiguration = intermediateItemConfiguration;
        this.currentItemConfiguration = currentItemConfiguration;
        this.renderableList = renderableList;
    }

    public BreadcrumbItemConfiguration getRootItemConfiguration() {
        return rootItemConfiguration;
    }

    public BreadcrumbItemConfiguration getIntermediateItemConfiguration() {
        return intermediateItemConfiguration;
    }

    public BreadcrumbItemConfiguration getCurrentItemConfiguration() {
        return currentItemConfiguration;
    }

    @Nonnull
    @Override
    public Iterator<BreadcrumbItem> iterator() {
        return renderableList.iterator();
    }

    public Boolean getRenderAsLink() {
        return renderAsLink;
    }
}
