package com.socialising.dto.response;

import com.socialising.dto.AbstractDTO;
import com.socialising.dto.ColorDTO;

import java.util.ArrayList;
import java.util.List;

public class ProductResponseDTO extends AbstractDTO<ProductResponseDTO> {

    private String name;
    private String code;
    private String content;
    private String shortDescription;
    private Integer price;
    private String priceDescription;
    private Integer numberOfSell;
    private String seoUrl;
    private String images;
    private String thumbnail;
    private String[] imageNames = new String[]{};
    private Integer isNew;
    private Integer isHot;
    private String brandCode;
    private List<ColorDTO> color = new ArrayList<>();
    private String[] size = new String[]{};
    private boolean statusProduct;

    public void setStatusProduct(boolean statusProduct) {
        this.statusProduct = statusProduct;
    }

    public boolean getStatusProduct() {
        return statusProduct;
    }

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

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
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

    public String[] getImageNames() {
        return imageNames;
    }

    public void setImageNames(String[] imageNames) {
        this.imageNames = imageNames;
    }

    public String getBrandCode() {
        return brandCode;
    }

    public void setBrandCode(String brandCode) {
        this.brandCode = brandCode;
    }

    public List<ColorDTO> getColor() {
        return color;
    }

    public void setColor(List<ColorDTO> color) {
        this.color = color;
    }

    public String[] getSize() {
        return size;
    }

    public void setSize(String[] size) {
        this.size = size;
    }
}
