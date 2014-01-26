package com.citytechinc.cq.harbor.components.content.list;

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

public abstract class AbstractList <RootType, ContentType> extends AbstractComponent {

    private List<ContentType> content;

    public AbstractList(ComponentNode componentNode) {
        super(componentNode);
    }

    public AbstractList(ComponentRequest componentRequest) {
        super(componentRequest);
    }

    public abstract RootType getRoot();

    protected abstract Resource getRootResource();

    protected Optional<Integer> getSearchDepthOptional() {
        return Optional.absent();
    }

    protected Optional<Ordering<ContentType>> getListOrderingOptional() {
        return Optional.absent();
    }

    protected abstract Predicate<Resource> getInclusionPredicate();

    protected abstract Function<Resource, ContentType> getItemTransformationFunction();

    /**
     * This default implementation collects all Resources which are direct or indirect children of the root resource
     * down to a depth defined by #getSearchDepthOptional.  The order in which the Resources are returned is the order
     * in which they are traversed which is a breadth first ordering based on the content tree structure.
     *
     * @return
     */
    protected List<Resource> buildChildResourceList() {

        Resource rootResource = getRootResource();
        Optional<Integer> remainingSearchDepthOptional = getSearchDepthOptional();

        List<Resource> resourcesToTraverse = Lists.newArrayList(rootResource);
        List<Resource> resourcesToExamine = Lists.newArrayList(rootResource);

        while (!resourcesToTraverse.isEmpty() && (!remainingSearchDepthOptional.isPresent() || remainingSearchDepthOptional.get() > 0)) {
            Resource curResource = resourcesToTraverse.remove(resourcesToTraverse.size() - 1);

            Iterator<Resource> children = curResource.listChildren();

            while (children.hasNext()) {
                Resource nextChild = children.next();

                resourcesToTraverse.add(nextChild);
                resourcesToExamine.add(nextChild);

            }

            if (remainingSearchDepthOptional.isPresent()) {
                remainingSearchDepthOptional = Optional.fromNullable(remainingSearchDepthOptional.get() - 1);
            }
        }

        return resourcesToExamine;

    }

    protected List<ContentType> buildList() {

        List<Resource> resourcesToExamine = buildChildResourceList();

        Function<Resource, ContentType> itemTransformationFunction = getItemTransformationFunction();
        Predicate<Resource> inclusionPredicate = getInclusionPredicate();

        content = Lists.newArrayList();

        for (Resource curResourceToExamine : resourcesToExamine) {
            if (inclusionPredicate.apply(curResourceToExamine)) {
                content.add(itemTransformationFunction.apply(curResourceToExamine));
            }
        }

        Optional<Ordering<ContentType>> contentOrdering = getListOrderingOptional();

        if (contentOrdering.isPresent()) {
            return contentOrdering.get().immutableSortedCopy(content);
        }

        return ImmutableList.copyOf(content);

    }

}
