package com.icfolson.aem.harbor.core.components.mixins.classifiable;

import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.widgets.TagInputField;
import com.day.cq.tagging.Tag;
import com.day.cq.tagging.TagManager;
import com.google.common.collect.Lists;
import com.icfolson.aem.harbor.api.components.mixins.classifiable.Classification;
import com.icfolson.aem.library.core.components.AbstractComponent;
import com.icfolson.aem.namespace.api.ontology.Properties;
import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

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
@Model(adaptables = Resource.class, adapters = Classification.class)
public class DefaultClassification extends AbstractComponent implements Classification {

    private static final Logger LOG = LoggerFactory.getLogger(DefaultClassification.class);

    public static final String CLASSIFICATION_FIELD_LABEL = "Classification";

    @Inject
    private TagManager tagManager;

    private List<Tag> classifications;

    /**
     * Produces a list of the Tag based classifications associated with this Resource.
     *
     * @return A list of the Tag based classifications associated with this Resource.
     */
    @DialogField(fieldLabel = CLASSIFICATION_FIELD_LABEL, name = "./" + Properties.ICF_OLSON_CLASSIFICATION)
    @TagInputField
    public List<Tag> getClassifications() {
        if (classifications == null) {
            classifications = Lists.newArrayList();

            for (String currentClassification : getClassificationIdStrings()) {
                final Tag currentTag = tagManager.resolve(currentClassification);

                if (currentTag != null) {
                    classifications.add(currentTag);
                } else {
                    LOG.warn("Classification Tag {} associated with classified resource {} but no such tag exists.",
                        currentClassification, getResource().getPath());
                }
            }
        }

        return classifications;
    }

    /**
     * Produces a space delimited String of the names of all the classifications associated with this Resource.
     * This is intended to be used for presenting classifications in contexts such as JSP.
     *
     * @return A space delimited String of the names of the classifications associated with this Resource.
     */
    public String getClassNames() {
        return StringUtils.join(getClassificationNames(), " ");
    }

    /**
     * Provides a list of the names of all the classifications associated with this Resource.
     *
     * @return A list of the names of all the classifications associated with this Resource.
     */
    public List<String> getClassificationNames() {
        return getClassifications()
            .stream()
            .map(Tag:: getName)
            .collect(Collectors.toList());
    }

    /**
     * Provides a list of the IDs of all the classifications associated with this Resource.  As classifications
     * are tag based these IDs are the AEM Tag IDs.
     *
     * @return A list of the IDs of all the classifications associated with this Resource.
     */
    public List<String> getClassificationIds() {
        return getClassifications()
            .stream()
            .map(Tag:: getTagID)
            .collect(Collectors.toList());
    }

    /**
     * Indicates whether any classifications have been associated with this Resource.
     *
     * @return True if one or more classifications is associated with this Resource, false otherwise.
     */
    public boolean isHasClassifications() {
        return !getClassifications().isEmpty();
    }

    protected List<String> getClassificationIdStrings() {
        return getAsList(Properties.ICF_OLSON_CLASSIFICATION, String.class);
    }
}
