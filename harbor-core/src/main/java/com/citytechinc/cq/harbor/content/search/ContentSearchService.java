package com.citytechinc.cq.harbor.content.search;

import java.util.List;
import javax.jcr.Session;

public interface ContentSearchService {
    List<ContentHit> search(Session session, String searchForText);
}
