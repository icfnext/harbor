package com.citytechinc.cq.harbor.proper.core.components.content.calltoaction;

import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.ContentProperty;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.Option;
import com.citytechinc.cq.component.annotations.widgets.PathField;
import com.citytechinc.cq.component.annotations.widgets.Selection;
import com.citytechinc.cq.harbor.proper.api.constants.bootstrap.Bootstrap;
import com.citytechinc.cq.library.components.AbstractComponent;
import com.citytechinc.cq.library.components.annotations.AutoInstantiate;
import com.citytechinc.cq.library.content.link.Link;
import com.citytechinc.cq.library.content.link.builders.LinkBuilder;
import com.citytechinc.cq.library.content.request.ComponentRequest;
import com.google.common.base.Optional;
import org.apache.commons.lang.StringUtils;

import javax.jcr.Node;
import javax.jcr.RepositoryException;


@Component(
        value = "Call to Action",
        group = "Harbor",
        name = "calltoaction",
        layout = "rollover",
        contentAdditionalProperties = {@ContentProperty(name="dependencies", value="[harbor.bootstrap.modals,harbor.bootstrap.buttons,harbor.author-common]")})
@AutoInstantiate( instanceName = CallToAction.INSTANCE_NAME )
public class CallToAction extends AbstractComponent {

    public static final String INSTANCE_NAME = "cta";

	private static final String TEXT_PROPERTY = "text";
    private static final String TEXT_DEFAULT = "Call to Action";
	private static final String SIZE_PROPERTY = "size";
	private static final String STYLE_PROPERTY = "style";
	private static final String ACTION_PROPERTY = "action";
	private static final String PATH_PROPERTY = "linkTarget";
	private static final String OPEN_MODAL = "modal";
	private static final String LINK_IN_WINDOW = "window";
	private static final String LINK_IN_CURRENT = "current";

    public static final String BUTTON_ID_PREFIX = "callToActionButton-";
    public static final String MODAL_ID_PREFIX = "callToActionModal-";

    private Link target;

    public CallToAction(ComponentRequest request) {
        super(request);
    }

    @DialogField(fieldLabel = "Text",
            fieldDescription = "Provide the widget's text", ranking = 0)
    public String getText() {
	    return get(TEXT_PROPERTY, TEXT_DEFAULT);
    }

    @DialogField(fieldLabel = "Size",
            fieldDescription = "Select the widget's size" , ranking = 2)
    @Selection(type=Selection.SELECT, options = {
            @Option(text = "Large", value = Bootstrap.BTN_LARGE),
            @Option(text = "Default", value = Bootstrap.BTN_DEFAULT),
            @Option(text = "Small", value = Bootstrap.BTN_SMALL),
            @Option(text = "Mini", value = Bootstrap.BTN_MINI)
    })
    public String getSize() {
	    return get(SIZE_PROPERTY, StringUtils.EMPTY);
    }

	@DialogField(fieldLabel = "Style",
			fieldDescription = "Select the widget's style" , ranking = 3)
	@Selection(type=Selection.SELECT, options = {
			@Option(text = "Default",
					qtip = "Standard basic button style, not tied to a semantic action or use",
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
					qtip = "Alternate to the default button style, not tied to a semantic action or use",
					value = Bootstrap.BTN_INVERSE),
			@Option(text = "Link",
					qtip = "Indicates that this button represents a simple link to a target resource or page",
					value = Bootstrap.BTN_LINK)
	})
	public String getStyle() {
		return get(STYLE_PROPERTY, StringUtils.EMPTY);
	}

	@DialogField(fieldLabel = "Action",
			fieldDescription = "Select the widget's action upon being clicked" , ranking = 4)
	@Selection (type=Selection.SELECT, options = {
			@Option(text = "Open as a Modal",
					qtip = "Opens an authored modal on button click.",
					value = OPEN_MODAL),
			@Option(text = "Open in a New Window/Tab",
					qtip = "Opens link to specified path in a new window or tab.",
					value = LINK_IN_WINDOW),
			@Option(text = "Open in the Current Window",
					qtip = "Opens link to specified path in current window.",
					value = LINK_IN_CURRENT)
	})
	public String getAction() {
		return get(ACTION_PROPERTY, StringUtils.EMPTY);
	}

	@DialogField(fieldLabel = "Link Target",
			fieldDescription = "URL path this button leads to", ranking = 2)
	@PathField
	public Link getLinkTarget(){
        if (target == null) {
            Optional<Link> targetOptional = getAsLink(PATH_PROPERTY);

            if (targetOptional.isPresent()) {
                target = targetOptional.get();
            }
            else {
                target = LinkBuilder.forPage(currentPage).build();
            }
        }

        return target;
	}

	public String getId() throws RepositoryException {
		return getResource().adaptTo(Node.class).getIdentifier();
	}

    public String getButtonId() throws RepositoryException {
        return BUTTON_ID_PREFIX + getId();
    }

    public String getModalId() throws RepositoryException {
        return MODAL_ID_PREFIX + getId();
    }

	public Boolean getOpensInNewWindow() {
		return StringUtils.equalsIgnoreCase(getAction(), LINK_IN_WINDOW);
	}

	public Boolean getOpensInCurrentWindow() {
		return StringUtils.equalsIgnoreCase(getAction(), LINK_IN_CURRENT);
	}

	public Boolean getOpensAsModal() {
		return StringUtils.equalsIgnoreCase(getAction(), OPEN_MODAL);
	}

}
