package com.icfolson.aem.harbor.core.lists.construction.nodesearch.predicates.nodetype;

import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.widgets.TextField;
import com.day.cq.search.Predicate;
import com.google.common.base.Optional;
import com.icfolson.aem.harbor.api.lists.construction.search.ConstructionPredicate;
import com.icfolson.aem.library.core.components.AbstractComponent;

import static com.day.cq.search.eval.TypePredicateEvaluator.TYPE;

/**
 * Dialog representation of a node type predicate. Should be converted to a
 * predicate for a query on the JCR by a parent component construction strategy,
 * thus limiting--based on given dialog input--what nodes will be rendered.
 * <p>
 * Stores and handles all predicates that have to do with selecting nodes based
 * on their type.
 */
public class NodeTypeConstructionPredicate extends AbstractComponent implements ConstructionPredicate {

    private Optional<Predicate> predicateOptional;

    @DialogField(fieldLabel = "Node Type", fieldDescription = "JCR node type to search for.")
    @TextField
    public Optional<String> getNodeType() {
        return get("nodeType", String.class);
    }

    @Override
    public Optional<Predicate> asPredicate() {
        if (predicateOptional == null) {
            predicateOptional = getNodeType().transform(nodeType -> new Predicate(TYPE).set(TYPE, nodeType));
        }

        return predicateOptional;
    }
}
