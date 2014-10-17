package com.citytechinc.aem.harbor.core.components.content.list.assets;

import java.util.List;

import javax.jcr.RepositoryException;

import com.citytechinc.aem.harbor.core.lists.construction.nodesearch.predicates.nodetype.AssetNodeTypeConstructionPredicate;
import com.citytechinc.aem.harbor.core.lists.construction.nodesearch.predicates.tags.AssetTagsConstructionPredicate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.widgets.DialogFieldSet;
import com.citytechinc.aem.harbor.api.lists.construction.search.ConstructionPredicate;
import com.citytechinc.aem.harbor.core.lists.construction.nodesearch.AbstractNodeSearchConstructionStrategy;
import com.citytechinc.aem.harbor.core.lists.construction.nodesearch.predicates.path.PathConstructionPredicate;
import com.citytechinc.aem.harbor.core.lists.construction.nodesearch.predicates.queryparameters.QueryParameterConstructionPredicate;
import com.day.cq.dam.api.Asset;
import com.day.cq.search.result.Hit;
import com.google.common.collect.Lists;

/**
 * Construct a list of assets with given predicates.
 *
 * Contains a hard-coded predicate restricting search to only asset nodes.
 */
public class AssetListConstructionStrategy extends AbstractNodeSearchConstructionStrategy<Asset> {

	private static final Logger LOG = LoggerFactory.getLogger(AssetListConstructionStrategy.class);

	@DialogField
	@DialogFieldSet(title = "Path", namePrefix = "pathpredicate/", collapsible = true, collapsed = true)
	private PathConstructionPredicate pathConstructionPredicate;

	@DialogField
	@DialogFieldSet(title = "Tags", namePrefix = "tagspredicate/", collapsible = true, collapsed = true)
	private AssetTagsConstructionPredicate tagsConstructionPredicate;

	@DialogField
	@DialogFieldSet(title = "Query Parameters", namePrefix = "queryparameterpredicate/", collapsible = true, collapsed = true)
	private QueryParameterConstructionPredicate queryParameterConstructionPredicate;

	private List<ConstructionPredicate> constructionPredicates;

    protected PathConstructionPredicate getPathConstructionPredicate() {
        if (pathConstructionPredicate == null) {
            pathConstructionPredicate = getComponent(getResource().getPath() + "/pathpredicate", PathConstructionPredicate.class).orNull();
        }

        return pathConstructionPredicate;
    }

    protected AssetTagsConstructionPredicate getTagsConstructionPredicate() {
        if (tagsConstructionPredicate == null) {
            tagsConstructionPredicate = getComponent(getResource().getPath() + "tagspredicate", AssetTagsConstructionPredicate.class).orNull();
        }

        return tagsConstructionPredicate;
    }

    protected QueryParameterConstructionPredicate getQueryParameterConstructionPredicate() {
        if (queryParameterConstructionPredicate == null) {
            queryParameterConstructionPredicate = getComponent(getResource().getPath() + "queryparameterpredicate", QueryParameterConstructionPredicate.class).orNull();
        }

        return queryParameterConstructionPredicate;
    }

    protected AssetNodeTypeConstructionPredicate getNodeTypeConstructionPredicate() {
        return getComponent(this, AssetNodeTypeConstructionPredicate.class);
    }

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

	@Override
	protected List<ConstructionPredicate> getConstructionPredicates() {

		if (constructionPredicates == null) {
			constructionPredicates = Lists.newArrayList();

            constructionPredicates.add(getNodeTypeConstructionPredicate());

            if (getPathConstructionPredicate() != null) {
                constructionPredicates.add(getPathConstructionPredicate());
            }
            if (getTagsConstructionPredicate() != null) {
                constructionPredicates.add(getTagsConstructionPredicate());
            }
            if (getQueryParameterConstructionPredicate() != null) {
                constructionPredicates.add(getQueryParameterConstructionPredicate());
            }
		}

		return constructionPredicates;

	}

    @Override
    protected boolean isReadyToQuery() {
        return
                getPathConstructionPredicate() != null &&
                getPathConstructionPredicate().getSearchPath().isPresent();
    }

}
