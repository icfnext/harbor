package com.citytechinc.aem.harbor.api.services.theme;

public interface ThemeService {

    /**
     * Get the title of the Theme
     * @return
     */
    String getTitle();

    /**
     * Get CQ client library associated with the Theme
     * @return
     */
    String getCategory();
}
