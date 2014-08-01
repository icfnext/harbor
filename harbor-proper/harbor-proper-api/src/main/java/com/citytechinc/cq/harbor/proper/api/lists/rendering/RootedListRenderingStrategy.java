package com.citytechinc.cq.harbor.proper.api.lists.rendering;

import com.citytechinc.cq.harbor.proper.api.lists.RootedItems;

public interface RootedListRenderingStrategy<T extends RootedItems, R extends RootedItems> {

	/**
	 * Transforms a Rooted Item represented by an implementation of the Parent
	 * interface into a renderable implementation of the Parent interface.
	 *
	 * @param rootedItem Implementation of the Parent interface representing the
	 *            items to be rendered
	 * @return Renderable implementation of the Parent interface
	 */
	public R toRenderableList(T rootedItem);

}
