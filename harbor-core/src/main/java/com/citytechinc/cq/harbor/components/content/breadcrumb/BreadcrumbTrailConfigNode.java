package com.citytechinc.cq.harbor.components.content.breadcrumb;

import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.Option;
import com.citytechinc.cq.component.annotations.widgets.Selection;
import com.citytechinc.cq.library.components.AbstractComponent;

public class BreadcrumbTrailConfigNode {
    private final boolean hideIcon;
    private final boolean hideTitle;



    public BreadcrumbTrailConfigNode(boolean hideIcon, boolean hideTitle) {
        this.hideIcon = hideIcon;
        this.hideTitle = hideTitle;
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
}
