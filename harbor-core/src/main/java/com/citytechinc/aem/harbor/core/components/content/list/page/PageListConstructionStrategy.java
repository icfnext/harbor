package com.citytechinc.aem.harbor.core.components.content.list.page;

import com.citytechinc.aem.bedrock.api.page.PageDecorator;
import com.citytechinc.aem.harbor.api.lists.construction.search.ConstructionPredicate;
import com.citytechinc.aem.harbor.core.lists.construction.nodesearch.AbstractNodeSearchConstructionStrategy;
import com.citytechinc.aem.harbor.core.lists.construction.nodesearch.predicates.nodetype.PageNodeTypeConstructionPredicate;
import com.citytechinc.aem.harbor.core.lists.construction.nodesearch.predicates.path.PathConstructionPredicate;
import com.citytechinc.aem.harbor.core.lists.construction.nodesearch.predicates.queryparameters.QueryParameterConstructionPredicate;
import com.citytechinc.aem.harbor.core.lists.construction.nodesearch.predicates.tags.PageTagsConstructionPredicate;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.widgets.DialogFieldSet;
import com.day.cq.search.result.Hit;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.RepositoryException;
import java.util.List;

public class PageListConstructionStrategy extends AbstractNodeSearchConstructionStrategy<PageDecorator> {

    private static final Logger LOG = LoggerFactory.getLogger(PageListConstructionStrategy.class);

    @DialogField
    @DialogFieldSet(title = "Path", namePrefix = "pathpredicate/", collapsible = true, collapsed = true)
    private PathConstructionPredicate pathConstructionPredicate;

    @DialogField
    @DialogFieldSet(title = "Tags", namePrefix = "tagspredicate/", collapsible = true, collapsed = true)
    private PageTagsConstructionPredicate tagsConstructionPredicate;

    @DialogField
    @DialogFieldSet(title = "Query Parameters", namePrefix = "queryparameterpredicate/", collapsible = true, collapsed = true)
    private QueryParameterConstructionPredicate queryParameterConstructionPredicate;

    private List<ConstructionPredicate> constructionPredicates;

    protected PathConstructionPredicate getPathConstructionPredicate() {
        if (pathConstructionPredicate == null) {
            /*
            Optional<ComponentNode> pathPredicateComponentNodeOptional = getComponentNode("pathpredicate");

            if (pathPredicateComponentNodeOptional.isPresent()) {
                pathConstructionPredicate = getComponent(pathPredicateComponentNodeOptional.get(), PathConstructionPredicate.class);
            }
            else {
                pathConstructionPredicate = null;
            }
            */
            pathConstructionPredicate = getComponent(getResource().getPath() + "/pathpredicate", PathConstructionPredicate.class).orNull();
        }

        return pathConstructionPredicate;
    }

    protected PageTagsConstructionPredicate getTagsConstructionPredicate() {
        if (tagsConstructionPredicate == null) {
            tagsConstructionPredicate = getComponent(getResource().getPath() + "/tagspredicate", PageTagsConstructionPredicate.class).orNull();
        }

        return tagsConstructionPredicate;
    }

    protected QueryParameterConstructionPredicate getQueryParameterConstructionPredicate() {
        if (queryParameterConstructionPredicate == null) {
            queryParameterConstructionPredicate = getComponent(getResource().getPath() + "/queryparameterpredicate", QueryParameterConstructionPredicate.class).orNull();
        }

        return queryParameterConstructionPredicate;
    }

    protected PageNodeTypeConstructionPredicate getNodeTypeConstructionPredicate() {
        return getComponent(this, PageNodeTypeConstructionPredicate.class);
    }

    @Override
    protected PageDecorator transformHit(Hit hit) {

        PageDecorator pageDecorator = null;

        try {
            pageDecorator = hit.getResource().adaptTo(PageDecorator.class);
        } catch (RepositoryException e) {
            LOG.error("Failed at creating PageDecorator from a hit.", e);
        }

        return pageDecorator;

    }

    @Override
    protected List<ConstructionPredicate> getConstructionPredicates() {

        if (constructionPredicates == null) {
            constructionPredicates = Lists.newArrayList();

            constructionPredicates.add(getNodeTypeConstructionPredicate());

            if (getPathConstructionPredicate() != null) {
                constructionPredicates.add(getPathConstructionPredicate());
            }

            if (getQueryParameterConstructionPredicate() != null) {
                constructionPredicates.add(getQueryParameterConstructionPredicate());
            }
            if (getTagsConstructionPredicate() != null) {
                constructionPredicates.add(getTagsConstructionPredicate());
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
