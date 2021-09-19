package com.socialising.dto.customexception;

public class NotPermissionAddToCart extends Exception {

    public NotPermissionAddToCart(String message) {
        super(message);
    }
}
