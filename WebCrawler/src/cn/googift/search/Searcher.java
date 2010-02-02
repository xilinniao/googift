package cn.googift.search;

import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.store.FSDirectory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Searcher {
    public static final int hitsPerPage = 10;
//    private Directory indexDir = new RAMDirectory();
    private Directory indexDir;
    private IndexHelper indexHelper = new IndexHelper();
    private SearchHelper searchHelper;

    public Searcher() {
        try {
            System.out.println("Searcher.Searcher");
            indexDir = FSDirectory.open(new File("c:\\_index"));
            indexHelper.index(indexDir, new DocumentIterator());
            searchHelper = new SearchHelper(indexDir);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int search(String q) {
        try {
            return searchHelper.doSearch(q, hitsPerPage);
        } catch (Throwable e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int search(String name, String lowPrice, String highPrice, String keywords) {
        try {
            return searchHelper.doAdvancedSearch(name, lowPrice, highPrice, keywords, hitsPerPage);
        } catch (Throwable e) {
            e.printStackTrace();
            return 0;
        }
    }

    public List<SearchResult> getPage(int page) {
        try {
            return searchHelper.getPage(page);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<SearchResult>(0);
        }
    }
}
