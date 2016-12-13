package com.icfolson.aem.harbor.core.lists.construction.nodesearch.predicates.path;

import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.widgets.PathField;
import com.day.cq.search.Predicate;
import com.day.cq.search.eval.PathPredicateEvaluator;
import com.google.common.base.Optional;
import com.icfolson.aem.harbor.api.lists.construction.search.ConstructionPredicate;
import com.icfolson.aem.library.core.components.AbstractComponent;
import com.icfolson.aem.library.core.constants.PathConstants;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

/**
 * Dialog representation of a path predicate. Should be converted to a predicate
 * for a query on the JCR by a parent component construction strategy, thus
 * limiting--based on given dialog input--what nodes will be rendered.
 * <p>
 * Stores and handles all predicates that have to do with selecting nodes based
 * on their path in the JCR.
 */
@Model(adaptables = Resource.class)
public class PathConstructionPredicate extends AbstractComponent implements ConstructionPredicate {

    public static final String PARAM_PATH = PathPredicateEvaluator.PATH;

    private Optional<Predicate> predicateOptional;

    /**
     * @return path to search for nodes under.
     */
    @DialogField(fieldLabel = "Path", fieldDescription = "Path to search for nodes under.")
    @PathField(rootPath = PathConstants.PATH_CONTENT)
    public Optional<String> getSearchPath() {
        return get("searchPath", String.class);
    }

    @Override
    public Optional<Predicate> asPredicate() {

        if (predicateOptional == null) {

            if (getSearchPath().isPresent()) {
                Predicate predicate = new Predicate(PARAM_PATH);
                predicate.set(PARAM_PATH, getSearchPath().get());

                predicateOptional = Optional.of(predicate);
            } else {
                predicateOptional = Optional.absent();
            }

        }

        return predicateOptional;

    }
}
