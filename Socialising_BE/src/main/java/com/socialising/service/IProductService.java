package com.socialising.service;

import com.socialising.dto.AttributeDTO;
import com.socialising.dto.GroupDTO;
import com.socialising.dto.request.AttributeRequestDTO;
import com.socialising.dto.request.ProductRequestDTO;
import com.socialising.dto.SlideDTO;
import com.socialising.dto.response.AttributeResponseDTO;
import com.socialising.dto.response.ProductResponseDTO;
import com.socialising.dto.response.ResponseDTO;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface IProductService {
    List<ProductResponseDTO> findAll();
    List<ProductResponseDTO> findAll(Pageable pageable);
    ResponseDTO findAllCheckBandCode(Integer minValue, Integer maxValue, String brandCode, Pageable pageable);

    ResponseDTO findAllProductActiveByName(Integer minValue, Integer maxValue,String name, Pageable pageable);
    List<ProductResponseDTO> findTop3ProductRelated(Integer number, Pageable pageable);

    ProductResponseDTO findById(Long id);
    ProductResponseDTO findById(Long id, String color, String size);
    List<SlideDTO> getSlides();
    List<GroupDTO> getGroups();
    ProductResponseDTO save(ProductRequestDTO dto);
    ProductResponseDTO update(long id, ProductRequestDTO dto);
    void delete(long[] ids);
    List<AttributeDTO> findAttributeList(Long productId);
    AttributeResponseDTO findAttribute(Long id);
    AttributeResponseDTO addAttribute(Long productId, AttributeRequestDTO attributeRequestDTO);
    AttributeResponseDTO updateAttribute(Long id, AttributeRequestDTO attributeRequestDTO);
    void deleteAttribute(long[] ids);

//    List<ProductResponseDTO> findProductByAdmin(String filter, Pageable pageable);

    ResponseDTO findProduct(String filter, Pageable pageable);
}
