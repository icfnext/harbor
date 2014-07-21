package com.citytechinc.cq.harbor.proper.core.domain.brand.bootstrap.impl;

import com.citytechinc.cq.harbor.proper.core.domain.brand.bootstrap.BootstrapBrand;

import java.util.Map;

public class DefaultBootstrapBrand implements BootstrapBrand {

    private final Map<String, String> bootstrapProperties;

    public DefaultBootstrapBrand(Map<String, String> bootstrapProperties) {
        this.bootstrapProperties = bootstrapProperties;
    }

    @Override
    public Map<String, String> getVariables() {
        return bootstrapProperties;
    }

}
