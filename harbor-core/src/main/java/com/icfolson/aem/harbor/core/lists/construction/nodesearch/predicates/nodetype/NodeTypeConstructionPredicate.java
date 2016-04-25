package com.icfolson.aem.harbor.core.lists.construction.nodesearch.predicates.nodetype;

import com.icfolson.aem.library.core.components.AbstractComponent;
import com.day.cq.search.Predicate;
import com.day.cq.search.eval.TypePredicateEvaluator;
import com.google.common.base.Optional;
import org.apache.commons.lang.StringUtils;

import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.widgets.TextField;
import com.icfolson.aem.harbor.api.lists.construction.search.ConstructionPredicate;

/**
 * Dialog representation of a node type predicate. Should be converted to a
 * predicate for a query on the JCR by a parent component construction strategy,
 * thus limiting--based on given dialog input--what nodes will be rendered.
 *
 * Stores and handles all predicates that have to do with selecting nodes based
 * on their type.
 */
public class NodeTypeConstructionPredicate extends AbstractComponent implements ConstructionPredicate {

	private static final String PARAM_NODE_TYPE = TypePredicateEvaluator.TYPE;
	private static final String DEFAULT_NODE_TYPE = StringUtils.EMPTY;

	private Optional<Predicate> predicateOptional;

    @DialogField(fieldLabel = "Node Type", fieldDescription = "JCR node type to search for.", defaultValue = DEFAULT_NODE_TYPE)
    @TextField
	public Optional<String> getNodeType() {

		return get("nodeType", String.class);

	}

    @Override
    public Optional<Predicate> asPredicate() {

        if (predicateOptional == null) {

            Optional<String> nodeTypeOptional = getNodeType();

            if (nodeTypeOptional.isPresent()) {
                Predicate predicate = new Predicate(PARAM_NODE_TYPE);
                predicate.set(PARAM_NODE_TYPE, nodeTypeOptional.get());
                predicateOptional = Optional.of(predicate);
            }
            else {
                predicateOptional = Optional.absent();
            }

        }

        return predicateOptional;

    }
}
