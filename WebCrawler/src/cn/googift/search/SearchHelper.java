package cn.googift.search;

import cn.googift.crawler.data.Product;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.search.*;
import org.apache.lucene.search.Searcher;
import org.apache.lucene.search.highlight.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.LockObtainFailedException;
import org.apache.lucene.util.NumericUtils;
import org.wltea.analyzer.lucene.IKAnalyzer;
import org.wltea.analyzer.lucene.IKQueryParser;
import org.wltea.analyzer.lucene.IKSimilarity;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SearchHelper {
    private static final String prefixHTML = "<font color='red'>";
    private static final String suffixHTML = "</font>";
    private static final SimpleHTMLFormatter simpleHTMLFormatter = new SimpleHTMLFormatter(prefixHTML, suffixHTML);
    private static String[] SIGLE_SEARCH_FIELDS = null;

    private final Directory indexDir;
    private final IndexSearcher searcher;

    private int hitsPerPage;
    private int numTotalHits;
    private ScoreDoc[] hits;
    private String keyword;
    private Query query;


    public SearchHelper(Directory indexDir) throws IOException {
        this.indexDir = indexDir;
        this.searcher = new IndexSearcher(indexDir);
        SIGLE_SEARCH_FIELDS = new String[3];
        SIGLE_SEARCH_FIELDS[0] = SearchConstants.FielName_NAME;
        SIGLE_SEARCH_FIELDS[1] = SearchConstants.FielName_CATEGORIES;
        SIGLE_SEARCH_FIELDS[2] = SearchConstants.FielName_DESCRIPTION;
    }

    public int doSearch(String keyword, int hitsPerPage) throws IOException {
        if (keyword == null) return 0;
        this.hitsPerPage = hitsPerPage;
        this.keyword = keyword;
        query = IKQueryParser.parseMultiField(SIGLE_SEARCH_FIELDS, keyword);
        numTotalHits = search(10 * hitsPerPage);
        return numTotalHits;
    }

    public int doAdvancedSearch(String name, String lowPrice, String highPrice, String keyword, int hitsPerPage) throws IOException {
        if (null == name && keyword == null) return 0;
        this.hitsPerPage = hitsPerPage;
        if(null != name) this.keyword = name;
        else this.keyword = keyword;
        Filter filter = new TermRangeFilter(SearchConstants.FielName_PRICE, NumericUtils.floatToPrefixCoded(Float.parseFloat(lowPrice)), NumericUtils.floatToPrefixCoded(Float.parseFloat(highPrice)), true, true);
        query = IKQueryParser.parseMultiField(new String[]{SearchConstants.FielName_NAME, SearchConstants.FielName_DESCRIPTION}, new String[]{name, keyword});
        numTotalHits = search(10 * hitsPerPage, filter);
        System.out.println("numTotalHits = " + numTotalHits);
        return numTotalHits;
    }

    public List<SearchResult> getPage(int page) throws IOException, InvalidTokenOffsetsException {
        int start = (page - 1) * hitsPerPage;
        int end = Math.min(page * hitsPerPage, numTotalHits);
        if (hits.length < end) {
            search(numTotalHits);
        }
        List<SearchResult> results = new ArrayList<SearchResult>(end - start);
        for (int i = start; i < end; i++) {
            results.add(toSearchResult(hits[i]));
        }
        return results;
    }

    private SearchResult toSearchResult(ScoreDoc sd) throws IOException, InvalidTokenOffsetsException {
        Document targetDoc = searcher.doc(sd.doc);
        Product product = ProductDocumentHelper.transferToProduct(targetDoc);
        if (null == product) return null;
        Highlighter highlighter = new Highlighter(simpleHTMLFormatter, new QueryScorer(query));
        highlighter.setTextFragmenter(new SimpleFragmenter(300));
        String highLightText = highlighter.getBestFragment(new IKAnalyzer(), keyword, product.getDescription());
        return new SearchResult(product, "..." + highLightText + "...");
    }

    private int search(int demandCount) throws IOException {
        TopScoreDocCollector collector = TopScoreDocCollector.create(demandCount, false);
        searcher.search(query, collector);
        hits = collector.topDocs().scoreDocs;
        return collector.getTotalHits();
    }

    private int search(int demandCount, Filter filter) throws IOException {
        TopScoreDocCollector collector = TopScoreDocCollector.create(demandCount, false);
        searcher.search(query, filter, collector);
        hits = collector.topDocs().scoreDocs;
        System.out.println("hits = " + hits);
        return collector.getTotalHits();
    }

}
