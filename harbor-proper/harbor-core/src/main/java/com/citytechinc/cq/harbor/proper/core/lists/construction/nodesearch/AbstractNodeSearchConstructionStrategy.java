package com.citytechinc.cq.harbor.proper.core.lists.construction.nodesearch;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.jcr.Session;

import com.google.common.base.Stopwatch;
import com.google.common.collect.Lists;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;

import com.citytechinc.aem.bedrock.api.node.ComponentNode;
import com.citytechinc.cq.harbor.proper.api.lists.construction.ListConstructionStrategy;
import com.citytechinc.cq.harbor.proper.api.lists.construction.search.ConstructionPredicate;
import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.Hit;
import com.day.cq.search.result.SearchResult;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A list construction strategy based on query builder.
 */
public abstract class AbstractNodeSearchConstructionStrategy<T> implements ListConstructionStrategy<T> {

    private static final Logger LOG = LoggerFactory.getLogger(AbstractNodeSearchConstructionStrategy.class);

	private Map<String, String> predicatesMap;

	private final QueryBuilder queryBuilder;

	private final Session session;

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

	/**
	 * Default constructor.
	 *
	 * @param componentNode used to get session that is used for query builder
	 *            queries.
	 */
	protected AbstractNodeSearchConstructionStrategy(ComponentNode componentNode) {

		this.session = componentNode.getResource().getResourceResolver().adaptTo(Session.class);

        //TODO: Cleanup so that it doesn't rely on framework utils to get a service reference
		// get query builder resource from bundle context
		BundleContext bundleContext = FrameworkUtil.getBundle(QueryBuilder.class).getBundleContext();
		ServiceReference serviceReference = bundleContext.getServiceReference(QueryBuilder.class.getName());
		this.queryBuilder = (QueryBuilder) bundleContext.getService(serviceReference);

	}

	protected abstract List<ConstructionPredicate> getPredicates();

	protected Map<String, String> getPredicatesMap() {

		if (predicatesMap == null) {
			predicatesMap = Maps.newHashMap();
			for (ConstructionPredicate currentConstructionPredicate : getPredicates()) {
				predicatesMap.putAll(currentConstructionPredicate.asQueryPredicate());
			}

            LOG.debug("Returning predicate map " + predicatesMap.toString());
		}

		return predicatesMap;

	}

	/**
	 * Call query builder with given predicates and return a list of results.
	 *
	 * @return List of Transformed Hits that are the results from query builder.
	 */
	protected List<T> doQuery() {

        if (!isReadyToQuery()) {
            return Lists.newArrayList();
        }

        LOG.debug("Executing query");

        Stopwatch stopwatch = Stopwatch.createStarted();

		// perform query
		PredicateGroup predicateGroup = PredicateGroup.create(getPredicatesMap());
		Query query = this.queryBuilder.createQuery(predicateGroup, this.session);
		SearchResult result = query.getResult();

        stopwatch.stop();

        LOG.debug("Query executed in " + stopwatch.elapsed(TimeUnit.MILLISECONDS) + "ms");

		// transform results into given object
		List<T> transformedHits = new ArrayList<T>();
		for (Hit hit : result.getHits()) {

			T transformedHit = this.transformHit(hit);

			if (transformedHit != null) {

				transformedHits.add(transformedHit);

			}

		}

        LOG.debug("Transformation completed");

		return transformedHits;

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
