package com.citytechinc.aem.harbor.core.components.content.responsivecontainer;

import java.util.List;

import com.citytechinc.aem.bedrock.api.components.annotations.AutoInstantiate;
import com.citytechinc.aem.bedrock.core.components.AbstractComponent;
import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.Option;
import com.citytechinc.cq.component.annotations.widgets.Selection;
import com.citytechinc.aem.harbor.api.constants.devices.DeviceTypes;
import com.citytechinc.aem.harbor.api.domain.devices.DeviceType;
import com.citytechinc.aem.harbor.core.constants.groups.ComponentGroups;
import com.citytechinc.aem.harbor.core.util.bootstrap.BootstrapUtils;
import com.google.common.base.Joiner;
import com.google.common.base.Optional;
import com.google.common.collect.Lists;

@Component(value = "Responsive Container", description = "A content container which can be shown or hidden at various device size breakpoints.", group = ComponentGroups.HARBOR_SCAFFOLDING)
@AutoInstantiate(instanceName = ResponsiveContainer.INSTANCE_NAME)
public class ResponsiveContainer extends AbstractComponent {

	public static final String INSTANCE_NAME = "responsiveContainer";

	public static final String EXTRA_SMALL_DESCRIPTION = "Extra Small devices such as smart phones.";
	public static final String SMALL_DESCRIPTION = "Small devices such as tablets.";
	public static final String MEDIUM_DESCRIPTION = "Medium devices such as large tablets or standard desktops.";
	public static final String LARGE_DESCRIPTION = "Large devices such as large desktops or laptops.";

	@DialogField(fieldLabel = "Hide In", fieldDescription = "Device types which the content of the container should be hidden in.")
	@Selection(type = "checkbox", options = {
		@Option(text = "Extra Small", value = DeviceTypes.EXTRA_SMALL, qtip = EXTRA_SMALL_DESCRIPTION),
		@Option(text = "Small", value = DeviceTypes.SMALL, qtip = SMALL_DESCRIPTION),
		@Option(text = "Medium", value = DeviceTypes.MEDIUM, qtip = MEDIUM_DESCRIPTION),
		@Option(text = "Large", value = DeviceTypes.LARGE, qtip = LARGE_DESCRIPTION) })
	public List<DeviceType> getHiddenInDeviceTypes() {

		List<String> deviceTypeStrings = Lists.newArrayList(get("hiddenInDeviceTypes", new String[0]));

		return transformDeviceTypeIdentifiers(deviceTypeStrings);

	}

	/**
	 * Produces a space delimited list of CSS classes representing the
	 * responsive configuration of this container. OOB Bootstrap's Responsive
	 * Utility Classes are used. This can be overridden in extending classes by
	 * overriding the protected #getHiddenInClassForDeviceType method.
	 *
	 * {@link "http://getbootstrap.com/css/#responsive-utilities-classes"}
	 *
	 * @return A list of CSS classes representing the responsive configuration
	 *         of this container
	 */
	public String getResponsiveClasses() {

		List<String> classes = Lists.newArrayList();

		List<DeviceType> hiddenInDevices = getHiddenInDeviceTypes();

		for (DeviceType hiddenInType : hiddenInDevices) {
			Optional<String> hiddenInTypeClassOptional = getHiddenInClassForDeviceType(hiddenInType);

			if (hiddenInTypeClassOptional.isPresent()) {
				classes.add(hiddenInTypeClassOptional.get());
			}
		}

		return Joiner.on(" ").join(classes);

	}

	public String getInverseResponsiveClasses() {

		List<String> classes = Lists.newArrayList();

		List<DeviceType> hiddenInDevices = getHiddenInDeviceTypes();

		for (DeviceType hiddenInType : hiddenInDevices) {
			Optional<String> shownInTypeClassOptional = getShownInClassForDeviceType(hiddenInType);

			if (shownInTypeClassOptional.isPresent()) {
				classes.add(shownInTypeClassOptional.get());
			}
		}

		return Joiner.on(" ").join(classes);

	}

	public Boolean getHiddenInSomeDevices() {
		return !getHiddenInDeviceTypes().isEmpty();
	}

	protected Optional<String> getHiddenInClassForDeviceType(DeviceType type) {
		return BootstrapUtils.getHiddenInClassForDeviceType(type);
	}

	protected Optional<String> getShownInClassForDeviceType(DeviceType type) {
		return BootstrapUtils.getShownInClassForDeviceType(type);
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
