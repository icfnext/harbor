package com.citytechinc.aem.harbor.core.components.content.classifiedcontent;

import com.citytechinc.aem.bedrock.api.components.annotations.AutoInstantiate;
import com.citytechinc.aem.bedrock.core.components.AbstractComponent;
import com.citytechinc.aem.harbor.core.components.mixins.classifiable.Classification;
import com.citytechinc.aem.harbor.core.constants.groups.ComponentGroups;
import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.widgets.DialogFieldSet;

@Component(value = "Classified Content", group = ComponentGroups.HARBOR_SCAFFOLDING)
@AutoInstantiate(instanceName = "classifiedcontent")
public class ClassifiedContent extends AbstractComponent {

    private Classification classification;

    @DialogField
    @DialogFieldSet
    public Classification getClassification() {
        if (classification == null) {
            classification = getComponent(this, Classification.class);
        }

        return classification;
    }

}
