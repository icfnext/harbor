package com.citytechinc.cq.harbor.util

import com.citytechinc.cq.library.content.page.PageDecorator
import groovy.xml.MarkupBuilder

class SitemapXmlGenerator {

	public static String getSitemapXMLFromRootPage(PageDecorator rootPage) {
		StringWriter stringWriter = new StringWriter()
		MarkupBuilder markupBuilder = new MarkupBuilder(stringWriter);
		def recursiveXMLBuilderFunction = { PageDecorator page ->
			if (!page.getChildren(true).isEmpty()) {
				for (PageDecorator childPage : page.getChildren()) {
					def path = childPage.getHref();
					//def lastModified = childPage.getLastModified().toString();
					call(childPage);
					markupBuilder.url() {
						loc(path)
					}
				}
			} else {
				def path = page.getHref();
				markupBuilder.url() {
					loc(path)
				}
				return
			}
		}
		markupBuilder.urlset(xmlns:"http://www.sitemaps.org/schemas/sitemap/0.9") {
			recursiveXMLBuilderFunction(rootPage)
		}
		return stringWriter.toString();
	}
}