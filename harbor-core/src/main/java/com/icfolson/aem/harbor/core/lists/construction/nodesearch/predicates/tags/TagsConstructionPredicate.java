package com.icfolson.aem.harbor.core.lists.construction.nodesearch.predicates.tags;

import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.widgets.Switch;
import com.citytechinc.cq.component.annotations.widgets.TagInputField;
import com.citytechinc.cq.component.annotations.widgets.TextField;
import com.day.cq.search.Predicate;
import com.day.cq.search.PredicateGroup;
import com.day.cq.search.eval.JcrPropertyPredicateEvaluator;
import com.day.cq.tagging.Tag;
import com.day.cq.tagging.TagManager;
import com.google.common.base.Optional;
import com.icfolson.aem.harbor.api.lists.construction.search.ConstructionPredicate;
import com.icfolson.aem.library.core.components.AbstractComponent;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Dialog representation of a tag predicate. Should be converted to a predicate
 * for a query on the JCR by a parent component construction strategy, thus
 * limiting--based on given dialog input--what nodes will be rendered.
 * <p>
 * Stores and handles all predicates that have to do with selecting nodes based
 * on their tags.
 */
@Model(adaptables = Resource.class)
public class TagsConstructionPredicate extends AbstractComponent implements ConstructionPredicate {

    private static final String PARAM_COMPOSE_WITH_AND = "composeWithAnd";

    private static final String PARAM_REL_PATH = "relPath";

    private static final String PARAM_TAGS = "tags";

    @Inject
    private TagManager tagManager;

    private List<Tag> tags;

    private Optional<Predicate> predicateOptional;

    /**
     * The logic used to evaluate tags. If true, tags will be logically
     * evaluated with AND, otherwise they will be evaluated with OR logic.
     *
     * @return true if tags should logically be evaluated with AND, otherwise they will be evaluated with OR logic.
     */
    @DialogField(fieldLabel = "Use AND Logic",
        fieldDescription = "Check box to search for tags using AND logic, otherwise search will use OR logic.")
    @Switch(offText = "No", onText = "Yes")
    public boolean isComposeWithAnd() {
        return get(PARAM_COMPOSE_WITH_AND, false);
    }

    /**
     * The relative path from the search result node to where its properties are
     * stored. For example, when searching for dam:Asset nodes, you probably
     * want to search on the asset's metadata node for properties, so the value
     * of this variable would be 'jcr:content/metadata'.
     */
    @DialogField(fieldLabel = "Property Relative Path",
        fieldDescription = "Relative path from nodes being searched to the node where their tag property is stored. For example, if searching for dam:Assets, you would set this field to 'jcr:content/metadata'. Leave blank to search for tags on result nodes themselves.")
    @TextField
    public Optional<String> getRelPath() {
        return get(PARAM_REL_PATH, String.class);
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
            tags = getAsList(PARAM_TAGS, String.class)
                .stream()
                .map(tagId -> tagManager.resolve(tagId))
                .filter(Objects:: nonNull)
                .collect(Collectors.toList());
        }

        return tags;
    }

    @Override
    public Optional<Predicate> asPredicate() {
        if (predicateOptional == null) {
            if (!getTags().isEmpty()) {
                if (getTags().size() > 1) {
                    final PredicateGroup predicateGroup = new PredicateGroup();

                    predicateGroup.setAllRequired(isComposeWithAnd());

                    for (final Tag tag : getTags()) {
                        predicateGroup.add(createTagPredicate(tag));
                    }

                    predicateOptional = Optional.of(predicateGroup);
                } else {
                    final Tag tag = getTags().get(0);

                    predicateOptional = Optional.of(createTagPredicate(tag));
                }
            } else {
                predicateOptional = Optional.absent();
            }
        }

        return predicateOptional;
    }

    private Predicate createTagPredicate(final Tag tag) {
        final Predicate predicate = new Predicate(JcrPropertyPredicateEvaluator.PROPERTY);

        if (getRelPath().isPresent()) {
            predicate.set("property", getRelPath().get());
        }

        predicate.set("tagid", tag.getTagID());

        return predicate;
    }
}
