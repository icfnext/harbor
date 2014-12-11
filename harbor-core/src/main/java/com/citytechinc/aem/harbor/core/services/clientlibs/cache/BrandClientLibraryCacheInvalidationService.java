package com.citytechinc.aem.harbor.core.services.clientlibs.cache;


import com.citytechinc.cq.clientlibs.api.services.clientlibs.cache.ClientLibraryCacheManager;
import com.citytechinc.cq.clientlibs.api.services.clientlibs.exceptions.ClientLibraryCachingException;
import org.apache.felix.scr.annotations.*;
import org.apache.sling.jcr.api.SlingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.observation.EventIterator;
import javax.jcr.observation.EventListener;
import java.util.Map;

@Component(immediate = true)
@Service
public class BrandClientLibraryCacheInvalidationService implements EventListener {

    private static final Logger LOG = LoggerFactory.getLogger(BrandClientLibraryCacheInvalidationService.class);

    private Session session;

    @Reference
    private SlingRepository repository;

    @Reference
    private ClientLibraryCacheManager cacheManager;

    @Activate
    protected void activate( Map<String, Object> properties ) throws RepositoryException {

        getAdministrativeSession().getWorkspace().getObservationManager().addEventListener(
                this,
                31,
                "etc/brands",
                true,
                null,
                new String[] {"cq:PageContent"},
                true);

    }

    @Deactivate
    protected void deactivate() throws RepositoryException {
        getAdministrativeSession().getWorkspace().getObservationManager().removeEventListener(this);
        getAdministrativeSession().logout();
        session = null;
    }

    @Override
    public void onEvent(EventIterator eventIterator) {
        try {
            cacheManager.clearCache();
        } catch (ClientLibraryCachingException e) {
            LOG.error("Client Library Caching Exception encountered attempting to clear cache after a change to an Authored Brand", e);
        }
    }

    protected final Session getAdministrativeSession() throws RepositoryException {
        if (session == null) {
            session = repository.loginAdministrative(null);
        }

        return session;
    }

}
