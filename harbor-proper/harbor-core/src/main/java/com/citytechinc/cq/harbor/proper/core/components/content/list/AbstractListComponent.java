package com.citytechinc.cq.harbor.proper.core.components.content.list;

import java.util.Iterator;

import com.citytechinc.aem.bedrock.core.components.AbstractComponent;
import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.harbor.proper.api.components.content.list.ListComponent;
import com.citytechinc.cq.harbor.proper.api.constants.dom.Elements;
import com.citytechinc.cq.harbor.proper.api.lists.construction.ListConstructionStrategy;
import com.citytechinc.cq.harbor.proper.api.lists.rendering.ListRenderingStrategy;

@Component(value = "Abstract List", group = ".hidden", name = "lists/abstractlist")
public abstract class AbstractListComponent<T, R extends Iterable<?>> extends AbstractComponent implements
        ListComponent<R> {

	public static final String RESOURCE_TYPE = "harbor/components/content/lists/abstractlist";

	protected Iterable<T> rawListItems;
	protected R listItems;

	protected abstract ListConstructionStrategy<T> getListConstructionStrategy();

	protected abstract ListRenderingStrategy<T, R> getListRenderingStrategy();

	@Override
	public R getItems() {
		if (listItems == null) {
			listItems = getListRenderingStrategy().toRenderableList(getRawListItems());
		}

		return listItems;
	}

	@Override
	public Iterator<?> getIterator() {
		return getItems().iterator();
	}

	public String getListElement() {
		if (getIsOrderedList()) {
			return Elements.OL;
		}

		if (getIsUnorderedList()) {
			return Elements.UL;
		}

		return null;
	}

	public Boolean getHasListElement() {
		return getListElement() != null;
	}

	public Boolean getIsOrderedList() {
		return false;
	}

	public Boolean getIsUnorderedList() {
		return false;
	}

	/**
	 * Indicates whether this list should be rendered as one of the two primary
	 * HTML list types, ol and ul
	 *
	 * @return True if this list should be rendered as either an ordered or
	 *         unordered list, false otherwise
	 */
	public Boolean getIsHtmlList() {
		return getIsOrderedList() || getIsUnorderedList();
	}

	public Boolean getIsReversed() {
		return false;
	}

	public Integer getStart() {
		return null;
	}

	public Boolean getHasStart() {
		return getStart() != null;
	}

	protected Iterable<T> getRawListItems() {
		if (rawListItems == null) {
			rawListItems = getListConstructionStrategy().construct();
		}

		return rawListItems;
	}

}
