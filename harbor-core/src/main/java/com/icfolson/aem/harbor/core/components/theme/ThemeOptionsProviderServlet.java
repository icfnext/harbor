package com.icfolson.aem.harbor.core.components.theme;

import com.icfolson.aem.library.api.request.ComponentServletRequest;
import com.icfolson.aem.library.core.servlets.optionsprovider.AbstractOptionsProviderServlet;
import com.icfolson.aem.library.core.servlets.optionsprovider.Option;
import com.icfolson.aem.harbor.api.services.theme.ThemeService;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.ReferenceCardinality;
import org.apache.felix.scr.annotations.ReferencePolicy;
import org.apache.felix.scr.annotations.sling.SlingServlet;

import java.util.List;
import java.util.Set;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@SlingServlet(resourceTypes = "foundation/components/page", selectors = ThemeOptionsProviderServlet.SELECTOR, methods = "GET", extensions = "json")
public class ThemeOptionsProviderServlet extends AbstractOptionsProviderServlet {

    public static final String SELECTOR = "harborthemeoptions";

	@Reference(cardinality = ReferenceCardinality.OPTIONAL_MULTIPLE, policy = ReferencePolicy.DYNAMIC, bind = "bindService", unbind = "unbindService", referenceInterface = ThemeService.class)
	private final Set<ThemeService> services = Sets.newHashSet();

    private final ReentrantReadWriteLock servicesLock = new ReentrantReadWriteLock();

	@Override
	protected List<Option> getOptions(ComponentServletRequest componentServletRequest) {
        List<Option> optionList = Lists.newArrayList();

		for (ThemeService currentThemeService : getServices()) {
			optionList.add(new Option(currentThemeService.getCategory(), currentThemeService.getTitle()));
		}

		return optionList;
	}

	@Override
	protected Optional<String> getOptionsRoot(ComponentServletRequest componentServletRequest) {
		return Optional.absent();
	}

    protected void bindService(ThemeService service) {

        servicesLock.writeLock().lock();

        try {
            services.add(service);
        }
        finally {
            servicesLock.writeLock().unlock();
        }

    }

    protected void unbindService(ThemeService service) {

        servicesLock.writeLock().lock();

        try {
            services.remove(service);
        }
        finally {
            servicesLock.writeLock().unlock();
        }

    }

    protected Set<ThemeService> getServices() {
        servicesLock.readLock().lock();

        try {
            return ImmutableSet.copyOf(services);
        }
        finally {
            servicesLock.readLock().unlock();
        }

    }

}
