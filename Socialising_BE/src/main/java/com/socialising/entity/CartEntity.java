package com.socialising.entity;

import javax.persistence.*;

@Entity
@Table(name = "cart")
public class CartEntity extends BaseEntity {

    @Column
    private String size;

    @Column
    private String color;

    @Column
    private Integer quantity;

    @Column
    private Integer total;

    @Column(name = "sessionid")
    private String sessionId;

    @ManyToOne
    @JoinColumn(name = "productid")
    private ProductEntity product;

    @ManyToOne
    @JoinColumn(name = "userid")
    private UserEntity user;

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
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
}
