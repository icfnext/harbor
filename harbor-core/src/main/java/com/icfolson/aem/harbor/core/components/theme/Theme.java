package com.icfolson.aem.harbor.core.components.theme;

import com.icfolson.aem.library.api.page.PageDecorator;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;

@Model(adaptables = Resource.class)
public class Theme {

	public static final String THEME_PROPERTY = "ct:theme";
    public static final String DEFAULT_THEME = "harbor.theme.default";

    @Inject
    private PageDecorator currentPage;

    public String getTheme() {
        return currentPage.getInherited(THEME_PROPERTY, String.class).or(DEFAULT_THEME);
    }

}
