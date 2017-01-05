package com.icfolson.aem.harbor.core.lists.construction.nodesearch;

import com.day.cq.search.Predicate;
import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.Hit;
import com.day.cq.search.result.SearchResult;
import com.google.common.base.Optional;
import com.google.common.base.Stopwatch;
import com.icfolson.aem.harbor.api.lists.construction.ListConstructionStrategy;
import com.icfolson.aem.harbor.api.lists.construction.search.ConstructionPredicate;
import com.icfolson.aem.library.core.components.AbstractComponent;
import org.apache.sling.api.resource.ResourceResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.Session;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * A list construction strategy based on query builder.
 */
public abstract class AbstractNodeSearchConstructionStrategy<T> extends AbstractComponent implements ListConstructionStrategy<T> {

    private static final Logger LOG = LoggerFactory.getLogger(AbstractNodeSearchConstructionStrategy.class);

    private PredicateGroup predicateGroup;

    private List<T> results;

    /**
     * Subclass-defined transformation function that will take a result from
     * query builder and transform it into given object. When list is returned
     * form construction strategy object, it will be casted to the correct
     * objects.
     *
     * @param hit Hit returned from query builder.
     * @return the transformed hit object, or null if an error occurs.
     */
    protected abstract T transformHit(Hit hit);

    protected abstract List<ConstructionPredicate> getConstructionPredicates();

    protected PredicateGroup getPredicateGroup() {
        if (predicateGroup == null) {
            predicateGroup = new PredicateGroup();
            predicateGroup.setAllRequired(true);

            for (ConstructionPredicate constructionPredicate : getConstructionPredicates()) {
                final Optional<Predicate> predicate = constructionPredicate.asPredicate();

                if (predicate.isPresent()) {
                    predicateGroup.add(predicate.get());
                }
            }

            LOG.info("predicate group = {}", predicateGroup);
        }

        return predicateGroup;
    }

    /**
     * Call query builder with given predicates and return a list of results.
     *
     * @return List of Transformed Hits that are the results from query builder.
     */
    protected List<T> doQuery() {
        if (results == null) {
            if (isReadyToQuery()) {
                LOG.info("Executing query");

                final Stopwatch stopwatch = Stopwatch.createStarted();

                final ResourceResolver resourceResolver = getResource().getResourceResolver();

                final Query query = resourceResolver.adaptTo(QueryBuilder.class).createQuery(getPredicateGroup(),
                    resourceResolver.adaptTo(Session.class));

                final SearchResult result = query.getResult();

                LOG.info("Query executed in {}ms", stopwatch.elapsed(TimeUnit.MILLISECONDS));

                results = result.getHits()
                    .stream()
                    .map(this :: transformHit)
                    .filter(Objects:: nonNull)
                    .collect(Collectors.toList());

                LOG.info("returning {} results", results.size());
            } else {
                results = Collections.emptyList();

                LOG.info("not ready to query, returning empty result set");
            }
        }

        return results;
    }

    /**
     * Construct list of objects based on query with given predicates, objects
     * will have been transformed by subclass- defined transform function.
     *
     * @return a list of objects for the construction strategy.
     */
    @Override
    public List<T> construct() {
        return doQuery();
    }

    /**
     * Indicates whether this strategy is ready to run the query in question.
     * In general, queries of un-authored components should not be executed
     */
    protected abstract boolean isReadyToQuery();
}
