package com.citytechinc.cq.harbor.components.content.breadcrumb;

import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.Option;
import com.citytechinc.cq.component.annotations.widgets.Selection;
import com.citytechinc.cq.library.content.node.BasicNode;

public class BreadcrumbItemConfigNode {

    private boolean hideTitle;
    private boolean hideIcon;
    private final String HIDE_ICON_PROPERTY_NAME = "hideIcon";
    private final String HIDE_TITLE_PROPERTY_NAME = "hideTitle";


    public BreadcrumbItemConfigNode(BasicNode basicNode) {
        this.hideTitle = basicNode.get(HIDE_TITLE_PROPERTY_NAME, false);
        this.hideIcon = basicNode.get(HIDE_ICON_PROPERTY_NAME, false);
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
