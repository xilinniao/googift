package cn.googift.search;

import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.IOException;

public class IndexHelper {
    public void index(Directory destDir, DocumentIterator di) throws IOException {
        IndexWriter writer = new IndexWriter(destDir, new IKAnalyzer(), true, IndexWriter.MaxFieldLength.LIMITED);
        while (di.hasNext()) {
            writer.addDocument(di.next());
        }
        writer.close();
    }

    public static void main(String arg[]) throws IOException {
        new IndexHelper().index(new RAMDirectory(), new DocumentIterator());
    }
}
