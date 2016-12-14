package com.icfolson.aem.harbor.core.util.bootstrap;

import com.google.common.base.Optional;
import com.icfolson.aem.harbor.api.constants.bootstrap.Bootstrap;
import com.icfolson.aem.harbor.api.domain.devices.DeviceType;

public final class BootstrapUtils {

    public static Optional<String> getHiddenInClassForDeviceType(DeviceType deviceType) {
        switch (deviceType) {
            case PHONE:
                return Optional.of(Bootstrap.HIDDEN_XS_CLASS);
            case TABLET:
                return Optional.of(Bootstrap.HIDDEN_SM_CLASS);
            case DESKTOP:
                return Optional.of(Bootstrap.HIDDEN_MD_CLASS);
            case LARGE_DESKTOP:
                return Optional.of(Bootstrap.HIDDEN_LG_CLASS);
            default:
                return Optional.absent();
        }
    }

    public static Optional<String> getShownInClassForDeviceType(DeviceType deviceType) {
        switch (deviceType) {
            case PHONE:
                return Optional.of(Bootstrap.VISIBLE_XS_CLASS);
            case TABLET:
                return Optional.of(Bootstrap.VISIBLE_SM_CLASS);
            case DESKTOP:
                return Optional.of(Bootstrap.VISIBLE_MD_CLASS);
            case LARGE_DESKTOP:
                return Optional.of(Bootstrap.VISIBLE_LG_CLASS);
            default:
                return Optional.absent();
        }
    }

    private BootstrapUtils() {

    }
}
