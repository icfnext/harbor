package com.citytechinc.cq.harbor.proper.core.components.content.list.assets;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.citytechinc.aem.bedrock.api.node.ComponentNode;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.Option;
import com.citytechinc.cq.component.annotations.widgets.Selection;
import com.citytechinc.cq.harbor.proper.api.lists.rendering.ListRenderingStrategy;
import com.day.cq.dam.api.Asset;
import com.google.common.collect.Lists;

/**
 * Renders a list of assets as links.
 */
public class AssetListRenderingStrategy implements
	ListRenderingStrategy<Asset, List<AssetListRenderingStrategy.RenderableAsset>> {

	private static final String PARAM_AS_LINKS = "renderAsLinks";
	private static final boolean DEFAULT_AS_LINKS = false;
	@DialogField(fieldLabel = "Render as Links", fieldDescription = "Check box to render items as links, otherwise items will be rendered as static text.")
	@Selection(type = Selection.CHECKBOX, options = @Option(value = "true"))
	private final boolean renderAsLinks;

	private List<RenderableAsset> renderableAssets;

	public AssetListRenderingStrategy(ComponentNode componentNode) {

		renderAsLinks = componentNode.get(PARAM_AS_LINKS, DEFAULT_AS_LINKS);

	}

	/**
	 * Render an asset based on provided options.
	 *
	 * @param item
	 * @return
	 */
	public String renderListItem(Asset item) {

		String rendered = StringUtils.EMPTY;

		if (renderAsLinks) {

			rendered = "<a href=\"" + item.getPath() + "\">" + item.getName() + "</a><br>";

		} else {

			rendered = item.getName();

		}

		return rendered;

	}

	/**
	 * True to render items as links to their resources, otherwise render items
	 * as static text.
	 *
	 */
	public boolean getRenderAsLinks() {

		return this.renderAsLinks;

	}

	@Override
	public List<RenderableAsset> toRenderableList(Iterable<Asset> itemIterable) {

		if (renderableAssets == null) {
			renderableAssets = Lists.newArrayList();

			for (Asset currentAsset : itemIterable) {
				renderableAssets.add(new RenderableAsset(currentAsset, getRenderAsLinks()));
			}
		}

		return renderableAssets;

	}

	public static class RenderableAsset {

		private final Asset asset;
		private final boolean renderAsLink;

		public RenderableAsset(Asset asset, boolean renderAsLink) {
			this.asset = asset;
			this.renderAsLink = renderAsLink;
		}

		public Asset getAsset() {
			return asset;
		}

		public boolean isRenderAsLink() {
			return renderAsLink;
		}

	}

}
