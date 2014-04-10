import com.citytechinc.aem.prosper.specs.ProsperSpec


class MainNavigationConstructionSpec extends ProsperSpec {
    def setupSpec(){
       pageBuilder.content {
           home("home") {
               "jcr:content"("rdf:type": "harbor:HomePage") {
                   autoNavigation(depthLevel: 1, rootPagePath: "/content/home")
               }

               sectionLandingPage1("section1") {
                   "jcr:content" ("rdf:type": "harbor:SectionLandingPage")

                   contentPage1("content1") {
                       "jcr:content" ("rdf:type": "harbor:ContentPage")
                   }

               }
               sectionLandingPage2("section2") {
                   "jcr:content" ("rdf:type": "harbor:SectionLandingPage")

                   contentPage2("content2") {
                       "jcr:content" ("rdf:type": "harbor:ContentPage")
                   }

               }
               hiddenSectionLandingPage("hiddenSection") {
                   "jcr:content" (
                           "rdf:type": "harbor:SectionLandingPage",
                           "hideInNav": "true"
                   )

                   occludedContentPage("occludedContent") {
                       "jcr:content" ("rdf:type": "harbor:ContentPage")
                   }
               }
           }
       }
    }

    def "Pages hidden from navigation do not get included"() {




    }


    def cleanup(){
        removeAllNodes();
    }
}
