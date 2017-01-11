package com.icfolson.aem.harbor.core.lists.construction.nodesearch.predicates.queryparameters;

import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.Option;
import com.citytechinc.cq.component.annotations.widgets.NumberField;
import com.citytechinc.cq.component.annotations.widgets.Selection;
import com.citytechinc.cq.component.annotations.widgets.TextField;
import com.day.cq.commons.jcr.JcrConstants;
import com.day.cq.search.Predicate;
import com.google.common.base.Function;
import com.icfolson.aem.library.core.components.AbstractComponent;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

/**
 * Dialog representation of a query parameter predicate. Should be converted to
 * a predicate for a query on the JCR by a parent component construction
 * strategy, thus limiting--based on given dialog input--what nodes will be
 * rendered.
 * <p>
 * Stores and handles all predicates that modify how search results are
 * returned.
 */
@Model(adaptables = Resource.class)
public class QueryParameters extends AbstractComponent {

    private static final String DEFAULT_LIMIT = "10";

    private static final String DEFAULT_SORT_TYPE = Predicate.SORT_ASCENDING;

    /**
     * @return limit on the number of results to return.
     */
    @DialogField(fieldLabel = "Limit",
        fieldDescription = "Number of results to return. Set to 0 to return all results. Default is "
            + DEFAULT_LIMIT + ".", defaultValue = DEFAULT_LIMIT, ranking = 1)
    @NumberField(allowDecimals = false, allowNegative = false)
    public String getLimit() {
        return get("limit", DEFAULT_LIMIT);
    }

    /**
     * @return a JCR field or special xpath variable to order results by.
     */
    @DialogField(fieldLabel = "Order By",
        fieldDescription = "Name of JCR property to order results by.  Leave blank for no ordering.", ranking = 2)
    @TextField
    public String getOrderBy() {
        return get("orderBy", String.class).transform(
            propertyName -> "@" + JcrConstants.JCR_CONTENT + "/" + propertyName).or("");
    }

    /**
     * @return direction to sort results in.
     */
    @DialogField(fieldLabel = "Sort", fieldDescription = "Direction to sort results if 'Order By' is set.", ranking = 3)
    @Selection(type = Selection.SELECT,
        options = {
            @Option(text = "Ascending", value = Predicate.SORT_ASCENDING),
            @Option(text = "Descending", value = Predicate.SORT_DESCENDING)
        })
    public String getSortType() {
        return get("sortType", DEFAULT_SORT_TYPE);
    }
}
