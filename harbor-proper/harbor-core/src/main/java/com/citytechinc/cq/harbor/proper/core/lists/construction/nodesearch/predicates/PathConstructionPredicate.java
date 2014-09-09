package com.citytechinc.cq.harbor.proper.core.lists.construction.nodesearch.predicates;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.citytechinc.aem.bedrock.api.node.ComponentNode;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.widgets.PathField;
import com.citytechinc.cq.harbor.proper.api.lists.construction.search.ConstructionPredicate;
import com.day.cq.search.eval.NodenamePredicateEvaluator;
import com.day.cq.search.eval.PathPredicateEvaluator;
import com.google.common.collect.Maps;

/**
 * Dialog representation of a path predicate. Should be converted to a predicate
 * for a query on the JCR by a parent component construction strategy, thus
 * limiting--based on given dialog input--what nodes will be rendered.
 *
 * Stores and handles all predicates that have to do with selecting nodes based
 * on their path in the JCR.
 */
public class PathConstructionPredicate implements ConstructionPredicate {

	private static final String PARAM_PATH = "path";
	private static final String PARAM_QUERY_PATH = PathPredicateEvaluator.PATH;
	private static final String DEFAULT_PARAM_PATH = "";

	@DialogField(fieldLabel = "Path", fieldDescription = "Path to search for nodes under.")
	@PathField(rootPath = DEFAULT_PARAM_PATH)
	private final String path;

	private static final String PARAM_NODE_NAME = "nodeName";
	private static final String PARAM_QUERY_NODE_NAME = NodenamePredicateEvaluator.NODENAME;
	private static final String DEFAULT_NODE_NAME = StringUtils.EMPTY;
	@DialogField(fieldLabel = "Node Name", fieldDescription = "Node name to search for. Use wildcard '*' to match multiple children. Leave empty to search for all children under path.")
	@PathField(rootPath = DEFAULT_NODE_NAME)
	private final String nodeName;

	private Map<String, String> queryPredicates;

	/**
	 * Default constructor. Properties for this class should be located under
	 * the component node, prefixed with the variable given with predicateName.
	 *
	 * @param componentNode Base component node that contains the properties for
	 *            this predicate.
	 * @param predicateName Name of prefix for predicate's properties, also the
	 *            name of this predicate when it is used to query.
	 */
	public PathConstructionPredicate(ComponentNode componentNode, String predicateName) {

		path = componentNode.get(predicateName + PARAM_QUERY_PATH, DEFAULT_PARAM_PATH);
		nodeName = componentNode.get(predicateName + PARAM_QUERY_NODE_NAME, DEFAULT_NODE_NAME);

	}

	/**
	 * @return path to search for nodes under.
	 */
	public String getPath() {

		return this.path;

	}

	/**
	 * @return get the specific node that will be searched for.
	 */
	public String getNodeName() {

		return nodeName;

	}

	@Override
	public Map<String, String> asQueryPredicate() {
		if (queryPredicates == null) {
			queryPredicates = Maps.newHashMap();
			queryPredicates.put(PARAM_PATH, getPath());

			if (StringUtils.isNotBlank(getNodeName())) {
				queryPredicates.put(PARAM_NODE_NAME, getNodeName());
			}
		}

		return queryPredicates;
	}
}
