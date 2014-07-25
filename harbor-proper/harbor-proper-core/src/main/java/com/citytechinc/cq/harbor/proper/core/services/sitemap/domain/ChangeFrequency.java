package com.citytechinc.cq.harbor.proper.core.services.sitemap.domain;

import org.codehaus.groovy.util.ArrayIterator;

import static org.apache.commons.lang.StringUtils.isEmpty;

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
        final StringBuffer resultBuffer = new StringBuffer();
        final ArrayIterator arrayIterator = new ArrayIterator(ChangeFrequency.values());

        while(arrayIterator.hasNext()) {
            resultBuffer.append(arrayIterator.next());

            if(arrayIterator.hasNext()) resultBuffer.append(", ");
        }

        return resultBuffer.toString();
    }

}
