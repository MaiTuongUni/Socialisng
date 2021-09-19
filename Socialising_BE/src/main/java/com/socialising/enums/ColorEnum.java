package com.socialising.enums;

public enum ColorEnum {

    WHITE("Trắng"),
    BLUE("Xanh"),
    BLACK("Đen"),
	GRAY("XÁM"),
	PINK("HỒNG"),
	YELLOW("VÀNG"),
	RED("ĐỎ");

    private String value;

    ColorEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
