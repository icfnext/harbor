package com.icfolson.aem.harbor.api.components.content.tabs;

import com.icfolson.aem.harbor.api.components.mixins.classifiable.Classifiable;

import java.util.List;

public interface Tabs extends Classifiable {

    List<Tab> getTabs();

}
