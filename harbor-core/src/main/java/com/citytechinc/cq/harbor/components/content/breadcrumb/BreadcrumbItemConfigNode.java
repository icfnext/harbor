package com.citytechinc.cq.harbor.components.content.breadcrumb;

import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.Option;
import com.citytechinc.cq.component.annotations.widgets.Selection;

public class BreadcrumbItemConfigNode {
    private final boolean hideIcon;
    private final boolean hideTitle;
    private final boolean useHtmlDelimiter;
    private final String delimiterIcon;
    private final String delimiterHtml;

    public BreadcrumbItemConfigNode(boolean hideIcon, boolean hideTitle, boolean useHtmlDelimiter, String delimiterIcon, String delimiterHtml) {
        this.hideIcon = hideIcon;
        this.hideTitle = hideTitle;
        this.useHtmlDelimiter = useHtmlDelimiter;
        this.delimiterIcon = delimiterIcon;
        this.delimiterHtml = delimiterHtml;
    }

    @DialogField(fieldLabel = "Hide Icon")
    @Selection(type = Selection.CHECKBOX, options = {
            @Option(text = "", value = "true")
    })
    public boolean getHideIcon() {
        return hideIcon;
    }

    @DialogField(fieldLabel = "Hide Title")
    @Selection(type = Selection.CHECKBOX, options = {
            @Option(text = "", value = "true")
    })
    public boolean getHideTitle() {
        return hideTitle;
    }

    public boolean getUseHtmlDelimiter() {
        return useHtmlDelimiter;
    }

    public String getDelimiterIcon() {
        return delimiterIcon;
    }

    public String getDelimiterHtml() {
        return delimiterHtml;
    }
}
