package com.socialising.dto;

import com.socialising.dto.response.ProductResponseDTO;

import java.util.ArrayList;
import java.util.List;

public class GroupDTO {

    private String code;
    private List<ProductResponseDTO> products = new ArrayList<>();

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<ProductResponseDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductResponseDTO> products) {
        this.products = products;
    }
}
