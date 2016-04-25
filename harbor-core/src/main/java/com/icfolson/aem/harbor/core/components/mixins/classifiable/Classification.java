package com.icfolson.aem.harbor.core.components.mixins.classifiable;

import com.google.common.collect.Lists;
import org.apache.commons.lang.StringUtils;

import com.icfolson.aem.library.core.components.AbstractComponent;
import com.citytechinc.aem.namespace.api.ontology.Properties;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.widgets.TagInputField;
import com.day.cq.tagging.Tag;
import com.day.cq.tagging.TagManager;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Classifications are intended to be used as a mixin Component by including it via a
 * DialogFieldSet annotation.
 *
 * A component which mixes in Classifications is said to be classifiable.  Classifiability affords
 * content authors the opportunity to indicate the type of content they are producing.  Any number of
 * Tags may be associated with a component as classifications.  These can then be used by the component
 * for various purposes.  One common purpose is the rendering of the tag names as CSS classes on the
 * DOM of the classifiable components.
 *
 * It is best practice to classify your content based on type.  For example, "article," "product,"
 * "fruit," etc are good classifications as they indicate the nature of the content.
 * "upper-left-panel," "site-sidebar," etc are not good classifications as they have more to do with the
 * position of the content.
 */
@Model(adaptables = Resource.class)
public class Classification extends AbstractComponent {

    private static final Logger LOG = LoggerFactory.getLogger(Classification.class);

	public static final String CLASSIFICATION_FIELD_LABEL = "Classification";

	private List<Tag> classifications;

    /**
     * Produces a list of the Tag based classifications associated with this Resource.
     *
     * @return A list of the Tag based classifications associated with this Resource.
     */
	@DialogField(fieldLabel = CLASSIFICATION_FIELD_LABEL, name = "./" + Properties.CITYTECH_CLASSIFICATION)
	@TagInputField
	public List<Tag> getClassifications() {

		if (classifications != null) {
			return classifications;
		}

        classifications = Lists.newArrayList();

		TagManager tagManager = this.getResource().getResourceResolver().adaptTo(TagManager.class);

        for (String currentClassification : getClassificationIdStrings()) {
            Tag currentTag = tagManager.resolve(currentClassification);

            if (currentTag != null) {
                classifications.add(currentTag);
            }
            else {
                LOG.warn("Classification Tag " + currentClassification + " associated with classified resource " + getResource().getPath() + " but no such tag exists.");
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

        List<String> nameList = Lists.newArrayList();

        for (Tag currentClassification : getClassifications()) {
            nameList.add(currentClassification.getName());
        }

        return nameList;

	}

    /**
     * Provides a list of the IDs of all the classifications associated with this Resource.  As classifications
     * are tag based these IDs are the AEM Tag IDs.
     *
     * @return A list of the IDs of all the classifications associated with this Resource.
     */
	public List<String> getClassificationIds() {

        List<String> idList = Lists.newArrayList();

		for (Tag currentClassification : getClassifications()) {
            idList.add(currentClassification.getTagID());
        }

        return idList;

	}

    /**
     * Indicates whether any classifications have been associated with this Resource.
     *
     * @return True if one or more classifications is associated with this Resource, false otherwise.
     */
	public boolean getHasClassifications() {

		return !getClassifications().isEmpty();

	}

    protected List<String> getClassificationIdStrings() {
        return getAsList(Properties.CITYTECH_CLASSIFICATION, String.class);
    }

}
