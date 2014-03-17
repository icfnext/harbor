package com.citytechinc.cq.harbor.components.content.calltoaction;

import com.citytechinc.cq.component.annotations.*;
import com.citytechinc.cq.component.annotations.widgets.PathField;
import com.citytechinc.cq.component.annotations.widgets.Selection;
import com.citytechinc.cq.harbor.constants.bootstrap.Bootstrap;
import com.citytechinc.cq.library.components.AbstractComponent;
import com.citytechinc.cq.library.content.request.ComponentRequest;

/**
 *
 */
@Component(value = "Call to Action", group = "Harbor", name = "calltoaction",
		contentAdditionalProperties = {
				@ContentProperty(name="dependencies", value="harbor.components.content.calltoaction")
		}
)
public class CallToAction extends AbstractComponent {

    private static final String TEXT_PROPERTY = "text";
    private static final String SIZE_PROPERTY = "size";
    private static final String STYLE_PROPERTY = "style";
	private static final String ACTION_PROPERTY = "action";

	private final String OPEN_MODAL = "openModal";
	private final String LINK_IN_WINDOW = "newWindow";
	private final String LINK_IN_CURRENT = "openModal";

    @DialogField(fieldLabel = "Text",
            fieldDescription = "Provide the widget's text")
    private final String text;

    @DialogField(fieldLabel = "Size",
            fieldDescription = "Select the widget's size")
    @Selection(type=Selection.SELECT, options = {
            @Option(text = "Large", value = Bootstrap.BTN_LARGE),
            @Option(text = "Default", value = Bootstrap.BTN_DEFAULT),
            @Option(text = "Small", value = Bootstrap.BTN_SMALL),
            @Option(text = "Mini", value = Bootstrap.BTN_MINI)
    })
	private final String size;

	@DialogField(fieldLabel = "Style",
			fieldDescription = "Select the widget's style")
	@Selection(type=Selection.SELECT, options = {
			@Option(text = "Default",
					qtip = "Standard gray button with gradient",
					value = Bootstrap.BTN_DEFAULT),
			@Option(text = "Primary",
					qtip = "Provides extra visual weight and identifies the primary action in a set of buttons",
					value = Bootstrap.BTN_PRIMARY),
			@Option(text = "Info",
					qtip = "Used as an alternative to the default styles",
					value = Bootstrap.BTN_INFO),
			@Option(text = "Success",
					qtip = "Indicates a successful or positive action",
					value = Bootstrap.BTN_SUCCESS),
			@Option(text = "Warning",
					qtip =  "Indicates caution should be taken with this action",
					value = Bootstrap.BTN_WARNING),
			@Option(text = "Danger",
					qtip = "Indicates a dangerous or potentially negative action",
					value = Bootstrap.BTN_DANGER),
			@Option(text = "Inverse",
					qtip = "Alternate dark gray button, not tied to a semantic action or use",
					value = Bootstrap.BTN_INVERSE),
			@Option(text = "Link",
					qtip = "Deemphasize a button by making it look like a link while maintaining button behavior",
					value = Bootstrap.BTN_LINK)
	})
    private final String style;

	@DialogField(fieldLabel = "Action",
			fieldDescription = "Select the widget's action upon being clicked")
	@Selection (type=Selection.SELECT, options = {
			@Option(text = "Modal",
					qtip = "Opens an authored modal on button click.",
					value = OPEN_MODAL),
			@Option(text = "New Window/Tab",
					qtip = "Opens link to specified path in a new window or tab.",
					value = LINK_IN_WINDOW),
			@Option(text = "Current Window",
					qtip = "Opens link to specified path in current window.",
					value = LINK_IN_CURRENT)
	})
	private final String action;

	@DialogField(fieldLabel = "Link Target",
			fieldDescription = "URL path this button leads to")
	@PathField(rootPath="/" , rootTitle="Test Title")
	public String getLinkTarget(){
		return get("linkTarget", "#");
	}

    public CallToAction(ComponentRequest request) {
        super(request);

        text = get(TEXT_PROPERTY, "");

        size = get(SIZE_PROPERTY, "");

        style = get(STYLE_PROPERTY, "");

	    action = get(ACTION_PROPERTY, "");
    }

    public String getText() {
        return text;
    }

    public String getSize() {
        return size;
    }

    public String getStyle() {
        return style;
    }

	public String getAction() {
		return action;
	}

	public String getOpenNewWindow() {
		if(action == LINK_IN_WINDOW){ return "true";}
		else {return "false";}
	}

	public String getOpenCurrentWindow() {
		if(action == LINK_IN_CURRENT){ return "true";}
		else {return "false";}
	}

	public String getOpenModal() {
		if(action == OPEN_MODAL){ return "true";}
		return "false";
	}
}
