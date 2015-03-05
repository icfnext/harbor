package com.citytechinc.aem.harbor.api.services.less.compiler;

import com.github.sommeri.less4j.Less4jException;
import org.apache.sling.api.resource.Resource;

public interface LessCompilationService {

    /**
     *
     * @param lessResource The resource representing the LESS file to compile
     * @return The compiled CSS as a String
     */
    public String compile(Resource lessResource) throws Less4jException;

}
