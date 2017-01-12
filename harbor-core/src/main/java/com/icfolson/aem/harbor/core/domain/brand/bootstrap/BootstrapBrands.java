package com.icfolson.aem.harbor.core.domain.brand.bootstrap;

import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.collect.Maps;
import com.icfolson.aem.harbor.core.domain.brand.bootstrap.impl.DefaultBootstrapBrand;
import com.icfolson.aem.library.api.node.ComponentNode;
import com.icfolson.aem.namespace.api.ontology.Properties;
import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.Property;
import javax.jcr.RepositoryException;
import java.util.List;
import java.util.Map;

public final class BootstrapBrands {

    private static final Logger LOG = LoggerFactory.getLogger(BootstrapBrands.class);

    private BootstrapBrands() {

    }

    public static final String BOOTSTRAP_PROPERTY_PREFIX = "bootstrap-";

    public static final Integer BOOTSTRAP_PROPERTY_PREFIX_LENGTH = BOOTSTRAP_PROPERTY_PREFIX.length();

    public static final Predicate<Property> BOOTSTRAP_PROPERTY_PREDICATE = property -> {
        try {
            return property.getName().startsWith(BOOTSTRAP_PROPERTY_PREFIX);
        } catch (RepositoryException e) {
            LOG.error("Repository Exception encountered checking property name");

            return false;
        }
    };

    public static Optional<BootstrapBrand> forTemplateResource(final Resource resource) throws RepositoryException {
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

    public static Optional<BootstrapBrand> forBrandResource(final Resource resource) throws RepositoryException {
        final Resource brandProperties = resource.getChild("brandproperties");

        if (brandProperties != null) {
            final List<Property> bootstrapPropertyList = brandProperties.adaptTo(ComponentNode.class).getProperties(
                BOOTSTRAP_PROPERTY_PREDICATE);

            final Map<String, String> bootstrapPropertyValueMap = Maps.newHashMap();

            for (final Property currentProperty : bootstrapPropertyList) {
                try {
                    final String propertyName = currentProperty.getName();

                    bootstrapPropertyValueMap.put(propertyName.substring(BOOTSTRAP_PROPERTY_PREFIX_LENGTH),
                        currentProperty.getString());
                } catch (RepositoryException e) {
                    LOG.error("error processing bootstrap brand properties during construction of Bootstrap Brand domain object", e);
                }
            }

            if (!bootstrapPropertyValueMap.isEmpty()) {
                final BootstrapBrand newBootstrapBrand = new DefaultBootstrapBrand(bootstrapPropertyValueMap);

                return Optional.of(newBootstrapBrand);
            }
        }

        return Optional.absent();
    }
}
