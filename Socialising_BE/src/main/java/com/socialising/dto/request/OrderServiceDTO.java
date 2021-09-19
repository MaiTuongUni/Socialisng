package com.socialising.dto.request;



import com.socialising.dto.AbstractDTO;

public class OrderServiceDTO  extends AbstractDTO{
	private Long orderServiceId;
	private String code;

	private String serviceName;
	private Integer servicePrice;
	private String dspPrice;
	private boolean type;

    private Integer total;
    private Integer quantity;
    private String fullName;
    private String email;
    private String phone;

    private String status;

    private String address;

    private String note;

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

	public Long getOrderServiceId() {
		return orderServiceId;
	}

	public void setOrderServiceId(Long orderServiceId) {
		this.orderServiceId = orderServiceId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isType() {
		return type;
	}

	public void setType(boolean type) {
		this.type = type;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
    
}
