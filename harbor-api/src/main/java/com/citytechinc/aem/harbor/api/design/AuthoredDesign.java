package com.citytechinc.aem.harbor.api.design;

import org.apache.sling.api.adapter.Adaptable;

public interface AuthoredDesign extends Adaptable {

    public String getName();

    public String getClientLibraryCategory();

}
