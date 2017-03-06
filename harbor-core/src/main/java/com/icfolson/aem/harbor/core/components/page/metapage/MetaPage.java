package com.icfolson.aem.harbor.core.components.page.metapage;

import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.Option;
import com.citytechinc.cq.component.annotations.widgets.PathField;
import com.citytechinc.cq.component.annotations.widgets.Selection;
import com.citytechinc.cq.component.annotations.widgets.Switch;
import com.citytechinc.cq.component.annotations.widgets.TextField;
import com.google.common.collect.Lists;
import com.icfolson.aem.harbor.api.content.page.HierarchicalPage;
import com.icfolson.aem.harbor.api.services.meta.MetadataConfigService;
import com.icfolson.aem.library.api.page.PageDecorator;
import com.icfolson.aem.library.core.constants.PathConstants;
import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;
import java.util.List;

@Component(value = "SEO Metadata", editConfig = false, path = "/page/common", name = "global",
    touchFileName = "touch-metadata")
@Model(adaptables = SlingHttpServletRequest.class)
public class MetaPage {

    @Inject
    private PageDecorator currentPage;

    @Inject
    private MetadataConfigService metadataConfigService;

    @Inject
    private SlingHttpServletRequest request;

    @DialogField(fieldLabel = "Schema.org Page Metadata",
        fieldDescription = "When enabled, Google metadata tags name, description, and image will be output as part of the page meta data.",
        ranking = 0)
    @Switch(onText = "Disabled", offText = "Enabled")
    public boolean isDisableSchemaOrg() {
        //TODO: Should this inherit?
        return currentPage.getProperties().get("disableSchemaOrg", false);
    }

    public String getPageName() {
        return StringUtils.isNotBlank(currentPage.getPageTitle()) ? currentPage.getPageTitle() : currentPage.getName();
    }

    public String getDescription() {
        return currentPage.getDescription();
    }

    public String getFullyQualifiedPageImage() {
        //TODO: See what this actually returns - it might need externalization
        return currentPage.isHasImage() ? currentPage.getImageSource().or("") : "";
    }

    public String getFullyQualifiedPageUrl() {
        return metadataConfigService.getExternalUrlForPage(request, currentPage.adaptTo(Resource.class));
    }

    @DialogField(fieldLabel = "Twitter Publisher Handle",
        fieldDescription = "e.g. @icfi.  If this value is present Twitter metadata will be included on the page",
        ranking = 10)
    @TextField
    public String getTwitterPublisherHandle() {
        //TODO: SHould this inherit?
        return currentPage.get("twitterPublisherHandle", "");
    }

    @DialogField(fieldLabel = "Open Graph Type",
        fieldDescription = "Select an og:Type value and Open Graph metadata will be included for the page",
        ranking = 20)
    @Selection(
        type = Selection.SELECT,
        options = {
            @Option(text = "None", value = "none"),
            @Option(text = "Article", value = "article"),
            @Option(text = "Book", value = "book"),
            @Option(text = "Profile", value = "profile"),
            @Option(text = "Website", value = "website"),
            @Option(text = "Movie", value = "video.movie"),
            @Option(text = "Episode", value = "video.episode"),
            @Option(text = "TV Show", value = "video.tv_show"),
            @Option(text = "Video", value = "video.other"),
            @Option(text = "Song", value = "music.song"),
            @Option(text = "Album", value = "music.album"),
            @Option(text = "Playlist", value = "music.playlist"),
            @Option(text = "Radio Station", value = "music.radio_station")
        }
    )
    public String getOgType() {
        //TODO: Should this inherit?
        return currentPage.get("ogType", "");
    }

    @DialogField(fieldLabel = "Canonical Url", fieldDescription = "Canonical Url of the content of this page",
        ranking = 30)
    @PathField(rootPath = PathConstants.PATH_CONTENT)
    public String getCanonicalUrl() {
        return currentPage.get("canonicalUrl", String.class).transform(canonicalUrl -> {
            final String url;

            if (canonicalUrl.startsWith("http:") || canonicalUrl.startsWith("https:")) {
                url = canonicalUrl;
            } else {
                url = metadataConfigService.getExternalUrlForPage(request, currentPage.adaptTo(Resource.class));
            }

            return url;
        }).or("");
    }

    public String getHomePageTitle() {
        final HierarchicalPage hierarchicalPage = currentPage.adaptTo(HierarchicalPage.class);

        String title = "";

        if (hierarchicalPage != null) {
            title = hierarchicalPage.getHomePage().transform(homePage -> {
                final String pageTitle = homePage.getPageTitle();

                return StringUtils.isNotBlank(pageTitle) ? pageTitle : homePage.getName();
            }).or("");
        }

        return title;
    }

    @DialogField(fieldLabel = "Add NOINDEX metadata tag",
        fieldDescription = "This setting requests the automated internet bots avoid indexing this page", ranking = 40)
    @Switch(offText = "No", onText = "Yes")
    public boolean isNoIndex() {
        return currentPage.get("noIndex", false);
    }

    @DialogField(fieldLabel = "Add NOFOLLOW metadata tag",
        fieldDescription = "This setting instructs some search engines that hyperlinks on this page should not be counted as votes in favor of the linked content",
        ranking = 50)
    @Switch(offText = "No", onText = "Yes")
    public boolean isNoFollow() {
        return currentPage.get("noFollow", false);
    }

    public List<String> getRobotsTags() {
        final List<String> robotsTags = Lists.newArrayList();

        if (isNoIndex()) {
            robotsTags.add("NOINDEX");
        }

        if (isNoFollow()) {
            robotsTags.add("NOFOLLOW");
        }

        return robotsTags;
    }

    public String getRobotsContent() {
        final StringBuilder content = new StringBuilder();

        final boolean noIndexIndicator = currentPage.get("noindex", false);
        final boolean noFollowIndicator = currentPage.get("nofollow", false);

        if (noIndexIndicator) {
            content.append("NOINDEX");
            content.append(noFollowIndicator ? ", NOFOLLOW" : ", FOLLOW");
        } else {
            if (noFollowIndicator) {
                content.append("INDEX, NOFOLLOW");
            }
        }

        return content.toString();
    }
}
