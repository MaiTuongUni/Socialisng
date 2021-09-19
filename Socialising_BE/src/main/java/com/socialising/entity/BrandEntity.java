package com.socialising.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "brand")
public class BrandEntity extends BaseEntity {
	
	@Column
	private String name;
	
	@Column
	private String code;

	@OneToMany(mappedBy = "brand", orphanRemoval = true, fetch = FetchType.LAZY)
	private List<ProductEntity> products = new ArrayList<>();

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

	public List<ProductEntity> getProducts() {
		return products;
	}

	public void setProducts(List<ProductEntity> products) {
		this.products = products;
	}
}
