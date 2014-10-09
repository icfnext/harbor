package com.citytechinc.aem.harbor.core.util.icon;

public class IconUtils {

    public static final String TEMPLATE_START = "{{icon ";
    public static final int TEMPLATE_START_LENGTH = TEMPLATE_START.length();
    public static final String TEMPLATE_END = "}}";
    public static final int TEMPLATE_END_LENGTH = TEMPLATE_END.length();

    //TODO: This is a quick and dirty implementation - I'm pretty sure this can be redone more elegantly, probably with regular expression replacements
    public static String iconify(String raw) {

        int priorTemplateEnd = 0;
        int currentTemplateStart = raw.indexOf(TEMPLATE_START);

        StringBuilder iconifiedStringBuilder = new StringBuilder();

        while (currentTemplateStart != -1) {
            iconifiedStringBuilder.append(raw.substring(priorTemplateEnd, currentTemplateStart));

            priorTemplateEnd = raw.indexOf(TEMPLATE_END, currentTemplateStart + TEMPLATE_START_LENGTH);

            if (priorTemplateEnd != -1) {
                iconifiedStringBuilder.append(iconifyIndividual(raw.substring(currentTemplateStart + TEMPLATE_START_LENGTH, priorTemplateEnd)));
            }

            priorTemplateEnd += TEMPLATE_END_LENGTH;
            currentTemplateStart = raw.indexOf(raw, priorTemplateEnd);
        }

        iconifiedStringBuilder.append(raw.substring(priorTemplateEnd));

        return iconifiedStringBuilder.toString();

    }

    //TODO: Beef up
    private static String iconifyIndividual(String raw) {
        StringBuilder iconStringBuilder = new StringBuilder();

        iconStringBuilder.append("<i class=\"fa ").
            append(raw).
            append("\"></i>");

        return iconStringBuilder.toString();

    }

}
