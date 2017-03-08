package com.icfolson.aem.harbor.core.components.content.title

import com.icfolson.aem.harbor.HarborSpec
import spock.lang.Unroll

class TitleSpec extends HarborSpec {

    def setupSpec() {
        pageBuilder.content {
            harbor {
                page1 {
                    "jcr:content"("jcr:title": null, pageTitle: null) {
                        title1()
                        title2(text: "Component Title")
                        title3(domId: "1234")
                        title4(domId: "1234", text: "Component Title")
                        title5(domId: "1234", text: "{{icon Component Title}}")
                    }
                }
                page2 {
                    "jcr:content"("jcr:title": "Page 1 Title", pageTitle: null) {
                        title1()
                        title2(text: "Component Title")
                        title3(domId: "1234")
                        title4(domId: "1234", text: "Component Title")
                        title5(domId: "1234", text: "{{icon Component Title}}")
                    }
                }
                page3 {
                    "jcr:content"("jcr:title": null, pageTitle: "Page 1 Page Title") {
                        title1()
                        title2(text: "Component Title")
                        title3(domId: "1234")
                        title4(domId: "1234", text: "Component Title")
                        title5(domId: "1234", text: "{{icon Component Title}}")
                    }
                }
                page4 {
                    "jcr:content"("jcr:title": "Page 1 Title", pageTitle: "Page 1 Page Title") {
                        title1()
                        title2(text: "Component Title")
                        title3(domId: "1234")
                        title4(domId: "1234", text: "Component Title")
                        title5(domId: "1234", text: "{{icon Component Title}}")
                    }
                }
                page5 {
                    "jcr:content"("jcr:title": "{{icon Page 1 Title}}", pageTitle: null) {
                        title1()
                    }
                }
                page6 {
                    "jcr:content"("jcr:title": null, pageTitle: "{{icon Page 1 Page Title}}") {
                        title1()
                    }
                }
            }
        }
    }

    @Unroll("title component at #path should have returned text '#result'")
    "get text"() {
        setup:
        def title = getResource(path).adaptTo(Title)

        expect:
        title.text == result

        where:
        path                                       | result
        "/content/harbor/page1/jcr:content/title1" | "Title"
        "/content/harbor/page1/jcr:content/title2" | "Component Title"
        "/content/harbor/page1/jcr:content/title3" | "Title"
        "/content/harbor/page1/jcr:content/title4" | "Component Title"
        "/content/harbor/page1/jcr:content/title5" | '<i class="fa Component Title" aria-hidden="true"></i>'
        "/content/harbor/page2/jcr:content/title1" | "Page 1 Title"
        "/content/harbor/page2/jcr:content/title2" | "Component Title"
        "/content/harbor/page2/jcr:content/title3" | "Page 1 Title"
        "/content/harbor/page2/jcr:content/title4" | "Component Title"
        "/content/harbor/page2/jcr:content/title5" | '<i class="fa Component Title" aria-hidden="true"></i>'
        "/content/harbor/page3/jcr:content/title1" | "Page 1 Page Title"
        "/content/harbor/page3/jcr:content/title2" | "Component Title"
        "/content/harbor/page3/jcr:content/title3" | "Page 1 Page Title"
        "/content/harbor/page3/jcr:content/title4" | "Component Title"
        "/content/harbor/page3/jcr:content/title5" | '<i class="fa Component Title" aria-hidden="true"></i>'
        "/content/harbor/page4/jcr:content/title1" | "Page 1 Page Title"
        "/content/harbor/page4/jcr:content/title2" | "Component Title"
        "/content/harbor/page4/jcr:content/title3" | "Page 1 Page Title"
        "/content/harbor/page4/jcr:content/title4" | "Component Title"
        "/content/harbor/page4/jcr:content/title5" | '<i class="fa Component Title" aria-hidden="true"></i>'
        "/content/harbor/page5/jcr:content/title1" | '<i class="fa Page 1 Title" aria-hidden="true"></i>'
        "/content/harbor/page6/jcr:content/title1" | '<i class="fa Page 1 Page Title" aria-hidden="true"></i>'
    }

    @Unroll("title component at #path should have returned domId '#result'")
    "get domId"() {
        setup:
        def title = getResource(path).adaptTo(Title)

        expect:
        title.domId == result

        where:
        path                                       | result
        "/content/harbor/page1/jcr:content/title1" | ""
        "/content/harbor/page1/jcr:content/title2" | "component-title"
        "/content/harbor/page1/jcr:content/title3" | "1234"
        "/content/harbor/page1/jcr:content/title4" | "1234"
        "/content/harbor/page1/jcr:content/title5" | '1234'
        "/content/harbor/page2/jcr:content/title1" | "page-1-title"
        "/content/harbor/page2/jcr:content/title2" | "component-title"
        "/content/harbor/page2/jcr:content/title3" | "1234"
        "/content/harbor/page2/jcr:content/title4" | "1234"
        "/content/harbor/page2/jcr:content/title5" | '1234'
        "/content/harbor/page3/jcr:content/title1" | "page-1-page-title"
        "/content/harbor/page3/jcr:content/title2" | "component-title"
        "/content/harbor/page3/jcr:content/title3" | "1234"
        "/content/harbor/page3/jcr:content/title4" | "1234"
        "/content/harbor/page3/jcr:content/title5" | '1234'
        "/content/harbor/page4/jcr:content/title1" | "page-1-page-title"
        "/content/harbor/page4/jcr:content/title2" | "component-title"
        "/content/harbor/page4/jcr:content/title3" | "1234"
        "/content/harbor/page4/jcr:content/title4" | "1234"
        "/content/harbor/page4/jcr:content/title5" | '1234'
        "/content/harbor/page5/jcr:content/title1" | '{{icon-page-1-title}}'
        "/content/harbor/page6/jcr:content/title1" | '{{icon-page-1-page-title}}'
    }

    @Unroll("title component at #path should have returned size '#result'")
    "get size"() {
        setup:
        def title = getResource(path).adaptTo(Title)

        expect:
        title.size == result

        where:
        path                                       | result
        "/content/harbor/page1/jcr:content/title1" | "h1"
    }
}