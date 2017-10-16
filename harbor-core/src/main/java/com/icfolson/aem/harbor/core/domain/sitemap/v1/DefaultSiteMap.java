package com.icfolson.aem.harbor.core.domain.sitemap.v1;

import com.day.cq.commons.Externalizer;
import com.google.common.collect.Lists;
import com.icfolson.aem.harbor.api.components.page.sitemappedpage.SitemappedPage;
import com.icfolson.aem.harbor.api.domain.sitemap.ChangeFrequency;
import com.icfolson.aem.harbor.api.domain.sitemap.SiteMap;
import com.icfolson.aem.harbor.api.domain.sitemap.SiteMapEntry;
import com.icfolson.aem.harbor.core.content.page.v1.PagePredicates;
import com.icfolson.aem.library.api.page.PageDecorator;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static com.day.cq.commons.jcr.JcrConstants.JCR_CREATED;
import static com.day.cq.wcm.api.NameConstants.PN_PAGE_LAST_MOD;

@XmlRootElement(name = "urlset", namespace = "http://www.sitemaps.org/schemas/sitemap/0.9")
@Model(adaptables = SlingHttpServletRequest.class, adapters = SiteMap.class)
public class DefaultSiteMap implements SiteMap {

    private static final Predicate<SitemappedPage> SITEMAPPED_PAGE_PREDICATE = new Predicate<SitemappedPage>() {
        @Override
        public boolean test(SitemappedPage sitemappedPage) {
            return !sitemappedPage.isHiddenFromRobots();
        }
    };

    private static SimpleDateFormat ISO_DATE_FORMATTER = new SimpleDateFormat("yyyy-MM-dd");

    @Inject
    private Externalizer externalizer;

    @Inject
    private SlingHttpServletRequest request;

    private List<SiteMapEntry> siteMapEntries;

    @XmlElement(name = "url", type = DefaultSiteMapEntry.class)
    @Override
    public List<SiteMapEntry> getSiteMapEntries() {
        if (siteMapEntries == null) {

            siteMapEntries = Lists.newArrayList(
                    request.getResource().adaptTo(PageDecorator.class).listChildPages(PagePredicates.ALL_PAGES_PREDICATE, true))
                    .stream()
                    .map(page -> page.getContentResource().adaptTo(SitemappedPage.class))
                    .filter(getSitemappedPagePredicate())
                    .map(page -> new DefaultSiteMapEntry(
                            getLocForSitemappedPage(page) + getExtension(),
                            getLastModForSitemappedPage(page),
                            getChangeFrequencyForSitemappedPage(page),
                            getPriorityForSitemappedPage(page)
                    ))
                    .collect(Collectors.toList());

        }

        return siteMapEntries;
    }

    public Predicate<SitemappedPage> getSitemappedPagePredicate() {
        return SITEMAPPED_PAGE_PREDICATE;
    }

    public String getLocForSitemappedPage(SitemappedPage page) {
        return externalizer.externalLink(this.request.getResourceResolver(),
                getExternalizerName(),
                this.request.getResourceResolver().map(this.request, page.getPage().getPath()));
    }

    public String getLastModForSitemappedPage(SitemappedPage page) {
        final ValueMap pageProperties = page.getPage().getProperties();
        final Calendar lastModified = pageProperties.get(PN_PAGE_LAST_MOD,
                pageProperties.get(JCR_CREATED, Calendar.class));

        return lastModified == null ? null : ISO_DATE_FORMATTER.format(lastModified.getTime());
    }

    public String getChangeFrequencyForSitemappedPage(SitemappedPage page) {
        final ChangeFrequency changeFrequency = page.getChangeFrequency();

        return changeFrequency == null ? null : changeFrequency.name().toLowerCase();
    }

    public String getPriorityForSitemappedPage(SitemappedPage page) {
        return page.getPriority();
    }

    public String getExternalizerName() {
        return Externalizer.PUBLISH;
    }

    public String getExtension() {
        return ".html";
    }

    @Override
    public void marshall(OutputStream stream) throws JAXBException, IOException {
        final JAXBContext context = JAXBContext.newInstance(DefaultSiteMap.class, DefaultSiteMapEntry.class);
        final Marshaller marshaller = context.createMarshaller();

        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(this, stream);
    }

}
