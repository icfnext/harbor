package com.citytechinc.cq.harbor.components.content.breadcrumb;

public interface BreadcrumbItem {

    /**
     * Returns objects {@link BreadcrumbItemConfigNode}
     *
     * @return {@link BreadcrumbItemConfigNode}
     */
    public BreadcrumbItemConfigNode getBreadcrumbTrailNode();

    /**
     * Sets objects {@link BreadcrumbItemConfigNode}
     *
     * @param breadcrumbTrailNode to be stored in the object
     */
    public void setBreadcrumbTrailNode(BreadcrumbItemConfigNode breadcrumbTrailNode);

    /**
     * Indicates if the Breadcrumb should hide this item's icon.
     *
     * @return <code>true</code>} if the item's icon should be hidden, <code>false</code> otherwise.
     */
    public boolean isHideIcon();

    /**
     * Indicates if the Breadcrumb should hide this item's icon.
     *
     * @return <code>true</code> if the item's icon should be hidden, <code>false</code> otherwise.
     */
    public boolean isHideTitle();

    /**
     * Returns a string containing this item's icon delimiter.
     *
     * @return This item's icon delimiter
     */
    public String getIconDelimiter();

    /**
     * Returns a string containing this item's icon delimiter.
     *
     * @return This item's icon delimiter
     */
    public String getHtmlDelimiter();

    /**
     * A function used in order to determine what type of delimiter should be shown for this item.
     *
     * @return
     */
    public boolean getUseHtmlDelimiter();

    /**
     * Returns the href to the page this BreadcrumbItem represents.
     *
     * @return An href formatted for html.
     */
    public String getHref();

    /**
     * Returns the title of the page this BreadcrumbItem represents.
     *
     * @return A string.
     */
    public String getTitle();

    /**
     * Returns a string representing the icon associated with this pages "pageicon"
     *
     * @return A string representing this page's "pageicon"
     */
    public String getPageIcon();
}
