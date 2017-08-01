package com.icfolson.aem.harbor.api.components.content.calltoaction;

import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.Option;
import com.citytechinc.cq.component.annotations.widgets.PathField;
import com.citytechinc.cq.component.annotations.widgets.Selection;
import com.icfolson.aem.library.api.link.Link;
import com.icfolson.aem.library.core.constants.PathConstants;

public interface CallToAction {

    String LINK_IN_WINDOW = "window";

    String LINK_IN_CURRENT = "current";

    @DialogField(fieldLabel = "Action", fieldDescription = "Select the widget's action upon being clicked", ranking = 4)
    @Selection(type = Selection.SELECT, options = {
            @Option(text = "Open in the Current Window", qtip = "Opens link to specified path in current window.",
                    value = LINK_IN_CURRENT),
            @Option(text = "Open in a New Window/Tab", qtip = "Opens link to specified path in a new window or tab.",
                    value = LINK_IN_WINDOW)
    })
    String getAction();

    @DialogField(fieldLabel = "Link Target", fieldDescription = "URL path this button leads to", ranking = 2)
    @PathField(rootPath = PathConstants.PATH_CONTENT)
    Link getLinkTarget();

    Boolean isOpensInNewWindow();

    Boolean isOpensInCurrentWindow();
}
