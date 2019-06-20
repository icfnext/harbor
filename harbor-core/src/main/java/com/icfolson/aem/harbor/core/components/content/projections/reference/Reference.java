package com.icfolson.aem.harbor.core.components.content.projections.reference;

import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.Property;
import com.citytechinc.cq.component.annotations.Tab;
import com.citytechinc.cq.component.annotations.widgets.PathField;
import com.citytechinc.cq.component.annotations.widgets.TextField;
import com.icfolson.aem.harbor.core.constants.groups.ComponentGroups;
//import com.icfolson.aem.library.api.components.annotations.AutoInstantiate;
import com.icfolson.aem.library.core.components.AbstractComponent;
import com.icfolson.aem.library.core.constants.PathConstants;
import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

@Component(value = "Reference", name = "projections/reference", tabs = {
    @Tab(title = "Reference"),
    @Tab(title = "Advanced")
}, group = ComponentGroups.HARBOR_PROJECTIONS)
//@AutoInstantiate
@Model(adaptables = Resource.class)
public class Reference extends AbstractComponent {

    @DialogField(fieldLabel = "Component Reference", tab = 1, additionalProperties = {
        @Property(name = "predicate", value = "nosystem")
    }, required = true)
    @PathField(rootPath = PathConstants.PATH_CONTENT)
    public String getParagraphPath() {
        return get("paragraphPath", "");
    }

    @DialogField(fieldLabel = "Rendering Selector", fieldDescription = "Specify a particular rendering which should be used to present the referenced component.", ranking = 1, tab = 2)
    @TextField
    public String getRenderingSelector() {
        return get("renderingSelector", "");
    }

    @DialogField(fieldLabel = "Rendering Resource Type", fieldDescription = "Specify a particular sling:resourceType in order to direct the rendering of the referenced component.", ranking = 2, tab = 2)
    @TextField
    public String getRenderingResourceType() {
        return get("renderingResourceType", "");
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
