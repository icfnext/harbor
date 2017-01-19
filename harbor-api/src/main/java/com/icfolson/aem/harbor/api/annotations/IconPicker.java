package com.icfolson.aem.harbor.api.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.CLASS)
@Target({ ElementType.METHOD, ElementType.FIELD })
public @interface IconPicker {

    /**
     * Path to icon picker datasource.  @see <a href="http://adobe-consulting-services.github.io/acs-aem-commons/features/icon-picker.html">ACS
     * AEM Commons Icon Picker</a>
     *
     * @return path to a generic list containing the icons for selection
     */
    String path() default "";
}
