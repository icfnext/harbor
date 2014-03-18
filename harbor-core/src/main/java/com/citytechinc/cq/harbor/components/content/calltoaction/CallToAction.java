package com.citytechinc.cq.harbor.components.content.calltoaction;

import com.citytechinc.cq.component.annotations.*;
import com.citytechinc.cq.component.annotations.widgets.PathField;
import com.citytechinc.cq.component.annotations.widgets.Selection;
import com.citytechinc.cq.harbor.constants.bootstrap.Bootstrap;
import com.citytechinc.cq.library.components.AbstractComponent;
import com.citytechinc.cq.library.content.request.ComponentRequest;
import org.apache.commons.lang.StringUtils;

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
	private static final String PATH_PROPERTY = "linkTarget";

	private final String OPEN_MODAL = "modal";
	private final String LINK_IN_WINDOW = "window";
	private final String LINK_IN_CURRENT = "current";

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
	@PathField(rootPath="/content/" , rootTitle="Test Title")
	private final String path;

    public CallToAction(ComponentRequest request) {
        super(request);
        text = get(TEXT_PROPERTY, StringUtils.EMPTY);
        size = get(SIZE_PROPERTY, StringUtils.EMPTY);
        style = get(STYLE_PROPERTY, StringUtils.EMPTY);
	    action = get(ACTION_PROPERTY, StringUtils.EMPTY);
		path = getAsHref(PATH_PROPERTY, true, true).or(StringUtils.EMPTY);
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
	public String getLinkTarget(){return path;}

	public Boolean getOpenWindow() {
		return StringUtils.equalsIgnoreCase(action,LINK_IN_WINDOW);
	}

	public Boolean getOpenCurrent() {
		return StringUtils.equalsIgnoreCase(action,LINK_IN_CURRENT);
	}

	public Boolean getOpenModal() {
		return StringUtils.equalsIgnoreCase(action,OPEN_MODAL);
	}
}
