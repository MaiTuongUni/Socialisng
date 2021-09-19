package com.socialising.dto.request;

import com.socialising.dto.AbstractDTO;

import java.util.ArrayList;
import java.util.List;

public class ProductRequestDTO extends AbstractDTO<ProductRequestDTO> {

    private String name;
    private String code;
    private String content;
    private String shortDescription;
    private Integer price;
    private String priceDescription;
    private Integer numberOfSell;
    private String seoUrl;
    private String images;
    private Base64DTO thumbnail;
    private List<Base64DTO> imageUploads = new ArrayList<>();
    private Integer isNew;
    private Integer isHot;
    private String brandCode;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
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

    public Integer getNumberOfSell() {
        return numberOfSell;
    }

    public void setNumberOfSell(Integer numberOfSell) {
        this.numberOfSell = numberOfSell;
    }

    public String getSeoUrl() {
        return seoUrl;
    }

    public void setSeoUrl(String seoUrl) {
        this.seoUrl = seoUrl;
    }

    public List<Base64DTO> getImageUploads() {
        return imageUploads;
    }

    public void setImageUploads(List<Base64DTO> imageUploads) {
        this.imageUploads = imageUploads;
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

    public String getBrandCode() {
        return brandCode;
    }

    public void setBrandCode(String brandCode) {
        this.brandCode = brandCode;
    }

    public Base64DTO getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Base64DTO thumbnail) {
        this.thumbnail = thumbnail;
    }
}
