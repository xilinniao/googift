package cn.googift.search;

import cn.googift.crawler.data.Product;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.LockObtainFailedException;
import org.wltea.analyzer.lucene.IKQueryParser;
import org.wltea.analyzer.lucene.IKSimilarity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SearchHelper {
    private final Directory indexDir;
    private static String[] SIGLE_SEARCH_FIELDS = null;

    public SearchHelper(Directory indexDir) {
        this.indexDir = indexDir;
        SIGLE_SEARCH_FIELDS = new String[3];
        SIGLE_SEARCH_FIELDS[0] = SearchConstants.FielName_NAME;
        SIGLE_SEARCH_FIELDS[1] = SearchConstants.FielName_CATEGORIES;
        SIGLE_SEARCH_FIELDS[2] = SearchConstants.FielName_DESCRIPTION;
    }

    public List<Product> singleQuery(String keyword) throws IOException {
        List<Product> products = null;
        IndexSearcher isearcher = new IndexSearcher(indexDir);
        try {
//在索引器中使用IKSimilarity相似度评估器
            isearcher.setSimilarity(new IKSimilarity());
//使用IKQueryParser查询分析器构造Query对象
            Query query = IKQueryParser.parseMultiField(SIGLE_SEARCH_FIELDS, keyword);
//搜索相似度最高的5条记录
            TopDocs topDocs = isearcher.search(query, 5);
            System.out.println("命中：" + topDocs.totalHits);
//输出结果
            ScoreDoc[] scoreDocs = topDocs.scoreDocs;
            products = new ArrayList<Product>(scoreDocs.length);
            for (int i = 0; i < scoreDocs.length; i++) {
                Document targetDoc = isearcher.doc(scoreDocs[i].doc);
                products.add(ProductDocumentHelper.transferToProduct(targetDoc));
                System.out.println("内容：" + targetDoc.toString());
            }
        } catch (CorruptIndexException e) {
            e.printStackTrace();
        } catch (LockObtainFailedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                isearcher.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return products;
    }
}
