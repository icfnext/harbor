package com.citytechinc.cq.harbor.components.content.list.assets;

import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.widgets.DialogFieldSet;
import com.citytechinc.cq.harbor.components.content.list.nodesearchstrategy.AbstractNodeSearchConstructionStrategy;
import com.citytechinc.cq.harbor.components.content.list.nodesearchstrategy.NodeTypeConstructionPredicate;
import com.citytechinc.cq.harbor.components.content.list.nodesearchstrategy.PathConstructionPredicate;
import com.citytechinc.cq.harbor.components.content.list.nodesearchstrategy.QueryParameterConstructionPredicate;
import com.citytechinc.cq.library.content.node.ComponentNode;
import com.day.cq.dam.api.Asset;
import com.day.cq.dam.api.DamConstants;
import com.day.cq.search.Predicate;
import com.day.cq.search.eval.TypePredicateEvaluator;
import com.day.cq.search.result.Hit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.RepositoryException;

/**
 * Construct a list of assets with given predicates.
 *
 * Contains a hard-coded predicate restricting search to only asset nodes.
 */
public class AssetListConstructionStrategy extends AbstractNodeSearchConstructionStrategy<Asset> {

    private static final Logger LOG = LoggerFactory.getLogger(AssetListConstructionStrategy.class);

    private static final String PROP_PREFIX_NODE_TYPE_PREDICATE = "type_predicate_";
    private NodeTypeConstructionPredicate nodeTypeConstructionPredicate;

    private static final String PROP_PREFIX_PATH_PREDICATE = "path_predicate_";
    @DialogField
    @DialogFieldSet(
            title = "Path",
            namePrefix = PROP_PREFIX_PATH_PREDICATE,
            collapsible = true,
            collapsed = true
    )
    private PathConstructionPredicate pathConstructionPredicate;

    private static final String PROP_PREFIX_QUERY_PARAM_PREDICATE = "query_predicate_";
    @DialogField
    @DialogFieldSet(
            title = "Query Parameters",
            namePrefix = PROP_PREFIX_QUERY_PARAM_PREDICATE,
            collapsible = true,
            collapsed = true
    )
    private QueryParameterConstructionPredicate queryParameterConstructionPredicate;

    @Override
    protected Asset transformHit(Hit hit) {

        Asset asset = null;
        try {

            asset = hit.getResource().adaptTo(Asset.class);

        } catch (RepositoryException e) {

            LOG.error("Failed at creating asset from a hit, an asset will not render.", e);

        }

        return asset;

    }

    public AssetListConstructionStrategy(ComponentNode componentNode) {
        super(componentNode);

        // add author specified path predicate
        pathConstructionPredicate = new PathConstructionPredicate(componentNode, PROP_PREFIX_PATH_PREDICATE);
        addPredicate(pathConstructionPredicate);

        // add author specified query predicate
        queryParameterConstructionPredicate = new QueryParameterConstructionPredicate(componentNode, PROP_PREFIX_QUERY_PARAM_PREDICATE);
        addPredicate(queryParameterConstructionPredicate);

        // add type predicate to narrow search down to only asset nodes
        nodeTypeConstructionPredicate = new NodeTypeConstructionPredicate(componentNode, PROP_PREFIX_NODE_TYPE_PREDICATE);
        nodeTypeConstructionPredicate.setNodeType(DamConstants.NT_DAM_ASSET);
        addPredicate(nodeTypeConstructionPredicate);

    }

}
