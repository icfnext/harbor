package com.citytechinc.cq.harbor.components.content.list;

import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.harbor.constants.dom.Elements;
import com.citytechinc.cq.library.components.AbstractComponent;
import com.citytechinc.cq.library.content.node.ComponentNode;
import com.citytechinc.cq.library.content.request.ComponentRequest;
import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import org.apache.sling.api.resource.Resource;

import java.util.Iterator;
import java.util.List;

@Component( value = "Abstract List", group = ".hidden", name = "lists/abstractlist" )
public abstract class AbstractListComponent<T> extends AbstractComponent implements ListComponent<T> {

    public static final String RESOURCE_TYPE = "harbor/components/content/lists/abstractlist";

    protected List<T> listItems;
    protected List<RenderableListItem<T>> renderableListItems;

    public AbstractListComponent(ComponentNode componentNode) {
        super(componentNode);
    }

    public AbstractListComponent(ComponentRequest request) {
        super(request);
    }

    protected abstract ListConstructionStrategy<T> getListConstructionStrategy();

    protected abstract ListRenderingStrategy<T> getListRenderingStrategy();

    public List<T> getListItems() {
        if (listItems == null) {
            listItems = getListConstructionStrategy().constructList();
        }

        return listItems;
    }

    public List<RenderableListItem<T>> getRenderableListItems() {
        if (renderableListItems == null) {
            renderableListItems = Lists.newArrayList();

            List<T> myListItems = getListItems();

            for (T curListItem : myListItems) {
                renderableListItems.add(new RenderableListItem<T>(curListItem, getListRenderingStrategy()));
            }
        }

        return renderableListItems;
    }

    public Optional<String> getListElementOptional() {
        if (getIsOrderedList()) {
            return Optional.fromNullable(Elements.OL);
        }
        if (getIsUnorderedList()) {
            return Optional.fromNullable(Elements.UL);
        }

        return Optional.absent();
    }

    public Boolean getHasListElement() {
        return getListElementOptional().isPresent();
    }

    public String getListElement() {
        return getListElementOptional().get();
    }

    /**
     * Indicates whether this list should be rendered as one of the two primary HTML list types, ol and ul
     *
     * @return True if this list should be rendered as either an ordered or unordered list, false otherwise
     */
    public Boolean getIsHtmlList() {
        return getIsOrderedList() || getIsUnorderedList();
    }

    public Boolean getIsOrderedList() {
        return false;
    }

    public Boolean getIsUnorderedList() {
        return false;
    }

    public Boolean getIsReversed() {
        return false;
    }

    public Optional<Integer> getStartOptional() {
        return Optional.absent();
    }

    public Boolean getHasStart() {
        return getStartOptional().isPresent();
    }

    public Integer getStart() {
        return getStartOptional().get();
    }



}
