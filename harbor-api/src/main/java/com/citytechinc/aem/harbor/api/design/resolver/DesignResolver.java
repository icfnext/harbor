package com.citytechinc.aem.harbor.api.design.resolver;

import com.citytechinc.aem.harbor.api.design.AuthoredDesign;

public interface DesignResolver {

    public AuthoredDesign getAuthoredDesign(String path);

    public <T extends AuthoredDesign> T getAuthoredDesign(String path, Class<T> clazz);

}
