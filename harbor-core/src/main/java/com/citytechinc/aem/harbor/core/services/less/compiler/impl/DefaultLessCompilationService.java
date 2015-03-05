package com.citytechinc.aem.harbor.core.services.less.compiler.impl;

import com.citytechinc.aem.harbor.api.services.less.compiler.LessCompilationService;
import com.github.sommeri.less4j.Less4jException;
import com.github.sommeri.less4j.LessCompiler;
import com.github.sommeri.less4j.LessSource;
import com.github.sommeri.less4j.core.DefaultLessCompiler;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.api.resource.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
@Service
public class DefaultLessCompilationService implements LessCompilationService {

    private static final Logger LOG = LoggerFactory.getLogger(DefaultLessCompilationService.class);

    private final LessCompiler lessCompiler;

    public DefaultLessCompilationService() {
        this.lessCompiler = new DefaultLessCompiler();
    }

    @Override
    public String compile(Resource lessResource) throws Less4jException {
        LessSource lessSource = new ResourceLessSource(lessResource);

        LessCompiler.CompilationResult compilationResult = lessCompiler.compile(lessSource);

        if (compilationResult.getWarnings() != null) {
            for (LessCompiler.Problem currentProblem : compilationResult.getWarnings()) {
                LOG.warn("At " + currentProblem.getSource().getName() + " Line " + currentProblem.getLine() + " Character " + currentProblem.getCharacter() + " " + currentProblem.getMessage());
            }
        }

        return compilationResult.getCss();
    }

}
