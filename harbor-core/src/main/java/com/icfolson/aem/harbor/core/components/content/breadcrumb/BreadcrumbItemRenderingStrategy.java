package com.icfolson.aem.harbor.core.components.content.breadcrumb;

import java.util.List;

import com.citytechinc.cq.component.annotations.widgets.Switch;
import org.apache.commons.lang3.StringUtils;

import com.icfolson.aem.library.api.node.ComponentNode;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.widgets.DialogFieldSet;
import com.citytechinc.cq.component.annotations.widgets.Selection;
import com.icfolson.aem.harbor.api.constants.components.ComponentConstants;
import com.icfolson.aem.harbor.api.lists.rendering.ListRenderingStrategy;
import com.icfolson.aem.harbor.core.components.content.page.TrailPage;
import com.google.common.collect.Lists;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;

@Model(adaptables = Resource.class)
public class BreadcrumbItemRenderingStrategy implements ListRenderingStrategy<TrailPage, BreadcrumbTrail> {

	private static final String DEFAULT_DELIMITER = "fa-bootstrap-slash";

	public static final String ROOT_ITEM_CONFIGURATION_PREFIX = "rootItem";
	public static final String INTERMEDIATE_ITEM_CONFIGURATION_PREFIX = "intermediateItem";
	public static final String CURRENT_ITEM_CONFIGURATION_PREFIX = "currentItem";

	@Inject
	private ComponentNode componentNode;

	private Boolean renderAsLink;

	private String iconDelimiter;
	private String htmlDelimiter;

	private BreadcrumbItemConfiguration rootItemConfiguration;
	private BreadcrumbItemConfiguration intermediateItemConfiguration;
	private BreadcrumbItemConfiguration currentItemConfiguration;

	private BreadcrumbTrail breadcrumbTrail;

	@Override
	public BreadcrumbTrail toRenderableList(Iterable<TrailPage> itemIterable) {
		if (breadcrumbTrail == null) {
			List<BreadcrumbItem> renderableList = Lists.newArrayList();

			for (TrailPage currentBreadcrumbPage : itemIterable) {
				if (currentBreadcrumbPage.isRoot()) {
					renderableList.add(new BreadcrumbItem(currentBreadcrumbPage, getRootItemConfiguration()));
				} else if (currentBreadcrumbPage.isCurrent()) {
					renderableList.add(new BreadcrumbItem(currentBreadcrumbPage, getCurrentItemConfiguration()));
				} else {
					renderableList.add(new BreadcrumbItem(currentBreadcrumbPage, getIntermediateItemConfiguration()));
				}
			}

			breadcrumbTrail = new BreadcrumbTrail(getRenderAsLink(), getIconDelimiter(), getHtmlDelimiter(),
				getRootItemConfiguration(), getIntermediateItemConfiguration(), getCurrentItemConfiguration(),
				renderableList);
		}

		return breadcrumbTrail;

	}

	@DialogField(fieldLabel = "Render As Link", ranking = 1)
	@Switch(offText = "No", onText = "Yes")
	public Boolean getRenderAsLink() {
		if (renderAsLink == null) {
			renderAsLink = isInherits() ? componentNode.getInherited("renderAsLink", false) : componentNode.get("renderAsLink", false);
		}

		return renderAsLink;
	}

	/**
	 * A dialog field which allows the user to choose what delimiter icon they
	 * wish to be displayed.
	 *
	 * @return a string representing the font awesome icon class the user has
	 *         selected.
	 */
	@DialogField(fieldLabel = "Delimiter Icon", ranking = 10)
	@Selection(type = Selection.SELECT, optionsUrl = ComponentConstants.FONT_AWESOME_SERVLET_PATH)
	public String getIconDelimiter() {
		if (iconDelimiter == null) {
			iconDelimiter = isInherits() ? componentNode.getInherited("iconDelimiter", DEFAULT_DELIMITER) : componentNode.get("iconDelimiter", DEFAULT_DELIMITER);
		}

		return iconDelimiter;
	}

	/**
	 * A dialog field which allows the user to enter any HTML string they wish.
	 *
	 * @return The {@link BreadcrumbComponent} HTML delimiter.
	 */
	@DialogField(fieldLabel = "Delimiter HTML", ranking = 20, fieldDescription = "Allows for the use of arbitrary HTML as a Breadcrumb Trail Item Delimiter. The delimiter authored in this field will trump the delimiter authored in the icon field.")
	public String getHtmlDelimiter() {
		if (htmlDelimiter == null) {
			htmlDelimiter = isInherits() ? componentNode.getInherited("htmlDelimiter", StringUtils.EMPTY) : componentNode.get("htmlDelimiter", StringUtils.EMPTY);
		}

		return htmlDelimiter;
	}

	@DialogField(ranking = 30)
	@DialogFieldSet(title = "Root Item Configuration", namePrefix = ROOT_ITEM_CONFIGURATION_PREFIX)
	public BreadcrumbItemConfiguration getRootItemConfiguration() {
		if (rootItemConfiguration == null) {
			rootItemConfiguration = getItemConfiguration(ROOT_ITEM_CONFIGURATION_PREFIX);
		}

		return rootItemConfiguration;
	}

	@DialogField(ranking = 40)
	@DialogFieldSet(title = "Intermediate Item Configuration", namePrefix = INTERMEDIATE_ITEM_CONFIGURATION_PREFIX)
	public BreadcrumbItemConfiguration getIntermediateItemConfiguration() {
		if (intermediateItemConfiguration == null) {
			intermediateItemConfiguration = getItemConfiguration(INTERMEDIATE_ITEM_CONFIGURATION_PREFIX);
		}

		return intermediateItemConfiguration;
	}

	@DialogField(ranking = 50)
	@DialogFieldSet(title = "Current Item Configuration", namePrefix = CURRENT_ITEM_CONFIGURATION_PREFIX)
	public BreadcrumbItemConfiguration getCurrentItemConfiguration() {
		if (currentItemConfiguration == null) {
			currentItemConfiguration = getItemConfiguration(CURRENT_ITEM_CONFIGURATION_PREFIX);
		}

		return currentItemConfiguration;
	}

	protected BreadcrumbItemConfiguration getItemConfiguration(String prefix) {
		return new BreadcrumbItemConfiguration(componentNode, prefix, isInherits());
	}

	protected boolean isInherits() {
		return false;
	}

}
