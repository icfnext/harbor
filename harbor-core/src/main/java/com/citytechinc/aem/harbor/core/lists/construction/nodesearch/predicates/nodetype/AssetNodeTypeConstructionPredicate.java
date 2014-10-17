package com.citytechinc.aem.harbor.core.lists.construction.nodesearch.predicates.nodetype;

import com.day.cq.dam.api.DamConstants;
import com.google.common.base.Optional;

public class AssetNodeTypeConstructionPredicate extends NodeTypeConstructionPredicate {

    private static final Optional<String> NODE_TYPE_OPTIONAL = Optional.of(DamConstants.NT_DAM_ASSET);

    public Optional<String> getNodeType() {

        return NODE_TYPE_OPTIONAL;

    }

}
