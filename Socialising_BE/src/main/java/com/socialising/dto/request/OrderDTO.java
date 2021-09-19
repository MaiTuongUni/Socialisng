package com.socialising.dto.request;

import com.socialising.dto.AbstractDTO;
import com.socialising.dto.UserDTO;

public class OrderDTO extends AbstractDTO {

    private Long orderId;
    private String code;
    private String sessionId;
    private Integer total;
    private String fullName;
    private String phone;
    private String address;
    private UserDTO person;
    private String status;
    private String note;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public UserDTO getPerson() {
        return person;
    }

    public void setPerson(UserDTO person) {
        this.person = person;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int compareTo(Object o) {
        return this.getCreatedDate().compareTo(((OrderDTO) o).getCreatedDate());
    }
}
