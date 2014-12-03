package com.citytechinc.aem.harbor.core.components.content.page.meta;

import com.citytechinc.aem.bedrock.core.components.AbstractComponent;
import com.citytechinc.aem.namespace.api.ontology.Properties;

public class MetaPage extends AbstractComponent {

    public boolean isHiddenFromRobots() {
        return getCurrentPage().getProperties().get(Properties.CITYTECH_HIDDEN_FROM_ROBOTS, false);
    }

}
