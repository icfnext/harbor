package com.icfolson.aem.harbor.api.components.content.text;

import com.icfolson.aem.harbor.api.components.mixins.classifiable.Classification;

public interface Text {

    String getContent();

    Classification getClassification();

}
