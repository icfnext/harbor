package com.icfolson.aem.harbor.api.components.content.calltoaction;

import com.icfolson.aem.harbor.api.components.mixins.identifiable.Identifiable;

public interface CallToAction extends Identifiable {

    String LINK_IN_WINDOW = "window";

    String LINK_IN_CURRENT = "current";

    String getAction();

    String getLinkHref();

    boolean isOpensInNewWindow();

    boolean isOpensInCurrentWindow();
}
