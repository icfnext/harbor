package com.icfolson.aem.harbor.core.components.mixins.classifiable;

import com.icfolson.aem.namespace.api.ontology.Properties;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

import java.util.List;

@Model(adaptables = Resource.class)
public class InheritedClassification extends Classification {

    @Override
    protected List<String> getClassificationIdStrings() {
        return getAsListInherited(Properties.ICF_OLSON_CLASSIFICATION, String.class);
    }
}
