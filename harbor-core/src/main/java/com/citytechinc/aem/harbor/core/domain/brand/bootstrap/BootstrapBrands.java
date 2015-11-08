package com.citytechinc.aem.harbor.core.domain.brand.bootstrap;

import java.util.List;
import java.util.Map;

import javax.jcr.Property;
import javax.jcr.RepositoryException;

import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.citytechinc.aem.bedrock.api.node.ComponentNode;
import com.citytechinc.aem.namespace.api.ontology.Properties;
import com.citytechinc.aem.harbor.core.domain.brand.bootstrap.impl.DefaultBootstrapBrand;
import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.collect.Maps;

public class BootstrapBrands {

	private static final Logger LOG = LoggerFactory.getLogger(BootstrapBrands.class);

	private BootstrapBrands() {
	}

	public static final String BOOTSTRAP_PROPERTY_PREFIX = "bootstrap-";
	public static final Integer BOOTSTRAP_PROPERTY_PREFIX_LENGTH = BOOTSTRAP_PROPERTY_PREFIX.length();

	public static final Predicate<Property> BOOTSTRAP_PROPERTY_PREDICATE = new Predicate<Property>() {

		@Override
		public boolean apply(Property property) {
			try {
				return property.getName().startsWith(BOOTSTRAP_PROPERTY_PREFIX);
			} catch (RepositoryException e) {
				LOG.error("Repository Exception encountered checking property name");
				return false;
			}
		}

	};

	public static Optional<BootstrapBrand> forTemplateResource(Resource resource) throws RepositoryException {

		ComponentNode componentNode = resource.adaptTo(ComponentNode.class);

		Optional<String> brandPath = componentNode.get(Properties.CITYTECH_BRAND, String.class);

		if (brandPath.isPresent() && StringUtils.isNotBlank(brandPath.get())) {
			ResourceResolver resourceResolver = resource.getResourceResolver();
			Resource brandResource = resourceResolver.getResource(brandPath.get());

			if (brandResource != null) {
				return forBrandResource(brandResource);
			}
		}

		return Optional.absent();

	}

	public static Optional<BootstrapBrand> forBrandResource(Resource resource) throws RepositoryException {

		Resource brandProperties = resource.getChild("brandproperties");

		if (brandProperties != null) {
			ComponentNode componentNode = brandProperties.adaptTo(ComponentNode.class);

			List<Property> bootstrapPropertyList = componentNode.getProperties(BOOTSTRAP_PROPERTY_PREDICATE);

			Map<String, String> bootstrapPropertyValueMap = Maps.newHashMap();

			for (Property currentProperty : bootstrapPropertyList) {
				try {
					String propertyName = currentProperty.getName();
					bootstrapPropertyValueMap.put(propertyName.substring(BOOTSTRAP_PROPERTY_PREFIX_LENGTH),
						currentProperty.getString());
				} catch (RepositoryException e) {
					LOG.error("Repository Exception encountered attempting to process bootstrap brand properties during construction of Bootstrap Brand domain object");
				}
			}

			if (!bootstrapPropertyValueMap.isEmpty()) {
				BootstrapBrand newBootstrapBrand = new DefaultBootstrapBrand(bootstrapPropertyValueMap);
				return Optional.of(newBootstrapBrand);
			}
		}

		return Optional.absent();

	}
}
