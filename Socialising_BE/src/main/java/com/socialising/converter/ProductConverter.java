package com.socialising.converter;

import com.socialising.dto.request.ProductRequestDTO;
import com.socialising.dto.response.ProductResponseDTO;
import com.socialising.entity.ProductEntity;
import org.apache.commons.lang.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductConverter {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public ProductRequestDTO convertToDTO(ProductEntity entity) {
		ProductRequestDTO dto = modelMapper.map(entity, ProductRequestDTO.class);
		return dto;
	}
	
	public ProductEntity convertToEntity(ProductRequestDTO dto) {
		ProductEntity entity = modelMapper.map(dto, ProductEntity.class);
		return entity;
	}

	public ProductResponseDTO convertToResponseDTO(ProductEntity entity) {
		ProductResponseDTO dto = modelMapper.map(entity, ProductResponseDTO.class);
		if (StringUtils.isNotBlank(entity.getImages())) {
			dto.setImageNames(entity.getImages().split(","));
		}
		dto.setBrandCode(entity.getBrand().getCode());
		dto.setStatusProduct(entity.getStatusProduct());
		return dto;
	}

}
