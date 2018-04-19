package com.icfolson.aem.harbor.api.components.content.heading;

import com.adobe.cq.export.json.ComponentExporter;
import com.icfolson.aem.harbor.api.components.mixins.identifiable.Identifiable;

public interface Heading extends Identifiable, ComponentExporter {

    String getText();

    String getSize();

}
