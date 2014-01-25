package com.citytechinc.cq.harbor.components.content.responsivecontainer;

import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.Option;
import com.citytechinc.cq.component.annotations.widgets.Selection;
import com.citytechinc.cq.harbor.constants.bootstrap.Bootstrap;
import com.citytechinc.cq.harbor.constants.devices.DeviceTypes;
import com.citytechinc.cq.harbor.domain.devices.DeviceType;
import com.citytechinc.cq.library.components.AbstractComponent;
import com.citytechinc.cq.library.content.request.ComponentRequest;
import com.google.common.base.Joiner;
import com.google.common.base.Optional;
import com.google.common.collect.Lists;

import java.util.List;

@Component( value = "Responsive Container", description =  "A content container which can be shown or hidden at various device size breakpoints." )
public class ResponsiveContainer extends AbstractComponent {

    public static final String EXTRA_SMALL_DESCRIPTION = "Extra Small devices such as smart phones.";
    public static final String SMALL_DESCRIPTION = "Small devices such as tablets.";
    public static final String MEDIUM_DESCRIPTION = "Medium devices such as large tablets or standard desktops.";
    public static final String LARGE_DESCRIPTION = "Large devices such as large desktops or laptops.";

    public ResponsiveContainer(ComponentRequest request) {
        super(request);
    }

    @DialogField( fieldLabel = "Show In", fieldDescription = "Device types which the content of the container should be visible in." )
    @Selection( type = "checkbox", options = {
        @Option( text = "Extra Small", value = DeviceTypes.EXTRA_SMALL, qtip = EXTRA_SMALL_DESCRIPTION ),
        @Option( text = "Small", value = DeviceTypes.SMALL, qtip = SMALL_DESCRIPTION ),
        @Option( text = "Medium", value = DeviceTypes.MEDIUM, qtip = MEDIUM_DESCRIPTION ),
        @Option( text = "Large", value = DeviceTypes.LARGE, qtip = LARGE_DESCRIPTION )
    } )
    public List<DeviceType> getShownInDeviceTypes() {

        List<String> deviceTypeStrings = Lists.newArrayList(get("shownInDeviceTypes", new String[0]));

        return transformDeviceTypeIdentifiers(deviceTypeStrings);

    }

    @DialogField( fieldLabel = "Hide In", fieldDescription = "Device types which the content of the container should be hidden in." )
    @Selection( type = "checkbox", options = {
            @Option( text = "Extra Small", value = DeviceTypes.EXTRA_SMALL, qtip = EXTRA_SMALL_DESCRIPTION ),
            @Option( text = "Small", value = DeviceTypes.SMALL, qtip = SMALL_DESCRIPTION ),
            @Option( text = "Medium", value = DeviceTypes.MEDIUM, qtip = MEDIUM_DESCRIPTION ),
            @Option( text = "Large", value = DeviceTypes.LARGE, qtip = LARGE_DESCRIPTION )
    } )
    public List<DeviceType> getHiddenInDeviceTypes() {

        List<String> deviceTypeStrings = Lists.newArrayList(get("hiddenInDeviceTypes", new String[0]));

        return transformDeviceTypeIdentifiers(deviceTypeStrings);

    }

    /**
     * Produces a space delimited list of CSS classes representing the responsive configuration
     * of this container.  OOB Bootstrap's Responsive Utility Classes are used.  This can be
     * overridden in extending classes by overriding the protected #getHiddenInClassForDeviceType
     * and #getShownInClassForDeviceType methods.
     *
     * {@link "http://getbootstrap.com/css/#responsive-utilities-classes"}
     *
     * @return A list of CSS classes representing the responsive configuration of this container
     */
    public String getResponsiveClasses() {

        List<String> classes = Lists.newArrayList();

        List<DeviceType> shownInDevices = getShownInDeviceTypes();
        List<DeviceType> hiddenInDevices = getHiddenInDeviceTypes();

        for (DeviceType shownInType : shownInDevices) {
            Optional<String> shownInTypeClassOptional = getShownInClassForDeviceType(shownInType);

            if (shownInTypeClassOptional.isPresent()) {
                classes.add(shownInTypeClassOptional.get());
            }
        }

        for (DeviceType hiddenInType : hiddenInDevices) {
            Optional<String> hiddenInTypeClassOptional = getHiddenInClassForDeviceType(hiddenInType);

            if (hiddenInTypeClassOptional.isPresent()) {
                classes.add(hiddenInTypeClassOptional.get());
            }
        }

        return Joiner.on(" ").join(classes);

    }

    protected Optional<String> getHiddenInClassForDeviceType(DeviceType deviceType) {

        switch(deviceType) {
            case PHONE:
                return Optional.fromNullable(Bootstrap.HIDDEN_XS_CLASS);
            case TABLET:
                return Optional.fromNullable(Bootstrap.HIDDEN_SM_CLASS);
            case DESKTOP:
                return Optional.fromNullable(Bootstrap.HIDDEN_MD_CLASS);
            case LARGE_DESKTOP:
                return Optional.fromNullable(Bootstrap.HIDDEN_LG_CLASS);
            default:
                return Optional.absent();
        }

    }

    protected Optional<String> getShownInClassForDeviceType(DeviceType deviceType) {

        switch(deviceType) {
            case PHONE:
                return Optional.fromNullable(Bootstrap.VISIBLE_XS_CLASS);
            case TABLET:
                return Optional.fromNullable(Bootstrap.VISIBLE_SM_CLASS);
            case DESKTOP:
                return Optional.fromNullable(Bootstrap.VISIBLE_MD_CLASS);
            case LARGE_DESKTOP:
                return Optional.fromNullable(Bootstrap.VISIBLE_LG_CLASS);
            default:
                return Optional.absent();
        }

    }

    protected List<DeviceType> transformDeviceTypeIdentifiers(List<String> typeIdentifiers) {

        List<DeviceType> deviceTypes = Lists.newArrayList();

        for (String curDeviceTypeIdentifier : typeIdentifiers) {
            Optional<DeviceType> deviceTypeOptional = DeviceType.forIdentifier(curDeviceTypeIdentifier);

            if (deviceTypeOptional.isPresent()) {
                deviceTypes.add(deviceTypeOptional.get());
            }
        }

        return deviceTypes;

    }

}
