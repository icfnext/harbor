package com.icfolson.aem.harbor.core.lists.construction.nodesearch.predicates.tags;

import com.google.common.base.Optional;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

@Model(adaptables = Resource.class)
public class PageTagsConstructionPredicate extends TagsConstructionPredicate {

    private static Optional<String> RELATIVE_PATH = Optional.of("jcr:content/cq:tags");

    @Override
    public Optional<String> getRelPath() {

        return RELATIVE_PATH;

    }

}
