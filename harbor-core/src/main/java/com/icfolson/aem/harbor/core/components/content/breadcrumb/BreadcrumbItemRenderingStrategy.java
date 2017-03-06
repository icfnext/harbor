package com.icfolson.aem.harbor.core.components.content.breadcrumb;

import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.widgets.DialogFieldSet;
import com.citytechinc.cq.component.annotations.widgets.Switch;
import com.google.common.collect.Lists;
import com.icfolson.aem.harbor.api.lists.rendering.ListRenderingStrategy;
import com.icfolson.aem.harbor.core.components.content.page.TrailPage;
import com.icfolson.aem.library.core.components.AbstractComponent;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

import java.util.List;

@Model(adaptables = Resource.class)
public class BreadcrumbItemRenderingStrategy extends AbstractComponent implements ListRenderingStrategy<TrailPage, BreadcrumbTrail> {

    public static final String ROOT_ITEM_CONFIGURATION_PREFIX = "./rootItem/";

    public static final String INTERMEDIATE_ITEM_CONFIGURATION_PREFIX = "./intermediateItem/";

    public static final String CURRENT_ITEM_CONFIGURATION_PREFIX = "./currentItem/";

    private BreadcrumbTrail breadcrumbTrail;

    @Override
    public BreadcrumbTrail toRenderableList(Iterable<TrailPage> itemIterable) {
        if (breadcrumbTrail == null) {
            final List<BreadcrumbItem> renderableList = Lists.newArrayList();

            for (TrailPage currentBreadcrumbPage : itemIterable) {
                final BreadcrumbItemConfiguration configuration;

                if (currentBreadcrumbPage.isRoot()) {
                    configuration = getRootItemConfiguration();
                } else if (currentBreadcrumbPage.isCurrent()) {
                    configuration = getCurrentItemConfiguration();
                } else {
                    configuration = getIntermediateItemConfiguration();
                }

                renderableList.add(new BreadcrumbItem(currentBreadcrumbPage, configuration));
            }

            breadcrumbTrail = new BreadcrumbTrail(getRenderAsLink(), getRootItemConfiguration(),
                getIntermediateItemConfiguration(), getCurrentItemConfiguration(), renderableList);
        }

        return breadcrumbTrail;
    }

    @DialogField(fieldLabel = "Render As Link", ranking = 1)
    @Switch(offText = "No", onText = "Yes")
    public Boolean getRenderAsLink() {
        return isInherits() ? getInherited("renderAsLink", false) : get("renderAsLink", false);
    }

    @DialogField(ranking = 2)
    @DialogFieldSet(title = "Root Item Configuration", namePrefix = ROOT_ITEM_CONFIGURATION_PREFIX)
    public BreadcrumbItemConfiguration getRootItemConfiguration() {
        return getItemConfiguration(ROOT_ITEM_CONFIGURATION_PREFIX);
    }

    @DialogField(ranking = 3)
    @DialogFieldSet(title = "Intermediate Item Configuration", namePrefix = INTERMEDIATE_ITEM_CONFIGURATION_PREFIX)
    public BreadcrumbItemConfiguration getIntermediateItemConfiguration() {
        return getItemConfiguration(INTERMEDIATE_ITEM_CONFIGURATION_PREFIX);
    }

    @DialogField(ranking = 4)
    @DialogFieldSet(title = "Current Item Configuration", namePrefix = CURRENT_ITEM_CONFIGURATION_PREFIX)
    public BreadcrumbItemConfiguration getCurrentItemConfiguration() {
        return getItemConfiguration(CURRENT_ITEM_CONFIGURATION_PREFIX);
    }

    protected BreadcrumbItemConfiguration getItemConfiguration(String prefix) {
        return new BreadcrumbItemConfiguration(this, prefix, isInherits());
    }

    protected boolean isInherits() {
        return false;
    }
}