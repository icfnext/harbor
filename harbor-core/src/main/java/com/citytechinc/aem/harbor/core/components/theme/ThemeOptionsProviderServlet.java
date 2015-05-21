package com.citytechinc.aem.harbor.core.components.theme;

import com.citytechinc.aem.bedrock.api.request.ComponentServletRequest;
import com.citytechinc.aem.bedrock.core.servlets.optionsprovider.AbstractOptionsProviderServlet;
import com.citytechinc.aem.bedrock.core.servlets.optionsprovider.Option;
import com.citytechinc.aem.harbor.api.services.theme.ThemeService;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.ReferenceCardinality;
import org.apache.felix.scr.annotations.ReferencePolicy;
import org.apache.felix.scr.annotations.sling.SlingServlet;

import java.util.List;

@SlingServlet(resourceTypes = "foundation/components/page", selectors = "harborthemeoptions", methods = "GET", extensions = "json")
public class ThemeOptionsProviderServlet extends AbstractOptionsProviderServlet {

	@Reference(cardinality = ReferenceCardinality.OPTIONAL_MULTIPLE, policy = ReferencePolicy.DYNAMIC, bind = "bindService", unbind = "unbindService", referenceInterface = ThemeService.class)
	private final List<ThemeService> services = Lists.newArrayList();

	protected void bindService(ThemeService service) {

		synchronized (services) {
			if (!services.contains(service)) {
                services.add(service);
			}
		}

	}

	protected void unbindService(ThemeService service) {

		synchronized (services) {
            services.remove(service);
		}

	}

	@Override
	protected List<Option> getOptions(ComponentServletRequest componentServletRequest) {
		List<Option> optionList = Lists.newArrayList();

		List<ThemeService> localServices = ImmutableList.copyOf(services);

		for (ThemeService currentThemeService : localServices) {
			optionList.add(new Option(currentThemeService.getCategory(), currentThemeService
				.getTitle()));
		}

		return optionList;
	}

	@Override
	protected Optional<String> getOptionsRoot(ComponentServletRequest componentServletRequest) {
		return Optional.absent();
	}

}
