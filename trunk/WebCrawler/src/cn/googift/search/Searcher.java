package cn.googift.search;

import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Searcher {
    private Directory indexDir = new RAMDirectory();
    private IndexHelper indexHelper = new IndexHelper();
    private SearchHelper searchHelper = new SearchHelper(indexDir);

    public Searcher() {
        try {
            indexHelper.index(indexDir, new DocumentIterator());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<SearchResult> search(String q, int start, int count) {
        try {
            return searchHelper.singleQuery(q);
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<SearchResult>(0);
        }
    }
}
