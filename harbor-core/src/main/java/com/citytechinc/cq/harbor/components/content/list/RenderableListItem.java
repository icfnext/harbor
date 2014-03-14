package com.citytechinc.cq.harbor.components.content.list;

public class RenderableListItem <T> {

    private final T item;
    private final ListRenderingStrategy <T> renderingStrategy;
    private String renderedItem;

    public RenderableListItem (T item, ListRenderingStrategy <T> renderingStrategy) {
        this.item = item;
        this.renderingStrategy = renderingStrategy;
    }

    public String getRenderedItem() {
        if (renderedItem == null) {
            renderedItem = renderingStrategy.renderListItem(item);
        }

        return renderedItem;
    }

}
