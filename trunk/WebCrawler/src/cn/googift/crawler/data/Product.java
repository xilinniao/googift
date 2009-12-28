package cn.googift.crawler.data;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.math.BigDecimal;


/**
 * The persistent class for the products database table.
 * 
 */
@Entity

@Table(name="products")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(updatable=false, unique=true, nullable=false, length=255)
	private String guid;

	@Column(name="created_at")
	private Timestamp createdAt;

    @Lob()
	private String description;

	@Column(name="discount_price", precision=10, scale=2)
	private Float discountPrice;

	@Column(name="display_name", length=255)
	private String displayName;

	@Column(name="market_price", precision=10, scale=2)
	private Float marketPrice;

	@Column(length=255)
	private String name;

	@Column(name="updated_at")
	private Timestamp updatedAt;

	@Column(length=255)
	private String url;

	@Version
	private int version;
	
	private List<String> picLinks;
	
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
	}

	private List<String> categories;

    public Product(String url, String name) {
    	this.url = url;
    	this.name = name;
    }

	public String getGuid() {
		return this.guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
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

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getVersion() {
		return this.version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
	
	public String toString()
	{
		return new StringBuilder()
			.append("Guid:" + guid + "|")
			.append("Name:" + name + "|")
			.append("Url:" + url + "|").toString();
			
			
	}
}