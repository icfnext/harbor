package com.icfolson.aem.harbor.core.components.content.text;

import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.Tab;
import com.citytechinc.cq.component.annotations.widgets.DialogFieldSet;
import com.citytechinc.cq.component.annotations.widgets.RichTextEditor;
import com.citytechinc.cq.component.annotations.widgets.rte.Edit;
import com.citytechinc.cq.component.annotations.widgets.rte.FindReplace;
import com.citytechinc.cq.component.annotations.widgets.rte.Format;
import com.citytechinc.cq.component.annotations.widgets.rte.Image;
import com.citytechinc.cq.component.annotations.widgets.rte.Justify;
import com.citytechinc.cq.component.annotations.widgets.rte.Links;
import com.citytechinc.cq.component.annotations.widgets.rte.SpellCheck;
import com.citytechinc.cq.component.annotations.widgets.rte.SubSuperscript;
import com.citytechinc.cq.component.annotations.widgets.rte.Table;
import com.citytechinc.cq.component.annotations.widgets.rte.Undo;
import com.icfolson.aem.harbor.core.components.mixins.classifiable.Classification;
import com.icfolson.aem.harbor.core.util.icon.IconUtils;
import com.icfolson.aem.library.core.components.AbstractComponent;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.Self;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Exposes authoring for a general block of text.
 */
@Component(
    value = "Text",
    inPlaceEditingEditorType = "text",
    inPlaceEditingConfigPath = "../../dialog/items/tabs/items/Content/items/content",
    tabs = {
        @Tab(title = "Content", touchUINodeName = Text.TAB_1_NODE_NAME)
    })
@Model(adaptables = Resource.class)
public class Text extends AbstractComponent {

    public static final String TAB_1_NODE_NAME = "contenttab";

    @DialogField(fieldLabel = "Content", name = "./text", suppressTouchUI = true)
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
    @Inject
    @Named("text")
    @Default(values = "")
    private String content;

    @DialogField(ranking = 10)
    @DialogFieldSet
    @Self
    private Classification classification;

    public String getContent() {
        //TODO: Consider content cleanup such as replacing all &nbsp; which the RTE inserts with spaces as it's incredibly rare that someone actually wants a non-breaking space
        return IconUtils.iconify(content);
    }

    public Classification getClassification() {
        return classification;
    }

}
