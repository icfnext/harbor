package com.citytechinc.cq.harbor.proper.core.components.content.page

import com.citytechinc.aem.bedrock.api.page.PageDecorator


class TrailPage implements PageDecorator {

	@Delegate
	private final PageDecorator pageDecorator

	private final Boolean root;
	private final Boolean current;

	protected TrailPage(PageDecorator pageDecorator, Boolean root, Boolean current) {
		this.pageDecorator = pageDecorator
		this.root = root
		this.current = current
	}

	public Boolean isRoot() {
		return root;
	}

	public Boolean isCurrent() {
		return current;
	}

	public static TrailPage forRootPage(PageDecorator pageDecorator) {
		return new TrailPage(pageDecorator, true, false)
	}

	public static TrailPage forCurrentPage(PageDecorator pageDecorator) {
		return new TrailPage(pageDecorator, false, true)
	}

	public static TrailPage forPage(PageDecorator pageDecorator) {
		return new TrailPage(pageDecorator, false, false)
	}

	public static TrailPage forRootCurrentPage(PageDecorator pageDecorator) {
		return new TrailPage(pageDecorator, true, true)
	}
}
