package com.citytechinc.cq.harbor.components.content.list.nodesearchstrategy.predicatewrappers;

import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.Option;
import com.citytechinc.cq.component.annotations.widgets.NumberField;
import com.citytechinc.cq.component.annotations.widgets.Selection;
import com.citytechinc.cq.component.annotations.widgets.TextField;
import com.citytechinc.cq.library.content.node.ComponentNode;
import com.day.cq.search.Predicate;
import com.day.cq.search.PredicateGroup;
import org.apache.commons.lang.StringUtils;

/**
 * Dialog representation of a query parameter predicate. Should be converted to a predicate for a query on the JCR by a
 *  parent component construction strategy, thus limiting--based on given dialog input--what nodes will be rendered.
 *
 * Stores and handles all predicates that modify how search results are returned.
 */
public class QueryParameterConstructionPredicate extends AbstractConstructionPredicate {

    private static final String PREDICATE_TYPE = "group";

    private static final String PARAM_LIMIT = "limit";
    private static final String PARAM_QUERY_LIMIT = "p." + PredicateGroup.PARAM_LIMIT;
    private static final int DEFAULT_LIMIT = 10;
    private static final String DEFAULT_LIMIT_STR = "10";
    @DialogField(
            fieldLabel = "Limit",
            fieldDescription = "Number of results to return. Set to 0 to return all results. Default is " + DEFAULT_LIMIT_STR + ".",
            defaultValue = DEFAULT_LIMIT_STR
    )
    @NumberField(
            allowDecimals = false,
            allowNegative = false
    )
    private int limit;

    private static final String PARAM_ORDER_BY = "orderBy";
    private static final String PARAM_QUERY_ORDER_BY = Predicate.ORDER_BY;
    private static final String DEFAULT_ORDER_BY = StringUtils.EMPTY;
    @DialogField(
            fieldLabel = "Order By",
            fieldDescription = "Name of JCR property to order results by. Leave blank for no ordering.",
            defaultValue = DEFAULT_ORDER_BY
    )
    @TextField
    private String orderBy;

    private static final String PARAM_SORT_TYPE = "sortType";
    private static final String PARAM_QUERY_SORT_TYPE = Predicate.ORDER_BY + '.' + Predicate.PARAM_SORT;
    private static final String DEFAULT_SORT_TYPE = Predicate.SORT_ASCENDING;
    @DialogField(
            fieldLabel = "Sort Direction",
            fieldDescription = "Direction to sort results if 'Order By' is set."
    )
    @Selection(
            type = Selection.SELECT,
            options = {
                    @Option( text = "Ascending", value = Predicate.SORT_ASCENDING ),
                    @Option( text = "Descending", value = Predicate.SORT_DESCENDING)
            }
    )
    private String sortType;

    @Override
    protected String getPredicateType() {

        return PREDICATE_TYPE;

    }

    /**
     * Default constructor. Properties for this class should be located under the component node, prefixed with the
     * variable given with predicateName.
     *
     * @param componentNode Base component node that contains the properties for this predicateß.
     * @param predicateName Name of prefix for predicate's properties, also the name of this predicate when it
     */
    public QueryParameterConstructionPredicate(ComponentNode componentNode, String predicateName) {
        super(componentNode, predicateName);

        setLimit(componentNode.get(predicateName + PARAM_LIMIT, DEFAULT_LIMIT));
        setOrderBy(componentNode.get(predicateName + PARAM_ORDER_BY, DEFAULT_ORDER_BY));
        setSortType(componentNode.get(predicateName + PARAM_SORT_TYPE, DEFAULT_SORT_TYPE));

    }

    /**
     * @return limit on the number of results to return.
     */
    public int getLimit() {

        return limit;

    }

    /**
     * Set the maximum number of results to return.
     *
     * @param limit
     */
    public void setLimit(int limit) {

        this.limit = limit;
        this.set(PARAM_QUERY_LIMIT, String.valueOf(limit));

    }

    /**
     * @return  a JCR field or special xpath variable to order results by.
     */
    public String getOrderBy() {

        return orderBy;

    }

    /**
     * Set a JCR field or special xpath variable to order results by. This predicate will only be added to a query if
     *  it is not blank.
     *
     * @param orderBy
     */
    public void setOrderBy(String orderBy) {

        this.orderBy = orderBy;
        if(StringUtils.isNotBlank(orderBy)) {

            // add this predicate only if order by isn't blank
            this.set(PARAM_QUERY_ORDER_BY, orderBy);

        }

    }

    /**
     * @return  direction to sort results in.
     */
    public String getSortType() {

        return sortType;

    }

    /**
     * Set the direction to sort results in. This predicate will only be added to a query if it is not blank.
     *
     * @param sortType
     */
    public void setSortType(String sortType) {

        this.sortType = sortType;
        if(StringUtils.isNotBlank(this.orderBy)) {

            // add this predicate only if order by isn't blank
            this.set(PARAM_QUERY_SORT_TYPE, sortType);

        }

    }

}
