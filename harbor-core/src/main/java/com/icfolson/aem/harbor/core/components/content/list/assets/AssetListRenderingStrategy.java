package com.icfolson.aem.harbor.core.components.content.list.assets;

import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.Option;
import com.citytechinc.cq.component.annotations.widgets.Selection;
import com.citytechinc.cq.component.annotations.widgets.Switch;
import com.citytechinc.cq.component.annotations.widgets.TextField;
import com.day.cq.dam.api.Asset;
import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import com.icfolson.aem.harbor.api.constants.dom.Headings;
import com.icfolson.aem.harbor.api.lists.rendering.ListRenderingStrategy;
import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Renders a list of assets as links.
 */
@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class AssetListRenderingStrategy implements
    ListRenderingStrategy<Asset, List<AssetListRenderingStrategy.RenderableAsset>> {

    // TODO: Move into a Constants object elsewhere
    public static final String TITLE_PROPERTY = "dc:title";

    public static final String DESCRIPTION_PROPERTY = "dc:description";

    public static final String FORMAT_PROPERTY = "dc:format";

    public static final String CREATOR_PROPERTY = "dc:creator";

    private static final String RENDITION_THUMBNAIL_PATH = "jcr:content/renditions/cq5dam.thumbnail";

    private static final String RENDITION_THUMBNAIL_EXTENSION = "png";

    private static final String ORIGINAL = "Original";

    @DialogField(fieldLabel = "Suppress Images?")
    @Switch(offText = "No", onText = "Yes")
    @Inject
    private boolean suppressImages;

    @DialogField(fieldLabel = "Image Size", fieldDescription = "Render image as original size or thumbnail rendition",
        defaultValue = ORIGINAL)
    @Selection(type = Selection.SELECT, options = {
        @Option(text = ORIGINAL, value = ORIGINAL),
        @Option(text = "48 x 48", value = "48.48"),
        @Option(text = "140 x 100", value = "140.100"),
        @Option(text = "319 x 319", value = "319.319") })
    @Inject
    @Default(values = ORIGINAL)
    private String imageSize;

    @DialogField(fieldLabel = "Render Asset Titles?")
    @Switch(offText = "No", onText = "Yes")
    @Inject
    private boolean renderTitles;

    @DialogField(fieldLabel = "Render Asset Creators?")
    @Switch(offText = "No", onText = "Yes")
    @Inject
    private boolean renderCreators;

    @DialogField(fieldLabel = "Creator Label",
        fieldDescription = "The text label to render prior to the creator name when the creator name is rendered",
        defaultValue = "By:")
    @TextField
    @Inject
    @Default(values = "By:")
    private String creatorLabel;

    @DialogField(fieldLabel = "Render Asset Descriptions?")
    @Switch(offText = "No", onText = "Yes")
    @Inject
    private boolean renderDescriptions;

    @DialogField(fieldLabel = "Render Asset Formats?")
    @Switch(offText = "No", onText = "Yes")
    @Inject
    private boolean renderFormats;

    @DialogField(fieldLabel = "Format Label",
        fieldDescription = "The text label to render prior to the format denotation when the format is rendered",
        defaultValue = "Format:")
    @TextField
    @Inject
    @Default(values = "Format:")
    private String formatLabel;

    @DialogField(fieldLabel = "Render as Links")
    @Switch(offText = "No", onText = "Yes")
    @Inject
    private boolean renderAsLinks;

    @DialogField(fieldLabel = "Title Heading Type",
        fieldDescription = "When the Asset Title is rendered in the context of a list, it will be rendered as a heading element.  What level of heading should be used is dependent on how your pages content is structured and specifically whether other headers are present on the page.",
        defaultValue = Headings.H3)
    @Selection(type = Selection.SELECT, options = {
        @Option(text = Headings.H3_LABEL, value = Headings.H3),
        @Option(text = Headings.H4_LABEL, value = Headings.H4),
        @Option(text = Headings.H5_LABEL, value = Headings.H5),
        @Option(text = Headings.H6_LABEL, value = Headings.H6) })
    @Inject
    @Default(values = Headings.H3)
    private String titleHeadingType;

    private List<RenderableAsset> renderableAssets;

    /**
     * True to render items as links to their resources, otherwise render items
     * as static text.
     */
    public boolean getRenderAsLinks() {
        return renderAsLinks;
    }

    @Override
    public List<RenderableAsset> toRenderableList(Iterable<Asset> itemIterable) {
        if (renderableAssets == null) {
            renderableAssets = Lists.newArrayList(itemIterable)
                .stream()
                .map(asset -> new RenderableAsset(asset,
                    suppressImages,
                    imageSize,
                    renderTitles,
                    renderCreators,
                    creatorLabel,
                    renderDescriptions,
                    renderFormats,
                    formatLabel,
                    getRenderAsLinks(),
                    titleHeadingType))
                .collect(Collectors.toList());
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
            final String imageSourceRendition;

            if (StringUtils.isNotBlank(imageSize) && !imageSize.equalsIgnoreCase(ORIGINAL)) {
                final StringBuilder builder = new StringBuilder(asset.getPath());

                builder.append("/").append(RENDITION_THUMBNAIL_PATH);
                builder.append(".").append(imageSize).append(".");
                builder.append(RENDITION_THUMBNAIL_EXTENSION);

                imageSourceRendition = builder.toString();
            } else {
                imageSourceRendition = asset.getPath();
            }

            return imageSourceRendition;
        }

        public String getTitle() {
            return asset.getMetadataValue(TITLE_PROPERTY);
        }

        public String getCreator() {
            return asset.getMetadataValue(CREATOR_PROPERTY);
        }

        public String getCreatedByLabel() {
            return StringUtils.isNotBlank(createdByLabel) ? createdByLabel + " " : "";
        }

        public String getDescription() {
            return asset.getMetadataValue(DESCRIPTION_PROPERTY);
        }

        public String getFormat() {
            return asset.getMetadataValue(FORMAT_PROPERTY);
        }

        public String getFormatLabel() {
            return StringUtils.isNotBlank(formatLabel) ? formatLabel + " " : "";
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
            final ResourceResolver resourceResolver = asset.adaptTo(Resource.class).getResourceResolver();

            return renderImage && resourceResolver.getResource(getImageSourceRendition()) != null;
        }

        /*
         * Render the Asset as an article if
         *
         * 1: Both the Title and Image are to be rendered
         * 2: Any of the additional meta is to be presented
         */
        public boolean isArticle() {
            return (isRenderImage() && isRenderTitle()) || isRenderDescription() || isRenderFormat() || isRenderCreator();
        }

        @Override
        public String toString() {
            return Objects.toStringHelper(this)
                .add("imageSource", getImageSource())
                .add("imageSourceRendition", getImageSourceRendition())
                .add("title", getTitle())
                .add("creator", getCreator())
                .add("createdByLabel", getCreatedByLabel())
                .add("description", getDescription())
                .add("format", getFormat())
                .add("formatLabel", getFormatLabel())
                .add("titleHeadingType", getTitleHeadingType())
                .add("renderAsLink", isRenderAsLink())
                .add("renderFormat", isRenderFormat())
                .add("renderDescription", isRenderDescription())
                .add("renderCreator", isRenderCreator())
                .add("renderTitle", isRenderTitle())
                .add("renderImage", isRenderImage())
                .add("article", isArticle())
                .toString();
        }
    }
}
