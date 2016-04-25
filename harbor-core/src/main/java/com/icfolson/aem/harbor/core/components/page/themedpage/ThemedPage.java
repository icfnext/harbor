package com.icfolson.aem.harbor.core.components.page.themedpage;

import com.icfolson.aem.harbor.core.components.theme.Theme;
import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.widgets.Selection;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;
import javax.inject.Named;

@Component(value = "Themed Page", editConfig = false, path = "/page/common", name = "global", touchFileName = "touch-theme")
@Model(adaptables = Resource.class)
public class ThemedPage {

    @DialogField(fieldLabel = "Theme", fieldDescription = "Select a theme", name = "./" + Theme.THEME_PROPERTY)
    @Selection(type = Selection.SELECT, optionsProvider = "")
    @Inject @Named("ct:theme") @Default(values = Theme.DEFAULT_THEME)
    private String theme;

    public String getTheme() {
        return theme;
    }

}
