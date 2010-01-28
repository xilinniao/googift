package cn.googift.search;

import cn.googift.crawler.data.Product;

public class SearchResult {
    private final Product product;
    private final String snippet;

    public SearchResult(Product product, String snippet) {
        this.product = product;
        this.snippet = snippet;
    }

    public String getSnippet() {
        return snippet;
    }

    public Product getProduct() {
        return product;
    }
}
