package com.socialising.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class OrderEntity extends BaseEntity {

    @Column(unique = true)
    private String code;

    @Column
    private Integer total;

    @Column(name = "fullname")
    private String fullName;

    @Column
    private String phone;

    @Column
    private String status;

    @Column
    private String address;

    @Column(columnDefinition = "TEXT")
    private String note;

    @OneToMany(mappedBy = "order", orphanRemoval = true, fetch = FetchType.LAZY,
                                cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<OrderDetailEntity> orderDetails = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "userid")
    private UserEntity userEntity;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<OrderDetailEntity> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetailEntity> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
