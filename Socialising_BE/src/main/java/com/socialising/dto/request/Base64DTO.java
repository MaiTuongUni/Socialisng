package com.socialising.dto.request;

import org.apache.commons.lang.StringUtils;

public class Base64DTO {

    private String imageBase64;
    private String imageName;

    public String getImageBase64() {
        if (StringUtils.isNotBlank(imageBase64)) {
            return imageBase64.split(",")[1];
        }
        return imageBase64;
    }

    public void setImageBase64(String imageBase64) {
        this.imageBase64 = imageBase64;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
}
