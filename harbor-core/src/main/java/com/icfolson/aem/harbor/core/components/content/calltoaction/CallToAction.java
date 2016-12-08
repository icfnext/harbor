package com.icfolson.aem.harbor.core.components.content.calltoaction;

import javax.inject.Inject;
import javax.jcr.RepositoryException;

import com.citytechinc.cq.component.annotations.widgets.TextField;
import com.icfolson.aem.harbor.core.constants.groups.ComponentGroups;
import com.icfolson.aem.library.api.page.PageDecorator;
import com.icfolson.aem.harbor.core.util.icon.IconUtils;
import com.icfolson.aem.library.core.constants.PathConstants;
import org.apache.commons.lang3.StringUtils;

import com.icfolson.aem.library.api.components.annotations.AutoInstantiate;
import com.icfolson.aem.library.api.link.Link;
import com.icfolson.aem.library.core.components.AbstractComponent;
import com.icfolson.aem.library.core.link.builders.factory.LinkBuilderFactory;
import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.ContentProperty;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.Option;
import com.citytechinc.cq.component.annotations.widgets.PathField;
import com.citytechinc.cq.component.annotations.widgets.Selection;
import com.icfolson.aem.harbor.api.constants.bootstrap.Bootstrap;
import com.google.common.base.Optional;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

@Component(value = "Call to Action", group = ComponentGroups.HARBOR, name = "calltoaction", layout = "rollover",
	contentAdditionalProperties = { @ContentProperty(name = "dependencies",
        value = "[harbor.bootstrap.modals,harbor.bootstrap.buttons,harbor.author-common,harbor.components.content.calltoaction]") },
    inPlaceEditingEditorType = "harborcta")
@AutoInstantiate(instanceName = CallToAction.INSTANCE_NAME)
@Model(adaptables = Resource.class)
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
    public static final String CONTAINER_PAR_ID_PREFIX = "container-par-";

	private Link target;

	@Inject
	private PageDecorator currentPage;

	@DialogField(fieldLabel = "Text", fieldDescription = "Provide the widget's text", ranking = 0)
	@TextField
	public String getText() {
		return IconUtils.iconify(get(TEXT_PROPERTY, TEXT_DEFAULT));
	}

	@DialogField(fieldLabel = "Size", fieldDescription = "Select the widget's size", ranking = 2)
	@Selection(type = Selection.SELECT, options = { @Option(text = "Large", value = Bootstrap.BTN_LARGE),
		@Option(text = "Default", value = Bootstrap.BTN_DEFAULT), @Option(text = "Small", value = Bootstrap.BTN_SMALL),
		@Option(text = "Mini", value = Bootstrap.BTN_MINI) })
	public String getSize() {
		return get(SIZE_PROPERTY, StringUtils.EMPTY);
	}

	@DialogField(fieldLabel = "Style", fieldDescription = "Select the widget's style", ranking = 3)
	@Selection(type = Selection.SELECT, options = {
		@Option(text = "Default", qtip = "Standard basic button style, not tied to a semantic action or use", value = Bootstrap.BTN_DEFAULT),
		@Option(text = "Primary", qtip = "Provides extra visual weight and identifies the primary action in a set of buttons", value = Bootstrap.BTN_PRIMARY),
		@Option(text = "Info", qtip = "Used as an alternative to the default styles", value = Bootstrap.BTN_INFO),
		@Option(text = "Success", qtip = "Indicates a successful or positive action", value = Bootstrap.BTN_SUCCESS),
		@Option(text = "Warning", qtip = "Indicates caution should be taken with this action", value = Bootstrap.BTN_WARNING),
		@Option(text = "Danger", qtip = "Indicates a dangerous or potentially negative action", value = Bootstrap.BTN_DANGER),
		@Option(text = "Inverse", qtip = "Alternate to the default button style, not tied to a semantic action or use", value = Bootstrap.BTN_INVERSE),
		@Option(text = "Link", qtip = "Indicates that this button represents a simple link to a target resource or page", value = Bootstrap.BTN_LINK) })
	public String getStyle() {
		return get(STYLE_PROPERTY, StringUtils.EMPTY);
	}

	@DialogField(fieldLabel = "Action", fieldDescription = "Select the widget's action upon being clicked", ranking = 4)
	@Selection(type = Selection.SELECT, options = {
			@Option(text = "Open in the Current Window", qtip = "Opens link to specified path in current window.", value = LINK_IN_CURRENT),
			@Option(text = "Open in a New Window/Tab", qtip = "Opens link to specified path in a new window or tab.", value = LINK_IN_WINDOW),
			@Option(text = "Open as a Modal", qtip = "Opens an authored modal on button click.", value = OPEN_MODAL)})
	public String getAction() {
		return get(ACTION_PROPERTY, StringUtils.EMPTY);
	}

	@DialogField(fieldLabel = "Link Target", fieldDescription = "URL path this button leads to", ranking = 2)
	@PathField(rootPath = PathConstants.PATH_CONTENT)
	public Link getLinkTarget() {
		if (target == null) {
			target = getAsLink(PATH_PROPERTY).or(LinkBuilderFactory.forPage(currentPage).build());
		}

		return target;
	}

	public String getButtonId() {
		return BUTTON_ID_PREFIX + getId();
	}

	public String getModalId() {
		return MODAL_ID_PREFIX + getId();
	}

	public String getContainerParId() {
        return CONTAINER_PAR_ID_PREFIX + getId();
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
