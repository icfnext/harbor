package com.icfolson.aem.harbor.core.components.content.carousel;

import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.ContentProperty;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.editconfig.ActionConfig;
import com.citytechinc.cq.component.annotations.widgets.Switch;
import com.icfolson.aem.library.api.components.annotations.AutoInstantiate;
import com.icfolson.aem.library.core.components.AbstractComponent;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

import java.util.List;
import java.util.stream.Collectors;

@Component(
    value = "Carousel",
    name = "contentcarousel",
    actions = { "text: Carousel", "edit", "-", "copymove", "delete", "-", "insert" },
    actionConfigs = {
        @ActionConfig(xtype = "tbseparator"),
        @ActionConfig(text = "Add Slide", handler = "function(){Harbor.Components.Carousel.addSlide(this)}") },
    contentAdditionalProperties = { @ContentProperty(name = "dependencies",
        value = "[harbor.components.content.carousel,harbor.bootstrap.carousel]") })
@AutoInstantiate(instanceName = "carousel")
@Model(adaptables = Resource.class)
public class Carousel extends AbstractComponent {

    private List<CarouselSlide> slides;

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
            slides = getComponentNodes()
                .stream()
                .map(componentNode -> componentNode.getResource().adaptTo(CarouselSlide.class))
                .collect(Collectors.toList());
        }

        return slides;
    }

    public String getCssClass() {
        return CSS_CLASS;
    }

    public String getIndicatorsCssClass() {
        return INDICATORS_CSS_CLASS;
    }
}
