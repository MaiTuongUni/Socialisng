package com.socialising.service;

import com.socialising.dto.customexception.NotPermissionAddToCart;
import com.socialising.dto.request.CartDTO;

import java.util.Map;

public interface ICartService {
    Map<String,Object> findAll(String sessionId);
    CartDTO addToCart(Long productId, CartDTO cartDTO) throws NotPermissionAddToCart;
    void editQuantityCart(Long cartId, CartDTO cartDTO);
    void deleteCart(Long cartId);
}
