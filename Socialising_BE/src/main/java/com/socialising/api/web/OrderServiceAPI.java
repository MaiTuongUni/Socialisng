package com.socialising.api.web;

import com.socialising.constant.SystemConstant;
import com.socialising.dto.customexception.NotPermissionAddToCart;
import com.socialising.dto.request.OrderServiceDTO;
import com.socialising.dto.response.ResponseDTO;
import com.socialising.entity.ServiceEntity;
import com.socialising.service.impl.OrderServiceSer;
import com.socialising.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(SystemConstant.API_VERSION + "/orderservice")
public class OrderServiceAPI {

    @Autowired
    private OrderServiceSer service;

    @PostMapping
    public ResponseDTO addToCart(@RequestBody OrderServiceDTO dto) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            responseDTO.setMessage(SystemConstant.SUCCESS_MESSAGE);
            responseDTO.setData(service.save(dto));
        } catch (NotPermissionAddToCart e) {
            responseDTO.setMessage(SystemConstant.FAIL_MESSAGE);
        }
        return responseDTO;
    }

    // day la api hiển thị theo user
    @GetMapping("/user")
    public ResponseDTO showOrderByUser() {
        ResponseDTO result = new ResponseDTO();
        result.setMessage(SystemConstant.SUCCESS_MESSAGE);
        System.out.println(SecurityUtils.getPrincipal().getId());
        result.setData(service.findAll(SecurityUtils.getPrincipal().getId()));
        return result;
    }

    @GetMapping("/get-all-type")
    public List<ServiceEntity> getAllServiceType() {
        List<ServiceEntity> list = service.getAllServiceType();
        return list;
    }
}
