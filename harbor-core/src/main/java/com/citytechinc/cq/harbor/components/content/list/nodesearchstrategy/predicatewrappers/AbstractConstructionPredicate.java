package com.citytechinc.cq.harbor.components.content.list.nodesearchstrategy.predicatewrappers;

import com.citytechinc.cq.library.content.node.ComponentNode;

import java.util.HashMap;
import java.util.Map;

/**
 * Base class for construction predicates. Has reference to underlying Predicate object that can be used to limit a
 *  query on the JCR.
 */
public abstract class AbstractConstructionPredicate {

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
     * @return name of this predicate, used as prefix for properties.
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

    /**
     * @return the map that stores the predicates to be used at query time.
     */
    public Map<String, String> getPredicates() {

        return predicates;

    }

}
