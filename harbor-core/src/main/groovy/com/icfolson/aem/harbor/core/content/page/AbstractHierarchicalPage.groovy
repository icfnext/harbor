package com.icfolson.aem.harbor.core.content.page

import com.icfolson.aem.library.api.page.PageDecorator
import com.icfolson.aem.harbor.api.content.page.HierarchicalPage

public abstract class AbstractHierarchicalPage implements HierarchicalPage,PageDecorator {

	@Delegate
	private final PageDecorator page;

	public AbstractHierarchicalPage(PageDecorator page) {
		this.page = page;
	}
}
