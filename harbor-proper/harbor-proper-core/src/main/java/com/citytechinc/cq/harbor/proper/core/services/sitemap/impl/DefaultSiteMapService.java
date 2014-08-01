package com.citytechinc.cq.harbor.proper.core.services.sitemap.impl;

import static com.citytechinc.cq.accelerate.api.ontology.Properties.ACCELERATE_SITEMAP_CHANGE_FREQUENCY;
import static com.citytechinc.cq.accelerate.api.ontology.Properties.ACCELERATE_SITEMAP_PRIORITY;
import static com.citytechinc.cq.accelerate.api.ontology.Properties.ACCELERATE_SITEMAP_RESOURCE_EXTENSION;
import static com.day.cq.commons.jcr.JcrConstants.JCR_CREATED;
import static com.day.cq.wcm.api.NameConstants.PN_PAGE_LAST_MOD;
import static com.google.common.collect.Lists.newArrayList;
import static org.apache.commons.lang.StringUtils.EMPTY;
import static org.apache.commons.lang.StringUtils.isEmpty;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.citytechinc.aem.bedrock.api.page.PageDecorator;
import com.citytechinc.cq.harbor.proper.api.domain.sitemap.SiteMap;
import com.citytechinc.cq.harbor.proper.api.domain.sitemap.SiteMapEntry;
import com.citytechinc.cq.harbor.proper.api.services.sitemap.SiteMapService;
import com.citytechinc.cq.harbor.proper.core.domain.sitemap.ChangeFrequency;
import com.day.cq.commons.Externalizer;
import com.google.common.base.Predicate;

@Service
@Component(name = "Site Map Service", label = "Site Map Service")
public class DefaultSiteMapService implements SiteMapService {
	protected static final Logger LOG = LoggerFactory.getLogger(DefaultSiteMapService.class);

	protected static final Predicate<PageDecorator> allMyChildrenPredicate = new Predicate<PageDecorator>() {
		@Override
		public boolean apply(PageDecorator pageDecorator) {
			return true;
		}
	};

	protected static final String iso8601DateFormat = "yyyy-MM-dd";
	protected static final String logTemplateSiteMapEntries = "built sitemap containing {} entries";
	protected static final String logTemplateSiteMapEntry = "built sitemap entry, loc={}, lastModified={}, changeFrequency={}, priority={}";
	protected static final String logTemplateEmptyFrequency = "{} value is not set, valid values are: [{}]";
	protected static final String logTemplateUnknownFrequency = "{} value must be value in: [{}]";
	protected static final String logTemplateInvalidPriority = "{} value must be between 0 and 1";

	protected static int priorityMax = 1;
	protected static int priorityMin = 0;

	@Reference
	protected Externalizer externalizer;

	@Override
	public SiteMap getSitemapEntryList(final PageDecorator root) {
		final ResourceResolver resourceResolver = root.adaptTo(Resource.class).getResourceResolver();
		final List<SiteMapEntry> siteMapEntries = newArrayList();

		final SiteMapEntry rootSiteMapEntry = this.buildSiteMapEntry(resourceResolver, root);
		siteMapEntries.add(rootSiteMapEntry);

		for (final PageDecorator pageDecorator : root.getChildren()) {
			final SiteMapEntry siteMapEntry = this.buildSiteMapEntry(resourceResolver, pageDecorator);
			siteMapEntries.add(siteMapEntry);
		}

		if (LOG.isDebugEnabled()) {
			LOG.debug(logTemplateSiteMapEntries, siteMapEntries.size());
		}

		return this.newSiteMap(siteMapEntries);
	}

