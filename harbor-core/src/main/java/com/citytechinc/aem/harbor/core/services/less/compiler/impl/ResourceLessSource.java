package com.citytechinc.aem.harbor.core.services.less.compiler.impl;

import com.github.sommeri.less4j.LessSource;
import org.apache.commons.io.IOUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.Node;
import javax.jcr.Property;
import javax.jcr.RepositoryException;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

public class ResourceLessSource extends LessSource.AbstractHierarchicalSource {

    private static final Logger LOG = LoggerFactory.getLogger(ResourceLessSource.class);

    private final Resource inputResource;
    private final Map<String, String> bindings;

    public ResourceLessSource(Resource inputResource, Map<String, String> bindings) {
        this.inputResource = inputResource;
        this.bindings = bindings;
    }

    public Resource getInputResource() {
        return inputResource;
    }


    @Override
    public LessSource relativeSource(String filename) throws FileNotFound, CannotReadFile, StringSourceException {

        Resource fileResource;
        if (filename.startsWith("/")) {
            ResourceResolver resourceResolver = getInputResource().getResourceResolver();
            fileResource = resourceResolver.getResource(filename);
        }
        else {
            fileResource = getInputResource().getParent().getChild(filename);
        }

        if (fileResource == null) {
            throw new FileNotFound();
        }

        return new ResourceLessSource(fileResource, bindings);

    }

    @Override
    public String getContent() throws FileNotFound, CannotReadFile {
        Resource contentResource = getInputResource().getChild("jcr:content");

        if (contentResource == null) {
            LOG.error("File Content Node requested for " + getInputResource().getPath() + " but this resource has no jcr:content child");
            throw new CannotReadFile();
        }

        Node contentNode = contentResource.adaptTo(Node.class);

        Property data;
        try {
            data = contentNode.getProperty("jcr:data");
        } catch (RepositoryException e) {
            LOG.error("Repository Exception encountered attempting to read file " + getInputResource().getPath(), e);
            throw new CannotReadFile();
        }

        if (data == null) {
            LOG.error("File Content Node requested for " + getInputResource().getPath() + " but the jcr:content node does not have a jcr:data property");
            throw new CannotReadFile();
        }

        StringWriter writer = new StringWriter();
        try {
            IOUtils.copy(data.getBinary().getStream(), writer);
        } catch (IOException e) {
            LOG.error("IOException encountered attempting to read file " + getInputResource().getPath(), e);
            throw new CannotReadFile();
        } catch (RepositoryException e) {
            LOG.error("Repository Exception encountered attempting to read file " + getInputResource().getPath(), e);
            throw new CannotReadFile();
        }

        return applyBindingsToString(writer.toString(), bindings);
    }

    @Override
    public byte[] getBytes() throws FileNotFound, CannotReadFile {
        return getContent().getBytes();
    }

    @Override
    public String getName() {
        return getInputResource().getPath();
    }

    /**
     * Two Resource-based LESS Sources are considered equal if their resources are at the same path
     *
     * @param obj The object under comparison
     * @return True if the resources backing both sources are the same, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        ResourceLessSource other = (ResourceLessSource) obj;

        return other.getInputResource().getPath().equals(getInputResource().getPath());
    }

    private static String applyBindingsToString(String input, Map<String, String> bindings) {
        if (bindings == null) {
            return input;
        }

        String result = input;

        for (String currentBinding : bindings.keySet()) {
            result = input.replaceAll("<%" + currentBinding + "%>", bindings.get(currentBinding));
        }

        return result;
    }

}
