package com.icfolson.aem.harbor.core.domain.brand.bootstrap;

import com.google.common.base.Optional;
import com.icfolson.aem.harbor.core.domain.brand.bootstrap.impl.DefaultBootstrapBrand;
import com.icfolson.aem.library.api.node.ComponentNode;
import com.icfolson.aem.namespace.api.ontology.Properties;
import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;

import java.util.Map;
import java.util.stream.Collectors;

public final class BootstrapBrands {

    private BootstrapBrands() {

    }

    public static final String BOOTSTRAP_PROPERTY_PREFIX = "bootstrap-";

    public static final Integer BOOTSTRAP_PROPERTY_PREFIX_LENGTH = BOOTSTRAP_PROPERTY_PREFIX.length();

    public static Optional<BootstrapBrand> forTemplateResource(final Resource resource) {
        final Optional<String> brandPath = resource.adaptTo(ComponentNode.class).get(Properties.ICF_OLSON_BRAND,
            String.class);

        if (brandPath.isPresent() && StringUtils.isNotBlank(brandPath.get())) {
            final ResourceResolver resourceResolver = resource.getResourceResolver();
            final Resource brandResource = resourceResolver.getResource(brandPath.get());

            if (brandResource != null) {
                return forBrandResource(brandResource);
            }
        }

        return Optional.absent();

    }

    public static Optional<BootstrapBrand> forBrandResource(final Resource resource) {
        final Resource brandProperties = resource.getChild("brandproperties");

        if (brandProperties != null) {
            final ValueMap brandPropertiesMap = brandProperties.getValueMap();

            final Map<String, String> bootstrapPropertyValueMap = brandPropertiesMap.keySet()
                .stream()
                .filter(propertyName -> propertyName.startsWith(BOOTSTRAP_PROPERTY_PREFIX))
                .collect(Collectors.toMap(propertyName -> propertyName.substring(BOOTSTRAP_PROPERTY_PREFIX_LENGTH),
                    propertyName -> brandPropertiesMap.get(propertyName, "")));

            if (!bootstrapPropertyValueMap.isEmpty()) {
                final BootstrapBrand newBootstrapBrand = new DefaultBootstrapBrand(bootstrapPropertyValueMap);

                return Optional.of(newBootstrapBrand);
            }
        }

        return Optional.absent();
    }
}
