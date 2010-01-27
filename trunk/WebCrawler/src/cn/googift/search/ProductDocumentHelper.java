package cn.googift.search;

import cn.googift.crawler.data.Product;
import cn.googift.crawler.service.ProductService;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.NumericField;

public class ProductDocumentHelper {
    private static ProductService productService = new ProductService();

    public static Document transferToDocument(Product p) {
        Document d = new Document();

        d.add(new Field(SearchConstants.FielName_GUID, p.getGuid(), Field.Store.YES, Field.Index.NO));
        d.add(new Field(SearchConstants.FielName_NAME, p.getName(), Field.Store.YES, Field.Index.ANALYZED));

        Float price = p.getDiscountPrice();
        if(null != price) {
            NumericField field = new NumericField(SearchConstants.FielName_PRICE, Field.Store.YES, false);
            field.setFloatValue(price);
            d.add(field);
        }

        String description = p.getDescription();
        if(null != description) {
            d.add(new Field(SearchConstants.FielName_DESCRIPTION, description, Field.Store.NO, Field.Index.ANALYZED_NO_NORMS));
        }

        String categories = p.getCategoryFromCategories();
        if(null != categories) {
            d.add(new Field(SearchConstants.FielName_CATEGORIES, categories, Field.Store.NO, Field.Index.ANALYZED));
        }
        return d;
    }

    public static Product transferToProduct(Document d) {
        String guid = d.get(SearchConstants.FielName_GUID);
        if(null == guid) return null;
        return productService.find(guid);
    }
}
