package com.citytechinc.aem.harbor.core.components.content.text;

import com.citytechinc.aem.bedrock.api.components.annotations.AutoInstantiate;
import com.citytechinc.aem.bedrock.core.components.AbstractComponent;
import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.widgets.RichTextEditor;
import com.citytechinc.cq.component.annotations.widgets.rte.*;

@Component("Text")
@AutoInstantiate(instanceName = "textcomponent")
public class Text extends AbstractComponent {

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
        return get("content", "Enter Text");
    }

}
