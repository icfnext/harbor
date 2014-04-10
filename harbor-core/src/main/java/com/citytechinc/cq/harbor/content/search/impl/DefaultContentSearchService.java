package com.citytechinc.cq.harbor.content.search.impl;

import com.citytechinc.cq.harbor.content.search.ContentHit;
import com.citytechinc.cq.harbor.content.search.ContentSearchService;
import com.citytechinc.cq.harbor.content.search.PageOfResults;
import com.google.common.base.Optional;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.Collections;
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

    private static final String QUERY_TEMPLATE = "select excerpt(.) from nt:base where jcr:path like '/content/%' and contains(*, 'searchForText')";

    @Override
    public PageOfResults search(Session session, Set<String> searchPaths, String searchForText,
            int requestedPageNbr, int pageSize) {
        try {
            QueryResult result = executeQuery(searchForText, session);
            List<ContentHit> hits = extractHits(result, searchPaths);
            PageOfResults results = getRequestedPage(hits, requestedPageNbr, pageSize);
            return results;
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

    private List<ContentHit> extractHits(QueryResult result, Set<String> searchPaths) throws RepositoryException {
        /* use a linked hash set to bounce duplicates while maintaining sort order */
        Set<ContentHit> hits = new LinkedHashSet<ContentHit>();
        for (RowIterator it = result.getRows(); it.hasNext();) {
            Row row = it.nextRow();
            Node node = row.getNode();
            Optional<Node> parentPageNodeOptional = getNearestParentPageNode(node);
            if (parentPageNodeOptional.isPresent()) {
                Node parentPageNode = parentPageNodeOptional.get();
                if (isNotUnderSearchPaths(parentPageNode, searchPaths)) {
                    continue;
                }
                String excerpt = row.getValue("rep:excerpt(.)").getString();
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

    private boolean isNotUnderSearchPaths(Node parentPageNode, Set<String> searchPaths) throws RepositoryException {
        for (String searchPath : searchPaths) {
            if (parentPageNode.getPath().startsWith(searchPath)) {
                return false;
            }
        }
        return true;
    }

    private PageOfResults getRequestedPage(List<ContentHit> hits, int requestedPageNbr, int pageSize) {
        int totalNbrOfPages = calculateTotalNbrOfPages(hits, pageSize);
        if (totalNbrOfPages == 0) {
            int pageNbr = 0;
            return new PageOfResults(pageNbr, totalNbrOfPages, hits);
        }

        if (requestedPageNbr > totalNbrOfPages) {
            return new PageOfResults(requestedPageNbr, totalNbrOfPages, Collections.EMPTY_LIST);
        }

        int indexOfFirstHitForRequestedPage = (requestedPageNbr - 1) * pageSize;

        if (requestedPageNbr == totalNbrOfPages) {
            int indexOfLastHitForRequestedPage = hits.size() - 1;
            List<ContentHit> pageOfHits = hits.subList(indexOfFirstHitForRequestedPage, indexOfLastHitForRequestedPage + 1);
            return new PageOfResults(requestedPageNbr, totalNbrOfPages, pageOfHits);
        }

        int indexOfLastHitForRequestedPage = requestedPageNbr * pageSize - 1;
        List<ContentHit> pageOfHits = hits.subList(indexOfFirstHitForRequestedPage, indexOfLastHitForRequestedPage + 1);
        return new PageOfResults(requestedPageNbr, totalNbrOfPages, pageOfHits);

    }

    private int calculateTotalNbrOfPages(List<ContentHit> hits, int pageSize) {
        if (hits.isEmpty()) {
            return 0;
        }
        int totalNbrOfPages = hits.size() / pageSize;
        if (hits.size() % pageSize > 0) {
            totalNbrOfPages++;
        }
        return totalNbrOfPages;
    }
}
