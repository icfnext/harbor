package com.citytechinc.aem.harbor.core.components.mixins.semantics;

import com.citytechinc.aem.namespace.api.ontology.Properties;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.widgets.TagInputField;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;

import javax.inject.Inject;
import javax.inject.Named;

@Model(adaptables = Resource.class)
public class Property {

    @DialogField(fieldLabel = "Property", fieldDescription = "The property type to associated with the component content via the RDFa property attribute", fieldName = "./" + Properties.SEMANTIC_PROPERTY)
    @TagInputField(multiple = false) //TODO: check whether property can be multivalued
    @Inject @Named(Properties.SEMANTIC_PROPERTY) @Optional
    private String propertyType;

    public String getType() {
        return propertyType;
    }

}
