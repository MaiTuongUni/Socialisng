package com.socialising.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name = "orderservice")
public class OrderServiceEntity extends BaseEntity  {

    @Column(unique = true)
    private String code;

    @Column
    private String serviceName;

    @Column
    private Integer servicePrice;

    @Column
    private String dspPrice;

    private boolean type;

    @Column
    private Integer quantity;

    @Column
    private Integer total;

    @Column(name = "fullname")
    private String fullName;

    private String email;
    
    @Column
    private String phone;

    @Column
    private String status;
    

//    @Column(name = "sessionid")
//    private String sessionId;
    
    @Column
    private String address;

    @Column(columnDefinition = "TEXT")
    private String note;


    @ManyToOne
    @JoinColumn(name = "userid")
    private UserEntity userEntity;

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public Integer getServicePrice() {
        return servicePrice;
    }

    public void setServicePrice(Integer servicePrice) {
        this.servicePrice = servicePrice;
    }

    public String getDspPrice() {
        return dspPrice;
    }

    public void setDspPrice(String dspPrice) {
        this.dspPrice = dspPrice;
    }

    public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
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

	public boolean isType() {
		return type;
	}

	public void setType(boolean type) {
		this.type = type;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
    
}
