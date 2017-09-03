package com.icfolson.aem.harbor.api.components.content.navigation.page;

public interface NavigablePage {

    boolean isRedirect();

    boolean isAlongActivePath();

    String getTitle();

    String getHref();

}
