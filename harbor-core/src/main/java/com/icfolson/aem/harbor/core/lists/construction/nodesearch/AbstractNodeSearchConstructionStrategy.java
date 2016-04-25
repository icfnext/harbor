package com.icfolson.aem.harbor.core.lists.construction.nodesearch;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.jcr.Session;

import com.icfolson.aem.library.core.components.AbstractComponent;
import com.google.common.base.Stopwatch;
import com.google.common.collect.Lists;

import com.icfolson.aem.harbor.api.lists.construction.ListConstructionStrategy;
import com.icfolson.aem.harbor.api.lists.construction.search.ConstructionPredicate;
import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.Hit;
import com.day.cq.search.result.SearchResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A list construction strategy based on query builder.
 */
public abstract class AbstractNodeSearchConstructionStrategy<T> extends AbstractComponent implements ListConstructionStrategy<T> {

    private static final Logger LOG = LoggerFactory.getLogger(AbstractNodeSearchConstructionStrategy.class);

	private PredicateGroup predicate;
    List<T> results;

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

        if (predicate == null) {

            predicate = new PredicateGroup();
            predicate.setAllRequired(true);

            for (ConstructionPredicate currentConstructionPredicate : getConstructionPredicates()) {

                if (currentConstructionPredicate.asPredicate().isPresent()) {
                    predicate.add(currentConstructionPredicate.asPredicate().get());
                }
            }

        }

        return predicate;

	}

	/**
	 * Call query builder with given predicates and return a list of results.
	 *
	 * @return List of Transformed Hits that are the results from query builder.
	 */
	protected List<T> doQuery() {

        if (results != null) {
            return results;
        }

        results = Lists.newArrayList();

        if (!isReadyToQuery()) {
            results = Lists.newArrayList();
            return results;
        }

        LOG.debug("Executing query");

        Stopwatch stopwatch = Stopwatch.createStarted();

		Query query = getResource().getResourceResolver().adaptTo(QueryBuilder.class).createQuery(getPredicateGroup(), getResource().getResourceResolver().adaptTo(Session.class));
		SearchResult result = query.getResult();

        stopwatch.stop();

        LOG.debug("Query executed in " + stopwatch.elapsed(TimeUnit.MILLISECONDS) + "ms");

		// transform results into given object
		for (Hit hit : result.getHits()) {

			T transformedHit = this.transformHit(hit);

			if (transformedHit != null) {

				results.add(transformedHit);

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
