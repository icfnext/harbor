package com.citytechinc.cq.harbor.components.content.list.nodesearchstrategy.predicatewrappers;

import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.widgets.PathField;
import com.citytechinc.cq.library.content.node.ComponentNode;
import com.day.cq.search.eval.NodenamePredicateEvaluator;
import com.day.cq.search.eval.PathPredicateEvaluator;
import org.apache.commons.lang.StringUtils;

/**
 * Dialog representation of a path predicate. Should be converted to a predicate for a query on the JCR by a parent
 *  component construction strategy, thus limiting--based on given dialog input--what nodes will be rendered.
 *
 * Stores and handles all predicates that have to do with selecting nodes based on their path in the JCR.
 */
public class PathConstructionPredicate extends AbstractConstructionPredicate {

    private static final String PARAM_PATH = "path";
    private static final String PARAM_QUERY_PATH = PathPredicateEvaluator.PATH;
    private static final String DEFAULT_PARAM_PATH = "/";
    @DialogField(
            fieldLabel = "Path",
            fieldDescription = "Path to search for nodes under."
    )
    @PathField(
            rootPath = DEFAULT_PARAM_PATH
    )
    private String path;


    private static final String PARAM_NODE_NAME = "nodeName";
    private static final String PARAM_QUERY_NODE_NAME = NodenamePredicateEvaluator.NODENAME;
    private static final String DEFAULT_NODE_NAME = StringUtils.EMPTY;
    @DialogField(
            fieldLabel = "Node Name",
            fieldDescription = "Node name to search for. Use wildcard '*' to match multiple children. Leave empty to search for all children under path."
    )
    @PathField(
            rootPath = DEFAULT_NODE_NAME
    )
    private String nodeName;

    /**
     * Default constructor. Properties for this class should be located under the component node, prefixed with the
     * variable given with predicateName.
     *
     * @param componentNode Base component node that contains the properties for this predicate.
     * @param predicateName Name of prefix for predicate's properties, also the name of this predicate when it is used to query.
     */
    public PathConstructionPredicate(ComponentNode componentNode, String predicateName) {
        super(componentNode, predicateName);

        setPath(componentNode.get(predicateName + PARAM_PATH, DEFAULT_PARAM_PATH));
        setNodeName(componentNode.get(predicateName + PARAM_NODE_NAME, DEFAULT_NODE_NAME));

    }

    /**
     * Set path to search for nodes under.
     *
     * @param path
     */
    public void setPath(String path) {

        this.path = path;
        this.set(PARAM_QUERY_PATH, path);

    }

    /**
     * @return  path to search for nodes under.
     */
    public String getPath() {

        return this.path;

    }

    /**
     * Set a specific node to search for. Can include wildcard characters to return multiple nodes, eg '*.jpg would return
     *  all nodes ending in '.jpg' under given path.
     *
     * @param nodeName
     */
    public void setNodeName(String nodeName) {

        this.nodeName = nodeName;
        if(StringUtils.isNotBlank(nodeName)) {

            // set this predicate only if it's not blank
            this.set(PARAM_QUERY_NODE_NAME, nodeName);

        }

    }

    /**
     * @return  get the specific node that will be searched for.
     */
    public String getNodeName() {

        return nodeName;

    }

}
