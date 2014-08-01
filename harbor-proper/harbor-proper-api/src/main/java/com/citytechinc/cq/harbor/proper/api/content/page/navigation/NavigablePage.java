package com.citytechinc.cq.harbor.proper.api.content.page.navigation;

import java.util.List;

import com.citytechinc.aem.bedrock.api.page.PageDecorator;
import com.citytechinc.cq.harbor.proper.api.lists.RootedItems;

/**
 * Represents a page in a Navigation Structure
 */
public interface NavigablePage extends PageDecorator, RootedItems<NavigablePage> {

	/**
	 * Provides the list of the pages which can be navigated to directly from
	 * this page
	 *
	 * @return The list of pages which can be navigated to directly from this
	 *         page
	 */
	public List<NavigablePage> getNavigableChildren();

}
