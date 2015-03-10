package com.citytechinc.aem.harbor.api.services.less.compiler;

import com.github.sommeri.less4j.Less4jException;
import org.apache.sling.api.resource.Resource;

import java.util.Map;

public interface LessCompilationService {

    /**
     *
     * @param lessResource The resource representing the LESS file to compile
     * @return The compiled CSS as a String
     */
    public String compile(Resource lessResource) throws Less4jException;

    /**
     *
     * @param lessResource The resource representing the LESS file to compile
     * @param bindings A map of variable bindings to replace placeholders in the pre-compilation LESS files
     * @return The compiled CSS as a String
     * @throws Less4jException
     */
    public String compile(Resource lessResource, Map<String, String> bindings) throws Less4jException;

}
