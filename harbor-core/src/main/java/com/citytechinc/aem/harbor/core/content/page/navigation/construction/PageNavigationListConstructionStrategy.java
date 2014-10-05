package com.citytechinc.aem.harbor.core.content.page.navigation.construction;

import com.citytechinc.aem.bedrock.api.page.PageDecorator;
import com.citytechinc.aem.bedrock.api.request.ComponentRequest;
import com.citytechinc.aem.bedrock.core.components.AbstractComponent;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.FieldProperty;
import com.citytechinc.cq.component.annotations.widgets.NumberField;
import com.citytechinc.cq.component.annotations.widgets.PathField;
import com.citytechinc.aem.harbor.api.content.page.navigation.NavigablePage;
import com.citytechinc.aem.harbor.api.trees.construction.TreeConstructionStrategy;
import com.citytechinc.aem.harbor.core.content.page.navigation.NavigablePages;
import com.google.common.base.Optional;

public class PageNavigationListConstructionStrategy extends AbstractComponent implements
        TreeConstructionStrategy<NavigablePage> {

	private Optional<NavigablePage> navigationRoot;
	private Integer navigationDepth;
	private Boolean ignoreHideInNavigation;

	@Override
	public void init(ComponentRequest request) {
		this.navigationDepth = getInherited("navigationDepth", 1);
		this.ignoreHideInNavigation = getInherited("ignoreHideInNavigation", false);
	}

	@DialogField(fieldLabel = "Navigation Root", fieldDescription = "The page at which the Navigation will start")
	@PathField
	public Optional<NavigablePage> getNavigationRoot() {
		if (navigationRoot == null) {
			PageDecorator rootPage = getRootPage();

			if (rootPage != null) {
				navigationRoot = Optional.of(NavigablePages.forPageAndDepthAndChildPolicyAndCurrentPage(getRootPage(),
					getNavigationDepth(), !getIgnoreHideInNavigation(), getCurrentPage()));
			} else {
				navigationRoot = Optional.absent();
			}
		}

		return navigationRoot;
	}

	protected PageDecorator getRootPage() {
		Optional<String> rootPagePath = get("navigationRoot", String.class);
		if (rootPagePath.isPresent()) {
			return getComponentRequest().getPageManager().getPage(rootPagePath.get());
		}

		return null;
	}

	@DialogField(fieldLabel = "Navigation Depth", fieldDescription = "The depth to which the navigation will search for child pages", additionalProperties = { @FieldProperty(name = "emptyText", value = "1") })
	@NumberField
	public Integer getNavigationDepth() {
		return navigationDepth;
	}

	public Boolean getIgnoreHideInNavigation() {
		return ignoreHideInNavigation;
	}

	@Override
	public Optional<NavigablePage> construct() {

		return getNavigationRoot();

	}
}
