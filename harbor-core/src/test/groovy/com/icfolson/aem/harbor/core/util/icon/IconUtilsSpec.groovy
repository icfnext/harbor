package com.icfolson.aem.harbor.core.util.icon

import com.icfolson.aem.prosper.specs.ProsperSpec
import spock.lang.Unroll

@Unroll
class IconUtilsSpec extends ProsperSpec {

    def "iconify"() {
        expect:
        IconUtils.iconify(raw) == result

        where:
        raw                                            | result
        ""                                             | ""
        "Harbor"                                       | "Harbor"
        "Search {{icon fa-search}}"                    | "Search <i class=\"fa fa-search\" aria-hidden=\"true\"></i>"
        "Search {{icon fa-search}} {{icon fa-search}}" | "Search <i class=\"fa fa-search\" aria-hidden=\"true\"></i> <i class=\"fa fa-search\" aria-hidden=\"true\"></i>"
    }
}
