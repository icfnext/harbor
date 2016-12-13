package com.icfolson.aem.harbor.core.components.content.list.page;

import java.util.List;

import com.icfolson.aem.library.api.page.PageDecorator;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.Option;
import com.citytechinc.cq.component.annotations.widgets.Selection;
import com.icfolson.aem.harbor.api.constants.dom.Headings;
import com.icfolson.aem.harbor.api.lists.rendering.ListRenderingStrategy;
import com.citytechinc.cq.component.annotations.widgets.Switch;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;

@Model(adaptables = Resource.class)
public class LinkablePageRenderingStrategy implements
	ListRenderingStrategy<PageDecorator, List<LinkablePageRenderingStrategy.LinkablePage>> {

    @DialogField(fieldLabel = "Suppress Page Titles?")
    @Switch(offText = "No", onText = "Yes")
    @Inject @Default(booleanValues = false)
    private Boolean suppressTitle;

    @DialogField(fieldLabel = "Render Page Subtitles?")
    @Switch(offText = "No", onText = "Yes")
    @Inject @Default(booleanValues = false)
    private Boolean renderSubtitle;

    @DialogField(fieldLabel = "Render Page Images?")
    @Switch(offText = "No", onText = "Yes")
    @Inject @Default(booleanValues = false)
    private Boolean renderImage;

    @DialogField(fieldLabel = "Render Page Descriptions?")
    @Switch(offText = "No", onText = "Yes")
    @Inject @Default(booleanValues = false)
    private Boolean renderDescription;

    @DialogField(fieldLabel = "Render as Link?")
    @Switch(offText = "No", onText = "Yes")
    @Inject @Default(booleanValues = false)
    private Boolean renderAsLink;

    @DialogField(fieldLabel = "Title Heading Type", fieldDescription = "When the Page Title is rendered in the context of a list, it will be rendered as a heading element.  What level of heading should be used is dependent on how your pages content is structured and specifically whether other headers are present on the page.", defaultValue = Headings.H3)
    @Selection(type = Selection.SELECT, options = {
            @Option(text = Headings.H3_LABEL, value = Headings.H3),
            @Option(text = Headings.H4_LABEL, value = Headings.H4),
            @Option(text = Headings.H5_LABEL, value = Headings.H5),
            @Option(text = Headings.H6_LABEL, value = Headings.H6)})
    @Inject @Default(values = Headings.H3)
    private String titleHeadingType;

    public boolean isSuppressTitle() {
        return suppressTitle;
    }

    public boolean isRenderSubtitle() {
        return renderSubtitle;
    }

    public boolean isRenderImage() {
        return renderImage;
    }

    public boolean isRenderDescription() {
        return renderDescription;
    }

    public boolean isRenderAsLink() {
		return renderAsLink;
	}

    public String getTitleHeadingType() {
        return titleHeadingType;
    }

    @Override
	public List<LinkablePage> toRenderableList(Iterable<PageDecorator> itemIterable) {
		List<LinkablePage> retList = Lists.newArrayList();

		for (PageDecorator currentPageDecorator : itemIterable) {
			retList.add(new LinkablePage(
                    currentPageDecorator,
                    !isSuppressTitle(),
                    isRenderSubtitle(),
                    isRenderImage(),
                    isRenderDescription(),
                    isRenderAsLink(),
                    getTitleHeadingType()));
		}

		return retList;
	}

	public static class LinkablePage {

		private final PageDecorator pageDecorator;
        private final boolean renderTitle;
        private final boolean renderSubtitle;
        private final boolean renderImage;
        private final boolean renderDescription;
		private final boolean renderAsLink;
        private final String titleHeadingType;

		public LinkablePage(
                PageDecorator pageDecorator,
                boolean renderTitle,
                boolean renderSubtitle,
                boolean renderImage,
                boolean renderDescription,
                boolean renderAsLink,
                String titleHeadingType) {
			this.pageDecorator = pageDecorator;
            this.renderTitle = renderTitle;
            this.renderSubtitle = renderSubtitle;
            this.renderImage = renderImage;
            this.renderDescription = renderDescription;
			this.renderAsLink = renderAsLink;
            this.titleHeadingType = titleHeadingType;
		}

		public PageDecorator getPageDecorator() {
			return pageDecorator;
		}

        public boolean isRenderTitle() {
            return renderTitle;
        }

        public String getTitle() {
            return getPageDecorator().getTitle();
        }

        public boolean isRenderSubtitle() {
            return renderSubtitle && StringUtils.isNotBlank(getSubtitle());
        }

        public String getSubtitle() {
            return getPageDecorator().get("subtitle", "");
        }

        public boolean isRenderImage() {
            return renderImage;
        }

        public String getImageSource() {
            return getPageDecorator().getLinkBuilder().addSelector("thumb").setExtension("png").build().getHref();
        }

        public boolean isRenderDescription() {
            return renderDescription && StringUtils.isNotBlank(getDescription());
        }

        public String getDescription() {
            return getPageDecorator().getDescription();
        }

        public boolean isRenderAsLink() {
			return renderAsLink;
		}

        public String getHref() {
            return pageDecorator.getHref();
        }

        public String getTitleHeadingType() {
            return titleHeadingType;
        }

        public boolean getIsArticle() {
            return isRenderSubtitle() || isRenderDescription() || isRenderImage();
        }
    }
}
