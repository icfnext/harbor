package com.icfolson.aem.harbor.core.groovyconsole.impl

import com.icfolson.aem.groovy.console.api.BindingExtensionProvider
import com.icfolson.aem.library.api.page.PageManagerDecorator
import org.apache.felix.scr.annotations.Component
import org.apache.felix.scr.annotations.Service
import org.apache.sling.api.SlingHttpServletRequest

@Service(BindingExtensionProvider)
@Component(immediate = true)
class HarborBindingsProvider implements BindingExtensionProvider {

    @Override
    Binding getBinding(SlingHttpServletRequest request) {
        def resourceResolver = request.resourceResolver

        new Binding([
            pageManager: resourceResolver.adaptTo(PageManagerDecorator)
        ])
    }
}