	protected SiteMapEntry buildSiteMapEntry(final ResourceResolver resourceResolver, final PageDecorator pageDecorator) {
		final String loc = this.determineLoc(resourceResolver, pageDecorator, pageDecorator.getProperties());
		final String lastModified = this.determineLastModified(pageDecorator.getProperties());
		final String changeFrequency = this.determineChangeFrequency(pageDecorator.getProperties());
		final String urlPriority = this.determinePriority(pageDecorator.getProperties());
		final SiteMapEntry siteMapEntry = this.newSiteMapEntry(loc, lastModified, changeFrequency, urlPriority);

		if (LOG.isDebugEnabled()) {
			LOG.debug(logTemplateSiteMapEntry, new Object[] { loc, lastModified, changeFrequency, urlPriority });
		}

		return siteMapEntry;
	}

	protected String determineChangeFrequency(final ValueMap contentResourceValueMap) {
		final String specifiedChangeFrequency = contentResourceValueMap.get(ACCELERATE_SITEMAP_CHANGE_FREQUENCY, null);

		final boolean changeFrequencyContains = ChangeFrequency.contains(specifiedChangeFrequency);

		if (isEmpty(specifiedChangeFrequency)) {
			LOG.warn(logTemplateEmptyFrequency,
				new Object[] { ACCELERATE_SITEMAP_CHANGE_FREQUENCY, ChangeFrequency.valuesString() });
			return null;
		} else if (!changeFrequencyContains) {
			LOG.error(logTemplateUnknownFrequency,
				new Object[] { ACCELERATE_SITEMAP_CHANGE_FREQUENCY, ChangeFrequency.valuesString() });
			return null;
		} else {
			return specifiedChangeFrequency;
		}
	}

	protected String determinePriority(final ValueMap contentResourceValueMap) {
		final String specifiedPriority = contentResourceValueMap.get(ACCELERATE_SITEMAP_PRIORITY, null);

		if (isEmpty(specifiedPriority)) {
			return null;
		}

		final Double parsedSpecifiedPriority = (this.parseDouble(specifiedPriority));

		if (null == parsedSpecifiedPriority
			|| (parsedSpecifiedPriority < priorityMin || parsedSpecifiedPriority > priorityMax)) {
			LOG.error(logTemplateInvalidPriority, ACCELERATE_SITEMAP_PRIORITY);
			return null;
		} else {
			return parsedSpecifiedPriority.toString();
		}
	}

	protected String determineLoc(final ResourceResolver resourceResolver, final PageDecorator pageDecorator,
		final ValueMap contentResourceValueMap) {
		final String externalPublishLink = this.externalizer.publishLink(resourceResolver, pageDecorator.getPath());
		final String extension = contentResourceValueMap.get(ACCELERATE_SITEMAP_RESOURCE_EXTENSION, EMPTY);

		if (isEmpty(extension)) {
			return externalPublishLink;
		} else {
			return this.newStringBuffer(externalPublishLink).append(extension).toString();
		}
	}

	protected Double parseDouble(final String value) {
		try {
			return Double.parseDouble(value);
		} catch (final Exception e) {
			return null;
		}
	}

	protected String determineLastModified(final ValueMap pageContentValueMap) {
		final GregorianCalendar lastModified = (GregorianCalendar) pageContentValueMap.get(PN_PAGE_LAST_MOD,
			pageContentValueMap.get(JCR_CREATED));

		if (null == lastModified) {
			return null;
		} else {
			final SimpleDateFormat iso8601SimpleDateFormat = this.newIso8601SimpleDateFormat();
			return iso8601SimpleDateFormat.format(lastModified.getTime());
		}
	}

	protected SiteMap newSiteMap(final List<SiteMapEntry> siteMapEntries) {
		return new SiteMap(siteMapEntries);
	}

	protected SiteMapEntry newSiteMapEntry(final String loc, final String lastModified, final String changeFrequency,
		final String urlPriority) {
		return new SiteMapEntry(loc, lastModified, changeFrequency, urlPriority);
	}

	protected StringBuffer newStringBuffer(final String source) {
		return new StringBuffer(source);
	}

	protected SimpleDateFormat newIso8601SimpleDateFormat() {
		return new SimpleDateFormat(iso8601DateFormat);
	}

}
