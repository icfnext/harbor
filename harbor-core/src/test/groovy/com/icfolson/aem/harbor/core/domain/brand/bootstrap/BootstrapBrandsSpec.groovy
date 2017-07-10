package com.icfolson.aem.harbor.core.domain.brand.bootstrap

import com.google.common.collect.Maps
import com.icfolson.aem.harbor.HarborSpec

class BootstrapBrandsSpec extends HarborSpec {

    def setupSpec() {
        pageBuilder.content {
            harbor {
                "jcr:content" {
                    brandproperties("bootstrap-title": "Harbor",
                        "bootstrap-logo": "logo.png",
                        "lastModified": Calendar.instance)
                }
            }
            one {
                "jcr:content" {
                    brandproperties("lastModified": Calendar.instance)
                }
            }
            two()
        }
    }

    def "has brand properties"() {
        setup:
        def resource = getResource("/content/harbor/jcr:content")

        def bootstrapBrand = BootstrapBrands.forBrandResource(resource)

        expect :
        bootstrapBrand.present

        and:
        Maps.difference(bootstrapBrand.get().variables, ["title": "Harbor", "logo": "logo.png"]).areEqual()
    }

    def "no brand properties"() {
        setup:
        def resource = getResource(path)

        def bootstrapBrand = BootstrapBrands.forBrandResource(resource)

        expect :
        !bootstrapBrand.present

        where:
        path << ["/content/one/jcr:content", "/content/two/jcr:content"]
    }
}