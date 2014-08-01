package com.citytechinc.cq.harbor.proper.core.content.page.impl;

import com.citytechinc.aem.bedrock.api.page.PageDecorator
import com.citytechinc.cq.harbor.proper.api.content.page.HomePage

public class DefaultHomePage extends DefaultSectionLandingPage implements HomePage {

	public DefaultHomePage(PageDecorator page) {
		super(page);
	}

	@Override
	public boolean isSubSectionLandingPage() {
		return false;
	}
}
