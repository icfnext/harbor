package com.citytechinc.cq.harbor.imperium.services.clientlibs;

import com.citytechinc.aem.imperium.proper.api.constants.paths.Paths;
import com.citytechinc.aem.imperium.proper.api.utils.ModeUtils;
import com.citytechinc.cq.accelerate.api.ontology.Properties;
import com.citytechinc.cq.clientlibs.api.services.clientlibs.transformer.VariableProvider;
import com.citytechinc.cq.harbor.proper.core.domain.brand.bootstrap.BootstrapBrand;
import com.citytechinc.cq.harbor.proper.core.domain.brand.bootstrap.BootstrapBrands;
import com.citytechinc.cq.library.content.node.ComponentNode;
import com.citytechinc.cq.library.content.page.PageDecorator;
import com.citytechinc.cq.library.content.page.PageManagerDecorator;
import com.google.common.base.Optional;
import com.google.common.collect.Maps;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;

import java.util.Map;

@Component( label="Harbor Imperium Bootstrap Variable Provider", description="Provides Bootstrap Variable Values based on the brand set for a page layout" )
@Service
public class LayoutModeBootstrapVariableProvider implements VariableProvider {

    @Override
    public Map<String, String> getVariables(Resource root) {

        //Variable provider only operates in Layout Mode
        if (ModeUtils.isLayoutMode(root)) {

            Optional<Resource> templateResourceOptional = getTemplateResourceForContainingPageOptional(root);

            if (templateResourceOptional.isPresent()) {
                ComponentNode templateResourceComponentNode = templateResourceOptional.get().adaptTo(ComponentNode.class);

                if (templateResourceComponentNode.get(Properties.ACCELERATE_BRAND, String.class).isPresent()) {
                    Optional<BootstrapBrand> brand = BootstrapBrands.forTemplateResource(templateResourceOptional.get());

                    if (brand.isPresent()) {
                        return brand.get().getVariables();
                    }

                }

            }

        }

        return Maps.newHashMap();

    }

    protected Optional<Resource> getTemplateResourceForContainingPageOptional(Resource resource) {

        ResourceResolver resourceResolver = resource.getResourceResolver();
        PageManagerDecorator pageManagerDecorator = resourceResolver.adaptTo(PageManagerDecorator.class);

        PageDecorator pageDecorator = pageManagerDecorator.getContainingPage(resource);

        return Optional.fromNullable(pageDecorator.getContentResource().getChild(Paths.DEFINITION));

    }

}
