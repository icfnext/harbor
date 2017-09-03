package com.icfolson.aem.harbor.api.components.content.navigation.bootstrapnavigation.navbar;

import com.icfolson.aem.harbor.api.components.mixins.identifiable.Identifiable;

public interface BootstrapPrimaryNavbar extends Identifiable {

    boolean isSticky();

    boolean isFullWidth();

    String getBrandResourceType();

    String getPrimaryNavigationResourceType();

}
