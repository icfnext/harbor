package com.citytechinc.cq.harbor.components.content.list.nodesearchstrategy.predicatewrappers;

import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.Option;
import com.citytechinc.cq.component.annotations.widgets.Selection;
import com.citytechinc.cq.component.annotations.widgets.TagInputField;
import com.citytechinc.cq.component.annotations.widgets.TextField;
import com.citytechinc.cq.library.content.node.ComponentNode;
import com.day.cq.tagging.Tag;
import com.day.cq.tagging.TagConstants;
import com.day.cq.tagging.TagManager;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Dialog representation of a tag predicate. Should be converted to a predicate for a query on the JCR by a parent
 *  component construction strategy, thus limiting--based on given dialog input--what nodes will be rendered.
 *
 * Stores and handles all predicates that have to do with selecting nodes based on their tags.
 */
public class TagsConstructionPredicate extends AbstractConstructionPredicate {

    private static final String PREDICATE_PROPERTY = "property";
    private static final String PREDICATE_VALUE = "value";
    private static final String PREDICATE_AND = "and";

    private TagManager tagManager;

    private static final String PARAM_COMPOSE_WITH_AND = "composeWithAnd";
    private static final boolean DEFAULT_COMPOSE_WITH_AND = false;
    @DialogField(
            fieldLabel = "Use AND Logic",
            fieldDescription = "Check box to search for tags using AND logic, otherwise search will use OR logic."
    )
    @Selection(
            type = Selection.CHECKBOX,
            options = @Option( value = "true" )
    )
    private boolean composeWithAnd;

    private static final String PARAM_REL_PATH = "relPath";
    private static final String DEFAULT_REL_PATH = StringUtils.EMPTY;
    @DialogField(
            fieldLabel = "Property Relative Path",
            fieldDescription = "Relative path from nodes being searched to the node where their tag property is stored. For example, if searching for dam:Assets, you would set this field to 'jcr:content/metadata'. Leave blank to search for tags on result nodes themselves."
    )
    @TextField
    private String relPath;

    private static final String PARAM_TAGS = "tags";
    @DialogField(
            fieldLabel = "Tags",
            fieldDescription = "List of tags to filter on."
    )
    @TagInputField
    private List<Tag> tags;

    /**
     * Redefine function so that predicates can be built properly without complex underlying logic.
     *
     * @return  a map of predicates that can be used to search for nodes based on selected tags.
     */
    @Override
    public Map<String, String> getPredicates() {

        // initialize predicates as a new map
        predicates = new HashMap<String, String>();

        // only add anything to predicates if there are some tags to filter on
        if(tags.size() > 0) {

            // specify some useful variables
            String predicateNameComplete = this.predicateName + "_" + PREDICATE_PROPERTY;

            // add main property predicate
            predicates.put(predicateNameComplete, this.getRelPath() + TagConstants.PN_TAGS);

            // add and specifier if necessary
            if (composeWithAnd) {

                predicates.put(predicateNameComplete + "." + PREDICATE_AND, "true");

            }

            // add tag values to predicates
            for (int i = 0; i < tags.size(); i++) {

                // get tag and add it to predicate list
                Tag tag = tags.get(i);
                predicates.put(predicateNameComplete + "." + i + "_" + PREDICATE_VALUE, tag.getTagID());

            }

        }

        return predicates;

    }

    /**
     * Default constructor. Properties for this class should be located under the component node, prefixed with the
     * variable given with predicateName.
     *
     * @param componentNode Base component node that contains the properties for this predicate.
     * @param predicateName Name of prefix for predicate's properties, also the name of this predicate when it is used to query.
     */
    public TagsConstructionPredicate(ComponentNode componentNode, String predicateName) {
        super(componentNode, predicateName);

        tagManager = componentNode.getResource().getResourceResolver().adaptTo(TagManager.class);

        setComposeWithAnd(componentNode.get(predicateName + PARAM_COMPOSE_WITH_AND, DEFAULT_COMPOSE_WITH_AND));
        setRelPath(componentNode.get(predicateName + PARAM_REL_PATH, DEFAULT_REL_PATH));
        setTags(componentNode.get(predicateName + PARAM_TAGS, new String[] {}));

    }

    /**
     * @return  true if tags should logically be evaluated with AND, otherwise they will be evaluated with OR logic.
     */
    public boolean getComposeWithAnd() {

        return composeWithAnd;

    }

    /**
     * Set the logic used to evaluate tags. If true, tags will be logically evaluated with AND, otherwise they will be
     *  evaluated with OR logic.
     *
     * @param composeWithAnd
     */
    public void setComposeWithAnd(boolean composeWithAnd) {

        this.composeWithAnd = composeWithAnd;

    }

    public String getRelPath() {

        return relPath;

    }

    /**
     * Set the relative path from the search result node to where its properties are stored. For example, when searching
     *  for dam:Asset nodes, you probably want to search on the asset's metadata node for properties, so the value of this
     *  variable would be 'jcr:content/metadata'.
     *
     * @param relPath
     */
    public void setRelPath(String relPath) {

        // add missing end parenthesis if necessary
        if(StringUtils.isNotBlank(relPath) && !StringUtils.endsWith(relPath, "/")) {

            relPath = relPath + '/';

        }

        this.relPath = relPath;

    }

    /**
     * @return tags that will be used for query.
     */
    public List<Tag> getTags() {

        return tags;

    }

    /**
     * Set the tags that will be used to query.
     *
     * @param tags
     */
    public void setTags(List<Tag> tags) {

        this.tags = tags;

    }

    /**
     * Set underlying tag list for query via an array of tag ids. Tag objects will only be added for tag ids that
     *  successfully resolve to a tag in the JCR.
     *
     * @param tagIds
     */
    public void setTags(String[] tagIds) {

        tags = new ArrayList<Tag>();
        for(String rawTagId : tagIds) {

            // attempt to find tag by id
            Tag tag = tagManager.resolve(rawTagId);
            if(tag != null) {

                // found tag, add it to list
                tags.add(tag);

            }

        }

    }

}
