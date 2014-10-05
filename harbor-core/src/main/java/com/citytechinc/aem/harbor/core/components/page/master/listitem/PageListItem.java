package com.citytechinc.aem.harbor.core.components.page.master.listitem;

import org.apache.commons.lang3.StringUtils;

import com.citytechinc.aem.bedrock.core.components.AbstractComponent;
import com.citytechinc.cq.component.annotations.Component;

@Component(value = "Page List Item", group = ".hidden", noDecoration = true, editConfig = false, path = "page/common/master/listitem")
public class PageListItem extends AbstractComponent {

	public String getListItemTitle() {
		if (StringUtils.isNotEmpty(getCurrentPage().getPageTitle())) {
			return getCurrentPage().getPageTitle();
		}

		return getCurrentPage().getTitle();
	}

}
