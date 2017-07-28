package com.icfolson.aem.harbor.core.util;

public final class ComponentUtils {

    private ComponentUtils() {

    }

    public static String sanitizeTextAsDomId(final String text) {
        //TODO: Full implementation
        return text.toLowerCase().replace(" ", "-");
    }

    public static String DomIdForResourcePath(final String resourcePath) {
        return resourcePath.replaceAll("/", "_").replaceAll(":", "__"); //TODO: Use MD5 Hash
    }
}
