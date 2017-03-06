package com.icfolson.aem.harbor.core.util.icon;

public final class IconUtils {

    public static final String TEMPLATE_START = "{{icon ";

    public static final int TEMPLATE_START_LENGTH = TEMPLATE_START.length();

    public static final String TEMPLATE_END = "}}";

    public static final int TEMPLATE_END_LENGTH = TEMPLATE_END.length();

    //TODO: This is a quick and dirty implementation - I'm pretty sure this can be redone more elegantly, probably with regular expression replacements
    public static String iconify(final String raw) {
        int priorTemplateEnd = 0;
        int currentTemplateStart = raw.indexOf(TEMPLATE_START);

        final StringBuilder builder = new StringBuilder();

        while (currentTemplateStart != -1) {
            builder.append(raw.substring(priorTemplateEnd, currentTemplateStart));

            priorTemplateEnd = raw.indexOf(TEMPLATE_END, currentTemplateStart + TEMPLATE_START_LENGTH);

            if (priorTemplateEnd != -1) {
                builder.append(
                    iconifyIndividual(raw.substring(currentTemplateStart + TEMPLATE_START_LENGTH, priorTemplateEnd)));
            }

            priorTemplateEnd += TEMPLATE_END_LENGTH;
            currentTemplateStart = raw.indexOf(TEMPLATE_START, priorTemplateEnd);
        }

        builder.append(raw.substring(priorTemplateEnd));

        return builder.toString();
    }

    //TODO: Beef up
    private static String iconifyIndividual(final String raw) {
        return new StringBuilder()
            .append("<i class=\"fa ")
            .append(raw)
            .append("\" aria-hidden=\"true\"></i>").toString();
    }

    private IconUtils() {

    }
}
