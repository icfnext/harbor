package com.citytechinc.aem.harbor.core.components.content.list.page;

import com.citytechinc.aem.bedrock.api.node.ComponentNode;
import com.citytechinc.aem.bedrock.api.page.PageDecorator;
import com.citytechinc.aem.harbor.api.lists.construction.search.ConstructionPredicate;
import com.citytechinc.aem.harbor.core.lists.construction.nodesearch.AbstractNodeSearchConstructionStrategy;
import com.citytechinc.aem.harbor.core.lists.construction.nodesearch.predicates.NodeTypeConstructionPredicate;
import com.citytechinc.aem.harbor.core.lists.construction.nodesearch.predicates.PathConstructionPredicate;
import com.citytechinc.aem.harbor.core.lists.construction.nodesearch.predicates.QueryParameterConstructionPredicate;
import com.citytechinc.aem.harbor.core.lists.construction.nodesearch.predicates.TagsConstructionPredicate;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.widgets.DialogFieldSet;
import com.day.cq.search.result.Hit;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.resource.ResourceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.RepositoryException;
import java.util.List;

public class PageListConstructionStrategy extends AbstractNodeSearchConstructionStrategy<PageDecorator> {

    private static final Logger LOG = LoggerFactory.getLogger(PageListConstructionStrategy.class);

    private final NodeTypeConstructionPredicate nodeTypeConstructionPredicate;

    private static final String PROP_PREFIX_PATH_PREDICATE = "1";
    @DialogField
    @DialogFieldSet(title = "Path", namePrefix = PROP_PREFIX_PATH_PREDICATE, collapsible = true, collapsed = true)
    private final PathConstructionPredicate pathConstructionPredicate;

    private static final String PROP_PREFIX_TAGS_PREDICATE = "2";
    @DialogField
    @DialogFieldSet(title = "Tags", namePrefix = PROP_PREFIX_TAGS_PREDICATE, collapsible = true, collapsed = true)
    private final TagsConstructionPredicate tagsConstructionPredicate;

    private static final String PROP_PREFIX_QUERY_PARAM_PREDICATE = "3";
    @DialogField
    @DialogFieldSet(title = "Query Parameters", namePrefix = PROP_PREFIX_QUERY_PARAM_PREDICATE, collapsible = true, collapsed = true)
    private final QueryParameterConstructionPredicate queryParameterConstructionPredicate;

    private final ComponentNode componentNode;

    private List<ConstructionPredicate> constructionPredicates;

    protected PageListConstructionStrategy(ComponentNode componentNode) {
        super(componentNode);

        this.componentNode = componentNode;

        // add author specified path predicate
        pathConstructionPredicate = new PathConstructionPredicate(componentNode, PROP_PREFIX_PATH_PREDICATE);

        // add author specified tags predicate
        tagsConstructionPredicate = new TagsConstructionPredicate(componentNode, PROP_PREFIX_TAGS_PREDICATE);

        // add author specified query predicate
        queryParameterConstructionPredicate = new QueryParameterConstructionPredicate(componentNode,
                PROP_PREFIX_QUERY_PARAM_PREDICATE);

        // add type predicate to narrow search down to only asset nodes
        nodeTypeConstructionPredicate = new NodeTypeConstructionPredicate("cq:PageContent");
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
    protected List<ConstructionPredicate> getPredicates() {
        if (constructionPredicates == null) {
            constructionPredicates = Lists.newArrayList(pathConstructionPredicate, tagsConstructionPredicate,
                    queryParameterConstructionPredicate, nodeTypeConstructionPredicate);
        }

        return constructionPredicates;
    }

    @Override
    protected boolean isReadyToQuery() {
        return
                componentNode.getResource() != null &&
                        !ResourceUtil.isNonExistingResource(componentNode.getResource()) &&
                        !ResourceUtil.isSyntheticResource(componentNode.getResource()) &&
                        StringUtils.isNotBlank(pathConstructionPredicate.getPath());
    }

}
