package com.citytechinc.aem.harbor.core.components.mixins.classifiable;

import com.citytechinc.aem.namespace.api.ontology.Properties;

import java.util.List;

public class InheritedClassification extends Classification {

    @Override
    protected List<String> getClassificationIdStrings() {
        return getAsListInherited(Properties.CITYTECH_CLASSIFICATION, String.class);
    }

}
