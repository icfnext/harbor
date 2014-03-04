package com.citytechinc.cq.harbor.services;

import com.google.common.collect.Sets;
import org.apache.felix.scr.annotations.*;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.commons.osgi.PropertiesUtil;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

@Component(label = "Accelerate Sitemap Configuration service", immediate = true, metatype = true)
@Service
public final class SitemapService {

    @Property(label = "Sitemap Root Locations",
            description = "List of paths that the sitemap servlet will use when generating a sitemap. ",
            unbounded = PropertyUnbounded.ARRAY)
    private static final String SITEMAP_ROOT_PATHS = "sitemap.root.paths";

    private Set<String> sitemapRootPaths = Collections.emptySet();

    public Set<String> getSiteMapRootPaths(){
        return sitemapRootPaths;
    }

    @Activate
    protected void activate(final Map<String, Object> properties) throws LoginException {
        modified(properties);
    }

    @Modified
    protected void modified(final Map<String, Object> properties) {
        /*
            Grabs sitemap paths from the @Property, and stuffs them into a hashSet
         */
        final String[] sitemapRootPaths = PropertiesUtil.toStringArray(properties.get(SITEMAP_ROOT_PATHS),new String[0]);
        this.sitemapRootPaths = Sets.newHashSet(sitemapRootPaths);
    }

    @Deactivate
    protected void deactivate() {
        //TODO: Something?
    }


}
