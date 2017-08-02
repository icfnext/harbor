package com.icfolson.aem.harbor.core.components.mixins.classifiable;

import com.icfolson.aem.harbor.api.components.mixins.classifiable.InheritedClassification;
import com.icfolson.aem.namespace.api.ontology.Properties;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

import java.util.List;

@Model(adaptables = Resource.class, adapters = InheritedClassification.class)
public class DefaultInheritedClassification extends DefaultClassification implements InheritedClassification {

    @Override
    protected List<String> getClassificationIdStrings() {
        return getAsListInherited(Properties.ICF_OLSON_CLASSIFICATION, String.class);
    }

}
