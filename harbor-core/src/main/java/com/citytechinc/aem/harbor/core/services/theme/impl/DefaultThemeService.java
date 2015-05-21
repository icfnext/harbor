package com.citytechinc.aem.harbor.core.services.theme.impl;

import com.citytechinc.aem.harbor.api.services.theme.ThemeService;
import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Modified;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.commons.osgi.PropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

@Component(label = "Harbor Theme Service", metatype = true, configurationFactory = true)
@Service
public class DefaultThemeService implements ThemeService {

    private static final Logger LOG = LoggerFactory.getLogger(DefaultThemeService.class);

    @Property(label = "Theme Title", value = "", description = "A title used to identify this Service Instance externally.")
    private static final String TITLE_PROPERTY = "themeTitle";
    private String themeTitle;

    @Property(label = "Theme category", value = "", description = "The category defined for this theme, i.e 'harbor.theme.XXX'")
    private static final String THEME_CATEGORY = "themeCategory";
    private String themeCategory;

    @Activate
    @Modified
    protected void activate(final Map<String, Object> properties) throws Exception {
        this.themeTitle = PropertiesUtil.toString(properties.get(TITLE_PROPERTY), "");
        this.themeCategory = PropertiesUtil.toString(properties.get(THEME_CATEGORY), "");
    }


    @Override
    public String getTitle() {
        return themeTitle;
    }

    @Override
    public String getCategory() {
        return themeCategory;
    }
}
