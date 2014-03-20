package com.citytechinc.cq.harbor.components.content.list.nodesearchstrategy;

import com.citytechinc.cq.library.content.node.ComponentNode;
import com.day.cq.search.Predicate;

import java.util.HashMap;
import java.util.Map;

/**
 * Base class for construction predicates. Has reference to underlying Predicate object that can be used to limit a
 *  query on the JCR.
 */
abstract class AbstractConstructionPredicate {

    protected ComponentNode parentNode;

    protected Map<String, String> predicates;

    protected String predicateName;

    /**
     * Must be implemented by subclasses.
     *
     * @return a constant string indicating what type of predicate this is.
     */
    protected abstract String getPredicateType();

    /**
     * Default constructor. Properties for this class should be located under the component node, prefixed with the
     *  variable given with predicateName.
     *
     * @param componentNode Base component node that contains the properties for this predicateß.
     * @param predicateName Name of prefix for predicate's properties, also the name of this predicate when it
     *                          is used to query.
     */
    protected AbstractConstructionPredicate (ComponentNode componentNode, String predicateName) {

        this.parentNode = componentNode;
        this.predicateName = predicateName;
        this.predicates = new HashMap<String, String>();

    }

    /**
     * Set a property on the underlying predicate.
     *
     * @param paramName     name of parameter to set.
     * @param paramValue    value to give parameter.
     */
    protected void set(String paramName, String paramValue) {

        this.predicates.put(paramName, paramValue);

    }

    /**
     * @return the name used for the node holding this predicate's properties and the name of the predicate in the JCR
     *      query.
     */
    protected String getPredicateName() {

        return predicateName;

    }

    /**
     * @return component ndoe that is the parent of this predicate's node.
     */
    protected ComponentNode getParentNode () {

        return parentNode;

    }

    public Map<String, String> getPredicates() {

        return predicates;

    }

}
