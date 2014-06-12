package com.citytechinc.cq.harbor.proper.core.content.page

import com.citytechinc.aem.prosper.specs.ProsperSpec
import com.citytechinc.cq.harbor.ns.ontology.Types
import com.citytechinc.cq.harbor.proper.api.content.page.HierarchicalPage
import com.citytechinc.cq.harbor.proper.core.content.page.impl.DefaultHierarchicalPage
import com.citytechinc.cq.library.content.adapter.ContentAdapterFactory
import com.citytechinc.cq.library.content.page.PageDecorator
import org.apache.sling.api.adapter.AdapterFactory

class DefaultHierarchicalPageSpec extends ProsperSpec {

    def setupSpec() {

        pageBuilder.content {
            home("Home") {
                "jcr:content"(Types.HARBOR_HOME_PAGE)
                section1("Section One") {
                    "jcr:content"(Types.HARBOR_SECTION_LANDING_PAGE)
                    subpage1("Subpage One") {
                        "jcr:content"("cq:PageContent")
                        subsubpage1("Sub Subpage One") {
                            "jcr:content"("cq:PageContent")
                        }
                    }
                }
            }
        }

    }

    @Override
    Collection<AdapterFactory> addAdapterFactories() {
        [new ContentAdapterFactory()]
    }

    def "Request for current Home Page from a Content Page should produce the closest parent Home Page"() {

        when: "The homepage is requested for a simple Hierarchical Page"
        def hierarchicalPage = new DefaultHierarchicalPage(getPage("/content/home/section1/subpage1").adaptTo(PageDecorator))
        def homePageOptional = hierarchicalPage.getHomePage()

        then: "The homepage should be present and should be the nearest Home Page instance"
        homePageOptional.isPresent()
        homePageOptional.get().getPath().equals("/content/home")

    }

}
