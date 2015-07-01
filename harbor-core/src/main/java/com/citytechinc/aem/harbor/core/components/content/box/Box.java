package com.citytechinc.aem.harbor.core.components.content.box;

import com.citytechinc.aem.bedrock.api.request.ComponentRequest;
import com.citytechinc.aem.bedrock.core.components.AbstractComponent;
import com.citytechinc.cq.component.annotations.Component;
import com.day.cq.wcm.api.components.DropTarget;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(value = "Box", group = "Harbor Layouts")
public final class Box extends AbstractComponent {

    private static final String BOX_CLASS = "Box";

    /** Logger */
    private static final Logger LOG = LoggerFactory.getLogger(Box.class);

    private String boxTitle;

    private String boxComponentClass;
    private String boxDesign;
    private String boxMasterClass;
    private String titleStyle = StringUtils.EMPTY;
    private String boxName = StringUtils.EMPTY;
    private boolean boxShowHide = false;
    private boolean parent = false;
    private String parentMasterClass;


    public boolean getBoxShowHide() {
        //LOG.info("Inside getBoxShowHide... ");
        return boxShowHide;
    }


    public void setBoxShowHide(boolean boxShowHide) {
        this.boxShowHide = boxShowHide;
    }
    public String getParentMasterClass() {
        return parentMasterClass;
    }

    @Override
    public void init(final ComponentRequest request) {

        boxTitle = get("boxTitle", "");

        parent = get("parent", false);

        boxComponentClass = request.getComponent().getName();
        LOG.debug("request.getComponent().getCellName(): " + boxComponentClass);
        boxDesign = get("boxDesign", "");
        boxMasterClass = "";

        // DEFAULT
        // set a default master class for the box used as a prefix to the box "master" classes.
        // if a design is set, use that: [design]Box, if not, use [componentName]Box.
        // That doesn't have to be styled, but it's sure nice to have it in there!
        // 
        if (!"box".equals(boxComponentClass)) { // if it's not just the plain box itself (no prefix)
            boxComponentClass = boxComponentClass + BOX_CLASS;
            boxMasterClass = boxComponentClass;
        }
        if (boxDesign.length() > 0) {
            boxDesign = boxDesign + BOX_CLASS;
            boxMasterClass = boxDesign;

            if(parent) {
                parentMasterClass =  boxMasterClass;
            }
            LOG.debug("Parent Master Class is "+parentMasterClass);
        }
        titleStyle = get("titleStyle", StringUtils.EMPTY);
        boxName = get("boxName", StringUtils.EMPTY);
    }


    public String getBoxTitle() {
        return boxTitle;
    }

    public String getTitleSize() {
        return get("titleSize", "h3");
    }

    public String getTitleStyle() {
        return titleStyle;
    }


    public boolean isDisplayTitle() {
        return boxTitle.length() > 0;
    }

    public String getDdClassNameA() {
        return DropTarget.CSS_CLASS_PREFIX + "image";
    }

    public String getBoxDesign() {
        return boxDesign;
    }

    public String getBoxComponentClass() {
        return boxComponentClass;
    }

    public String getBoxMasterClass() {
        return boxMasterClass;
    }

    public String getBoxFooter() {
        return get("boxFtr", "");
    }

    public String getBoxId() {
        return get("boxID", "");
    }




    public String getBoxName() {
        return boxName;
    }


    public String getBoxClass() {
        return get("boxClass", "");
    }

    public String getBoxStyle() {
        return get("boxStyle", "");
    }

    public String getBoxBodyStyle() {
        return get("boxBodyStyle", "");
    }

    public boolean isParent() {
        return parent;
    }

}
