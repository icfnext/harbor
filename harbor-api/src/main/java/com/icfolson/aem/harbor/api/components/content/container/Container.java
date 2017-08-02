package com.icfolson.aem.harbor.api.components.content.container;

import com.icfolson.aem.harbor.api.components.mixins.classifiable.Classification;

public interface Container {

    boolean isContainerFullWidth();

    boolean isParsysInherits();

    Classification getClassification();

    String getDomId();

    String getContainerClass();

    String getSectionClass();

    String getContainerElement();

    String getRole();

    boolean isHasRole();

    boolean isSection();

    String getAuthorHelpMessage();

}
