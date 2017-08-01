package com.icfolson.aem.harbor.core.annotations

import groovy.transform.AnnotationCollector
import com.citytechinc.cq.component.annotations.Component
import org.apache.sling.api.resource.Resource
import org.apache.sling.models.annotations.Model

@AnnotationCollector
@Component(editConfig = false, suppressTouchUIDialog = true, suppressClassicUIDialog = true)
@Model(adaptables = Resource)
@interface HarborProxyComponent {

}