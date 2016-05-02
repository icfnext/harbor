package com.icfolson.aem.harbor.api.services.theme;

/*
 * TODO: Remove when removing legacy theme engine
 */
@Deprecated
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
