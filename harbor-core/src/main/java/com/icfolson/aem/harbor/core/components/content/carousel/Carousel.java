package com.icfolson.aem.harbor.core.components.content.carousel;

import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.ContentProperty;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.editconfig.ActionConfig;
import com.citytechinc.cq.component.annotations.widgets.NumberField;
import com.citytechinc.cq.component.annotations.widgets.Switch;
import com.icfolson.aem.library.core.components.AbstractComponent;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

import java.util.List;
import java.util.stream.Collectors;

@Component(
    value = "Carousel",
    actions = { "text: Carousel", "edit", "-", "copymove", "delete", "-", "insert" },
    actionConfigs = {
        @ActionConfig(xtype = "tbseparator"),
        @ActionConfig(text = "Add Slide", handler = "function() { Harbor.Components.Carousel.addSlide(this) }") },
    contentAdditionalProperties = { @ContentProperty(name = "dependencies",
        value = "[harbor.components.content.carousel,harbor.bootstrap.carousel]") })
@Model(adaptables = Resource.class)
public class Carousel extends AbstractComponent {

    private List<CarouselSlide> slides;

    public static final String CSS_CLASS = "carousel slide";

    public static final String INDICATORS_CSS_CLASS = "carousel-indicators";

    @DialogField(fieldLabel = "Show Previous and Next Controls", ranking = 1)
    @Switch(offText = "No", onText = "Yes")
    public boolean isShowPreviousAndNextControls() {
        return get("showPreviousAndNextControls", false);
    }

    @DialogField(fieldLabel = "Show Slide Selector Controls", ranking = 2)
    @Switch(offText = "No", onText = "Yes")
    public boolean isShowSlideSelectorControls() {
        return get("showSlideSelectorControls", false);
    }

    @DialogField(fieldLabel = "Interval",
        fieldDescription = "The amount of time to delay between automatically cycling an item.  Defaults to 5000.  If set to 0, automatic cycling will be disabled.",
        ranking = 3)
    @NumberField(allowDecimals = false, allowNegative = false)
    public Integer getInterval() {
        return get("interval", 5000);
    }

    @DialogField(fieldLabel = "Pause on Hover?",
        fieldDescription = "Whether cycling of the carousel should pause on mouse hover.", ranking = 4)
    @Switch(offText = "No", onText = "Yes")
    public boolean isPauseOnHover() {
        return get("pauseOnHover", false);
    }

    @DialogField(fieldLabel = "Wrap?",
        fieldDescription = "Whether the carousel should cycle continuously or have hard stops.", ranking = 5)
    @Switch(offText = "No", onText = "Yes")
    public boolean isWrap() {
        return get("wrap", false);
    }

    @DialogField(fieldLabel = "Keyboard?",
        fieldDescription = "Whether the carousel should react to keyboard events.", ranking = 6)
    @Switch(offText = "No", onText = "Yes")
    public boolean isKeyboard() {
        return get("keyboard", false);
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
