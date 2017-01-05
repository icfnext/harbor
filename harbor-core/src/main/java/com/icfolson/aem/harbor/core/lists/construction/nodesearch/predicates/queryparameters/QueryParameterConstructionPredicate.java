package com.icfolson.aem.harbor.core.lists.construction.nodesearch.predicates.queryparameters;

import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.Option;
import com.citytechinc.cq.component.annotations.widgets.NumberField;
import com.citytechinc.cq.component.annotations.widgets.Selection;
import com.citytechinc.cq.component.annotations.widgets.TextField;
import com.day.cq.search.Predicate;
import com.day.cq.search.PredicateGroup;
import com.google.common.base.Optional;
import com.icfolson.aem.harbor.api.lists.construction.search.ConstructionPredicate;
import com.icfolson.aem.library.core.components.AbstractComponent;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

/**
 * Dialog represenwtation of a query parameter predicate. Should be converted to
 * a predicate for a query on the JCR by a parent component construction
 * strategy, thus limiting--based on given dialog input--what nodes will be
 * rendered.
 * <p>
 * Stores and handles all predicates that modify how search results are
 * returned.
 */
@Model(adaptables = Resource.class)
public class QueryParameterConstructionPredicate extends AbstractComponent implements ConstructionPredicate {

    private static final String PARAM_LIMIT = "limit";

    private static final int DEFAULT_LIMIT = 10;

    private static final String DEFAULT_LIMIT_STR = "10";

    private static final String PARAM_ORDER_BY = "orderBy";

    private static final String DEFAULT_ORDER_BY = "nodename";

    private static final String PARAM_SORT_TYPE = "sortType";

    private static final String DEFAULT_SORT_TYPE = Predicate.SORT_ASCENDING;

    private Optional<Predicate> predicateOptional;

    /**
     * @return limit on the number of results to return.
     */
    @DialogField(fieldLabel = "Limit",
        fieldDescription = "Number of results to return. Set to 0 to return all results. Default is "
            + DEFAULT_LIMIT_STR + ".", defaultValue = DEFAULT_LIMIT_STR)
    @NumberField(allowDecimals = false, allowNegative = false)
    public int getLimit() {
        return get(PARAM_LIMIT, DEFAULT_LIMIT);
    }

    /**
     * @return a JCR field or special xpath variable to order results by.
     */
    @DialogField(fieldLabel = "Order By",
        fieldDescription = "Name of JCR property to order results by. Leave blank for no ordering.",
        defaultValue = DEFAULT_ORDER_BY)
    @TextField
    public String getOrderBy() {
        return get(PARAM_ORDER_BY, DEFAULT_ORDER_BY);
    }

    /**
     * @return direction to sort results in.
     */
    @DialogField(fieldLabel = "Sort Direction", fieldDescription = "Direction to sort results if 'Order By' is set.")
    @Selection(type = Selection.SELECT,
        options = {
            @Option(text = "Ascending", value = Predicate.SORT_ASCENDING),
            @Option(text = "Descending", value = Predicate.SORT_DESCENDING)
        })
    public String getSortType() {
        return get(PARAM_SORT_TYPE, DEFAULT_SORT_TYPE);
    }

    @Override
    public Optional<Predicate> asPredicate() {
        if (predicateOptional == null) {
            final PredicateGroup predicateGroup = new PredicateGroup();

            predicateGroup.setAllRequired(true);
            predicateGroup.add(createPredicate(Predicate.ORDER_BY, getOrderBy()));
            predicateGroup.add(createPredicate(Predicate.PARAM_SORT, getSortType()));

            if (getLimit() > 0) {
                predicateGroup.add(createPredicate(Predicate.PARAM_LIMIT, String.valueOf(getLimit())));
            }

            predicateOptional = Optional.of(predicateGroup);
        }

        return predicateOptional;
    }

    private Predicate createPredicate(final String type, final String value) {
        return new Predicate(type).set(type, value);
    }
}
