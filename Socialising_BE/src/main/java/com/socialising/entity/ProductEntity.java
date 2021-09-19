package com.socialising.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "product")
public class ProductEntity extends BaseEntity {
	
	@Column
	private String name;
	
	@Column
	private String code;

	@Column(columnDefinition = "TEXT")
	private String content;
	
	@Column(name = "shortdescription", columnDefinition = "TEXT")
	private String shortDescription;

	@Column(columnDefinition = "TEXT")
	private String images;

	@Column(columnDefinition = "TEXT")
	private String thumbnail;

	@Column
	private Integer price;

	@Column(columnDefinition = "TEXT", name = "pricedescription")
	private String priceDescription;

	@Column(name = "numberofsell")
	private Integer numberOfSell;

	@Column(name="createddate")
	private Date createdDate;

	@Column(name = "seourl")
	private String seoUrl;

	@Column(name = "isnew", columnDefinition = "integer default 0")
	private Integer isNew;

	@Column(name="statusproduct")
	private boolean statusProduct;

	@Override
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public boolean getStatusProduct() {
		return statusProduct;
	}

	public void setStatusProduct(boolean statusProduct) {
		this.statusProduct = statusProduct;
	}

	@Column(name = "ishot", columnDefinition = "integer default 0")
	private Integer isHot;

	@ManyToOne
	@JoinColumn(name = "brandid")
	private BrandEntity brand;

	@OneToMany(mappedBy = "product", orphanRemoval = true, fetch = FetchType.LAZY)
	private List<CartEntity> carts = new ArrayList<>();

	@OneToMany(mappedBy = "product", orphanRemoval = true, fetch = FetchType.LAZY)
	private List<OrderDetailEntity> orders = new ArrayList<>();

	@OneToMany(mappedBy = "product", orphanRemoval = true, fetch = FetchType.LAZY)
	private List<AttributeEntity> attributes = new ArrayList<>();

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getPriceDescription() {
		return priceDescription;
	}

	public void setPriceDescription(String priceDescription) {
		this.priceDescription = priceDescription;
	}

	public BrandEntity getBrand() {
		return brand;
	}

	public void setBrand(BrandEntity brand) {
		this.brand = brand;
	}

	public Integer getNumberOfSell() {
		return numberOfSell;
	}

	public void setNumberOfSell(Integer numberOfSell) {
		this.numberOfSell = numberOfSell;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public String getSeoUrl() {
		return seoUrl;
	}

	public void setSeoUrl(String seoUrl) {
		this.seoUrl = seoUrl;
	}

	public List<CartEntity> getCarts() {
		return carts;
	}

	public void setCarts(List<CartEntity> carts) {
		this.carts = carts;
	}

	public List<OrderDetailEntity> getOrders() {
		return orders;
	}

	public void setOrders(List<OrderDetailEntity> orders) {
		this.orders = orders;
	}

	public Integer getIsNew() {
		return isNew;
	}

	public void setIsNew(Integer isNew) {
		this.isNew = isNew;
	}

	public Integer getIsHot() {
		return isHot;
	}

	public void setIsHot(Integer isHot) {
		this.isHot = isHot;
	}

	public List<AttributeEntity> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<AttributeEntity> attributes) {
		this.attributes = attributes;
	}
}
