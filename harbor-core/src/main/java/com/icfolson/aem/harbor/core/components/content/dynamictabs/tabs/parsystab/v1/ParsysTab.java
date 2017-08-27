package com.icfolson.aem.harbor.core.components.content.dynamictabs.tabs.parsystab.v1;

import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.Listener;
import com.citytechinc.cq.component.annotations.widgets.DialogFieldSet;
import com.citytechinc.cq.component.annotations.widgets.TextField;
import com.icfolson.aem.harbor.api.components.content.dynamictabs.DynamicTab;
import com.icfolson.aem.harbor.api.components.mixins.classifiable.Classification;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;
import org.apache.sling.models.annotations.injectorspecific.Self;

import javax.inject.Inject;

@Component(value = "Parsys Tab",
        group = ".hidden",
        resourceSuperType = DynamicTab.RESOURCE_TYPE,
        actions = { "text: Parsys Tab", "edit", "-", "delete" },
        name = "dynamictabs/tabs/parsystab/v1/parsystab",
        listeners = {
                @Listener(name = "afterinsert", value = "REFRESH_PARENT"),
                @Listener(name = "afteredit", value = "REFRESH_PARENT"),
                @Listener(name = "afterdelete", value = "REFRESH_PARENT")
        })
@Model(adaptables = Resource.class, adapters = DynamicTab.class, resourceType = ParsysTab.RESOURCE_TYPE )
public class ParsysTab implements DynamicTab {

    public static final String RESOURCE_TYPE = "harbor/components/content/dynamictabs/tabs/parsystab/v1/parsystab";

    @Inject @Optional
    private String label;

    @Inject @Self
    private Classification classification;

    @Inject
    private Resource resource;

    @DialogField(fieldLabel = "Tab Label") @TextField
    @Override
    public String getLabel() {
        return this.label;
    }

    @Override
    public String getId() {
        return resource.getPath().replaceAll("/", "_").replaceAll(":", "__"); //TODO: Use MD5 Hash
    }

    @Override
    public String getType() {
        return ParsysTab.RESOURCE_TYPE;
    }

    @Override
    public String getPath() {
        return this.resource.getPath();
    }

    @DialogField(ranking = 20) @DialogFieldSet
    public Classification getClassification() {
        return this.classification;
    }

}
