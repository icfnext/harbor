package com.citytechinc.aem.harbor.core.components.mixins.classifiable;

import org.apache.commons.lang.StringUtils;

import com.citytechinc.aem.bedrock.core.components.AbstractComponent;
import com.citytechinc.aem.namespace.api.ontology.Properties;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.widgets.TagInputField;
import com.day.cq.tagging.Tag;
import com.day.cq.tagging.TagManager;
import com.google.common.base.Optional;

public class Classification extends AbstractComponent {

	public static final String CLASSIFICATION_FIELD_LABEL = "Classification";

	private Optional<Tag> classification;

	@DialogField(fieldLabel = CLASSIFICATION_FIELD_LABEL, name = "./" + Properties.CITYTECH_CLASSIFICATION)
	@TagInputField
	public Optional<Tag> getClassification() {

		if (classification != null) {
			return classification;
		}

		TagManager tagManager = this.getResource().getResourceResolver().adaptTo(TagManager.class);

		String tag = get(Properties.CITYTECH_CLASSIFICATION, StringUtils.EMPTY);

		if (StringUtils.isNotEmpty(tag)) {
			classification = Optional.fromNullable(tagManager.resolve(tag));
		} else {
			classification = Optional.absent();
		}

		return classification;

	}

	public String getClassificationName() {

		Optional<Tag> classification = getClassification();

		if (classification.isPresent()) {
			return classification.get().getName();
		}

		return StringUtils.EMPTY;

	}

	public String getClassificationId() {

		Optional<Tag> classification = getClassification();

		if (classification.isPresent()) {
			return classification.get().getTagID();
		}

		return StringUtils.EMPTY;

	}

	public boolean getHasClassification() {

		return getClassification().isPresent();

	}

}
