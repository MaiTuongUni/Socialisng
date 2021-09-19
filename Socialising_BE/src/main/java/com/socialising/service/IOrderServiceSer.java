package com.socialising.service;

import java.util.List;

import com.socialising.dto.customexception.NotPermissionAddToCart;
import com.socialising.dto.request.OrderServiceDTO;
import com.socialising.entity.ServiceEntity;


public interface IOrderServiceSer {
    List<OrderServiceDTO > findAll();
    List<ServiceEntity> getAllServiceType();
    List<OrderServiceDTO> findAll(Long userId);
    OrderServiceDTO save(OrderServiceDTO dto) throws NotPermissionAddToCart;
    void updateStatus(OrderServiceDTO orderDTO);
    void deleteOrderServic(Long cartId);

}
