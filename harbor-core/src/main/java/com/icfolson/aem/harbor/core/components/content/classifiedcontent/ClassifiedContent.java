package com.icfolson.aem.harbor.core.components.content.classifiedcontent;

import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.widgets.DialogFieldSet;
import com.icfolson.aem.harbor.core.components.mixins.classifiable.Classification;
import com.icfolson.aem.harbor.core.constants.groups.ComponentGroups;
import com.icfolson.aem.library.core.components.AbstractComponent;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.Self;

@Component(value = "Classified Content", group = ComponentGroups.HARBOR_SCAFFOLDING)
@Model(adaptables = Resource.class)
public class ClassifiedContent extends AbstractComponent {

    @Self
    private Classification classification;

    @DialogField
    @DialogFieldSet
    public Classification getClassification() {
        return classification;
    }
}