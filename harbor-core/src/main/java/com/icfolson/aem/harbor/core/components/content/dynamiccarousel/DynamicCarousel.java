package com.icfolson.aem.harbor.core.components.content.dynamiccarousel;

import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.ContentProperty;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.editconfig.ActionConfig;
import com.citytechinc.cq.component.annotations.editconfig.ActionConfigProperty;
import com.citytechinc.cq.component.annotations.widgets.NumberField;
import com.citytechinc.cq.component.annotations.widgets.Switch;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;

@Component(
        value = "Dynamic Carousel",
        actions = { "text: Dynamic Carousel", "edit", "-", "copymove", "delete", "-", "insert" },
        isContainer = true,
        actionConfigs = {
                @ActionConfig(xtype = "tbseparator"),
                @ActionConfig(text = "Add Slide",
                        handler = "function() { Harbor.Components.DynamicCarousel.addSlide( this, '" + "/apps/" + NewSlide.RESOURCE_TYPE + "/" + NewSlide.DIALOG_FILE_NAME + "' ) }",
                        additionalProperties = {
                                @ActionConfigProperty(name = "icon", value = "coral-Icon--experienceAdd")
                        } ) } )
@Model(adaptables = Resource.class)
public class DynamicCarousel {

    public static final String CSS_CLASS = "carousel slide";

    public static final String INDICATORS_CSS_CLASS = "carousel-indicators";

    public static final String SLIDE_RESOURCE_TYPE = "harbor/components/content/dynamiccarousel/slide";

    @Inject @Default(booleanValues = true)
    private boolean showPreviousAndNextControls;

    @Inject @Default(booleanValues = true)
    private boolean showSlideSelectorControls;

    @Inject @Default(intValues = 5000)
    private Integer interval;

    @Inject @Default(booleanValues = false)
    private boolean pauseOnHover;

    @Inject @Default(booleanValues = true)
    private boolean wrap;

    @Inject @Default(booleanValues = false)
    private boolean keyboard;

    @Inject
    private Resource resource;

    @DialogField(fieldLabel = "Show Previous and Next Controls", ranking = 1)
    @Switch(offText = "No", onText = "Yes")
    public boolean isShowPreviousAndNextControls() {
        return showPreviousAndNextControls;
    }

    @DialogField(fieldLabel = "Show Slide Selector Controls", ranking = 2)
    @Switch(offText = "No", onText = "Yes")
    public boolean isShowSlideSelectorControls() {
        return showSlideSelectorControls;
    }

    @DialogField(fieldLabel = "Interval",
            fieldDescription = "The amount of time to delay between automatically cycling an item.  Defaults to 5000.  If set to 0, automatic cycling will be disabled.", value = "5000", ranking = 3)
    @NumberField(allowDecimals = false, allowNegative = false)
    public Integer getInterval() {
        return interval;
    }

    @DialogField(fieldLabel = "Pause on Hover?",
            fieldDescription = "Whether cycling of the carousel should pause on mouse hover.", ranking = 4)
    @Switch(offText = "No", onText = "Yes")
    public boolean isPauseOnHover() {
        return pauseOnHover;
    }

    @DialogField(fieldLabel = "Wrap?",
            fieldDescription = "Whether the carousel should cycle continuously or have hard stops.", ranking = 5)
    @Switch(offText = "No", onText = "Yes")
    public boolean isWrap() {
        return wrap;
    }

    @DialogField(fieldLabel = "Keyboard?",
            fieldDescription = "Whether the carousel should react to keyboard events.", ranking = 6)
    @Switch(offText = "No", onText = "Yes")
    public boolean isKeyboard() {
        return keyboard;
    }

    public Iterable<Resource> getSlides() {
        return resource.getChildren();
    }

    public String getCssClass() {
        return CSS_CLASS;
    }

    public String getIndicatorsCssClass() {
        return INDICATORS_CSS_CLASS;
    }

}
