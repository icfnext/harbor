package com.citytechinc.aem.harbor.core.components.theme;

import com.citytechinc.aem.bedrock.api.request.ComponentRequest;
import com.citytechinc.aem.bedrock.core.components.AbstractComponent;
import org.apache.commons.lang3.StringUtils;

public class Theme extends AbstractComponent {

	public static final String THEME_PROPERTY = "ct:theme";
    private static final String DEFAULT_THEME = "harbor.theme.default";

    private String theme;

	@Override
	public void init(ComponentRequest request) {

        theme =  getCurrentPage().getInherited(THEME_PROPERTY, String.class).orNull();
        if(StringUtils.isBlank(theme)) {
            theme = DEFAULT_THEME;
        }
	}

    public String getTheme() {
        return theme;
    }
}
