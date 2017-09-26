package com.icfolson.aem.harbor.api.components.page.metapage;

import java.util.List;

public interface MetaPage {

    String getPageName();

    String getDescription();

    String getFullyQualifiedPageImage();

    String getFullyQualifiedPageUrl();

    String getTwitterPublisherHandle();

    String getFacebookOpenGraphType();

    String getCanonicalUrl();

    String getHomePageTitle();

    List<String> getRobotsTags();

    String getRobotsContent();

    String getExternalizerName();

    boolean isNoIndex();

    boolean isNoFollow();

    boolean isDisableSchemaOrg();

    boolean isDisableTwitterCard();

    boolean isDisableFacebookOpenGraph();

}
