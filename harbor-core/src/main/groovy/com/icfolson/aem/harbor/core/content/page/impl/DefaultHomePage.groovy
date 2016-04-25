package com.icfolson.aem.harbor.core.content.page.impl;

import com.icfolson.aem.library.api.page.PageDecorator
import com.icfolson.aem.harbor.api.content.page.HomePage

public class DefaultHomePage extends DefaultSectionLandingPage implements HomePage {

	public DefaultHomePage(PageDecorator page) {
		super(page);
	}

	@Override
	public boolean isSubSectionLandingPage() {
		return false;
	}
}
