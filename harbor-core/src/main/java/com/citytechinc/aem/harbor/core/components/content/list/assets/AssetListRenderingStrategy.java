package com.citytechinc.aem.harbor.core.components.content.list.assets;

import com.citytechinc.aem.bedrock.api.node.ComponentNode;
import com.citytechinc.aem.harbor.api.constants.dom.Headings;
import com.citytechinc.aem.harbor.api.lists.rendering.ListRenderingStrategy;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.Option;
import com.citytechinc.cq.component.annotations.widgets.Selection;
import com.day.cq.dam.api.Asset;
import com.google.common.collect.Lists;
import org.apache.commons.lang.StringUtils;

import java.util.List;

/**
 * Renders a list of assets as links.
 */
public class AssetListRenderingStrategy implements
	ListRenderingStrategy<Asset, List<AssetListRenderingStrategy.RenderableAsset>> {

    //TODO: Move into a Constants object elsewhere
    public static final String TITLE_PROPERTY = "dc:title";
    public static final String DESCRIPTION_PROPERTY = "dc:description";
    public static final String FORMAT_PROPERTY = "dc:format";
    public static final String CREATOR_PROPERTY = "dc:creator";

    private static final String RENDITION_THUMNAIL_PATH = "jcr:content/renditions/cq5dam.thumbnail";
    private static final String RENDITION_THUMNAIL_EXTENSION = "png";
    private static final String ORIGINAL = "Original";

	private static final String PARAM_AS_LINKS = "renderAsLinks";
	private static final boolean DEFAULT_AS_LINKS = false;

    @DialogField(fieldLabel = "Suppress Images?")
    @Selection(type = Selection.CHECKBOX, options = { @Option(text = "", value = "true") })
    private final boolean suppressImages;

    @DialogField(fieldLabel = "Image Size", fieldDescription = "Render image as original size or thumbnail rendition", defaultValue = ORIGINAL)
    @Selection(type = Selection.SELECT, options = {
            @Option(text = ORIGINAL, value = ORIGINAL),
            @Option(text = "48 x 48", value = "48.48"),
            @Option(text = "140 x 100", value = "140.100"),
            @Option(text = "319 x 319", value = "319.319")})
    private final String imageSize;

    @DialogField(fieldLabel = "Render Asset Titles?")
    @Selection(type = Selection.CHECKBOX, options = { @Option(text = "", value = "true") })
    private final boolean renderTitles;

    @DialogField(fieldLabel = "Render Asset Creators?")
    @Selection(type = Selection.CHECKBOX, options = { @Option(text = "", value = "true") })
    private final boolean renderCreators;

    @DialogField(fieldLabel = "Creator Label", fieldDescription = "The text label to render prior to the creator name when the creator name is rendered", defaultValue = "By:")
    private final String creatorLabel;

    @DialogField(fieldLabel = "Render Asset Descriptions?")
    @Selection(type = Selection.CHECKBOX, options = { @Option(text = "", value = "true") })
    private final boolean renderDescriptions;

    @DialogField(fieldLabel = "Render Asset Formats?")
    @Selection(type = Selection.CHECKBOX, options = { @Option(text = "", value = "true") })
    private final boolean renderFormats;

    @DialogField(fieldLabel = "Format Label", fieldDescription = "The text label to render prior to the format denotation when the format is rendered", defaultValue = "Format:")
    private final String formatLabel;

	@DialogField(fieldLabel = "Render as Links")
	@Selection(type = Selection.CHECKBOX, options = @Option(value = "true"))
	private final boolean renderAsLinks;

    @DialogField(fieldLabel = "Title Heading Type", fieldDescription = "When the Asset Title is rendered in the context of a list, it will be rendered as a heading element.  What level of heading should be used is dependent on how your pages content is structured and specifically whether other headers are present on the page.", defaultValue = Headings.H3)
    @Selection(type = Selection.SELECT, options = {
            @Option(text = Headings.H3_LABEL, value = Headings.H3),
            @Option(text = Headings.H4_LABEL, value = Headings.H4),
            @Option(text = Headings.H5_LABEL, value = Headings.H5),
            @Option(text = Headings.H6_LABEL, value = Headings.H6)})
    private final String titleHeadingType;

	private List<RenderableAsset> renderableAssets;

	public AssetListRenderingStrategy(ComponentNode componentNode) {

		renderAsLinks = componentNode.get(PARAM_AS_LINKS, DEFAULT_AS_LINKS);
        suppressImages = componentNode.get("suppressImages", false);
        imageSize = componentNode.get("imageSize", ORIGINAL);
        renderTitles = componentNode.get("renderTitles", false);
        renderCreators = componentNode.get("renderCreators", false);
        creatorLabel = componentNode.get("creatorLabel", "By:");
        renderDescriptions = componentNode.get("renderDescriptions", false);
        renderFormats = componentNode.get("renderFormats", false);
        formatLabel = componentNode.get("formatLabel", "Format:");
        titleHeadingType = componentNode.get("titleHeadingType", Headings.H3);

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
				renderableAssets.add(
                        new RenderableAsset(
                                currentAsset,
                                suppressImages,
                                imageSize,
                                renderTitles,
                                renderCreators,
                                creatorLabel,
                                renderDescriptions,
                                renderFormats,
                                formatLabel,
                                getRenderAsLinks(),
                                titleHeadingType));
			}
		}

		return renderableAssets;

	}

	public static class RenderableAsset {

		private final Asset asset;

        private final boolean renderImage;
        private final String imageSize;
        private final boolean renderTitle;
        private final boolean renderCreator;
        private final String createdByLabel;
        private final boolean renderDescription;
        private final boolean renderFormat;
        private final String formatLabel;
		private final boolean renderAsLink;
        private final String titleHeadingType;

		public RenderableAsset(
                Asset asset,
                boolean suppressImage,
                String imageSize,
                boolean renderTitle,
                boolean renderCreator,
                String createdByLabel,
                boolean renderDescription,
                boolean renderFormat,
                String formatLabel,
                boolean renderAsLink,
                String titleHeadingType) {
			this.asset = asset;
            this.renderImage = !suppressImage;
            this.imageSize = imageSize;
            this.renderTitle = renderTitle;
            this.renderCreator = renderCreator;
            this.createdByLabel = createdByLabel;
            this.renderDescription = renderDescription;
            this.renderFormat = renderFormat;
            this.formatLabel = formatLabel;
            this.renderAsLink = renderAsLink;
            this.titleHeadingType = titleHeadingType;
		}

		public Asset getAsset() {
			return asset;
		}

        public String getImageSource() {
            return asset.getPath();
        }

        public String getImageSourceRendition() {
            if(StringUtils.isNotBlank(imageSize) && !imageSize.equalsIgnoreCase(ORIGINAL)) {
                StringBuilder builder = new StringBuilder(asset.getPath());
                builder.append("/").append(RENDITION_THUMNAIL_PATH);
                builder.append(".").append(imageSize).append(".");
                builder.append(RENDITION_THUMNAIL_EXTENSION);
                return builder.toString();
            }
            else {
                return asset.getPath();
            }
        }

        public String getTitle() {
            return asset.getMetadataValue(TITLE_PROPERTY);
        }

        public String getCreator() {
            return asset.getMetadataValue(CREATOR_PROPERTY);
        }

        public String getCreatedByLabel() {
            if (StringUtils.isNotBlank(createdByLabel)) {
                return createdByLabel + " ";
            }

            return "";
        }

        public String getDescription() {
            return asset.getMetadataValue(DESCRIPTION_PROPERTY);
        }

        public String getFormat() {
            return asset.getMetadataValue(FORMAT_PROPERTY);
        }

        public String getFormatLabel() {
            if (StringUtils.isNotBlank(formatLabel)) {
                return formatLabel + " ";
            }

            return "";
        }

        public String getTitleHeadingType() {
            return titleHeadingType;
        }

		public boolean isRenderAsLink() {
			return renderAsLink;
		}

        public boolean isRenderFormat() {
            return renderFormat && StringUtils.isNotBlank(getFormat());
        }

        public boolean isRenderDescription() {
            return renderDescription && StringUtils.isNotBlank(getDescription());
        }

        public boolean isRenderCreator() {
            return renderCreator && StringUtils.isNotBlank(getCreator());
        }

        public boolean isRenderTitle() {
            return renderTitle && StringUtils.isNotBlank(getTitle());
        }

        public boolean isRenderImage() {
            return renderImage;
        }

        /*
         * Render the Asset as an article if
         *
         * 1: Both the Title and Image are to be rendered
         * 2: Any of the additional meta is to be presented
         */
        public boolean getIsArticle() {
            return (isRenderImage() && isRenderTitle()) || isRenderDescription() || isRenderFormat() || isRenderCreator();
        }
    }

}
