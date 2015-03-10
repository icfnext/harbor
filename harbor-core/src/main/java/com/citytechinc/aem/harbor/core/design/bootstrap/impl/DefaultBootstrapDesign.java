package com.citytechinc.aem.harbor.core.design.bootstrap.impl;

import com.citytechinc.aem.harbor.api.constants.design.Brand;
import com.citytechinc.aem.harbor.api.design.bootstrap.BootstrapDesign;
import com.citytechinc.aem.harbor.api.services.less.compiler.LessCompilationService;
import com.github.sommeri.less4j.Less4jException;
import com.google.common.base.Optional;
import com.google.common.collect.Maps;
import org.apache.jackrabbit.commons.JcrUtils;
import org.apache.sling.api.resource.PersistenceException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class DefaultBootstrapDesign implements BootstrapDesign {

    private static final Logger LOG = LoggerFactory.getLogger(DefaultBootstrapDesign.class);

    private final Resource designResource;
    private final ValueMap designValueMap;

    private final Optional<Resource> brandResourceOptional;
    private final Optional<ValueMap> brandValueMapOptional;

    private final LessCompilationService lessCompilationService;

    public DefaultBootstrapDesign(Resource designResource, LessCompilationService lessCompilationService) {
        this.designResource = designResource;
        this.designValueMap = designResource.getChild("jcr:content").adaptTo(ValueMap.class);

        this.lessCompilationService = lessCompilationService;

        brandResourceOptional = Optional.fromNullable(getBrandResourceForDesignResource(designResource));

        if (brandResourceOptional.isPresent()) {
            brandValueMapOptional = Optional.of(brandResourceOptional.get().getChild("jcr:content").adaptTo(ValueMap.class));
        }
        else {
            brandValueMapOptional = Optional.absent();
        }
    }

    private static Resource getBrandResourceForDesignResource(Resource designResource) {
        for (Resource currentDesignChild : designResource.getChildren()) {
            Resource designChildContent = currentDesignChild.getChild("jcr:content");

            if (designChildContent != null && designChildContent.isResourceType("harbor/components/brand/bootstrapbrandbuilder")) {
                return currentDesignChild;
            }
        }

        return null;
    }

    /**
     * The brand resource - when present - will be a child of the design resource with the name
     * 'brand'.
     */
    @Override
    public void compileBrand() throws Less4jException, PersistenceException, RepositoryException {

        if (!brandResourceOptional.isPresent() || !brandValueMapOptional.isPresent()) {
            LOG.warn("Compile called on Bootstrap Design without a Bootstrap Brand " + designResource.getPath());
            return;
        }

        Resource brandResource = brandResourceOptional.get();
        ValueMap brandValueMap = brandValueMapOptional.get();

        String bootstrapDirectoryPath = brandValueMap.get(Brand.BOOTSTRAP_DIRECTORY, String.class);
        ResourceResolver resourceResolver = brandResource.getResourceResolver();
        Resource bootstrapDirectoryResource = resourceResolver.getResource(bootstrapDirectoryPath);
        Resource bootstrapLessResource = bootstrapDirectoryResource.getChild("less/bootstrap.less");

        Resource clientLibraryResource = getOrCreateBrandClientLibrary(brandResource);

        createCssFileForBrandClientLibrary(clientLibraryResource, lessCompilationService.compile(bootstrapLessResource, getBrandVariableReplacements()));

        createCssTxtFileForBrandClientLibrary(clientLibraryResource);
        createJsTxtFileForBrandClientLibrary(clientLibraryResource, bootstrapDirectoryResource);

        refreshClientLibraryDefinition();

    }

    @Override
    public void refreshClientLibraryDefinition() throws PersistenceException, RepositoryException {

        Resource clientLibraryResource = getOrCreateDesignClientLibrary();
        createCssTxtFileForDesignClientLibrary(clientLibraryResource);
        createJsTxtFileForDesignClientLibrary(clientLibraryResource);

    }

    @Override
    public String getBrandCategory() {
        return getClientLibraryCategory() + ".brand";
    }

    @Override
    public String getName() {
        return designResource.getName();
    }

    @Override
    public String getClientLibraryCategory() {
        //TODO: Handle Defaulting
        return designResource.getPath().substring("/etc/designs".length() + 1).replace("/", ".") + ".design";
    }

    @Override
    public <AdapterType> AdapterType adaptTo(Class<AdapterType> aClass) {
        return null;
    }

    private Map<String, String> getBrandVariableReplacements() {
        Map<String, String> variableReplacements = Maps.newHashMap();

        if (brandValueMapOptional.isPresent()) {
            Resource brandProperties = brandResourceOptional.get().getChild("jcr:content/brandproperties");

            if (brandProperties != null) {
                for (String currentKey : brandProperties.adaptTo(ValueMap.class).keySet()) {
                    variableReplacements.put(currentKey, brandValueMapOptional.get().get(currentKey, String.class));
                }
            }
        }

        return variableReplacements;
    }

    private Resource getOrCreateBrandClientLibrary(Resource brandResource) throws PersistenceException {

        if (brandResource.getChild("clientlib") != null) {
            return brandResource.getChild("clientlib");
        }

        Map<String, Object> clientLibraryProperties = Maps.newHashMap();

        clientLibraryProperties.put("jcr:primaryType", "cq:ClientLibraryFolder");
        clientLibraryProperties.put("categories", getBrandCategory());

        Resource clientLibraryResource = brandResource.getResourceResolver().create(
                brandResource,
                "clientlib",
                clientLibraryProperties
        );

        brandResource.getResourceResolver().commit();

        return clientLibraryResource;

    }

    private Resource getOrCreateDesignClientLibrary() throws PersistenceException {

        if (designResource.getChild("clientlib") != null) {
            return designResource.getChild("clientlib");
        }

        List<String> clientLibraryCategories = Arrays.asList(designValueMap.get("ct:clientLibraryCategories", new String[0]));
        clientLibraryCategories.set(clientLibraryCategories.indexOf("{brand}"), getBrandCategory());
        clientLibraryCategories.set(clientLibraryCategories.indexOf("{brand.override}"), getBrandCategory() + ".override");

        Map<String, Object> clientLibraryProperties = Maps.newHashMap();

        clientLibraryProperties.put("jcr:primaryType", "cq:ClientLibraryFolder");
        clientLibraryProperties.put("categories", getClientLibraryCategory());
        clientLibraryProperties.put("embed", clientLibraryCategories.toArray());

        Resource clientLibraryResource = designResource.getResourceResolver().create(
                designResource,
                "clientlib",
                clientLibraryProperties
        );

        designResource.getResourceResolver().commit();

        return clientLibraryResource;

    }

    private void createCssTxtFileForBrandClientLibrary(Resource clientLibraryResource) throws RepositoryException, PersistenceException {

        if (clientLibraryResource.getChild("css.txt") != null) {
            return;
        }

        InputStream stream = new ByteArrayInputStream("bootstrap.css\n".getBytes());

        JcrUtils.putFile(
                clientLibraryResource.adaptTo(Node.class),
                "css.txt",
                "text/plain",
                stream
        );

        clientLibraryResource.getResourceResolver().commit();

    }

    private void createCssTxtFileForDesignClientLibrary(Resource clientLibraryResource) throws RepositoryException, PersistenceException {

        if (clientLibraryResource.getChild("css.txt") != null) {
            return;
        }

        InputStream stream = new ByteArrayInputStream("".getBytes());

        JcrUtils.putFile(
                clientLibraryResource.adaptTo(Node.class),
                "css.txt",
                "text/plain",
                stream
        );

        clientLibraryResource.getResourceResolver().commit();

    }


    private void createJsTxtFileForBrandClientLibrary(Resource clientLibraryResource, Resource bootstrapDirectory) throws RepositoryException, PersistenceException {

        if (clientLibraryResource.getChild("js.txt") != null) {
            return;
        }

        InputStream stream = new ByteArrayInputStream((bootstrapDirectory.getPath() + "/js/bootstrap.js\n").getBytes());

        JcrUtils.putFile(
                clientLibraryResource.adaptTo(Node.class),
                "js.txt",
                "text/plain",
                stream
        );

        clientLibraryResource.getResourceResolver().commit();

    }

    private void createJsTxtFileForDesignClientLibrary(Resource clientLibraryResource) throws RepositoryException, PersistenceException {

        if (clientLibraryResource.getChild("js.txt") != null) {
            return;
        }

        InputStream stream = new ByteArrayInputStream("".getBytes());

        JcrUtils.putFile(
                clientLibraryResource.adaptTo(Node.class),
                "js.txt",
                "text/plain",
                stream
        );

        clientLibraryResource.getResourceResolver().commit();

    }

    private void createCssFileForBrandClientLibrary(Resource clientLibraryResource, String bootstrap) throws PersistenceException, RepositoryException {

        InputStream stream = new ByteArrayInputStream(bootstrap.getBytes());

        JcrUtils.putFile(
                clientLibraryResource.adaptTo(Node.class),
                "bootstrap.css",
                "text/css",
                stream
        );

        clientLibraryResource.getResourceResolver().commit();

    }

}
