package com.socialising.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.socialising.entity.ServiceEntity;
import com.socialising.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.socialising.converter.OrderServiceConvert;
import com.socialising.dto.customexception.NotPermissionAddToCart;
import com.socialising.dto.request.MailRequest;
import com.socialising.dto.request.OrderServiceDTO;
import com.socialising.entity.OrderServiceEntity;
import com.socialising.entity.UserEntity;
import com.socialising.repository.OrderServiceRepo;
import com.socialising.repository.UserRepository;
import com.socialising.service.IOrderServiceSer;
import com.socialising.util.SecurityUtils;
import com.socialising.util.StringUtils;
@Service
public class OrderServiceSer implements IOrderServiceSer {

	@Autowired 
	private UserRepository userRepo;
	@Autowired
	private OrderServiceRepo orderServiceRepo;
	@Autowired
	private OrderServiceConvert orderserviceConvert;
    @Autowired
    private EmailServiceWash emailServicew;
    @Autowired
	private ServiceRepository serviceType;
    
	@Override
	public List<OrderServiceDTO> findAll() {
		// TODO Auto-generated method stub
		return orderServiceRepo.findAll().stream().map(item -> orderserviceConvert.convertToDTO(item)).collect(Collectors.toList());
	}

	@Override
	public List<ServiceEntity> getAllServiceType() {
		return serviceType.findAll();
	}

	@Override
	public List<OrderServiceDTO> findAll(Long userId) {
		// TODO Auto-generated method stub
		 List<OrderServiceEntity> orderEntities = orderServiceRepo.findByUserEntity_Id(userId);
	        return orderEntities.stream().map(item -> orderserviceConvert.convertToDTO(item)).collect(Collectors.toList());
	}

	@Override
	public OrderServiceDTO save(OrderServiceDTO dto) throws NotPermissionAddToCart {
		try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            UserEntity userEntity = null;
            if (auth != null && !auth.getPrincipal().equals("UserAmout")) {
                Long userId = SecurityUtils.getPrincipal().getId();
                userEntity = userRepo.findById(userId).get();
            } 
            OrderServiceEntity entity = new OrderServiceEntity();
			entity.setFullName(dto.getFullName());
			entity.setEmail(dto.getEmail());
			entity.setPhone(dto.getPhone());
			entity.setAddress(dto.getAddress());
			entity.setNote(dto.getNote());
			entity.setQuantity(dto.getQuantity());
			entity.setType(dto.isType());
			entity.setServiceName(dto.getServiceName());
			entity.setServicePrice(dto.getServicePrice());
			entity.setDspPrice(dto.getDspPrice());
			entity.setTotal(dto.getTotal());

            entity.setUserEntity(userEntity);
            entity.setCode(StringUtils.generateValue(4));

            entity.setStatus(dto.getStatus());
        
            orderServiceRepo.save(entity);
//            result.setSessionId(sessionId);
            return dto;
        } catch (Exception e) {
            throw e;
        }
	}

	@Override
	public void updateStatus(OrderServiceDTO orderServiceDTO) {
		try {
			if(!orderServiceDTO.getEmail().isEmpty()&&!orderServiceDTO.getFullName().isEmpty()) {
	            System.out.println(orderServiceDTO.getEmail());
	            MailRequest mailRequest = new MailRequest();
	            mailRequest.setName(orderServiceDTO.getFullName());
	            mailRequest.setFrom("nangtbph07510@fpt.edu.vn");
	            mailRequest.setTo(orderServiceDTO.getEmail());
	            mailRequest.setSubject("Đơn hàng #208WUMU" + orderServiceDTO.getPhone());
	            Map<String, Object> model = new HashMap<>();
	            model.put("Name", mailRequest.getName());
	            model.put("Status", orderServiceDTO.getStatus());
	            model.put("location", "Xuân Phương , Hà Nội");
	            model.put("Email", mailRequest.getTo());
	            model.put("Don", "Đơn hàng #208WUMU" + orderServiceDTO.getPhone());
//	            emailServicew.sendEmail(mailRequest, model);
			}

            OrderServiceEntity orderEntity = orderServiceRepo.findById(orderServiceDTO.getId()).get();
            orderEntity.setStatus(orderServiceDTO.getStatus());
            if (orderServiceDTO.getNote() != null) {
                orderEntity.setNote(orderServiceDTO.getNote());
            }
            orderServiceRepo.save(orderEntity);
        } catch (Exception e) {
            throw e;
        }
		
	}

	@Override
	public void deleteOrderServic(Long cartId) {
		// TODO Auto-generated method stub
		
	}

}
