package com.citytechinc.aem.harbor.core.design.bootstrap.impl;

import com.citytechinc.aem.harbor.api.design.bootstrap.BootstrapDesign;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ValueMap;

public class DefaultBootstrapDesign implements BootstrapDesign {

    private final Resource designResource;
    private final ValueMap designValueMap;

    public DefaultBootstrapDesign(Resource designResource) {
        this.designResource = designResource;
        this.designValueMap = designResource.adaptTo(ValueMap.class);
    }

    /**
     * The brand resource - when present - will be a child of the design resource with the name
     * 'brand'.
     */
    @Override
    public void compileBrand() {

        Resource brandResource = designResource.getChild("brand");
        ValueMap brandValueMap = brandResource.adaptTo(ValueMap.class);
    }

    @Override
    public void refreshClientLibraryDefinition() {

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
        return designResource.getPath().substring("/etc/designs".length()).replace("/", ".") + ".design";
    }

    @Override
    public <AdapterType> AdapterType adaptTo(Class<AdapterType> aClass) {
        return null;
    }
}
