package com.socialising.entity;

import javax.persistence.*;

@Entity
@Table(name = "orderdetail")
public class OrderDetailEntity extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "productid")
    private ProductEntity product;

    @ManyToOne
    @JoinColumn(name = "orderid")
    private OrderEntity order;

    @Column
    private Integer quantity;

    @Column
    private Integer total;

    @Column
    private String size;

    @Column
    private String color;

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
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

    public OrderEntity getOrder() {
        return order;
    }

    public void setOrder(OrderEntity order) {
        this.order = order;
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
