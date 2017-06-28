package com.icfolson.aem.harbor.core.components.page.metapage

import com.icfolson.aem.harbor.HarborSpec
import com.icfolson.aem.harbor.api.services.meta.MetadataConfigService
import org.apache.sling.api.SlingHttpServletRequest
import org.apache.sling.api.resource.Resource
import spock.lang.Unroll

@Unroll
class MetaPageSpec extends HarborSpec {

    def setupSpec() {
        pageBuilder.content {
            harbor("Harbor", pageTitle: "Harbor Page") {
                one("One", canonicalUrl: "/one", noindex: false, nofollow: false)
                two("Two", canonicalUrl: "http://www.two.com", noindex: true, nofollow: true)
                three("Three", noindex: false, nofollow: true)
                four("Three", noindex: true, nofollow: false)
            }
        }

        slingContext.registerService(MetadataConfigService, new MetadataConfigService() {
            @Override
            String getExternalUrl(SlingHttpServletRequest requestContext, Resource resource, String extension) {
                "${resource.path}.html"
            }

            @Override
            String getExternalUrlForPage(SlingHttpServletRequest requestContext, Resource resource) {
                "${resource.path}.html"
            }

            @Override
            String getExternalUrlForImage(SlingHttpServletRequest requestContext, Resource resource) {
                "${resource.path}.png"
            }
        })
    }

    def "get page name"() {
        setup:
        def request = requestBuilder.build {
            path = pagePath
        }

        def metaPage = request.adaptTo(MetaPage)

        expect:
        metaPage.pageName == pageName

        where:
        pagePath                          | pageName
        "/content/harbor/jcr:content"     | "Harbor Page"
        "/content/harbor/one/jcr:content" | "one"
    }

    def "get fully qualified page URL"() {
        setup:
        def request = requestBuilder.build {
            path = "/content/harbor"
        }

        def metaPage = request.adaptTo(MetaPage)

        expect:
        metaPage.fullyQualifiedPageUrl == "/content/harbor.html"
    }

    def "get canonical URL"() {
        setup:
        def request = requestBuilder.build {
            path = pagePath
        }

        def metaPage = request.adaptTo(MetaPage)

        expect:
        metaPage.canonicalUrl == canonicalUrl

        where:
        pagePath              | canonicalUrl
        "/content/harbor"     | ""
        "/content/harbor/one" | "/content/harbor/one.html"
        "/content/harbor/two" | "http://www.two.com"
    }

    def "get robots content"() {
        setup:
        def request = requestBuilder.build {
            path = pagePath
        }

        def metaPage = request.adaptTo(MetaPage)

        expect:
        metaPage.robotsContent == robotsContent

        where:
        pagePath                | robotsContent
        "/content/harbor/one"   | ""
        "/content/harbor/two"   | "NOINDEX, NOFOLLOW"
        "/content/harbor/three" | "INDEX, NOFOLLOW"
        "/content/harbor/four"  | "NOINDEX, FOLLOW"
    }
}
