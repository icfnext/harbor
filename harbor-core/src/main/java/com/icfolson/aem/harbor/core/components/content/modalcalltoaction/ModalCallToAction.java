package com.icfolson.aem.harbor.core.components.content.modalcalltoaction;

import com.citytechinc.cq.component.annotations.Component;
import com.icfolson.aem.harbor.core.components.content.calltoaction.AbstractCallToAction;
import com.icfolson.aem.harbor.core.constants.groups.ComponentGroups;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

@Component(value = "Call to Action With Modal", group = ComponentGroups.HARBOR, name = "modalcalltoaction",
    layout = "rollover", inPlaceEditingEditorType = "harborcta")
@Model(adaptables = Resource.class)
public class ModalCallToAction extends AbstractCallToAction {

}
