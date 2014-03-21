package com.citytechinc.cq.harbor.components.content.list.nodesearchstrategy.predicatewrappers;

import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.widgets.TextField;
import com.citytechinc.cq.library.content.node.ComponentNode;
import com.day.cq.search.eval.TypePredicateEvaluator;
import org.apache.commons.lang.StringUtils;

/**
 * Dialog representation of a node type predicate. Should be converted to a predicate for a query on the JCR by a parent
 *  component construction strategy, thus limiting--based on given dialog input--what nodes will be rendered.
 */
public class NodeTypeConstructionPredicate extends AbstractConstructionPredicate {

    private static final String PREDICATE_TYPE = "type";

    private static final String PARAM_NODE_TYPE = "nodeType";
    private static final String DEFAULT_NODE_TYPE = StringUtils.EMPTY;
    @DialogField(
            fieldLabel = "Node Type",
            fieldDescription = "JCR node type to search for.",
            defaultValue = DEFAULT_NODE_TYPE
    )
    @TextField
    private String nodeType;

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
    public NodeTypeConstructionPredicate(ComponentNode componentNode, String predicateName) {
        super(componentNode, predicateName);

        setNodeType(componentNode.get(predicateName + PARAM_NODE_TYPE, DEFAULT_NODE_TYPE));

    }

    public void setNodeType(String nodeType) {

        this.nodeType = nodeType;
        this.set(TypePredicateEvaluator.TYPE, this.nodeType);

    }

    public String getNodeType() {

        return nodeType;

    }

}
