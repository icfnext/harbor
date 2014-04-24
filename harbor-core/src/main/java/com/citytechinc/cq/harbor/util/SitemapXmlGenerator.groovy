package com.citytechinc.cq.harbor.util

import com.citytechinc.cq.library.content.page.PageDecorator
import groovy.xml.MarkupBuilder

class SitemapXmlGenerator {

	public static String getSitemapXMLFromRootPage(PageDecorator rootPage) {

		//Init MarkerBuilder
		StringWriter stringWriter = new StringWriter()
		MarkupBuilder markupBuilder = new MarkupBuilder(stringWriter);

		//Here, we define the function that recursively iterates over the JCR, and adds them to the xml file
		def recursiveXMLBuilderFunction = { PageDecorator page ->

			if (!page.getChildren(true).isEmpty()) {

				for (PageDecorator childPage : page.getChildren()) {
					def path = childPage.getHref();

					String lastModified = childPage.getLastModified().format("yyyy-MM-ddHH:mm").toString();
					call(childPage);
					markupBuilder.url() {
						loc(path)
						lastmod(lastModified)
					}
				}
			} else {
				def path = page.getHref();
				String lastModified = page.getLastModified().format("yyyy-MM-ddHH:mm").toString();
				markupBuilder.url() {
					loc(path)
					lastmod(lastModified)
				}
				return
			}
		}

		markupBuilder.urlset(xmlns: "http://www.sitemaps.org/schemas/sitemap/0.9") {
			recursiveXMLBuilderFunction(rootPage)
		}
		return stringWriter.toString();
	}
}