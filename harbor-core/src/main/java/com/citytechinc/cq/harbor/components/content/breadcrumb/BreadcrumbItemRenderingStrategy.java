package com.citytechinc.cq.harbor.components.content.breadcrumb;

import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.widgets.Selection;
import com.citytechinc.cq.harbor.components.content.list.ListRenderingStrategy;
import com.citytechinc.cq.harbor.constants.components.ComponentConstants;
import com.citytechinc.cq.library.content.node.ComponentNode;
import com.citytechinc.cq.library.content.request.ComponentRequest;

public class BreadcrumbItemRenderingStrategy implements ListRenderingStrategy<BreadcrumbItem> {


    private static final String DEFAULT_DELIMITER = "fa-bootstrap-slash";
    private static final String END_ANCHOR_HTML = "</a>";
    private final String ICON_DELIMITER_HTML = "<i class='fa %s'></i>";
    private final String START_ANCHOR_HTML = "<a href=\"%s\">";
    private final ComponentNode currentComponentNode;
    private final String currentPagePath;

    public BreadcrumbItemRenderingStrategy(ComponentRequest componentRequest) {
        currentComponentNode = componentRequest.getComponentNode();
        currentPagePath = componentRequest.getCurrentPage().getPath();
    }

    @Override
    public String renderListItem(BreadcrumbItem item) {
        StringBuffer itemStringBuffer = new StringBuffer();
        itemStringBuffer.append(getDelimiter());
        String currentItemPagePath = item.getPage().getPath();
        boolean isCurrentPage = currentItemPagePath.equals(currentPagePath);
        if (!isCurrentPage)
            itemStringBuffer.append(String.format(START_ANCHOR_HTML, item.getPage().getPath()));
        if (!item.isHideIcon())
            itemStringBuffer.append(item.getPageIcon());
        if (!item.isHideTitle())
            itemStringBuffer.append(item.getTitle());
        if (!isCurrentPage)
            itemStringBuffer.append(END_ANCHOR_HTML);
        return itemStringBuffer.toString();

    }

    /**
     * A dialog field which allows the user to choose what delimiter icon they wish to be displayed.
     *
     * @return a string representing the font awesome icon class the user has selected.
     */
    @DialogField(fieldLabel = "Delimiter Icon", ranking = 1)
    @Selection(type = Selection.SELECT, optionsUrl = ComponentConstants.FONT_AWESOME_SERVLET_PATH)
    public String getIconDelimiter() {
        return currentComponentNode.get("iconDelimiter", DEFAULT_DELIMITER);
    }

    /**
     * A dialog field which allows the user to enter any HTML string they wish.
     *
     * @return The {@link Breadcrumb} HTML delimiter.
     */
    @DialogField(fieldLabel = "Delimiter HTML", ranking = 2)
    public String getHtmlDelimiter() {
        return currentComponentNode.get("htmlDelimiter", "");
    }

    public String getDelimiter() {
        if (!getHtmlDelimiter().isEmpty()) {
            return getHtmlDelimiter();
        } else {
            String iconDelimiterHtml = String.format(ICON_DELIMITER_HTML, getIconDelimiter());
            return iconDelimiterHtml;
        }
    }


}
