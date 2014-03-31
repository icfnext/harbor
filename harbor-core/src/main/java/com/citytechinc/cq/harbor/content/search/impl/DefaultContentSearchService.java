package com.citytechinc.cq.harbor.content.search.impl;

import com.citytechinc.cq.harbor.content.search.ContentSearchService;
import java.util.ArrayList;
import java.util.List;
import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.query.Query;
import javax.jcr.query.QueryManager;
import javax.jcr.query.QueryResult;
import javax.jcr.query.Row;
import javax.jcr.query.RowIterator;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Service;

@Component
@Service
public class DefaultContentSearchService implements ContentSearchService {

    private static final String QUERY_TEMPLATE
            = "select * from [nt:base] as s where isdescendantnode([/content]) and contains(s.*, '%s')";

    @Override
    public List<Node> search(Session session, String searchForText) {
        List<Node> foundNodes = new ArrayList<Node>();
        String queryString = String.format(QUERY_TEMPLATE, searchForText);
        try {
            QueryManager qm = session.getWorkspace().getQueryManager();
            Query q = qm.createQuery(queryString, Query.JCR_SQL2);
            QueryResult result = q.execute();
            for (RowIterator it = result.getRows(); it.hasNext();) {
                Row r = it.nextRow();
                foundNodes.add(r.getNode());
            }
        } catch (RepositoryException e) {
            throw new RuntimeException(e);
        }
        return foundNodes;
    }

}
