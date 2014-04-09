package com.citytechinc.cq.harbor.content.search;

import java.util.List;
import java.util.Set;
import javax.jcr.Session;

public interface ContentSearchService {

    List<ContentHit> search(Session session, Set<String> searchPaths, String searchForText);
}
