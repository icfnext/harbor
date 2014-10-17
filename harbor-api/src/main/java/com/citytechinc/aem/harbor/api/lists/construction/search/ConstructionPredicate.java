package com.citytechinc.aem.harbor.api.lists.construction.search;

import com.day.cq.search.Predicate;
import com.google.common.base.Optional;

public interface ConstructionPredicate {

	public Optional<Predicate> asPredicate();

}
