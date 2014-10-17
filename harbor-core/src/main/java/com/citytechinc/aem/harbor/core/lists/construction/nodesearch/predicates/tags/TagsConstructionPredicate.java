package com.citytechinc.aem.harbor.core.lists.construction.nodesearch.predicates.tags;

import java.util.List;

import com.citytechinc.aem.bedrock.core.components.AbstractComponent;
import com.day.cq.search.Predicate;
import com.day.cq.search.PredicateGroup;
import com.day.cq.tagging.impl.search.AbstractTagPredicateEvaluator;
import com.day.cq.tagging.impl.search.TagPredicateEvaluator;
import com.google.common.base.Optional;

import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.Option;
import com.citytechinc.cq.component.annotations.widgets.Selection;
import com.citytechinc.cq.component.annotations.widgets.TagInputField;
import com.citytechinc.cq.component.annotations.widgets.TextField;
import com.citytechinc.aem.harbor.api.lists.construction.search.ConstructionPredicate;
import com.day.cq.tagging.Tag;
import com.day.cq.tagging.TagManager;
import com.google.common.collect.Lists;

/**
 * Dialog representation of a tag predicate. Should be converted to a predicate
 * for a query on the JCR by a parent component construction strategy, thus
 * limiting--based on given dialog input--what nodes will be rendered.
 *
 * Stores and handles all predicates that have to do with selecting nodes based
 * on their tags.
 */
public class TagsConstructionPredicate extends AbstractComponent implements ConstructionPredicate {

	private static final String PARAM_COMPOSE_WITH_AND = "composeWithAnd";
	private static final boolean DEFAULT_COMPOSE_WITH_AND = false;

	private static final String PARAM_REL_PATH = "relPath";

	private Optional<String> relPath;

	private static final String PARAM_TAGS = "tags";

	private List<Tag> tags;

	private Optional<Predicate> predicateOptional;


	/**
	 * The logic used to evaluate tags. If true, tags will be logically
	 * evaluated with AND, otherwise they will be evaluated with OR logic.
	 *
	 * @return true if tags should logically be evaluated with AND, otherwise
	 *         they will be evaluated with OR logic.
	 */
    @DialogField(fieldLabel = "Use AND Logic", fieldDescription = "Check box to search for tags using AND logic, otherwise search will use OR logic.")
    @Selection(type = Selection.CHECKBOX, options = @Option(value = "true"))
	public boolean getComposeWithAnd() {

		return get(PARAM_COMPOSE_WITH_AND, DEFAULT_COMPOSE_WITH_AND);

	}

	/**
	 * The relative path from the search result node to where its properties are
	 * stored. For example, when searching for dam:Asset nodes, you probably
	 * want to search on the asset's metadata node for properties, so the value
	 * of this variable would be 'jcr:content/metadata'.
	 *
	 */
    @DialogField(fieldLabel = "Property Relative Path", fieldDescription = "Relative path from nodes being searched to the node where their tag property is stored. For example, if searching for dam:Assets, you would set this field to 'jcr:content/metadata'. Leave blank to search for tags on result nodes themselves.")
    @TextField
	public Optional<String> getRelPath() {

		if (relPath == null) {
            relPath = get(PARAM_REL_PATH, String.class);
        }

        return relPath;

	}

	/**
	 * Underlying tag list for query via an array of tag ids. Tag objects will
	 * only be added for tag ids that successfully resolve to a tag in the JCR.
	 *
	 * @return tags that will be used for query.
	 */
    @DialogField(fieldLabel = "Tags", fieldDescription = "List of tags to filter on.")
    @TagInputField
	public List<Tag> getTags() {

		if (tags == null) {

            TagManager tagManager = getResource().getResourceResolver().adaptTo(TagManager.class);

			tags = Lists.newArrayList();

            for (String currentTagId : getAsList(PARAM_TAGS, String.class)) {
                Tag tag = tagManager.resolve(currentTagId);
                if (tag != null) {
                    tags.add(tag);
                }
            }

		}

		return tags;

	}

    @Override
    public Optional<Predicate> asPredicate() {

        if (predicateOptional != null) {
            return predicateOptional;
        }

        if (!getTags().isEmpty()) {
            if (getTags().size() > 1) {
                PredicateGroup predicateGroup = new PredicateGroup();

                predicateGroup.setAllRequired(getComposeWithAnd());

                for (Tag currentTag : getTags()) {
                    Predicate predicate = new Predicate(TagPredicateEvaluator.TAG_ID_PARAM);
                    if (getRelPath().isPresent()) {
                        predicate.set(AbstractTagPredicateEvaluator.PROPERTY_PARAM, getRelPath().get());
                    }
                    predicate.set(TagPredicateEvaluator.TAG_ID_PARAM, currentTag.getTagID());

                    predicateGroup.add(predicate);
                }

                predicateOptional = Optional.of((Predicate) predicateGroup);
            }
            else {
                Predicate predicate = new Predicate(TagPredicateEvaluator.TAG_ID_PARAM);
                if (getRelPath().isPresent()) {
                    predicate.set(AbstractTagPredicateEvaluator.PROPERTY_PARAM, getRelPath().get());
                }
                predicate.set(TagPredicateEvaluator.TAG_ID_PARAM, getTags().get(0).getTagID());

                predicateOptional = Optional.of(predicate);
            }
        }
        else {
            predicateOptional = Optional.absent();
        }

        return predicateOptional;

    }
}
