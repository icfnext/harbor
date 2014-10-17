package com.citytechinc.aem.harbor.core.lists.construction.nodesearch.predicates.nodetype;

import com.google.common.base.Optional;

public class PageNodeTypeConstructionPredicate extends NodeTypeConstructionPredicate {

    private static final Optional<String> NODE_TYPE_OPTIONAL = Optional.of("cq:Page");

    public Optional<String> getNodeType() {

        return NODE_TYPE_OPTIONAL;

    }

}
