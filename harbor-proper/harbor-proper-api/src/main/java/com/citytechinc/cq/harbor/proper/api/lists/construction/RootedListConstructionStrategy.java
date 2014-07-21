package com.citytechinc.cq.harbor.proper.api.lists.construction;

import com.citytechinc.cq.harbor.proper.api.lists.RootedItems;
import com.google.common.base.Optional;

/**
 * A Strategy used to construct a Rooted List of elements
 *
 * @param <T>
 */
public interface RootedListConstructionStrategy<T extends RootedItems> {

    /**
     * Construction produces a Parent object representing the rooted list.  If construction does not
     * result in a root then an absent Optional is returned representing the absence of a list root.
     *
     * @return An optional Parent representing the rooted list or Optional.absent if no root can be produced by
     *         the Strategy.
     */
    Optional<T> construct();

}
