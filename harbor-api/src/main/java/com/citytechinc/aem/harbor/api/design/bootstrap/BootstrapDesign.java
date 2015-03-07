package com.citytechinc.aem.harbor.api.design.bootstrap;

import com.citytechinc.aem.harbor.api.design.AuthoredDesign;
import com.github.sommeri.less4j.Less4jException;
import org.apache.sling.api.adapter.Adaptable;
import org.apache.sling.api.resource.PersistenceException;

import javax.jcr.RepositoryException;

public interface BootstrapDesign extends AuthoredDesign, Adaptable {

    public static final String RESOURCE_TYPE = "harbor/components/design/bootstrapdesign";

    public void compileBrand() throws Less4jException, PersistenceException, RepositoryException;

    public void refreshClientLibraryDefinition() throws PersistenceException, RepositoryException;

    public String getBrandCategory();

}
