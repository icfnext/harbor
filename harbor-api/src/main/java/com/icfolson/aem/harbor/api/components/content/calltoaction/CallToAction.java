package com.icfolson.aem.harbor.api.components.content.calltoaction;

import com.icfolson.aem.library.api.link.Link;

public interface CallToAction {

    String LINK_IN_WINDOW = "window";

    String LINK_IN_CURRENT = "current";

    String getAction();

    Link getLinkTarget();

    Boolean isOpensInNewWindow();

    Boolean isOpensInCurrentWindow();
}
