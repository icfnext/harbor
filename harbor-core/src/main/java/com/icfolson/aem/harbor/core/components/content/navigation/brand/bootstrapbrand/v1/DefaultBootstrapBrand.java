package com.icfolson.aem.harbor.core.components.content.navigation.brand.bootstrapbrand.v1;

import com.citytechinc.cq.component.annotations.Component;
import com.day.cq.wcm.api.Page;
import com.google.common.base.Optional;
import com.icfolson.aem.harbor.api.components.content.navigation.brand.bootstrapbrand.BootstrapBrand;
import com.icfolson.aem.harbor.api.content.page.HierarchicalPage;
import com.icfolson.aem.harbor.api.content.page.HomePage;
import com.icfolson.aem.library.api.page.PageDecorator;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;

@Component(value = "Bootstrap Brand (v1)",
        group = ".hidden",
        actions = { "text: Bootstrap Brand (v1)", "-", "edit" },
        name = "navigation/brand/bootstrapbrand/v1/bootstrapbrand")
@Model(adaptables = Resource.class, adapters = BootstrapBrand.class, resourceType = DefaultBootstrapBrand.RESOURCE_TYPE)
public class DefaultBootstrapBrand implements BootstrapBrand {

    public static final String RESOURCE_TYPE = "harbor/components/content/navigation/brand/bootstrapbrand/v1/bootstrapbrand";

    @Inject
    private PageDecorator currentPage;

    private Optional<HomePage> homePageOptional;

    @Override
    public String getTitle() {
        return getHomePageOptional().transform(Page::getTitle).or("Brand Title");
    }

    @Override
    public String getHref() {
        return getHomePageOptional().transform(PageDecorator::getHref).or("#");
    }

    protected Optional<HomePage> getHomePageOptional() {
        if (homePageOptional == null) {
            homePageOptional = currentPage.adaptTo(HierarchicalPage.class).getHomePage();
        }

        return homePageOptional;
    }

}
