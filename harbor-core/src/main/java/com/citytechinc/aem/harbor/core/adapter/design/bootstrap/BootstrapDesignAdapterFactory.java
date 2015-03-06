package com.citytechinc.aem.harbor.core.adapter.design.bootstrap;

import com.citytechinc.aem.harbor.api.design.bootstrap.BootstrapDesign;
import com.citytechinc.aem.harbor.api.services.less.compiler.LessCompilationService;
import com.citytechinc.aem.harbor.core.design.bootstrap.impl.DefaultBootstrapDesign;
import org.apache.felix.scr.annotations.*;
import org.apache.sling.api.SlingConstants;
import org.apache.sling.api.adapter.AdapterFactory;
import org.apache.sling.api.resource.Resource;
import org.osgi.framework.Constants;

@Component(metatype = true, name = "BootstrapDesignAdapterFactory", label = "Bootstrap Design Adapter Factory", description = "Adapter for transforming Resources into BootstrapDesign instances")
@Service(AdapterFactory.class)
@Properties({
        @Property(name = Constants.SERVICE_DESCRIPTION, value = "Harbor Bootstrap Design Adapter Factory"),
        @Property(name = SlingConstants.PROPERTY_ADAPTABLE_CLASSES, value = {
                "org.apache.sling.api.resource.Resource"
        }),
        @Property(name = SlingConstants.PROPERTY_ADAPTER_CLASSES, value = {
                "com.citytechinc.aem.harbor.api.design.bootstrap.BootstrapDesign"
        })
})
public class BootstrapDesignAdapterFactory implements AdapterFactory {

    @Reference
    private LessCompilationService lessCompilationService;

    @Override
    public <AdapterType> AdapterType getAdapter(Object adaptable, Class<AdapterType> type) {

        if (adaptable instanceof Resource) {
            return getResourceAdapter((Resource) adaptable, type);
        }

        return null;
    }

    public <AdapterType> AdapterType getResourceAdapter(Resource resource, Class<AdapterType> type) {

        if (type == BootstrapDesign.class && resource.getChild("jcr:content") != null && resource.getChild("jcr:content").isResourceType(BootstrapDesign.RESOURCE_TYPE)) {
            return (AdapterType) new DefaultBootstrapDesign(resource, lessCompilationService);
        }

        return null;

    }

}
