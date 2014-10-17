package com.citytechinc.aem.harbor.core.lists.construction.nodesearch.predicates.tags;

import com.citytechinc.aem.harbor.core.lists.construction.nodesearch.predicates.tags.TagsConstructionPredicate;
import com.google.common.base.Optional;

public class PageTagsConstructionPredicate extends TagsConstructionPredicate {

    private static Optional<String> RELATIVE_PATH = Optional.of("jcr:content/cq:tags");

    @Override
    public Optional<String> getRelPath() {

        return RELATIVE_PATH;

    }

}
