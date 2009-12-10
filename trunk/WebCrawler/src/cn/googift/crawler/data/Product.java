package cn.googift.crawler.data;

import java.util.List;

public class Product {
    private final String url;
    private final String name;
    private Float marketPrice;
    private Float price;
    private String displayName;
    private String description;
    private List<String> picLinks;
    private List<String> categories;

    public Product(String url, String name) {
        this.url = url;
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public String getName() {
        return name;
    }

    public Float getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(Float marketPrice) {
        this.marketPrice = marketPrice;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getPicLinks() {
        return picLinks;
    }

    public void setPicLinks(List<String> picLinks) {
        this.picLinks = picLinks;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
        for(int i=0;i<categories.size();i++) {
            categories.set(i, categories.get(i).replaceAll("\\s", ""));
        }
    }
}
