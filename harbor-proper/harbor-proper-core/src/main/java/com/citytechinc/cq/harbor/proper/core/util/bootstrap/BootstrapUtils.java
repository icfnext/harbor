package com.citytechinc.cq.harbor.proper.core.util.bootstrap;

import com.citytechinc.cq.harbor.proper.api.constants.bootstrap.Bootstrap;
import com.citytechinc.cq.harbor.proper.api.domain.devices.DeviceType;
import com.google.common.base.Optional;

public class BootstrapUtils {

    public static Optional<String> getHiddenInClassForDeviceType(DeviceType deviceType) {

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

    public static Optional<String> getShownInClassForDeviceType(DeviceType deviceType) {

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

}
