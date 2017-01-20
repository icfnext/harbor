package com.icfolson.aem.harbor.core.components.content.breadcrumb;

import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.widgets.DialogFieldSet;
import com.citytechinc.cq.component.annotations.widgets.Switch;
import com.citytechinc.cq.component.annotations.widgets.TextField;
import com.google.common.collect.Lists;
import com.icfolson.aem.harbor.api.annotations.IconPicker;
import com.icfolson.aem.harbor.api.constants.components.ComponentConstants;
import com.icfolson.aem.harbor.api.lists.rendering.ListRenderingStrategy;
import com.icfolson.aem.harbor.core.components.content.page.TrailPage;
import com.icfolson.aem.library.core.components.AbstractComponent;
import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

import java.util.List;

@Model(adaptables = Resource.class)
public class BreadcrumbItemRenderingStrategy extends AbstractComponent implements ListRenderingStrategy<TrailPage, BreadcrumbTrail> {

    private static final String DEFAULT_DELIMITER = "fa-bootstrap-slash";

    public static final String ROOT_ITEM_CONFIGURATION_PREFIX = "rootItem";

    public static final String INTERMEDIATE_ITEM_CONFIGURATION_PREFIX = "intermediateItem";

    public static final String CURRENT_ITEM_CONFIGURATION_PREFIX = "currentItem";

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

            breadcrumbTrail = new BreadcrumbTrail(getRenderAsLink(), getIconDelimiter(), getHtmlDelimiter(),
                getRootItemConfiguration(), getIntermediateItemConfiguration(), getCurrentItemConfiguration(),
                renderableList);
        }

        return breadcrumbTrail;
    }

    @DialogField(fieldLabel = "Render As Link", ranking = 1)
    @Switch(offText = "No", onText = "Yes")
    public Boolean getRenderAsLink() {
        return isInherits() ? getInherited("renderAsLink", false) : get("renderAsLink", false);
    }

    /**
     * A dialog field which allows the user to choose what delimiter icon they
     * wish to be displayed.
     *
     * @return a string representing the font awesome icon class the user has selected.
     */
    @DialogField(fieldLabel = "Delimiter Icon", ranking = 10)
    @IconPicker(path = ComponentConstants.ICON_DATASOURCE_PATH)
    public String getIconDelimiter() {
        return isInherits() ? getInherited("iconDelimiter", DEFAULT_DELIMITER) : get("iconDelimiter",
            DEFAULT_DELIMITER);
    }

    /**
     * A dialog field which allows the user to enter any HTML string they wish.
     *
     * @return The {@link Breadcrumb} HTML delimiter.
     */
    @DialogField(fieldLabel = "Delimiter HTML", ranking = 20,
        fieldDescription = "Allows for the use of arbitrary HTML as a Breadcrumb Trail Item Delimiter. The delimiter authored in this field will trump the delimiter authored in the icon field.")
    @TextField
    public String getHtmlDelimiter() {
        return isInherits() ? getInherited("htmlDelimiter", StringUtils.EMPTY) : get("htmlDelimiter",
            StringUtils.EMPTY);
    }

    @DialogField(ranking = 30)
    @DialogFieldSet(title = "Root Item Configuration", namePrefix = ROOT_ITEM_CONFIGURATION_PREFIX)
    public BreadcrumbItemConfiguration getRootItemConfiguration() {
        return getItemConfiguration(ROOT_ITEM_CONFIGURATION_PREFIX);
    }

    @DialogField(ranking = 40)
    @DialogFieldSet(title = "Intermediate Item Configuration", namePrefix = INTERMEDIATE_ITEM_CONFIGURATION_PREFIX)
    public BreadcrumbItemConfiguration getIntermediateItemConfiguration() {
        return getItemConfiguration(INTERMEDIATE_ITEM_CONFIGURATION_PREFIX);
    }

    @DialogField(ranking = 50)
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