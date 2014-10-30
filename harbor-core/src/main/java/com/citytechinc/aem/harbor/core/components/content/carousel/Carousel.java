package com.citytechinc.aem.harbor.core.components.content.carousel;

import com.citytechinc.aem.bedrock.api.components.annotations.AutoInstantiate;
import com.citytechinc.aem.bedrock.api.node.ComponentNode;
import com.citytechinc.aem.bedrock.core.components.AbstractComponent;
import com.citytechinc.aem.harbor.core.util.ComponentUtils;
import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.ContentProperty;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.Option;
import com.citytechinc.cq.component.annotations.editconfig.ActionConfig;
import com.citytechinc.cq.component.annotations.widgets.Selection;
import com.google.common.collect.Lists;

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
public class Carousel extends AbstractComponent {

    private List<CarouselSlide> slides;

    public static final String CSS_CLASS = "carousel slide";
    public static final String INDICATORS_CSS_CLASS = "carousel-indicators";

    @DialogField(fieldLabel = "Show Previous and Next Controls")
    @Selection(type = Selection.CHECKBOX, options = {@Option(value = "true")})
    public boolean isShowPreviousAndNextControls() {

        return get("showPreviousAndNextControls", false);

    }

    @DialogField(fieldLabel = "Show Slide Selector Controls")
    @Selection(type = Selection.CHECKBOX, options = {@Option(value = "true")})
    public boolean isShowSlideSelectorControls() {

        return get("showSlideSelectorControls", false);

    }

    public List<CarouselSlide> getSlides() {

        if (slides == null) {
            slides = Lists.newArrayList();

            for (ComponentNode currentSlideNode : getComponentNodes()) {
                slides.add(getComponent(currentSlideNode, CarouselSlide.class));
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
        return ComponentUtils.getUniqueIdentifierForResourceInPage(getCurrentPage(), getResource());
    }

}