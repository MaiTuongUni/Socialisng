package com.socialising.entity;

import javax.persistence.*;

@Entity
@Table(name = "attribute")
public class AttributeEntity extends BaseEntity {
	
	@Column
	private String size;
	
	@Column
	private String color;

	@Column(columnDefinition = "TEXT")
	private String images;

	@ManyToOne
	@JoinColumn(name = "productid")
	private ProductEntity product;

	public ProductEntity getProduct() {
		return product;
	}

	public void setProduct(ProductEntity product) {
		this.product = product;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}
}
