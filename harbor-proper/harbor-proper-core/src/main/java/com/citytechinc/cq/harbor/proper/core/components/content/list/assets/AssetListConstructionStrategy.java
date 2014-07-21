package com.citytechinc.cq.harbor.proper.core.components.content.list.assets;

import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.widgets.DialogFieldSet;
import com.citytechinc.cq.harbor.proper.api.lists.construction.search.ConstructionPredicate;
import com.citytechinc.cq.harbor.proper.core.components.content.list.nodesearchstrategy.AbstractNodeSearchConstructionStrategy;
import com.citytechinc.cq.harbor.proper.core.components.content.list.nodesearchstrategy.predicatewrappers.NodeTypeConstructionPredicate;
import com.citytechinc.cq.harbor.proper.core.components.content.list.nodesearchstrategy.predicatewrappers.PathConstructionPredicate;
import com.citytechinc.cq.harbor.proper.core.components.content.list.nodesearchstrategy.predicatewrappers.QueryParameterConstructionPredicate;
import com.citytechinc.cq.harbor.proper.core.components.content.list.nodesearchstrategy.predicatewrappers.TagsConstructionPredicate;
import com.citytechinc.cq.library.content.node.ComponentNode;
import com.day.cq.dam.api.Asset;
import com.day.cq.dam.api.DamConstants;
import com.day.cq.search.result.Hit;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.RepositoryException;
import java.util.List;

/**
 * Construct a list of assets with given predicates.
 *
 * Contains a hard-coded predicate restricting search to only asset nodes.
 */
public class AssetListConstructionStrategy extends AbstractNodeSearchConstructionStrategy<Asset> {

    private static final Logger LOG = LoggerFactory.getLogger(AssetListConstructionStrategy.class);

    private NodeTypeConstructionPredicate nodeTypeConstructionPredicate;

    private static final String PROP_PREFIX_PATH_PREDICATE = "1";
    @DialogField
    @DialogFieldSet(
            title = "Path",
            namePrefix = PROP_PREFIX_PATH_PREDICATE,
            collapsible = true,
            collapsed = true
    )
    private PathConstructionPredicate pathConstructionPredicate;

    private static final String PROP_PREFIX_TAGS_PREDICATE = "2";
    @DialogField
    @DialogFieldSet(
            title = "Tags",
            namePrefix = PROP_PREFIX_TAGS_PREDICATE,
            collapsible = true,
            collapsed = true
    )
    private TagsConstructionPredicate tagsConstructionPredicate;

    private static final String PROP_PREFIX_QUERY_PARAM_PREDICATE = "3";
    @DialogField
    @DialogFieldSet(
            title = "Query Parameters",
            namePrefix = PROP_PREFIX_QUERY_PARAM_PREDICATE,
            collapsible = true,
            collapsed = true
    )
    private QueryParameterConstructionPredicate queryParameterConstructionPredicate;

    private List<ConstructionPredicate> constructionPredicates;

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

        // add author specified tags predicate
        tagsConstructionPredicate = new TagsConstructionPredicate(componentNode, PROP_PREFIX_TAGS_PREDICATE);

        // add author specified query predicate
        queryParameterConstructionPredicate = new QueryParameterConstructionPredicate(componentNode, PROP_PREFIX_QUERY_PARAM_PREDICATE);

        // add type predicate to narrow search down to only asset nodes
        nodeTypeConstructionPredicate = new NodeTypeConstructionPredicate(DamConstants.NT_DAM_ASSET);

    }

    @Override
    protected List<ConstructionPredicate> getPredicates() {

        if (constructionPredicates == null) {
            constructionPredicates = Lists.newArrayList(pathConstructionPredicate, tagsConstructionPredicate, queryParameterConstructionPredicate, nodeTypeConstructionPredicate);
        }

        return constructionPredicates;

    }

}
