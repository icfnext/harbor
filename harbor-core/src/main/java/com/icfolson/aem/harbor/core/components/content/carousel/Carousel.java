package com.icfolson.aem.harbor.core.components.content.carousel;

import com.icfolson.aem.library.api.components.annotations.AutoInstantiate;
import com.icfolson.aem.library.api.node.ComponentNode;
import com.icfolson.aem.library.api.page.PageDecorator;
import com.icfolson.aem.library.core.components.AbstractComponent;
import com.icfolson.aem.harbor.core.util.ComponentUtils;
import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.ContentProperty;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.editconfig.ActionConfig;
import com.citytechinc.cq.component.annotations.widgets.Switch;
import com.google.common.collect.Lists;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;
import java.util.List;

@Component(
        value = "Carousel",
        name = "contentcarousel",
        actions = { "text: Carousel", "edit", "-", "copymove", "delete", "-", "insert" },
        actionConfigs = {
            @ActionConfig(xtype = "tbseparator"),
            @ActionConfig(text = "Add Slide", handler = "function(){Harbor.Components.Carousel.addSlide(this)}") },
        contentAdditionalProperties = { @ContentProperty(name = "dependencies", value = "[harbor.components.content.carousel,harbor.bootstrap.carousel]") } )
@AutoInstantiate(instanceName = "carousel")
@Model(adaptables = Resource.class)
public class Carousel extends AbstractComponent {

    private List<CarouselSlide> slides;

    @Inject
    private PageDecorator currentPage;

    public static final String CSS_CLASS = "carousel slide";
    public static final String INDICATORS_CSS_CLASS = "carousel-indicators";

    @DialogField(fieldLabel = "Show Previous and Next Controls")
    @Switch(offText = "No", onText = "Yes")
    public boolean isShowPreviousAndNextControls() {

        return get("showPreviousAndNextControls", false);

    }

    @DialogField(fieldLabel = "Show Slide Selector Controls")
    @Switch(offText = "No", onText = "Yes")
    public boolean isShowSlideSelectorControls() {

        return get("showSlideSelectorControls", false);

    }

    public List<CarouselSlide> getSlides() {

        if (slides == null) {
            slides = Lists.newArrayList();

            for (ComponentNode currentSlideNode : getComponentNodes()) {
                slides.add(currentSlideNode.getResource().adaptTo(CarouselSlide.class));
            }
        }

        return slides;

    }

    public String getCssClass() {
        return CSS_CLASS;
    }

    public String getIndicatorsCssClass() {
        return INDICATORS_CSS_CLASS;
    }

    public String getUniqueIdentifier() {
        return ComponentUtils.getUniqueIdentifierForResourceInPage(currentPage, getResource());
    }

}
