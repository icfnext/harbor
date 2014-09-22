package com.citytechinc.cq.harbor.proper.core.components.content.reference;


import com.citytechinc.aem.bedrock.api.components.annotations.AutoInstantiate;
import com.citytechinc.aem.bedrock.core.components.AbstractComponent;
import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.Tab;
import org.apache.commons.lang3.StringUtils;

@Component(value = "Reference", tabs = {@Tab(title="Reference"), @Tab(title="Advanced")})
@AutoInstantiate(instanceName = "reference")
public class Reference extends AbstractComponent {

    @DialogField(fieldLabel = "Component Reference", xtype = "paragraphreference", ranking = 10, tab = 1)
    private String paragraphPath;

    @DialogField(fieldLabel = "Rendering Selector", fieldDescription = "Specify a particular rendering which should be used to present the referenced component.", ranking = 10, tab = 2)
    private String renderingSelector;

    @DialogField(fieldLabel = "Rendering ResourceType", fieldDescription = "Specify a particular sling:resourceType in order to direct the rendering of the referenced component.", ranking = 20, tab = 2)
    private String renderingResourceType;

    public String getParagraphPath() {
        if (paragraphPath == null) {
            paragraphPath = get("paragraphPath", "");
        }

        return paragraphPath;
    }

    public String getRenderingSelector() {
        if (renderingSelector == null) {
            renderingSelector = get("renderingSelector", "");
        }

        return renderingSelector;
    }

    public String getRenderingResourceType() {
        if (renderingResourceType == null) {
            renderingResourceType = get("renderingResourceType", "");
        }

        return renderingResourceType;
    }

    public boolean isHasRenderingSelector() {
        return StringUtils.isNotBlank(getRenderingSelector());
    }

    public boolean isHasRenderingResourceType() {
        return StringUtils.isNotBlank(getRenderingResourceType());
    }

    public boolean isHasParagraphPath() {
        return StringUtils.isNotBlank(getParagraphPath());
    }

}
