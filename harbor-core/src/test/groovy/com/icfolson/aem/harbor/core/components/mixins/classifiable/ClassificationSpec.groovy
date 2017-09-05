package com.icfolson.aem.harbor.core.components.mixins.classifiable

import com.icfolson.aem.harbor.HarborSpec
import com.icfolson.aem.namespace.api.ontology.Properties

class ClassificationSpec extends HarborSpec {

    def setupSpec() {
        pageBuilder.content {
            harbor {
                "jcr:content"((Properties.ICF_OLSON_CLASSIFICATION): [
                    "/etc/tags/colors/green",
                    "/etc/tags/colors/red",
                    "/etc/tags/colors/blue"
                ])
                section()
            }
        }
    }

    def "get classification IDs"() {
        setup:
        def classification = getResource("/content/harbor/jcr:content").adaptTo(TagBasedClassification)

        expect:
        classification.classified

        and:
        classification.classificationIds == ["colors:green", "colors:red", "colors:blue"]
    }

    def "get class names"() {
        setup:
        def classification = getResource("/content/harbor/jcr:content").adaptTo(TagBasedClassification)

        expect:
        classification.classNames == "green red blue"
    }

    def "get inherited class names"() {
        setup:
        def classification = getResource("/content/harbor/section/jcr:content").adaptTo(InheritedTagBasedClassification)

        expect:
        classification.classNames == "green red blue"
    }
}
