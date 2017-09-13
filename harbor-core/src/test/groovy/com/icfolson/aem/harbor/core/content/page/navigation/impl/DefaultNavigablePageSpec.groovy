package com.icfolson.aem.harbor.core.content.page.navigation.impl

import com.day.cq.wcm.api.NameConstants
import com.icfolson.aem.harbor.HarborSpec
import com.icfolson.aem.harbor.core.components.content.navigation.page.v1.DefaultNavigablePage
import com.icfolson.aem.harbor.core.content.page.navigation.navigablepage.v1.NavigationElementConfiguration
import spock.lang.Unroll

@Unroll
class DefaultNavigablePageSpec extends HarborSpec {

    def setupSpec() {
        pageBuilder.content {
            harbor {
                one {
                    sub1()
                    sub2()
                }
                two()
                three((NameConstants.PN_HIDE_IN_NAV): true)
            }
        }
    }

    def "get navigable children"() {
        setup:
        def page = getPage("/content/harbor")
        def configuration = new NavigationElementConfiguration(respectHideInNav, depth)

        def navigablePage = new DefaultNavigablePage(page, configuration)

        expect:
        navigablePage.navigableChildren.size() == size

        where:
        respectHideInNav | depth | size
        false            | 0     | 0
        false            | 1     | 3
        true             | 1     | 2
    }

    def "get navigable children with depth"() {
        setup:
        def page = getPage("/content/harbor")
        def configuration = new NavigationElementConfiguration(true, depth)

        def navigablePage = new DefaultNavigablePage(page, configuration)

        expect:
        navigablePage.navigableChildren.size() == 2

        and:
        navigablePage.navigableChildren[0].navigableChildren.size() == size

        where:
        depth | size
        1     | 0
        2     | 2
    }

    def "get title"() {

    }

    def "test if redirect"() {

    }

    def "test whether along active path"() {

    }

    def "get href"() {

    }
}