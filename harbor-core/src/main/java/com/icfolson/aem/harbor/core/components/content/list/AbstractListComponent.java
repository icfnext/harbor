package com.icfolson.aem.harbor.core.components.content.list;

import com.google.common.collect.Lists;
import com.icfolson.aem.harbor.api.components.content.list.ListComponent;
import com.icfolson.aem.harbor.api.lists.construction.ListConstructionStrategy;
import com.icfolson.aem.harbor.api.lists.rendering.ListRenderingStrategy;
import com.icfolson.aem.harbor.api.lists.rendering.RenderableItem;
import com.icfolson.aem.library.core.components.AbstractComponent;

import java.util.Iterator;
import java.util.List;

public abstract class AbstractListComponent<T, R extends Iterable<?>> extends AbstractComponent implements
    ListComponent<R> {

    public static final String RESOURCE_TYPE = "harbor/components/content/lists/abstractlist";

    protected Iterable<T> rawListItems;

    protected R listItems;

    protected List<RenderableItemWrapper> renderableItems;

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

    public List<RenderableItemWrapper> getRenderableItems() {
        if (renderableItems == null) {
            renderableItems = Lists.newArrayList();

            for (Object item : getItems()) {
                if (item instanceof RenderableItem) {
                    renderableItems.add(new RenderableItemWrapper((RenderableItem) item));
                }
            }
        }

        return renderableItems;
    }

    protected Iterable<T> getRawListItems() {
        if (rawListItems == null) {
            rawListItems = getListConstructionStrategy().construct();
        }

        return rawListItems;
    }

    /**
     * Wrapper to expose the render method of a renderable item in a template-friendly manner (i.e. using Java bean
     * getter conventions).
     */
    public static class RenderableItemWrapper {

        private final RenderableItem item;

        public RenderableItemWrapper(final RenderableItem item) {
            this.item = item;
        }

        public String getRendered() {
            return item.render();
        }
    }
}
