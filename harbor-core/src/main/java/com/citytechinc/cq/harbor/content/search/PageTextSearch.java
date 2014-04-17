package com.citytechinc.cq.harbor.content.search;

import java.util.Set;
import javax.jcr.Session;

public interface PageTextSearch {

    PageOfResults search(Session session, Set<String> searchPaths, String searchForText, int requestedPageNumber,
            int pageSize);
}
