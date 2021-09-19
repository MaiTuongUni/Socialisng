package com.socialising.api.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.socialising.constant.SystemConstant;
import com.socialising.dto.request.OrderServiceDTO;
import com.socialising.dto.response.ResponseDTO;
import com.socialising.service.impl.OrderServiceSer;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(SystemConstant.API_VERSION +"/admin/orderservices")

public class OrderServiceAdminAPI {
	@Autowired 
	private OrderServiceSer service;
	
	// đây là hàm show tất cả thông tin
    @GetMapping
    public ResponseDTO showOrder() {
        ResponseDTO result = new ResponseDTO();
        result.setMessage(SystemConstant.SUCCESS_MESSAGE);
        List<OrderServiceDTO> orderServices = service.findAll();
        result.setData(orderServices);
        result.setTotalCount(orderServices.size());
        return result;
    }
    @PostMapping
    public ResponseDTO updateStatus(@RequestBody OrderServiceDTO orderServiceDTO) {
    	service.updateStatus(orderServiceDTO);
        ResponseDTO result = new ResponseDTO();
        result.setMessage(SystemConstant.SUCCESS_MESSAGE);
        return result;
    }
}
