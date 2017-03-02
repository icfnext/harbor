package com.icfolson.aem.harbor.core.lists.construction.nodesearch.predicates.builder;

import com.day.cq.search.Predicate;
import com.day.cq.search.PredicateGroup;
import com.day.cq.search.eval.JcrPropertyPredicateEvaluator;
import com.day.cq.tagging.Tag;
import com.day.cq.wcm.api.NameConstants;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

public final class TagsPredicateBuilder {

    private List<Tag> tags;

    private String relativePath = NameConstants.PN_TAGS;

    private boolean composeWithAnd = false;

    public TagsPredicateBuilder withTags(final List<Tag> tags) {
        this.tags = tags;

        return this;
    }

    public TagsPredicateBuilder withRelativePath(final String relativePath) {
        this.relativePath = relativePath;

        return this;
    }

    public TagsPredicateBuilder composeWithAnd(final boolean composeWithAnd) {
        this.composeWithAnd = composeWithAnd;

        return this;
    }

    public Predicate build() {
        final Predicate predicate;

        if (checkNotNull(tags, "tags are required to build the predicate").size() > 1) {
            final PredicateGroup predicateGroup = new PredicateGroup();

            predicateGroup.setAllRequired(composeWithAnd);

            for (final Tag tag : tags) {
                predicateGroup.add(createTagPredicate(tag));
            }

            predicate = predicateGroup;
        } else {
            final Tag tag = tags.get(0);

            predicate = createTagPredicate(tag);
        }

        return predicate;
    }

    private Predicate createTagPredicate(final Tag tag) {
        return new Predicate(JcrPropertyPredicateEvaluator.PROPERTY)
            .set(JcrPropertyPredicateEvaluator.PROPERTY, relativePath)
            .set(JcrPropertyPredicateEvaluator.VALUE, tag.getTagID());
    }
}
