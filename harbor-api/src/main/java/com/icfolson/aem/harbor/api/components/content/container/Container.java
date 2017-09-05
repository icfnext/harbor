package com.icfolson.aem.harbor.api.components.content.container;

import com.icfolson.aem.harbor.api.components.mixins.classifiable.Classifiable;
import com.icfolson.aem.harbor.api.components.mixins.classifiable.Classification;
import com.icfolson.aem.harbor.api.components.mixins.identifiable.Identifiable;
import com.icfolson.aem.harbor.api.components.mixins.inheritable.Inheritable;
import com.icfolson.aem.harbor.api.components.mixins.paragraphsystem.ParagraphSystemContainer;

public interface Container extends Identifiable, Inheritable, Classifiable, ParagraphSystemContainer {

    boolean isFullWidth();

    Classification getClassification();

    String getContainerElement();

    String getRole();

    boolean isHasRole();

    boolean isSection();

}
