package com.citytechinc.aem.harbor.core.services.search.page.impl;

import com.citytechinc.aem.harbor.api.services.search.page.PageHit;
import com.citytechinc.aem.harbor.api.services.search.page.PageOfResults;
import com.citytechinc.aem.harbor.api.services.search.page.PageTextSearchService;
import com.google.common.base.Optional;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@Service
public class DefaultPageTextSearchService implements PageTextSearchService {

    private static final String QUERY_TEMPLATE = "select excerpt(.) from nt:base where jcr:path like '/content/%' and contains(*, 'searchForText') order by jcr:score desc";

    @Override
    public PageOfResults search(Session session, Set<String> searchPaths, String searchForText,
            int requestedPageNbr, int pageSize) {

        searchForText = searchForText.trim();

        try {
            QueryResult result = executeQuery(searchForText, session);
            List<PageHit> hits = extractHits(result, searchPaths, searchForText);
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

    private List<PageHit> extractHits(QueryResult result, Set<String> searchPaths, String searchForText) throws RepositoryException {
        /* use a linked hash set to bounce duplicates while maintaining sort order */
        Set<PageHit> hits = new LinkedHashSet<PageHit>();
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
                excerpt = fixExcerptHighlighting(searchForText, excerpt);
                excerpt = transcodeToUtf8(excerpt);
                hits.add(new DefaultPageHit(parentPageNode, excerpt));
            }
        }
        return new ArrayList<PageHit>(hits);
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

    private PageOfResults getRequestedPage(List<PageHit> hits, int requestedPageNbr, int pageSize) {
        int totalNbrOfPages = calculateTotalNbrOfPages(hits, pageSize);
        if (totalNbrOfPages == 0) {
            int pageNbr = 0;
            return new DefaultPageOfResults(pageNbr, totalNbrOfPages, hits);
        }

        if (requestedPageNbr > totalNbrOfPages) {
            return new DefaultPageOfResults(requestedPageNbr, totalNbrOfPages, Collections.EMPTY_LIST);
        }

        int indexOfFirstHitForRequestedPage = (requestedPageNbr - 1) * pageSize;

        if (requestedPageNbr == totalNbrOfPages) {
            int indexOfLastHitForRequestedPage = hits.size() - 1;
            List<PageHit> pageOfHits = hits.subList(indexOfFirstHitForRequestedPage, indexOfLastHitForRequestedPage + 1);
            return new DefaultPageOfResults(requestedPageNbr, totalNbrOfPages, pageOfHits);
        }

        int indexOfLastHitForRequestedPage = requestedPageNbr * pageSize - 1;
        List<PageHit> pageOfHits = hits.subList(indexOfFirstHitForRequestedPage, indexOfLastHitForRequestedPage + 1);
        return new DefaultPageOfResults(requestedPageNbr, totalNbrOfPages, pageOfHits);
    }

    private int calculateTotalNbrOfPages(List<PageHit> hits, int pageSize) {
        if (hits.isEmpty()) {
            return 0;
        }
        int totalNbrOfPages = hits.size() / pageSize;
        if (hits.size() % pageSize > 0) {
            totalNbrOfPages++;
        }
        return totalNbrOfPages;
    }

    /**
     * Removes and then reapplies the highlighting (wrapping with <strong> tags)
     * of the search terms found within the excerpt Strings returned by the jcr
     * query. This is being done because the jcr query often incorrectly
     * highlights blocks of text other than the search terms.
     */
    private String fixExcerptHighlighting(String searchForText, String excerpt) {
        excerpt = excerpt.replaceAll("<strong>", "");
        excerpt = excerpt.replaceAll("</strong>", "");

        /* split up the terms in the search string based on them having 1 or more spaces between them */
        String[] searchTerms = searchForText.split(" +");
        /* add to set to eliminate duplicate search terms */
        Set<String> searchTermsSet = new HashSet<String>();
        searchTermsSet.addAll(Arrays.asList(searchTerms));

        for (String searchTerm : searchTermsSet) {

            Pattern p = Pattern.compile("(?i)" + searchTerm);
            Matcher m = p.matcher(excerpt);
            StringBuffer sb = new StringBuffer();
            while (m.find()) {
                String foundSearchTerm = m.group();
                m.appendReplacement(sb, "<strong>" + foundSearchTerm + "</strong>");
            }
            m.appendTail(sb);
            excerpt = sb.toString();
        }
        return excerpt;
    }

    /**
     * Transcodes the String excerpt from ISO-8859-1 to UTF-8 to fix strange
     * characters showing up in the text when the excerpt is displayed on a web
     * page.
     */
    private String transcodeToUtf8(String excerpt) throws RuntimeException {
        try {
            byte[] utf8 = new String(excerpt.getBytes(), "ISO-8859-1").getBytes("UTF-8");
            excerpt = new String(utf8);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        return excerpt;
    }
}
