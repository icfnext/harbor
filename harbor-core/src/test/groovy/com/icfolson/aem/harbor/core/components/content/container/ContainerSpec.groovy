package com.icfolson.aem.harbor.core.components.content.container

import com.icfolson.aem.harbor.HarborSpec
import com.icfolson.aem.harbor.api.constants.bootstrap.Bootstrap
import com.icfolson.aem.namespace.api.ontology.Properties
import spock.lang.Unroll

@Unroll
class ContainerSpec extends HarborSpec {

    def setupSpec() {
        pageBuilder.content {
            harbor {
                "jcr:content" {
                    container1(fullWidth: false)
                    container2(fullWidth: true, (Properties.ICF_OLSON_CLASSIFICATION): [
                        "/etc/tags/colors/green",
                        "/etc/tags/colors/red"
                    ])
                }
            }
        }
    }

    def "get container class"() {
        setup:
        def container = getResource(path).adaptTo(Container)

        expect:
        container.containerClass == containerClass

        where:
        path                                     | containerClass
        "/content/harbor/jcr:content/container1" | Bootstrap.CONTAINER_CLASS
        "/content/harbor/jcr:content/container2" | Bootstrap.CONTAINER_FULL_WIDTH_CLASS
    }

    def "get section class"() {
        setup:
        def container = getResource("/content/harbor/jcr:content/container2").adaptTo(Container)

        expect:
        container.sectionClass == "green red"
    }
}
