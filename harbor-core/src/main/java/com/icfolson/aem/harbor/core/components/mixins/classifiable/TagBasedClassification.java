package com.icfolson.aem.harbor.core.components.mixins.classifiable;

import com.day.cq.tagging.Tag;
import com.day.cq.tagging.TagManager;
import com.icfolson.aem.harbor.api.components.mixins.classifiable.Classification;
import com.icfolson.aem.library.core.components.AbstractComponent;
import com.icfolson.aem.namespace.api.ontology.Properties;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
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
@Model(adaptables = Resource.class)
public class TagBasedClassification extends AbstractComponent implements Classification {

    @Inject
    private TagManager tagManager;

    private List<Tag> classifications;

    /**
     * Produces a list of the Tag based classifications associated with this Resource.
     *
     * @return A list of the Tag based classifications associated with this Resource.
     */
    public List<Tag> getClassifications() {
        if (classifications == null) {
            classifications = getClassificationIdStrings()
                    .stream()
                    .map(currentClassification -> tagManager.resolve(currentClassification))
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
        }

        return classifications;
    }

    @Override
    public List<String> getClassificationNames() {
        return getClassifications()
            .stream()
            .map(Tag::getName)
            .collect(Collectors.toList());
    }

    @Override
    public boolean isClassified() {
        return !getClassifications().isEmpty();
    }

    protected List<String> getClassificationIdStrings() {
        return getAsList(Properties.ICF_OLSON_CLASSIFICATION, String.class);
    }

}
