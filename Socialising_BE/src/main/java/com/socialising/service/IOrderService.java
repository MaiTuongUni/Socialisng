package com.socialising.service;

import com.socialising.dto.request.OrderDTO;
import com.socialising.dto.response.ReportResponseDTO;

import java.util.List;
import java.util.Map;

public interface IOrderService {
    List<OrderDTO> findAll();
    List<OrderDTO> findAll(Long userId);
    void save(OrderDTO orderDTO);
    void updateStatus(OrderDTO orderDTO);
    Map<String, Object> getOrderDetail(Long orderId);
    ReportResponseDTO report(String fromDate, String toDate);
}
