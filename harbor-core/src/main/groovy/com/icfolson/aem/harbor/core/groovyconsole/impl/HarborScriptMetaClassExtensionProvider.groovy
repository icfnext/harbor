package com.icfolson.aem.harbor.core.groovyconsole.impl

import com.icfolson.aem.groovy.console.api.ScriptMetaClassExtensionProvider
import com.icfolson.aem.library.api.page.PageManagerDecorator
import org.apache.felix.scr.annotations.Component
import org.apache.felix.scr.annotations.Service
import org.apache.sling.api.SlingHttpServletRequest

@Service(ScriptMetaClassExtensionProvider)
@Component(immediate = true)
class HarborScriptMetaClassExtensionProvider implements ScriptMetaClassExtensionProvider {

    @Override
    Closure getScriptMetaClass(SlingHttpServletRequest request) {
        def resourceResolver = request.resourceResolver
        def pageManager = resourceResolver.adaptTo(PageManagerDecorator)

        def closure = {
            delegate.getPage = { String path ->
                pageManager.getPage(path)
            }
        }

        closure
    }
}
