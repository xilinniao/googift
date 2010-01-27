package cn.googift.search;

import cn.googift.crawler.data.Product;
import cn.googift.crawler.service.ProductService;
import org.apache.lucene.document.Document;

import java.util.Iterator;

public class DocumentIterator implements Iterator<Document> {
    private Iterator<Product> productIter = new ProductService().findAll().iterator();
    public boolean hasNext() {
        return productIter.hasNext();
    }

    public Document next() {
        Product product = productIter.next();
        return ProductDocumentHelper.transferToDocument(product);
    }

    public void remove() {
        
    }
}
