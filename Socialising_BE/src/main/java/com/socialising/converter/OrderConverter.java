package com.socialising.converter;

import com.socialising.dto.request.CartDTO;
import com.socialising.dto.request.OrderDTO;
import com.socialising.entity.OrderEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderConverter {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public OrderDTO convertToDTO(OrderEntity entity) {
		OrderDTO dto = modelMapper.map(entity, OrderDTO.class);
		return dto;
	}
	
	public OrderEntity convertToEntity(CartDTO dto) {
		OrderEntity entity = modelMapper.map(dto, OrderEntity.class);
		return entity;
	}
}
