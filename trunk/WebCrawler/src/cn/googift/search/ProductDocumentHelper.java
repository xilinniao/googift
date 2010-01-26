package cn.googift.search;

import cn.googift.crawler.data.Product;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;

public class ProductDocumentHelper {
    public static Document transferToDocument(Product p) {
        Document d = new Document();

        d.add(new Field(SearchConstants.FielName_URL, p.getUrl(), Field.Store.YES, Field.Index.NO));
        d.add(new Field(SearchConstants.FielName_NAME, p.getName(), Field.Store.YES, Field.Index.ANALYZED));

        Float price = p.getDiscountPrice();
        if(null != price) {
            d.add(new Field(SearchConstants.FielName_PRICE, price.toString(), Field.Store.YES, Field.Index.NOT_ANALYZED));
        }

        String description = p.getDescription();
        if(null != description) {
            d.add(new Field(SearchConstants.FielName_DESCRIPTION, description, Field.Store.YES, Field.Index.ANALYZED_NO_NORMS));
        }

        String categories = p.getCategoryFromCategories();
        if(null != categories) {
            d.add(new Field(SearchConstants.FielName_CATEGORIES, categories, Field.Store.YES, Field.Index.ANALYZED));
        }
        return d;
    }

    public static Product transferToProduct(Document d) {
        Product p = new Product(d.get(SearchConstants.FielName_URL),d.get(SearchConstants.FielName_NAME));
        String price = d.get(SearchConstants.FielName_PRICE);
        if(null != price) {
            p.setDiscountPrice(Float.parseFloat(price));
        }
        String desc = d.get(SearchConstants.FielName_DESCRIPTION);
        if(null != desc) p.setDescription(desc);

        String categories = d.get(SearchConstants.FielName_CATEGORIES);
        if(null != categories) p.setCategory(categories);
        return p;
    }
}
