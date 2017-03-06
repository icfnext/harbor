package com.icfolson.aem.harbor.core.content.page.navigation;

import com.icfolson.aem.library.api.page.PageDecorator;
import com.icfolson.aem.harbor.api.content.page.navigation.NavigablePage;
import com.icfolson.aem.harbor.core.content.page.navigation.impl.DefaultNavigablePage;

public class NavigablePages {

	public static NavigablePage forPage(PageDecorator pageDecorator) {
		return forPageAndDepth(pageDecorator, 1);
	}

	public static NavigablePage forPageIncludingAllChildren(PageDecorator pageDecorator) {
		return forPageAndDepthIncludingAllChildren(pageDecorator, 1);
	}

	public static NavigablePage forPageAndDepth(PageDecorator pageDecorator, Integer depth) {
		return forPageAndDepthAndChildPolicy(pageDecorator, depth, true);
	}

	public static NavigablePage forPageAndDepthIncludingAllChildren(PageDecorator pageDecorator, Integer depth) {
		return forPageAndDepthAndChildPolicy(pageDecorator, depth, false);
	}

	public static NavigablePage forPageAndDepthAndChildPolicy(PageDecorator pageDecorator, Integer depth,
		Boolean respectHideInNav) {
		NavigationElementConfiguration navigationElementConfiguration = new NavigationElementConfiguration(
			respectHideInNav, depth);
		return new DefaultNavigablePage(pageDecorator, navigationElementConfiguration);
	}

	public static NavigablePage forPageAndDepthAndChildPolicyAndCurrentPage(PageDecorator pageDecorator, Integer depth,
		Boolean respectHideInNav, PageDecorator currentPage) {
		NavigationElementConfiguration navigationElementConfiguration = new NavigationElementConfiguration(
			respectHideInNav, depth, currentPage);
		return new DefaultNavigablePage(pageDecorator, navigationElementConfiguration);
	}
}
