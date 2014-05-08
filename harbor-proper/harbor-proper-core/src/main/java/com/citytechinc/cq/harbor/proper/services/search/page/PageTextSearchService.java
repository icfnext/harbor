package com.citytechinc.cq.harbor.proper.services.search.page;

import java.util.Set;
import javax.jcr.Session;

public interface PageTextSearchService {

    PageOfResults search(Session session, Set<String> searchPaths, String searchForText, int requestedPageNumber,
            int pageSize);
}
