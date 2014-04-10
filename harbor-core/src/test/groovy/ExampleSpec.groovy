import com.citytechinc.aem.prosper.specs.ProsperSpec

class ExampleSpec extends ProsperSpec {

    def setupSpec() {
        // use PageBuilder from base spec to create test content
        pageBuilder.content {
            home("Home") {
                "jcr:content"("sling:resourceType": "foundation/components/page")
            }
        }
    }

    // basic content assertions provided
    def "home page exists"() {
        expect:
        assertPageExists("/content/home")
    }

    // Node metaclass provided by AEM Groovy Extension simplifies JCR operations
    def "home page has expected resource type"() {
        setup:
        def contentNode = session.getNode("/content/home/jcr:content")

        expect:
        contentNode.get("sling:resourceType") == "foundation/components/page"
    }
}