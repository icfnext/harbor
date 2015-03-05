package com.citytechinc.aem.harbor.api.design.bootstrap;

import com.citytechinc.aem.harbor.api.design.AuthoredDesign;
import org.apache.sling.api.adapter.Adaptable;

public interface BootstrapDesign extends AuthoredDesign, Adaptable {

    public void compileBrand();

    public void refreshClientLibraryDefinition();

    public String getBrandCategory();

}
