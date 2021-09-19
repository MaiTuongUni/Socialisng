package com.socialising.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.socialising.dto.request.OrderServiceDTO;
import com.socialising.entity.OrderServiceEntity;
@Component
public class OrderServiceConvert {
	@Autowired
	private ModelMapper modelMapper;
	
	public OrderServiceDTO convertToDTO(OrderServiceEntity entity) {
		OrderServiceDTO dto = modelMapper.map(entity, OrderServiceDTO.class);
		return dto;
	}
	
	public OrderServiceEntity convertToEntity(OrderServiceDTO dto) {
		OrderServiceEntity entity = modelMapper.map(dto, OrderServiceEntity.class);
		return entity;
	}

}
