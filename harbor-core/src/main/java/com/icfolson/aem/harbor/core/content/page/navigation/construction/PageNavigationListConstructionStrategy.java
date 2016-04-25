package com.icfolson.aem.harbor.core.content.page.navigation.construction;

import com.icfolson.aem.harbor.core.content.page.navigation.NavigablePages;
import com.icfolson.aem.library.api.page.PageDecorator;
import com.icfolson.aem.library.core.components.AbstractComponent;
import com.icfolson.aem.library.models.annotations.InheritInject;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.Property;
import com.citytechinc.cq.component.annotations.widgets.NumberField;
import com.citytechinc.cq.component.annotations.widgets.PathField;
import com.icfolson.aem.harbor.api.content.page.navigation.NavigablePage;
import com.icfolson.aem.harbor.api.trees.construction.TreeConstructionStrategy;
import com.google.common.base.Optional;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;

@Model(adaptables = Resource.class)
public class PageNavigationListConstructionStrategy extends AbstractComponent implements
        TreeConstructionStrategy<NavigablePage> {

	private Optional<NavigablePage> navigationRoot;

	@Inject @InheritInject @Default(intValues = 1)
	private Integer navigationDepth;

	@Inject @InheritInject @Default(booleanValues = false)
	private Boolean ignoreHideInNavigation;

	@Inject
	private PageDecorator currentPage;

	@DialogField(fieldLabel = "Navigation Root", fieldDescription = "The page at which the Navigation will start")
	@PathField
	public Optional<NavigablePage> getNavigationRoot() {
		if (navigationRoot == null) {
			PageDecorator rootPage = getRootPage();

			if (rootPage != null) {
				navigationRoot = Optional.of(NavigablePages.forPageAndDepthAndChildPolicyAndCurrentPage(getRootPage(),
						getNavigationDepth(), !getIgnoreHideInNavigation(), currentPage));
			} else {
				navigationRoot = Optional.absent();
			}
		}

		return navigationRoot;
	}

	protected PageDecorator getRootPage() {
		Optional<String> rootPagePath = get("navigationRoot", String.class);
		if (rootPagePath.isPresent()) {
			return currentPage.getPageManager().getPage(rootPagePath.get());
		}

		return null;
	}

	@DialogField(fieldLabel = "Navigation Depth", fieldDescription = "The depth to which the navigation will search for child pages", additionalProperties = { @Property(name = "emptyText", value = "1") })
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
