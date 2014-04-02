package com.citytechinc.cq.harbor.content.search.impl;

import com.citytechinc.cq.harbor.content.search.ContentHit;
import com.citytechinc.cq.harbor.content.search.ContentSearchService;
import java.util.ArrayList;
import java.util.List;
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
            = "select excerpt(.) from nt:base where jcr:path like '/content/%' and contains(*, 'searchForText')";

    @Override
    public List<ContentHit> search(Session session, String searchForText) {
        List<ContentHit> foundContent = new ArrayList<ContentHit>();
        String queryString = QUERY_TEMPLATE.replace("searchForText", searchForText);
        try {
            QueryManager qm = session.getWorkspace().getQueryManager();
            Query q = qm.createQuery(queryString, Query.SQL);
            QueryResult result = q.execute();
            for (RowIterator it = result.getRows(); it.hasNext();) {
                Row r = it.nextRow();
                String excerpt = r.getValue("rep:excerpt(.)").getString();
                foundContent.add(new ContentHit(r.getNode(), excerpt));
            }
        } catch (RepositoryException e) {
            throw new RuntimeException(e);
        }
        return foundContent;
    }

}
