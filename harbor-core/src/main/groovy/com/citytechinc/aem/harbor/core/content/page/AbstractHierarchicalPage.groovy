package com.citytechinc.aem.harbor.core.content.page;

import org.apache.sling.api.resource.Resource
import org.apache.sling.api.resource.ValueMap

import com.citytechinc.aem.bedrock.api.link.ImageLink
import com.citytechinc.aem.bedrock.api.link.NavigationLink
import com.citytechinc.aem.bedrock.api.link.builders.LinkBuilder
import com.citytechinc.aem.bedrock.api.page.PageDecorator
import com.citytechinc.aem.bedrock.api.page.PageManagerDecorator
import com.citytechinc.aem.harbor.api.content.page.HierarchicalPage
import com.day.cq.commons.Filter
import com.day.cq.tagging.Tag
import com.day.cq.wcm.api.Page
import com.day.cq.wcm.api.Template
import com.day.cq.wcm.api.WCMException
import com.google.common.base.Optional
import com.google.common.base.Predicate

public abstract class AbstractHierarchicalPage implements HierarchicalPage,PageDecorator {

	@Delegate
	private final PageDecorator page;

	public AbstractHierarchicalPage(PageDecorator page) {
		this.page = page;
	}
}
