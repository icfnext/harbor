package com.citytechinc.cq.harbor.components.content.breadcrumb;

import org.apache.commons.lang3.StringUtils;

import java.util.Iterator;
import java.util.List;

public class BreadcrumbTrail implements Iterable<BreadcrumbItem> {

    private final Boolean renderAsLink;

    private final String iconDelimiter;
    private final String htmlDelimiter;

    private final BreadcrumbItemConfiguration rootItemConfiguration;
    private final BreadcrumbItemConfiguration intermediateItemConfiguration;
    private final BreadcrumbItemConfiguration currentItemConfiguration;

    private final List<BreadcrumbItem> renderableList;

    public BreadcrumbTrail(Boolean renderAsLink, String iconDelimiter, String htmlDelimiter, BreadcrumbItemConfiguration rootItemConfiguration, BreadcrumbItemConfiguration intermediateItemConfiguration, BreadcrumbItemConfiguration currentItemConfiguration, List<BreadcrumbItem> renderableList) {
        this.renderAsLink = renderAsLink;

        this.iconDelimiter = iconDelimiter;
        this.htmlDelimiter = htmlDelimiter;

        this.rootItemConfiguration = rootItemConfiguration;
        this.intermediateItemConfiguration = intermediateItemConfiguration;
        this.currentItemConfiguration = currentItemConfiguration;
        this.renderableList = renderableList;
    }

    public String getIconDelimiter() {
        return iconDelimiter;
    }

    public String getHtmlDelimiter() {
        return htmlDelimiter;
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

    public Boolean getHasIconDelimiter() {
        return !getHasHtmlDelimiter() && StringUtils.isNotBlank(getIconDelimiter());
    }

    public Boolean getHasHtmlDelimiter() {
        return StringUtils.isNotBlank(getHtmlDelimiter());
    }

    @Override
    public Iterator<BreadcrumbItem> iterator() {
        return renderableList.iterator();
    }

    public Boolean getRenderAsLink() {
        return renderAsLink;
    }
}
