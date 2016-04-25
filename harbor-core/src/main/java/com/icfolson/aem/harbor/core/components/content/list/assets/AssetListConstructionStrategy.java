package com.icfolson.aem.harbor.core.components.content.list.assets;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.jcr.RepositoryException;

import com.icfolson.aem.harbor.core.lists.construction.nodesearch.predicates.nodetype.AssetNodeTypeConstructionPredicate;
import com.icfolson.aem.harbor.core.lists.construction.nodesearch.predicates.tags.AssetTagsConstructionPredicate;
import com.icfolson.aem.harbor.core.lists.construction.nodesearch.AbstractNodeSearchConstructionStrategy;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.widgets.DialogFieldSet;
import com.icfolson.aem.harbor.api.lists.construction.search.ConstructionPredicate;
import com.icfolson.aem.harbor.core.lists.construction.nodesearch.predicates.path.PathConstructionPredicate;
import com.icfolson.aem.harbor.core.lists.construction.nodesearch.predicates.queryparameters.QueryParameterConstructionPredicate;
import com.day.cq.dam.api.Asset;
import com.day.cq.search.result.Hit;
import com.google.common.collect.Lists;

/**
 * Construct a list of assets with given predicates.
 *
 * Contains a hard-coded predicate restricting search to only asset nodes.
 */
@Model(adaptables = Resource.class)
public class AssetListConstructionStrategy extends AbstractNodeSearchConstructionStrategy<Asset> {

	private static final Logger LOG = LoggerFactory.getLogger(AssetListConstructionStrategy.class);

	@DialogField
	@DialogFieldSet(title = "Path", namePrefix = "pathpredicate/", collapsible = true, collapsed = true)
    @Inject @Named("pathpredicate") @Optional
	private PathConstructionPredicate pathConstructionPredicate;

	@DialogField
	@DialogFieldSet(title = "Tags", namePrefix = "tagspredicate/", collapsible = true, collapsed = true)
    @Inject @Named("tagspredicate") @Optional
	private AssetTagsConstructionPredicate tagsConstructionPredicate;

	@DialogField
	@DialogFieldSet(title = "Query Parameters", namePrefix = "queryparameterpredicate/", collapsible = true, collapsed = true)
    @Inject @Named("queryparameterpredicate") @Optional
	private QueryParameterConstructionPredicate queryParameterConstructionPredicate;

	private List<ConstructionPredicate> constructionPredicates;

    protected PathConstructionPredicate getPathConstructionPredicate() {
        return pathConstructionPredicate;
    }

    protected AssetTagsConstructionPredicate getTagsConstructionPredicate() {
        return tagsConstructionPredicate;
    }

    protected QueryParameterConstructionPredicate getQueryParameterConstructionPredicate() {
        return queryParameterConstructionPredicate;
    }

    protected AssetNodeTypeConstructionPredicate getNodeTypeConstructionPredicate() {
        return this.getResource().adaptTo(AssetNodeTypeConstructionPredicate.class);
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