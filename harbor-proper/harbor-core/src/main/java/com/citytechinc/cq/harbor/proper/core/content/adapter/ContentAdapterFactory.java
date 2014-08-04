package com.citytechinc.cq.harbor.proper.core.content.adapter;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.api.adapter.AdapterFactory;
import org.apache.sling.api.resource.Resource;
import org.osgi.framework.Constants;

import com.citytechinc.aem.bedrock.api.page.PageDecorator;
import com.citytechinc.cq.harbor.proper.api.content.page.HierarchicalPage;
import com.citytechinc.cq.harbor.proper.api.content.page.HomePage;
import com.citytechinc.cq.harbor.proper.api.content.page.SectionLandingPage;
import com.citytechinc.cq.harbor.proper.core.content.page.impl.DefaultHierarchicalPage;
import com.citytechinc.cq.harbor.proper.core.content.page.impl.DefaultHomePage;
import com.citytechinc.cq.harbor.proper.core.content.page.impl.DefaultSectionLandingPage;
import com.citytechinc.cq.harbor.proper.core.content.page.impl.PagePredicates;
import com.day.cq.wcm.api.Page;

@Component
@Service
@Properties({
	@Property(name = Constants.SERVICE_DESCRIPTION, value = "CQ Library Content API Adapter Factory"),
	@Property(name = "adaptables", value = { "org.apache.sling.api.resource.Resource",
		"com.citytechinc.cq.library.content.page.PageDecorator", "com.day.cq.wcm.api.Page" }),
	@Property(name = "adapters", value = { "com.citytechinc.cq.harbor.proper.api.content.page.HierarchicalPage",
		"com.citytechinc.cq.harbor.proper.api.content.page.SectionLandingPage",
		"com.citytechinc.cq.harbor.proper.api.content.page.HomePage" }) })
public class ContentAdapterFactory implements AdapterFactory {

	@Override
	public <AdapterType> AdapterType getAdapter(Object adaptable, Class<AdapterType> type) {

		if (adaptable instanceof Resource) {
			return getAdapter((Resource) adaptable, type);
		}
		if (adaptable instanceof PageDecorator) {
			return getAdapter(adaptable, type);
		}
		if (adaptable instanceof Page) {
			return getAdapter((Page) adaptable, type);
		}

		return null;

	}

	@SuppressWarnings("unchecked")
	private <AdapterType> AdapterType getAdapter(Resource resource, Class<AdapterType> type) {

		PageDecorator pageDecorator = resource.adaptTo(PageDecorator.class);

		if (pageDecorator != null) {

			return getAdapter(pageDecorator, type);

		}

		return null;

	}

	@SuppressWarnings("unchecked")
	private <AdapterType> AdapterType getAdapter(PageDecorator pageDecorator, Class<AdapterType> type) {

		if (type == HierarchicalPage.class) {
			return (AdapterType) new DefaultHierarchicalPage(pageDecorator);
		}
		if (type == SectionLandingPage.class && PagePredicates.SECTION_LANDING_PAGE_PREDICATE.apply(pageDecorator)) {
			return (AdapterType) new DefaultSectionLandingPage(pageDecorator);
		}
		if (type == HomePage.class && PagePredicates.HOME_PAGE_PREDICATE.apply(pageDecorator)) {
			return (AdapterType) new DefaultHomePage(pageDecorator);
		}

		return null;

	}

	private <AdapterType> AdapterType getAdapter(Page page, Class<AdapterType> type) {

		return getAdapter(page.adaptTo(PageDecorator.class), type);

	}

}
