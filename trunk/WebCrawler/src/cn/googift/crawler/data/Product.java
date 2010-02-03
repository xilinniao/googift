package cn.googift.crawler.data;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;


/**
 * The persistent class for the products database table.
 */
@Entity

@Table(name = "products")
public class Product implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(updatable = false, unique = true, nullable = false, length = 255)
    private String guid;

    @Column(length = 255)
    private String url;

    @Column(length = 255)
    private String name;

    @Column(name = "display_name", length = 255)
    private String displayName;

    @Lob
    @Column(name = "resouce_link")
    private String resourceLink;

    @Column(name = "market_price", precision = 10, scale = 2)
    private Float marketPrice;

    @Column(name = "discount_price", precision = 10, scale = 2)
    private Float discountPrice;

    @Column(length = 255)
    private String category;

    @Lob()
    private String description;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "updated_at")
    private Timestamp updatedAt;

    @Version
    private int version;

    private Map<String, String> smallBigPicLinks;

    private List<String> categories;

    public Product() {
    }

    public Product(String url, String name) {
        this.url = url;
        this.name = name;
    }


    public Map<String, String> getPicLinks() {
        if (null == smallBigPicLinks) {
            if(null != resourceLink && resourceLink.length() > 3) {
                String[] linkPairs = resourceLink.split("  ");
                smallBigPicLinks = new HashMap<String, String>(linkPairs.length);
                for(String linkPair : linkPairs) {
                    String[] links = linkPair.split(" ");
                    if(links.length >=2 ) {
                        smallBigPicLinks.put(links[0], links[1]);
                    }
                }
            }
        }
        return smallBigPicLinks;
    }


    public void setPicLinks(Map<String, String> smallBigPicLinks) {
        if(null != smallBigPicLinks) {
            this.smallBigPicLinks = smallBigPicLinks;
            resourceLink = "";
            for(Map.Entry<String, String> entry : smallBigPicLinks.entrySet()) {
                resourceLink = resourceLink + entry.getKey() + " " + entry.getValue() + "  ";
            }
            if(resourceLink.length() > 3) {
                resourceLink = resourceLink.substring(0, resourceLink.length() - 2);
            } else {
                resourceLink = null;
            }
        }
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
        this.category = getCategoryFromCategories();
    }


    public String getGuid() {
        return this.guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getResouceLink() {
        return resourceLink;
    }

    public void setResouceLink(String resouceLink) {
        this.resourceLink = resouceLink;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Timestamp getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getDiscountPrice() {
        return this.discountPrice;
    }

    public void setDiscountPrice(Float discountPrice) {
        this.discountPrice = discountPrice;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Float getMarketPrice() {
        return this.marketPrice;
    }

    public void setMarketPrice(Float marketPrice) {
        this.marketPrice = marketPrice;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getUpdatedAt() {
        return this.updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public int getVersion() {
        return this.version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String toString() {
        return new StringBuilder()
                .append("Guid:" + guid + "|")
                .append("Name:" + name + "|")
                .append("Url:" + url + "|").toString();
    }

    public void copyPropertiesFromProduct(Product p) {
        try {
            BeanUtils.copyProperties(this, p);
        } catch (Exception e) {
        }
    }

    public String getCategoryFromCategories() {
        if (null == categories) return null;
        String s = StringUtils.replace(categories.toString(), ", ", "|");
        s = StringUtils.replace(s, "[", "");
        s = StringUtils.replace(s, "]", "");
        return s;
    }
}