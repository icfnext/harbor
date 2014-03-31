package com.citytechinc.cq.harbor.content.search;

import java.util.List;
import javax.jcr.Node;
import javax.jcr.Session;

public interface ContentSearchService {
    List<Node> search(Session session, String searchForText);
}
