package com.icfolson.aem.harbor.core.components.content.accordion

import com.icfolson.aem.harbor.HarborSpec
import spock.lang.Unroll

@Unroll
class AccordionSpec extends HarborSpec {

    def setupSpec() {
        pageBuilder.content {
            harbor {
                "jcr:content" {
                    accordion1(openFirstItem: true) {
                        item1("sling:resourceType": AccordionItem.RESOURCE_TYPE, title: "Item 1")
                        item2("sling:resourceType": AccordionItem.RESOURCE_TYPE)
                    }
                    accordion2() {
                        item1("sling:resourceType": AccordionItem.RESOURCE_TYPE)
                        item2("sling:resourceType": AccordionItem.RESOURCE_TYPE)
                    }
                    accordion3()
                }
            }
        }
    }

    def "accordion name"() {
        setup:
        def resource = getResource("/content/harbor/jcr:content/accordion1")
        def accordion = resource.adaptTo(Accordion)

        expect:
        accordion.name == "accordion1"
    }

    def "accordion items"() {
        setup:
        def accordion = getResource(path).adaptTo(Accordion)

        expect:
        accordion.hasItems == hasItems

        and:
        accordion.items.size() == size

        where:
        path                                     | hasItems | size
        "/content/harbor/jcr:content/accordion1" | true     | 2
        "/content/harbor/jcr:content/accordion3" | false    | 0
    }

    def "accordion item unique ID"() {
        setup:
        def accordion = getResource("/content/harbor/jcr:content/accordion1").adaptTo(Accordion)

        accordion.items*.accordionUniqueId == [
            "content-harbor-jcr:content-accordion1-item1",
            "content-harbor-jcr:content-accordion1-item2"
        ]
    }

    def "accordion item is open"() {
        setup:
        def accordion = getResource(path).adaptTo(Accordion)

        expect:
        accordion.items.get(index).open == isOpen

        where:
        path                                     | index | isOpen
        "/content/harbor/jcr:content/accordion1" | 0     | true
        "/content/harbor/jcr:content/accordion1" | 1     | false
        "/content/harbor/jcr:content/accordion2" | 0     | false
        "/content/harbor/jcr:content/accordion2" | 1     | false
    }

    def "accordion item title"() {
        setup:
        def accordion = getResource("/content/harbor/jcr:content/accordion1").adaptTo(Accordion)

        expect:
        accordion.items.get(index).title == title

        where:
        index | title
        0     | "Item 1"
        1     | "Accordion Item 2"
    }
}
