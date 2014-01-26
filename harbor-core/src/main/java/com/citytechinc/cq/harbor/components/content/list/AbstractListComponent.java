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

public abstract class AbstractListComponent<RootType, RawType, ContentType> extends AbstractComponent implements ListComponent<RootType, ContentType> {

    private static final Predicate<Object> INCLUDE_ALL_PREDICATE = new Predicate<Object>() {

        @Override
        public boolean apply(Object o) {
            return true;
        }

    };

    public AbstractListComponent(ComponentNode componentNode) {
        super(componentNode);
    }

    public AbstractListComponent(ComponentRequest componentRequest) {
        super(componentRequest);
    }

    public Optional<RootType> getRootOptional() {
        return Optional.absent();
    }

    public Boolean isHasRoot() {
        return getRootOptional().isPresent();
    }

    public List<ContentType> getContent() {
        return buildList();
    }

    protected Optional<Integer> getSearchDepthOptional() {
        return Optional.absent();
    }

    protected Optional<Ordering<ContentType>> getListOrderingOptional() {
        return Optional.absent();
    }

    protected Predicate<? super RawType> getInclusionPredicate() {

        return INCLUDE_ALL_PREDICATE;

    }

    protected Function<RawType, ContentType> getItemTransformationFunction() {
        return new Function<RawType, ContentType>() {
            @Override
            public ContentType apply(RawType rawType) {
                return null;
            }
        };
    }

    protected List<RawType> buildRawItemList() {
        return Lists.newArrayList();
    }

    protected List<ContentType> buildList() {

        List<RawType> rawItemList = buildRawItemList();

        Function<RawType, ContentType> itemTransformationFunction = getItemTransformationFunction();
        Predicate<? super RawType> inclusionPredicate = getInclusionPredicate();

        List<ContentType> content = Lists.newArrayList();

        for (RawType curRawItem : rawItemList) {
            if (inclusionPredicate.apply(curRawItem)) {
                content.add(itemTransformationFunction.apply(curRawItem));
            }
        }

        Optional<Ordering<ContentType>> contentOrdering = getListOrderingOptional();

        if (contentOrdering.isPresent()) {
            return contentOrdering.get().immutableSortedCopy(content);
        }

        return ImmutableList.copyOf(content);

    }

}
