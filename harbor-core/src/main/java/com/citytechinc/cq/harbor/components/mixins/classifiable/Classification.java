package com.citytechinc.cq.harbor.components.mixins.classifiable;


import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.widgets.TagInputField;
import com.citytechinc.cq.harbor.constants.ontology.Properties;
import com.citytechinc.cq.library.components.AbstractComponent;
import com.citytechinc.cq.library.content.request.ComponentRequest;
import com.day.cq.tagging.Tag;
import com.day.cq.tagging.TagManager;
import com.google.common.base.Optional;
import org.apache.commons.lang3.StringUtils;

public class Classification extends AbstractComponent {

    public static final String CLASSIFICATION_FIELD_LABEL = "Classification";

    public Classification(ComponentRequest request) {
        super(request);
    }

    @DialogField(fieldLabel = CLASSIFICATION_FIELD_LABEL, name = "./" + Properties.HARBOR_CLASSIFICATION)
    @TagInputField
    public Optional<Tag> getClassification() {

        TagManager tagManager = this.getResource().getResourceResolver().adaptTo(TagManager.class);

        String tag = get(Properties.HARBOR_CLASSIFICATION, StringUtils.EMPTY);

        if (StringUtils.isNotEmpty(tag)) {
            return Optional.fromNullable(tagManager.resolve(tag));
        }

        return Optional.absent();

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

}
