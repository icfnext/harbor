package com.citytechinc.aem.harbor.core.domain.sitemap;

import static org.apache.commons.lang.StringUtils.isEmpty;
import static org.apache.commons.lang.StringUtils.join;

public enum ChangeFrequency {
    always, hourly, daily, weekly, monthly, yearly, never;

    public static boolean contains(final String value) {
        if(isEmpty(value)) return false;

        for(final ChangeFrequency changeFrequency : values()) {
            if(changeFrequency.name().equalsIgnoreCase(value)) {
                return true;
            }else {
                continue;
            }
        }

        return false;
    }

    public static String valuesString() {
        return join(ChangeFrequency.values(), ',');
    }

}
