package com.citytechinc.aem.harbor.core.components.content.text;

import com.citytechinc.aem.bedrock.api.components.annotations.AutoInstantiate;
import com.citytechinc.aem.bedrock.core.components.AbstractComponent;
import com.citytechinc.aem.harbor.core.components.mixins.classifiable.Classification;
import com.citytechinc.aem.harbor.core.components.mixins.classifiable.InheritedClassification;
import com.citytechinc.aem.harbor.core.util.icon.IconUtils;
import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.ContentProperty;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.widgets.DialogFieldSet;
import com.citytechinc.cq.component.annotations.widgets.RichTextEditor;
import com.citytechinc.cq.component.annotations.widgets.rte.*;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

@Component("Text")
@AutoInstantiate(instanceName = "textcomponent")
@Model(adaptables = Resource.class)
public class Text extends AbstractComponent {

    private Classification classification;

    @DialogField(fieldLabel = "Content")
    @RichTextEditor(
            edit = @Edit,
            findreplace = @FindReplace,
            format = @Format,
            image = @Image,
            justify = @Justify,
            links = @Links,
            spellcheck = @SpellCheck,
            subsuperscript = @SubSuperscript,
            table = @Table,
            undo = @Undo
    )
    public String getContent() {
        //TODO: Consider content cleanup such as replacing all &nbsp; which the RTE inserts with spaces as it's incredibly rare that someone actually wants a non-breaking space
        return IconUtils.iconify(get("content", "Enter Text"));
    }

    @DialogField(ranking = 10)
    @DialogFieldSet
    public Classification getClassification() {
        if (classification == null) {
            classification = getResource().adaptTo(Classification.class);
        }

        return classification;
    }

}
