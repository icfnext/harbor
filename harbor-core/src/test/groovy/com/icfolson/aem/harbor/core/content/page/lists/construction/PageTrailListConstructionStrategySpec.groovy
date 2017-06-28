package com.icfolson.aem.harbor.core.content.page.lists.construction

import com.icfolson.aem.harbor.HarborSpec
import com.icfolson.aem.harbor.api.content.page.HomePage
import org.apache.sling.api.resource.ModifiableValueMap
import spock.lang.Unroll

@Unroll
class PageTrailListConstructionStrategySpec extends HarborSpec {

    static final String COMPONENT_PATH = "/content/harbor/category/product/jcr:content/component"

    def setupSpec() {
        pageBuilder.content {
            harbor {
                category() {
                    product() {
                        "jcr:content" {
                            component()
                        }
                    }
                }
            }
        }

        getNode("/content/harbor/jcr:content").addMixin(HomePage.RDF_TYPE)
    }

    def "construct list of trail pages"() {
        setup:
        def properties = getResource(COMPONENT_PATH).adaptTo(ModifiableValueMap)

        properties.putAll([
            includeCurrentPageInTrail: includeCurrentPageInTrail,
            includeRootPageInTrail: includeRootPageInTrail
        ])

        resourceResolver.commit()

        def strategy = getResource(COMPONENT_PATH).adaptTo(PageTrailListConstructionStrategy)

        expect:
        strategy.construct()*.path == paths

        where:
        includeCurrentPageInTrail | includeRootPageInTrail | paths
        false                     | false                  | ["/content/harbor/category"]
        false                     | true                   | ["/content/harbor", "/content/harbor/category"]
        true                      | false                  | ["/content/harbor/category", "/content/harbor/category/product"]
        true                      | true                   | ["/content/harbor", "/content/harbor/category", "/content/harbor/category/product"]
    }

    def "trail page properties"() {
        setup:
        def properties = getResource(COMPONENT_PATH).adaptTo(ModifiableValueMap)

        properties.putAll([
            includeCurrentPageInTrail: true,
            includeRootPageInTrail: true
        ])

        resourceResolver.commit()

        def strategy = getResource(COMPONENT_PATH).adaptTo(PageTrailListConstructionStrategy)
        def trailPage = strategy.construct().get(index)

        expect:
        trailPage.root == isRoot

        and:
        trailPage.current == isCurrent

        where:
        index | isRoot | isCurrent
        0     | true   | false
        1     | false  | false
        2     | false  | true
    }
}
