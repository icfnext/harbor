package com.icfolson.aem.harbor.core.widget;

import com.citytechinc.cq.component.dialog.exception.InvalidComponentFieldException;
import com.citytechinc.cq.component.touchuidialog.TouchUIDialogElement;
import com.citytechinc.cq.component.touchuidialog.exceptions.TouchUIDialogGenerationException;
import com.citytechinc.cq.component.touchuidialog.widget.datasource.DataSource;
import com.citytechinc.cq.component.touchuidialog.widget.datasource.DataSourceParameters;
import com.citytechinc.cq.component.touchuidialog.widget.maker.AbstractTouchUIWidgetMaker;
import com.citytechinc.cq.component.touchuidialog.widget.maker.TouchUIWidgetMakerParameters;
import com.icfolson.aem.harbor.api.annotations.IconPicker;

import java.util.Collections;

public class IconPickerWidgetMaker extends AbstractTouchUIWidgetMaker<IconPickerWidgetParameters> {

    private static final String DATASOURCE_RESOURCE_TYPE = "acs-commons/components/utilities/genericlist/datasource";

    public IconPickerWidgetMaker(TouchUIWidgetMakerParameters parameters) {
        super(parameters);
    }

    @Override
    protected TouchUIDialogElement make(final IconPickerWidgetParameters parameters) throws ClassNotFoundException,
        InvalidComponentFieldException, TouchUIDialogGenerationException {
        final IconPicker iconPicker = getAnnotation(IconPicker.class);

        parameters.setContainedElements(Collections.singletonList(getDataSource(iconPicker)));

        return new IconPickerWidget(parameters);
    }

    private DataSource getDataSource(final IconPicker iconPicker) {
        final DataSourceParameters dataSourceParameters = new DataSourceParameters();

        dataSourceParameters.setResourceType(DATASOURCE_RESOURCE_TYPE);
        dataSourceParameters.setAdditionalProperties(Collections.singletonMap("path", iconPicker.path()));

        return new DataSource(dataSourceParameters);
    }
}
