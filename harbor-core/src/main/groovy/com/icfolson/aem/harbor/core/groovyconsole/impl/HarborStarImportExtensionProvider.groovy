package com.icfolson.aem.harbor.core.groovyconsole.impl

import com.icfolson.aem.groovy.console.api.StarImportExtensionProvider
import org.apache.felix.scr.annotations.Component
import org.apache.felix.scr.annotations.Service

@Service(StarImportExtensionProvider)
@Component(immediate = true)
class HarborStarImportExtensionProvider implements StarImportExtensionProvider {

    private static final Set<String> HARBOR_STAR_IMPORTS = [
        "com.icfolson.aem.harbor.api.services.meta",
        "com.icfolson.aem.harbor.api.services.rss",
        "com.icfolson.aem.harbor.api.services.sitemap",
        "com.icfolson.aem.harbor.core.components.content.accordion",
        "com.icfolson.aem.harbor.core.components.content.breadcrumb",
        "com.icfolson.aem.harbor.core.components.content.calltoaction",
        "com.icfolson.aem.harbor.core.components.content.carousel",
        "com.icfolson.aem.harbor.core.components.content.classifiedcontent",
        "com.icfolson.aem.harbor.core.components.content.columns",
        "com.icfolson.aem.harbor.core.components.content.container",
        "com.icfolson.aem.harbor.core.components.content.heading",
        "com.icfolson.aem.harbor.core.components.content.html",
        "com.icfolson.aem.harbor.core.components.content.list",
        "com.icfolson.aem.harbor.core.components.content.list.assets",
        "com.icfolson.aem.harbor.core.components.content.list.link",
        "com.icfolson.aem.harbor.core.components.content.list.page",
        "com.icfolson.aem.harbor.core.components.content.modalcalltoaction",
        "com.icfolson.aem.harbor.core.components.content.navigation.bootstrapnavigation.bakedinmainmanualnavigation",
        "com.icfolson.aem.harbor.core.components.content.navigation.bootstrapnavigation.mainautonavigation",
        "com.icfolson.aem.harbor.core.components.content.navigation.bootstrapnavigation.mainmanualnavigation",
        "com.icfolson.aem.harbor.core.components.content.navigation.bootstrapnavigation.secondarynavigation",
        "com.icfolson.aem.harbor.core.components.content.pagefooter",
        "com.icfolson.aem.harbor.core.components.content.pageheader",
        "com.icfolson.aem.harbor.core.components.content.projections.pagereference",
        "com.icfolson.aem.harbor.core.components.content.projections.reference",
        "com.icfolson.aem.harbor.core.components.content.responsivecontainer",
        "com.icfolson.aem.harbor.core.components.content.rssfeed",
        "com.icfolson.aem.harbor.core.components.content.search.searchinputwidget",
        "com.icfolson.aem.harbor.core.components.content.subtitle",
        "com.icfolson.aem.harbor.core.components.content.tabs",
        "com.icfolson.aem.harbor.core.components.content.text",
        "com.icfolson.aem.harbor.core.components.content.title",
        "com.icfolson.aem.harbor.core.components.mixins.classifiable",
        "com.icfolson.aem.harbor.core.components.page",
        "com.icfolson.aem.harbor.core.components.page.global",
        "com.icfolson.aem.harbor.core.components.page.master.listitem",
        "com.icfolson.aem.harbor.core.components.page.metapage",
        "com.icfolson.aem.harbor.core.components.page.sitemappedpage"
    ] as Set

    @Override
    Set<String> getStarImports() {
        HARBOR_STAR_IMPORTS
    }
}