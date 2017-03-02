package com.icfolson.aem.harbor.core.lists.construction.nodesearch.predicates.tags;

import com.google.common.base.Optional;
import com.icfolson.aem.harbor.core.lists.construction.nodesearch.predicates.constants.PredicateConstants;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

@Model(adaptables = Resource.class)
public class PageTagsConstructionPredicate extends TagsConstructionPredicate {

    private static Optional<String> RELATIVE_PATH = Optional.of(PredicateConstants.RELATIVE_PATH_PAGE_TAGS);

    @Override
    public Optional<String> getRelativePath() {
        return RELATIVE_PATH;
    }
}