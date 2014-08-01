package com.citytechinc.cq.harbor.proper.core.components.content.list.demo;

import java.util.List;

import com.citytechinc.aem.bedrock.api.node.ComponentNode;
import com.citytechinc.aem.bedrock.api.page.PageDecorator;
import com.citytechinc.aem.bedrock.api.page.PageManagerDecorator;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.widgets.PathField;
import com.citytechinc.cq.harbor.proper.api.lists.construction.ListConstructionStrategy;
import com.google.common.base.Optional;
import com.google.common.collect.Lists;

public class ChildPageListConstructionStrategy implements ListConstructionStrategy<PageDecorator> {

	@DialogField(fieldLabel = "Start Page", name = "./startPagePath")
	@PathField
	private final Optional<PageDecorator> startPageOptional;

	public ChildPageListConstructionStrategy(ComponentNode componentNode) {
		PageManagerDecorator pageManagerDecorator = componentNode.getResource().getResourceResolver()
			.adaptTo(PageManagerDecorator.class);

		Optional<String> startPagePathOptional = componentNode.get("startPagePath", String.class);

		if (startPagePathOptional.isPresent()) {
			startPageOptional = Optional.fromNullable(pageManagerDecorator.getContainingPage(startPagePathOptional
				.get()));
		} else {
			startPageOptional = Optional.absent();
		}
	}

	@Override
	public List<PageDecorator> construct() {

		if (startPageOptional.isPresent()) {
			return startPageOptional.get().getChildren();
		}

		return Lists.newArrayList();

	}
}
