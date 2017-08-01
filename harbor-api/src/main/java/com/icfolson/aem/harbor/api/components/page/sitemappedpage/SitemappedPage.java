package com.icfolson.aem.harbor.api.components.page.sitemappedpage;

import com.icfolson.aem.harbor.api.domain.sitemap.ChangeFrequency;
import com.icfolson.aem.library.api.page.PageDecorator;

public interface SitemappedPage {

    boolean isHiddenFromRobots();

    String getPriority();

    PageDecorator getPage();

    ChangeFrequency getChangeFrequency();

}
