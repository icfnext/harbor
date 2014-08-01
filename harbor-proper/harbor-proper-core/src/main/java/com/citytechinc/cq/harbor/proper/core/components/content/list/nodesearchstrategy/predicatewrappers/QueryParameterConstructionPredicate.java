package com.citytechinc.cq.harbor.proper.core.components.content.list.nodesearchstrategy.predicatewrappers;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.citytechinc.aem.bedrock.api.node.ComponentNode;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.Option;
import com.citytechinc.cq.component.annotations.widgets.NumberField;
import com.citytechinc.cq.component.annotations.widgets.Selection;
import com.citytechinc.cq.component.annotations.widgets.TextField;
import com.citytechinc.cq.harbor.proper.api.lists.construction.search.ConstructionPredicate;
import com.day.cq.search.Predicate;
import com.day.cq.search.PredicateGroup;
import com.google.common.collect.Maps;

/**
 * Dialog representation of a query parameter predicate. Should be converted to
 * a predicate for a query on the JCR by a parent component construction
 * strategy, thus limiting--based on given dialog input--what nodes will be
 * rendered.
 *
 * Stores and handles all predicates that modify how search results are
 * returned.
 */
public class QueryParameterConstructionPredicate implements ConstructionPredicate {

	private static final String PARAM_LIMIT = "limit";
	private static final String PARAM_QUERY_LIMIT = "p." + PredicateGroup.PARAM_LIMIT;
	private static final int DEFAULT_LIMIT = 10;
	private static final String DEFAULT_LIMIT_STR = "10";
	@DialogField(fieldLabel = "Limit", fieldDescription = "Number of results to return. Set to 0 to return all results. Default is "
		+ DEFAULT_LIMIT_STR + ".", defaultValue = DEFAULT_LIMIT_STR)
	@NumberField(allowDecimals = false, allowNegative = false)
	private final int limit;

	private static final String PARAM_ORDER_BY = "orderBy";
	private static final String PARAM_QUERY_ORDER_BY = Predicate.ORDER_BY;
	private static final String DEFAULT_ORDER_BY = "nodename";
	@DialogField(fieldLabel = "Order By", fieldDescription = "Name of JCR property to order results by. Leave blank for no ordering.", defaultValue = DEFAULT_ORDER_BY)
	@TextField
	private final String orderBy;

	private static final String PARAM_SORT_TYPE = "sortType";
	private static final String PARAM_QUERY_SORT_TYPE = Predicate.ORDER_BY + '.' + Predicate.PARAM_SORT;
	private static final String DEFAULT_SORT_TYPE = Predicate.SORT_ASCENDING;
	@DialogField(fieldLabel = "Sort Direction", fieldDescription = "Direction to sort results if 'Order By' is set.")
	@Selection(type = Selection.SELECT, options = { @Option(text = "Ascending", value = Predicate.SORT_ASCENDING),
		@Option(text = "Descending", value = Predicate.SORT_DESCENDING) })
	private final String sortType;

	private Map<String, String> queryParameters;

	/**
	 * Default constructor. Properties for this class should be located under
	 * the component node, prefixed with the variable given with predicateName.
	 *
	 * @param componentNode Base component node that contains the properties for
	 *            this predicate.
	 * @param predicateName Name of prefix for predicate's properties, also the
	 *            name of this predicate when it is used to query.
	 */
	public QueryParameterConstructionPredicate(ComponentNode componentNode, String predicateName) {

		limit = componentNode.get(predicateName + PARAM_LIMIT, DEFAULT_LIMIT);
		orderBy = componentNode.get(predicateName + PARAM_ORDER_BY, DEFAULT_ORDER_BY);
		sortType = componentNode.get(predicateName + PARAM_SORT_TYPE, DEFAULT_SORT_TYPE);

	}

	/**
	 * @return limit on the number of results to return.
	 */
	public int getLimit() {

		return limit;

	}

	/**
	 * @return a JCR field or special xpath variable to order results by.
	 */
	public String getOrderBy() {

		return orderBy;

	}

	/**
	 * @return direction to sort results in.
	 */
	public String getSortType() {

		return sortType;

	}

	@Override
	public Map<String, String> asQueryPredicate() {

		if (queryParameters == null) {
			queryParameters = Maps.newHashMap();
			queryParameters.put(PARAM_QUERY_LIMIT, String.valueOf(getLimit()));

			if (StringUtils.isNotBlank(getOrderBy())) {
				queryParameters.put(PARAM_QUERY_ORDER_BY, getOrderBy());
			}

			if (StringUtils.isNotBlank(getSortType())) {
				queryParameters.put(PARAM_QUERY_SORT_TYPE, getSortType());
			}
		}

		return queryParameters;
	}
}
