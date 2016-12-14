package com.icfolson.aem.harbor.core.components.content.responsivecontainer;

import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.Option;
import com.citytechinc.cq.component.annotations.widgets.Selection;
import com.google.common.base.Optional;
import com.icfolson.aem.harbor.api.constants.devices.DeviceTypes;
import com.icfolson.aem.harbor.api.domain.devices.DeviceType;
import com.icfolson.aem.harbor.core.constants.groups.ComponentGroups;
import com.icfolson.aem.harbor.core.util.bootstrap.BootstrapUtils;
import com.icfolson.aem.library.core.components.AbstractComponent;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

import java.util.List;
import java.util.stream.Collectors;

@Component(value = "Responsive Container",
    description = "A content container which can be shown or hidden at various device size breakpoints.",
    group = ComponentGroups.HARBOR_SCAFFOLDING)
@Model(adaptables = Resource.class)
public class ResponsiveContainer extends AbstractComponent {

    public static final String EXTRA_SMALL_DESCRIPTION = "Extra Small devices such as smart phones.";

    public static final String SMALL_DESCRIPTION = "Small devices such as tablets.";

    public static final String MEDIUM_DESCRIPTION = "Medium devices such as large tablets or standard desktops.";

    public static final String LARGE_DESCRIPTION = "Large devices such as large desktops or laptops.";

    @DialogField(fieldLabel = "Hide In",
        fieldDescription = "Device types which the content of the container should be hidden in.")
    @Selection(type = Selection.SELECT, multiple = true, options = {
        @Option(text = "Extra Small", value = DeviceTypes.EXTRA_SMALL, qtip = EXTRA_SMALL_DESCRIPTION),
        @Option(text = "Small", value = DeviceTypes.SMALL, qtip = SMALL_DESCRIPTION),
        @Option(text = "Medium", value = DeviceTypes.MEDIUM, qtip = MEDIUM_DESCRIPTION),
        @Option(text = "Large", value = DeviceTypes.LARGE, qtip = LARGE_DESCRIPTION) })
    public List<DeviceType> getHiddenInDeviceTypes() {
        final List<String> deviceTypeStrings = getAsList("hiddenInDeviceTypes", String.class);

        return transformDeviceTypeIdentifiers(deviceTypeStrings);
    }

    /**
     * Produces a space delimited list of CSS classes representing the
     * responsive configuration of this container. OOB Bootstrap's Responsive
     * Utility Classes are used. This can be overridden in extending classes by
     * overriding the protected #getHiddenInClassForDeviceType method.
     * <p>
     * http://getbootstrap.com/css/#responsive-utilities-classes
     *
     * @return A list of CSS classes representing the responsive configuration of this container
     */
    public String getResponsiveClasses() {
        return getHiddenInDeviceTypes().stream()
            .map(this :: getHiddenInClassForDeviceType)
            .filter(Optional:: isPresent)
            .map(Optional:: get)
            .collect(Collectors.joining(" "));
    }

    public String getInverseResponsiveClasses() {
        return getHiddenInDeviceTypes().stream()
            .map(this :: getShownInClassForDeviceType)
            .filter(Optional:: isPresent)
            .map(Optional:: get)
            .collect(Collectors.joining(" "));
    }

    public Boolean isHiddenInSomeDevices() {
        return !getHiddenInDeviceTypes().isEmpty();
    }

    protected Optional<String> getHiddenInClassForDeviceType(final DeviceType type) {
        return BootstrapUtils.getHiddenInClassForDeviceType(type);
    }

    protected Optional<String> getShownInClassForDeviceType(final DeviceType type) {
        return BootstrapUtils.getShownInClassForDeviceType(type);
    }

    protected List<DeviceType> transformDeviceTypeIdentifiers(final List<String> typeIdentifiers) {
        return typeIdentifiers.stream()
            .map(DeviceType:: forIdentifier)
            .filter(Optional:: isPresent)
            .map(Optional:: get)
            .collect(Collectors.toList());
    }
}
