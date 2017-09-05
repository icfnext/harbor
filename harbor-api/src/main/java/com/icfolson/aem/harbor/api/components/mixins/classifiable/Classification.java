package com.icfolson.aem.harbor.api.components.mixins.classifiable;

import com.day.cq.tagging.Tag;

import java.util.List;

/**
 * Classifications are intended to be used as a mixin Component by including it via a
 * DialogFieldSet annotation.
 * <p>
 * A component which mixes in Classifications is said to be classifiable.  Classifiability affords
 * content authors the opportunity to indicate the type of content they are producing.  Any number of
 * Tags may be associated with a component as classifications.  These can then be used by the component
 * for various purposes.  One common purpose is the rendering of the tag names as CSS classes on the
 * DOM of the classifiable components.
 * <p>
 * It is best practice to classify your content based on type.  For example, "article," "product,"
 * "fruit," etc are good classifications as they indicate the nature of the content.
 * "upper-left-panel," "site-sidebar," etc are not good classifications as they have more to do with the
 * position of the content.
 */
public interface Classification {

    /**
     * Produces a list of the Tag based classifications associated with this Resource.
     *
     * @return A list of the Tag based classifications associated with this Resource.
     */
    List<Tag> getClassifications();

    /**
     * Produces a space delimited String of the names of all the classifications associated with this Resource.
     * This is intended to be used for presenting classifications in contexts such as JSP.
     *
     * @return A space delimited String of the names of the classifications associated with this Resource.
     */
    String getClassNames();

    /**
     * Provides a list of the names of all the classifications associated with this Resource.
     *
     * @return A list of the names of all the classifications associated with this Resource.
     */
    List<String> getClassificationNames();

    /**
     * Provides a list of the IDs of all the classifications associated with this Resource.  As classifications
     * are tag based these IDs are the AEM Tag IDs.
     *
     * @return A list of the IDs of all the classifications associated with this Resource.
     */
    List<String> getClassificationIds();

    /**
     * Indicates whether any classifications have been associated with this Resource.
     *
     * @return True if one or more classifications is associated with this Resource, false otherwise.
     */
    boolean isHasClassifications();
}
