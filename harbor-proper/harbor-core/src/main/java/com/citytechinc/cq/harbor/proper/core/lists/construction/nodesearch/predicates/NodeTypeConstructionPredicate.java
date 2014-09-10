package com.citytechinc.cq.harbor.proper.core.lists.construction.nodesearch.predicates;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.citytechinc.aem.bedrock.api.node.ComponentNode;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.widgets.TextField;
import com.citytechinc.cq.harbor.proper.api.lists.construction.search.ConstructionPredicate;
import com.google.common.collect.Maps;

/**
 * Dialog representation of a node type predicate. Should be converted to a
 * predicate for a query on the JCR by a parent component construction strategy,
 * thus limiting--based on given dialog input--what nodes will be rendered.
 *
 * Stores and handles all predicates that have to do with selecting nodes based
 * on their type.
 */
public class NodeTypeConstructionPredicate implements ConstructionPredicate {

	private static final String PARAM_NODE_TYPE = "type";
	private static final String DEFAULT_NODE_TYPE = StringUtils.EMPTY;

	@DialogField(fieldLabel = "Node Type", fieldDescription = "JCR node type to search for.", defaultValue = DEFAULT_NODE_TYPE)
	@TextField
	private final String nodeType;

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
	public NodeTypeConstructionPredicate(ComponentNode componentNode, String predicateName) {

		nodeType = componentNode.get(predicateName + PARAM_NODE_TYPE, DEFAULT_NODE_TYPE);

	}

	public NodeTypeConstructionPredicate(String nodeType) {
		this.nodeType = nodeType;
	}

	/**
	 * @return node type to search for.
	 */
	public String getNodeType() {

		return nodeType;

	}

	@Override
	public Map<String, String> asQueryPredicate() {

		if (queryParameters == null) {
			queryParameters = Maps.newHashMap();
			queryParameters.put(PARAM_NODE_TYPE, getNodeType());
		}

		return queryParameters;

	}
}
