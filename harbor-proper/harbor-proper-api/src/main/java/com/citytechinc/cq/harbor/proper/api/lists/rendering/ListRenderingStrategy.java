package com.citytechinc.cq.harbor.proper.api.lists.rendering;

/**
 * A ListRenderingStrategy acts as a transformer between an Iterable of items of
 * a generic type to an Iterable of items of another type. In practice, the from
 * type &lt;T&gt; will be the type produced by a partnered
 * {@link com.citytechinc.cq.harbor.proper.api.lists.construction.ListConstructionStrategy}
 * and will be specific to the domain of the content of the list while the to
 * type &lt;R&gt; and its containing Iterable will house properties germane to
 * the rendering of the list in any context.
 *
 * @param <T>
 * @param <R>
 */
public interface ListRenderingStrategy<T, R extends Iterable<?>> {

	public R toRenderableList(Iterable<T> itemIterable);

}
