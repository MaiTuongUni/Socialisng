package com.socialising.converter;

import com.socialising.dto.BrandDTO;
import com.socialising.entity.BrandEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BrandConverter {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public BrandDTO convertToDTO(BrandEntity entity) {
		BrandDTO dto = modelMapper.map(entity, BrandDTO.class);
		return dto;
	}
	
	public BrandEntity convertToEntity(BrandDTO dto) {
		BrandEntity entity = modelMapper.map(dto, BrandEntity.class);
		return entity;
	}

}
