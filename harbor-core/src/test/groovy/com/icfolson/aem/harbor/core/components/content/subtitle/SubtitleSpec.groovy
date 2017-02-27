package com.icfolson.aem.harbor.core.components.content.subtitle

import com.icfolson.aem.harbor.HarborSpec
import spock.lang.Unroll

class SubtitleSpec extends HarborSpec {
    def setupSpec() {
        pageBuilder.content {
            harbor {
                page1 {
                    "jcr:content"("jcr:title": null, pageTitle: null) {
                        subtitle1()
                        subtitle2(domId: "", text: "")
                        subtitle3(domId: "", text: "Component Title")
                        subtitle4(domId: "1234", text: "")
                        subtitle5(domId: "1234", text: "Component Title")
                        subtitle6(domId: "1234", text: "{{icon Component Title}}")
                    }
                }
                page2 {
                    "jcr:content"("jcr:title": "Page 1 Title", pageTitle: null) {
                        subtitle1()
                        subtitle2(domId: "", text: "")
                        subtitle3(domId: "", text: "Component Title")
                        subtitle4(domId: "1234", text: "")
                        subtitle5(domId: "1234", text: "Component Title")
                        subtitle6(domId: "1234", text: "{{icon Component Title}}")
                    }
                }
                page3 {
                    "jcr:content"("jcr:title": null, pageTitle: "Page 1 Page Title") {
                        subtitle1()
                        subtitle2(domId: "", text: "")
                        subtitle3(domId: "", text: "Component Title")
                        subtitle4(domId: "1234", text: "")
                        subtitle5(domId: "1234", text: "Component Title")
                        subtitle6(domId: "1234", text: "{{icon Component Title}}")
                    }
                }
                page4 {
                    "jcr:content"("jcr:title": "Page 1 Title", pageTitle: "Page 1 Page Title") {
                        subtitle1()
                        subtitle2(domId: "", text: "")
                        subtitle3(domId: "", text: "Component Title")
                        subtitle4(domId: "1234", text: "")
                        subtitle5(domId: "1234", text: "Component Title")
                        subtitle6(domId: "1234", text: "{{icon Component Title}}")
                    }
                }
                page5 {
                    "jcr:content"("jcr:title": "{{icon Page 1 Title}}", pageTitle: null) {
                        subtitle1()
                    }
                }
                page6 {
                    "jcr:content"("jcr:title": null, pageTitle: "{{icon Page 1 Page Title}}") {
                        subtitle1()
                    }
                }
            }
        }
    }

    @Unroll("subtitle component at #path should have returned text '#result'")
    "get text"() {
        setup:
        def subtitle = getResource(path).adaptTo(Subtitle)

        expect:
        subtitle.text == result

        where:
        path                                          | result
        "/content/harbor/page1/jcr:content/subtitle1" | "Subtitle"
        "/content/harbor/page1/jcr:content/subtitle2" | "Subtitle"
        "/content/harbor/page1/jcr:content/subtitle3" | "Component Title"
        "/content/harbor/page1/jcr:content/subtitle4" | "Subtitle"
        "/content/harbor/page1/jcr:content/subtitle5" | "Component Title"
        "/content/harbor/page1/jcr:content/subtitle6" | '<i class="fa Component Title" aria-hidden="true"></i>'
        "/content/harbor/page2/jcr:content/subtitle1" | "Subtitle"
        "/content/harbor/page2/jcr:content/subtitle2" | "Subtitle"
        "/content/harbor/page2/jcr:content/subtitle3" | "Component Title"
        "/content/harbor/page2/jcr:content/subtitle4" | "Subtitle"
        "/content/harbor/page2/jcr:content/subtitle5" | "Component Title"
        "/content/harbor/page2/jcr:content/subtitle6" | '<i class="fa Component Title" aria-hidden="true"></i>'
        "/content/harbor/page3/jcr:content/subtitle1" | "Subtitle"
        "/content/harbor/page3/jcr:content/subtitle2" | "Subtitle"
        "/content/harbor/page3/jcr:content/subtitle3" | "Component Title"
        "/content/harbor/page3/jcr:content/subtitle4" | "Subtitle"
        "/content/harbor/page3/jcr:content/subtitle5" | "Component Title"
        "/content/harbor/page3/jcr:content/subtitle6" | '<i class="fa Component Title" aria-hidden="true"></i>'
        "/content/harbor/page4/jcr:content/subtitle1" | "Subtitle"
        "/content/harbor/page4/jcr:content/subtitle2" | "Subtitle"
        "/content/harbor/page4/jcr:content/subtitle3" | "Component Title"
        "/content/harbor/page4/jcr:content/subtitle4" | "Subtitle"
        "/content/harbor/page4/jcr:content/subtitle5" | "Component Title"
        "/content/harbor/page4/jcr:content/subtitle6" | '<i class="fa Component Title" aria-hidden="true"></i>'
        "/content/harbor/page5/jcr:content/subtitle1" | 'Subtitle'
        "/content/harbor/page6/jcr:content/subtitle1" | 'Subtitle'
    }

    @Unroll("subtitle component at #path should have returned domId '#result'")
    "get domId"() {
        setup:
        def subtitle = getResource(path).adaptTo(Subtitle)

        expect:
        subtitle.domId == result

        where:
        path                                          | result
        "/content/harbor/page1/jcr:content/subtitle1" | "subtitle"
        "/content/harbor/page1/jcr:content/subtitle2" | "subtitle"
        "/content/harbor/page1/jcr:content/subtitle3" | "component-title"
        "/content/harbor/page1/jcr:content/subtitle4" | "1234"
        "/content/harbor/page1/jcr:content/subtitle5" | "1234"
        "/content/harbor/page1/jcr:content/subtitle6" | '1234'
        "/content/harbor/page2/jcr:content/subtitle1" | "subtitle"
        "/content/harbor/page2/jcr:content/subtitle2" | "subtitle"
        "/content/harbor/page2/jcr:content/subtitle3" | "component-title"
        "/content/harbor/page2/jcr:content/subtitle4" | "1234"
        "/content/harbor/page2/jcr:content/subtitle5" | "1234"
        "/content/harbor/page2/jcr:content/subtitle6" | '1234'
        "/content/harbor/page3/jcr:content/subtitle1" | "subtitle"
        "/content/harbor/page3/jcr:content/subtitle2" | "subtitle"
        "/content/harbor/page3/jcr:content/subtitle3" | "component-title"
        "/content/harbor/page3/jcr:content/subtitle4" | "1234"
        "/content/harbor/page3/jcr:content/subtitle5" | "1234"
        "/content/harbor/page3/jcr:content/subtitle6" | '1234'
        "/content/harbor/page4/jcr:content/subtitle1" | "subtitle"
        "/content/harbor/page4/jcr:content/subtitle2" | "subtitle"
        "/content/harbor/page4/jcr:content/subtitle3" | "component-title"
        "/content/harbor/page4/jcr:content/subtitle4" | "1234"
        "/content/harbor/page4/jcr:content/subtitle5" | "1234"
        "/content/harbor/page4/jcr:content/subtitle6" | '1234'
        "/content/harbor/page5/jcr:content/subtitle1" | 'subtitle'
        "/content/harbor/page6/jcr:content/subtitle1" | 'subtitle'
    }

    @Unroll("subtitle component at #path should have returned size '#result'")
    "get size"() {
        setup:
        def subtitle = getResource(path).adaptTo(Subtitle)

        expect:
        subtitle.size == result

        where:
        path                                          | result
        "/content/harbor/page1/jcr:content/subtitle1" | "h2"
    }
}
