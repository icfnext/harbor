package com.icfolson.aem.harbor.core.components.content.breadcrumb

import com.icfolson.aem.harbor.HarborSpec
import spock.lang.Unroll

import static com.icfolson.aem.harbor.core.components.content.breadcrumb.BreadcrumbItemRenderingStrategy.INTERMEDIATE_ITEM_CONFIGURATION_PREFIX
import static com.icfolson.aem.harbor.core.components.content.breadcrumb.BreadcrumbItemRenderingStrategy.ROOT_ITEM_CONFIGURATION_PREFIX

@Unroll
class BreadcrumbItemConfigurationSpec extends HarborSpec {

    def setupSpec() {
        pageBuilder.content {
            harbor {
                "jcr:content" {
                    breadcrumb {
                        rootItem(hideIcon: true, hideTitle: true)
                        intermediateItem(hideIcon: true, hideTitle: true)
                    }
                }
                one() {
                    "jcr:content" {
                        breadcrumb()
                    }
                }
            }
        }
    }

    def "hide icon"() {
        setup:
        def componentNode = getComponentNode(path)
        def configuration = new BreadcrumbItemConfiguration(componentNode, prefix, inherits)

        expect:
        configuration.hideIcon == hideIcon

        where:
        path                                         | prefix                                 | inherits | hideIcon
        "/content/harbor/jcr:content/breadcrumb"     | ROOT_ITEM_CONFIGURATION_PREFIX         | false    | true
        "/content/harbor/jcr:content/breadcrumb"     | ROOT_ITEM_CONFIGURATION_PREFIX         | true     | true
        "/content/harbor/jcr:content/breadcrumb"     | INTERMEDIATE_ITEM_CONFIGURATION_PREFIX | false    | true
        "/content/harbor/jcr:content/breadcrumb"     | INTERMEDIATE_ITEM_CONFIGURATION_PREFIX | true     | true
        "/content/harbor/one/jcr:content/breadcrumb" | ROOT_ITEM_CONFIGURATION_PREFIX         | false    | false
        "/content/harbor/one/jcr:content/breadcrumb" | ROOT_ITEM_CONFIGURATION_PREFIX         | true     | true
        "/content/harbor/one/jcr:content/breadcrumb" | INTERMEDIATE_ITEM_CONFIGURATION_PREFIX | false    | false
        "/content/harbor/one/jcr:content/breadcrumb" | INTERMEDIATE_ITEM_CONFIGURATION_PREFIX | true     | true
    }

    def "hide title"() {
        setup:
        def componentNode = getComponentNode(path)
        def configuration = new BreadcrumbItemConfiguration(componentNode, prefix, inherits)

        expect:
        configuration.hideTitle == hideTitle

        where:
        path                                         | prefix                                 | inherits | hideTitle
        "/content/harbor/jcr:content/breadcrumb"     | ROOT_ITEM_CONFIGURATION_PREFIX         | false    | true
        "/content/harbor/jcr:content/breadcrumb"     | ROOT_ITEM_CONFIGURATION_PREFIX         | true     | true
        "/content/harbor/jcr:content/breadcrumb"     | INTERMEDIATE_ITEM_CONFIGURATION_PREFIX | false    | true
        "/content/harbor/jcr:content/breadcrumb"     | INTERMEDIATE_ITEM_CONFIGURATION_PREFIX | true     | true
        "/content/harbor/one/jcr:content/breadcrumb" | ROOT_ITEM_CONFIGURATION_PREFIX         | false    | false
        "/content/harbor/one/jcr:content/breadcrumb" | ROOT_ITEM_CONFIGURATION_PREFIX         | true     | true
        "/content/harbor/one/jcr:content/breadcrumb" | INTERMEDIATE_ITEM_CONFIGURATION_PREFIX | false    | false
        "/content/harbor/one/jcr:content/breadcrumb" | INTERMEDIATE_ITEM_CONFIGURATION_PREFIX | true     | true
    }
}