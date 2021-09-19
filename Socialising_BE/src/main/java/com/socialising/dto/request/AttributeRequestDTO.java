package com.socialising.dto.request;

import com.socialising.dto.AbstractDTO;

public class AttributeRequestDTO extends AbstractDTO {

    private String size;
    private String color;
    private String[] images = new String[]{};

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

    public String[] getImages() {
        return images;
    }

    public void setImages(String[] images) {
        this.images = images;
    }
}
