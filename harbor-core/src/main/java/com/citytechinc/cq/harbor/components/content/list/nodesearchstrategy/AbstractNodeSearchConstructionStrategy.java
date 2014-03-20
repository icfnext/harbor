package com.citytechinc.cq.harbor.components.content.list.nodesearchstrategy;

import com.citytechinc.cq.harbor.components.content.list.ListConstructionStrategy;
import com.citytechinc.cq.library.content.node.ComponentNode;
import com.day.cq.search.Predicate;
import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.Hit;
import com.day.cq.search.result.SearchResult;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;

import javax.jcr.Session;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A list construction strategy based on query builder.
 */
public abstract class AbstractNodeSearchConstructionStrategy<T> implements ListConstructionStrategy<T> {

    private Map<String, String> predicatesMap;

    private QueryBuilder queryBuilder;

    private Session session;

    /**
     * Must be implemented by subclasses.
     *
     * Subclass-defined transformation function that will take a result from query builder and transform it into given
     *  object. When list is returned form construction strategy object, it will be casted to the correct objects.
     *
     * @param hit   Hit returned from query builder.
     * @return the transformed hit object, or null if an error occurs.
     */
    protected abstract T transformHit(Hit hit);

    /**
     * Default constructor.
     *
     * @param componentNode used to get session that is used for query builder queries.
     */
    protected AbstractNodeSearchConstructionStrategy(ComponentNode componentNode) {

        this.session = componentNode.getResource().getResourceResolver().adaptTo(Session.class);

        this.predicatesMap = new HashMap<String, String>();

        BundleContext bundleContext = FrameworkUtil.getBundle(QueryBuilder.class).getBundleContext();
        ServiceReference serviceReference = bundleContext.getServiceReference(QueryBuilder.class.getName());
        this.queryBuilder = (QueryBuilder) bundleContext.getService(serviceReference);

    }

    /**
     * Each construction predicate a construction strategy has should be added via this function so that the predicate
     *  can be used when constructing the list via query builder.
     *
     * @param abstractConstructionPredicate construction predicate to add.
     */
    protected void addPredicate(AbstractConstructionPredicate abstractConstructionPredicate) {

        Map<String, String> predicates = abstractConstructionPredicate.getPredicates();
        this.predicatesMap.putAll(predicates);

    }

    /**
     * Call query builder with given predicates and return a list of results.
     *
     * @return  a list of Hits that are the results from query builder.
     */
    private List<T> doQuery() {

        // perform query
        PredicateGroup predicateGroup = PredicateGroup.create(this.predicatesMap);
        Query query = queryBuilder.createQuery(predicateGroup, this.session);
        SearchResult result = query.getResult();

        // transform results into given object
        List<T> transformedHits = new ArrayList<T>();
        for(Hit hit : result.getHits()) {

            T transformedHit = transformHit(hit);

            if(transformedHit != null) {

                transformedHits.add(transformedHit);

            }

        }

        return transformedHits;

    }

    /**
     * Construct list of objects based on query with given predicates, objects will have been transformed by subclass-
     *  defined transform function.
     *
     * @return  a list of objects for the construction strategy.
     */
    @Override
    public List<T> constructList() {

        return doQuery();

    }

}
