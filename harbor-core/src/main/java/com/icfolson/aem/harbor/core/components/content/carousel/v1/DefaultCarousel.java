package com.icfolson.aem.harbor.core.components.content.carousel.v1;

import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.ContentProperty;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.editconfig.ActionConfig;
import com.citytechinc.cq.component.annotations.widgets.NumberField;
import com.citytechinc.cq.component.annotations.widgets.Switch;
import com.icfolson.aem.harbor.api.components.content.carousel.Carousel;
import com.icfolson.aem.harbor.api.components.content.carousel.CarouselSlide;
import com.icfolson.aem.library.api.node.ComponentNode;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@Component(
    value = "Carousel (v1)",
    name = "carousel/v1/carousel",
    actions = { "text: Carousel (v1)", "edit", "-", "copymove", "delete", "-", "insert" },
    isContainer = true,
    actionConfigs = {
        @ActionConfig(xtype = "tbseparator"),
        @ActionConfig(text = "Add Slide", handler = "function() { Harbor.Components.Carousel.v1.Carousel.addSlide(this, '" + DefaultCarouselSlide.RESOURCE_TYPE + "') }") })
@Model(adaptables = Resource.class, adapters = Carousel.class, resourceType = DefaultCarousel.RESOURCE_TYPE)
public class DefaultCarousel implements Carousel {

    @Inject
    private ComponentNode componentNode;

    private List<CarouselSlide> slides;

    public static final String RESOURCE_TYPE = "harbor/components/content/carousel/v1/carousel";

    public static final String CSS_CLASS = "carousel slide";

    public static final String INDICATORS_CSS_CLASS = "carousel-indicators";

    @DialogField(fieldLabel = "Show Previous and Next Controls", ranking = 1)
    @Switch(offText = "No", onText = "Yes")
    @Inject
    @Default(booleanValues = false)
    private boolean showPreviousAndNextControls;

    @DialogField(fieldLabel = "Show Slide Selector Controls", ranking = 2)
    @Switch(offText = "No", onText = "Yes")
    @Inject
    @Default(booleanValues = false)
    private boolean showSlideSelectorControls;

    @DialogField(fieldLabel = "Interval",
        fieldDescription = "The amount of time in milliseconds to delay between automatically cycling an item.  Defaults to 5000 (5 seconds).  If set to 0, automatic cycling will be disabled.", value = "5000", ranking = 3)
    @NumberField(allowDecimals = false, allowNegative = false)
    @Inject
    @Default(intValues = 5000)
    private Integer interval;

    @DialogField(fieldLabel = "Pause on Hover?",
        fieldDescription = "Whether cycling of the carousel should pause on mouse hover.", ranking = 4)
    @Switch(offText = "No", onText = "Yes")
    @Inject
    @Default(booleanValues = false)
    private boolean pauseOnHover;

    @DialogField(fieldLabel = "Wrap?",
        fieldDescription = "Whether the carousel should cycle continuously or have hard stops.", ranking = 5)
    @Switch(offText = "No", onText = "Yes")
    @Inject
    @Default(booleanValues = false)
    private boolean wrap;

    @DialogField(fieldLabel = "Keyboard?",
        fieldDescription = "Whether the carousel should react to keyboard events.", ranking = 6)
    @Switch(offText = "No", onText = "Yes")
    @Inject
    @Default(booleanValues = false)
    private boolean keyboard;

    public boolean isShowPreviousAndNextControls() {
        return showPreviousAndNextControls;
    }

    public boolean isShowSlideSelectorControls() {
        return showSlideSelectorControls;
    }

    public Integer getInterval() {
        return interval;
    }

    public boolean isPauseOnHover() {
        return pauseOnHover;
    }

    public boolean isWrap() {
        return wrap;
    }

    public boolean isKeyboard() {
        return keyboard;
    }

    public List<CarouselSlide> getSlides() {
        if (slides == null) {
            slides = componentNode.getComponentNodes()
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
