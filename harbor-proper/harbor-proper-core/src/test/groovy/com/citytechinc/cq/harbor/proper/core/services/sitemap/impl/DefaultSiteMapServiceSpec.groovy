package com.citytechinc.cq.harbor.proper.core.services.sitemap.impl

import static com.citytechinc.cq.accelerate.api.ontology.Properties.ACCELERATE_SITEMAP_PRIORITY
import static com.citytechinc.cq.accelerate.api.ontology.Properties.ACCELERATE_SITEMAP_RESOURCE_EXTENSION
import static org.apache.commons.lang.StringUtils.EMPTY

import org.apache.sling.api.resource.ResourceResolver
import org.apache.sling.api.resource.ValueMap

import spock.lang.Specification

import com.citytechinc.aem.bedrock.api.page.PageDecorator
import com.day.cq.commons.Externalizer

class DefaultSiteMapServiceSpec extends Specification {
	DefaultSiteMapService defaultSiteMapService = Spy()

	ValueMap contentResourceValueMap = Mock()
	ResourceResolver resourceResolver = Mock()
	PageDecorator pageDecorator = Mock()

	def jcrPagePath = "/content/page"
	def externalPublishLink = "http://bin${jcrPagePath}"
	def jcrPageExtension = ".htm"


	def setup() {
		defaultSiteMapService.externalizer = Mock(Externalizer)
	}

	def "determinePriority should return the resource accelerate:priority when it resolves to a valid non-empty numeric value between 0 and 1"() {
		setup:
		contentResourceValueMap.get(ACCELERATE_SITEMAP_PRIORITY, null) >> "0.3"

		when:
		def result = defaultSiteMapService.determinePriority(contentResourceValueMap)

		then:
		result == "0.3"
	}

	def "determinePriority should return null if resource accelerate:priority resolves to empty"() {
		setup:
		contentResourceValueMap.get(ACCELERATE_SITEMAP_PRIORITY, null) >> null

		when:
		def result = defaultSiteMapService.determinePriority(contentResourceValueMap)

		then:
		result == null
	}

	def "determinePriority should return null when resolved accelerate:priority value is numeric but less than 0"() {
		setup:
		contentResourceValueMap.get(ACCELERATE_SITEMAP_PRIORITY, null) >> "-0.3"

		when:
		def result = defaultSiteMapService.determinePriority(contentResourceValueMap)

		then:
		result == null
	}

	def "determinePriority should return null when resolved accelerate:priority value is numeric but more than 1"() {
		setup:
		contentResourceValueMap.get(ACCELERATE_SITEMAP_PRIORITY, null) >> "1.1"

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

	def "determineLoc should lookup the external publish link and accelerate:locExtension then combine it with the pageDecorator path for the loc value"() {
		setup:
		StringBuffer locBuffer = new StringBuffer(externalPublishLink)

		pageDecorator.getPath() >> jcrPagePath
		defaultSiteMapService.externalizer.publishLink(resourceResolver, jcrPagePath) >> externalPublishLink
		contentResourceValueMap.get(ACCELERATE_SITEMAP_RESOURCE_EXTENSION, EMPTY) >> jcrPageExtension
		defaultSiteMapService.newStringBuffer(externalPublishLink) >> locBuffer

		when:
		def result = defaultSiteMapService.determineLoc(resourceResolver, pageDecorator, contentResourceValueMap)

		then:
		result == "${externalPublishLink}${jcrPageExtension}"
	}

	def "determineLoc should lookup the external publish link and use an empty string as the extension when no jcrPageExtension resolves then combine it with the pageDecorator path for the loc value"() {
		setup:
		StringBuffer locBuffer = new StringBuffer(externalPublishLink)

		pageDecorator.getPath() >> jcrPagePath
		defaultSiteMapService.externalizer.publishLink(resourceResolver, jcrPagePath) >> externalPublishLink
		contentResourceValueMap.get(ACCELERATE_SITEMAP_RESOURCE_EXTENSION, EMPTY) >> EMPTY
		defaultSiteMapService.newStringBuffer(externalPublishLink) >> locBuffer

		when:
		def result = defaultSiteMapService.determineLoc(resourceResolver, pageDecorator, contentResourceValueMap)

		then:
		result == "${externalPublishLink}${EMPTY}"
	}
}
