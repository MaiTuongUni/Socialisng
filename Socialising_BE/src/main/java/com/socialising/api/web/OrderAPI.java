package com.socialising.api.web;

import com.socialising.constant.SystemConstant;
import com.socialising.dto.request.OrderDTO;
import com.socialising.dto.response.ResponseDTO;
import com.socialising.service.IOrderService;
import com.socialising.service.IOrderServiceSer;
import com.socialising.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController(value = "orderApiOfWeb")
@RequestMapping(SystemConstant.API_VERSION + "/orders")
public class OrderAPI {

    @Autowired
    private IOrderServiceSer orderServiceSer;

    @Autowired
    private IOrderService orderService;

    @GetMapping
    public ResponseDTO showOrder() {
        ResponseDTO result = new ResponseDTO();
        result.setMessage(SystemConstant.SUCCESS_MESSAGE);
        result.setData(orderService.findAll(SecurityUtils.getPrincipal().getId()));
        return result;
    }

    @GetMapping("/detail/{id}")
    public ResponseDTO showOrderDetail(@PathVariable("id") Long id) {
        ResponseDTO result = new ResponseDTO();
        result.setMessage(SystemConstant.SUCCESS_MESSAGE);
        result.setData(orderService.getOrderDetail(id));
        return result;
    }

    @PostMapping
    public ResponseDTO updateStatus(@RequestBody OrderDTO orderDTO) {
        orderService.updateStatus(orderDTO);
        ResponseDTO result = new ResponseDTO();
        result.setMessage(SystemConstant.SUCCESS_MESSAGE);
        return result;
    }
}
