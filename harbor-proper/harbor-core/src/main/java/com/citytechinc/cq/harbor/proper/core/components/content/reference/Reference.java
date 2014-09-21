package com.citytechinc.cq.harbor.proper.core.components.content.reference;


import com.citytechinc.aem.bedrock.api.components.annotations.AutoInstantiate;
import com.citytechinc.aem.bedrock.core.components.AbstractComponent;
import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.DialogField;
import org.apache.commons.lang3.StringUtils;

@Component("Reference")
@AutoInstantiate(instanceName = "reference")
public class Reference extends AbstractComponent {

    @DialogField(fieldLabel = "Component Reference", xtype = "paragraphreference", ranking = 10)
    private String paragraphPath;

    @DialogField(fieldLabel = "Rendering Selector", fieldDescription = "Specify a particular rendering which should be used to present the referenced component.", ranking = 20)
    private String selector;

    public String getParagraphPath() {
        if (paragraphPath == null) {
            paragraphPath = get("paragraphPath", "");
        }

        return paragraphPath;
    }

    public String getSelector() {
        if (selector == null) {
            selector = get("selector", "");
        }

        return selector;
    }

    public boolean isHasSelector() {
        return StringUtils.isNotBlank(getSelector());
    }

    public boolean isHasParagraphPath() {
        return StringUtils.isNotBlank(getParagraphPath());
    }
}
