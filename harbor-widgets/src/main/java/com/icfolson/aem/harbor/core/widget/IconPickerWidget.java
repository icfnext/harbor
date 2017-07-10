package com.icfolson.aem.harbor.core.widget;

import com.citytechinc.cq.component.annotations.config.TouchUIWidget;
import com.citytechinc.cq.component.touchuidialog.widget.AbstractTouchUIWidget;
import com.icfolson.aem.harbor.api.annotations.IconPicker;

@TouchUIWidget(annotationClass = IconPicker.class, makerClass = IconPickerWidgetMaker.class,
    resourceType = IconPickerWidget.RESOURCE_TYPE)
public class IconPickerWidget extends AbstractTouchUIWidget {

    public static final String RESOURCE_TYPE = "acs-commons/components/authoring/graphiciconselect";

    public IconPickerWidget(final IconPickerWidgetParameters parameters) {
        super(parameters);
    }
}
