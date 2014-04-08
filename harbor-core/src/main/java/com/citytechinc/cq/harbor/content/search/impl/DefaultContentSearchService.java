package com.citytechinc.cq.harbor.content.search.impl;

import com.citytechinc.cq.harbor.content.search.ContentHit;
import com.citytechinc.cq.harbor.content.search.ContentSearchService;
import com.google.common.base.Optional;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
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
            = "select excerpt(.) from nt:base where jcr:path like '/content/%' and contains(*, 'searchForText')";

    @Override
    public List<ContentHit> search(Session session, String searchForText) {
        try {
            QueryResult result = executeQuery(searchForText, session);
            List<ContentHit> hits = extractHits(result);
            return hits;
        } catch (RepositoryException e) {
            throw new RuntimeException(e);
        }
    }

    private QueryResult executeQuery(String searchForText, Session session) throws RepositoryException {
        String queryString = QUERY_TEMPLATE.replace("searchForText", searchForText);
        QueryManager qm = session.getWorkspace().getQueryManager();
        Query q = qm.createQuery(queryString, Query.SQL);
        QueryResult result = q.execute();
        return result;
    }

    private List<ContentHit> extractHits(QueryResult result) throws RepositoryException {
        /* use a linked hash set to bounce duplicates while maintaining sort order */
        Set<ContentHit> hits = new LinkedHashSet<ContentHit>();
        for (RowIterator it = result.getRows(); it.hasNext();) {
            Row r = it.nextRow();
            Optional<Node> parentPageNodeOptional = getNearestParentPageNode(r.getNode());
            if (parentPageNodeOptional.isPresent()) {
                String excerpt = r.getValue("rep:excerpt(.)").getString();
                Node parentPageNode = parentPageNodeOptional.get();
                hits.add(new ContentHit(parentPageNode, excerpt));
            }
        }
        return new ArrayList<ContentHit>(hits);
    }

    private Optional<Node> getNearestParentPageNode(Node node) throws RepositoryException {
        if (isPageNode(node)) {
            return Optional.of(node);
        }
        do {
            node = node.getParent();
            if (isPageNode(node)) {
                return Optional.of(node);
            }
        } while (node.getDepth() > 0);

        return Optional.absent();
    }

    private boolean isPageNode(Node n) throws RepositoryException {
        return n.isNodeType("cq:Page");
    }
}
