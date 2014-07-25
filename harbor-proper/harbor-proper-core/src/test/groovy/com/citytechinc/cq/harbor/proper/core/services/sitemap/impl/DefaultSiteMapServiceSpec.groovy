package com.citytechinc.cq.harbor.proper.core.services.sitemap.impl

import com.citytechinc.cq.library.content.page.PageDecorator
import com.day.cq.commons.Externalizer
import org.apache.sling.api.resource.ResourceResolver
import org.apache.sling.api.resource.ValueMap
import spock.lang.Specification

class DefaultSiteMapServiceSpec extends Specification {
    DefaultSiteMapService defaultSiteMapService = Spy()

    ValueMap contentResourceValueMap = Mock()
    ResourceResolver resourceResolver = Mock()
    PageDecorator pageDecorator = Mock()


    def setup() {
        defaultSiteMapService.externalizer = Mock(Externalizer)
    }

    def "determinePriority should return the resource jcrAccelerateSitemapPriority when it resolves to a non-empty numeric value between 0 and 1"() {
        setup:
        contentResourceValueMap.get(DefaultSiteMapService.jcrAccelerateSitemapPriority, null) >> "0.3"

        when:
        def result = defaultSiteMapService.determinePriority(contentResourceValueMap)

        then:
        result == "0.3"
    }

    def "determinePriority should return null if resource jcrAccelerateSitemapPriority resolves to empty"() {
        setup:
        contentResourceValueMap.get(DefaultSiteMapService.jcrAccelerateSitemapPriority, null) >> null

        when:
        def result = defaultSiteMapService.determinePriority(contentResourceValueMap)

        then:
        result == null
    }

    def "determinePriority should return null when resolved jcrAccelerateSitemapPriority value is numeric but less than 0"() {
        setup:
        contentResourceValueMap.get(DefaultSiteMapService.jcrAccelerateSitemapPriority, null) >> "-0.3"

        when:
        def result = defaultSiteMapService.determinePriority(contentResourceValueMap)

        then:
        result == null
    }

    def "determinePriority should return null when resolved jcrAccelerateSitemapPriority value is numeric but more than 1"() {
        setup:
        contentResourceValueMap.get(DefaultSiteMapService.jcrAccelerateSitemapPriority, null) >> "1.1"

        when:
        def result = defaultSiteMapService.determinePriority(contentResourceValueMap)

        then:
        result == null
    }

    def "parseDouble should parse doubles"() {
        when:
        def theNumber = defaultSiteMapService.parseDouble("1.2")

        then:
        theNumber == 1.2
    }

    def "parseDouble should swallow exceptions and return null when the numeric string is not numeric"() {
        when:
        def theNumber = defaultSiteMapService.parseDouble("x")

        then:
        theNumber == null
        notThrown(NumberFormatException)
    }

    def "parseDouble should swallow exceptions and return null when the numeric string is null"() {
        when:
        def theNumber = defaultSiteMapService.parseDouble(null)

        then:
        theNumber == null
        notThrown(NullPointerException)
    }

    def jcrPagePath = "/content/page"
    def externalPublishLink = "http://bin${jcrPagePath}"
    def jcrPageExtension = ".htm"

    def "determineLoc should lookup the external publish link and jcrPageExtension then combine it with the pageDecorator path for the loc value"() {
        setup:
        StringBuffer locBuffer = new StringBuffer(externalPublishLink)

        pageDecorator.getPath() >> jcrPagePath
        defaultSiteMapService.externalizer.publishLink(resourceResolver, jcrPagePath) >> externalPublishLink
        contentResourceValueMap.get(DefaultSiteMapService.jcrAccelerateSitemapExtension, DefaultSiteMapService.defaultLocSuffix) >> jcrPageExtension
        defaultSiteMapService.newStringBuffer(externalPublishLink) >> locBuffer

        when:
        def result = defaultSiteMapService.determineLoc(resourceResolver, pageDecorator, contentResourceValueMap)

        then:
        result == "${externalPublishLink}${jcrPageExtension}"
    }

    def "determineLoc should lookup the external publish link and user default extension when no jcrPageExtension resolves then combine it with the pageDecorator path for the loc value"() {
        setup:
        StringBuffer locBuffer = new StringBuffer(externalPublishLink)

        pageDecorator.getPath() >> jcrPagePath
        defaultSiteMapService.externalizer.publishLink(resourceResolver, jcrPagePath) >> externalPublishLink
        contentResourceValueMap.get(DefaultSiteMapService.jcrAccelerateSitemapExtension, DefaultSiteMapService.defaultLocSuffix) >> DefaultSiteMapService.defaultLocSuffix
        defaultSiteMapService.newStringBuffer(externalPublishLink) >> locBuffer

        when:
        def result = defaultSiteMapService.determineLoc(resourceResolver, pageDecorator, contentResourceValueMap)

        then:
        result == "${externalPublishLink}${DefaultSiteMapService.defaultLocSuffix}"
    }

}
