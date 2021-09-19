package com.socialising.api.web;

import com.socialising.constant.SystemConstant;
import com.socialising.dto.response.ProductResponseDTO;
import com.socialising.dto.response.ResponseDTO;
import com.socialising.repository.ProductRepository;
import com.socialising.service.IProductService;
import com.socialising.util.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;

@RestController(value = "productApiOfWeb")
@RequestMapping(SystemConstant.API_VERSION + "/product")
public class ProductAPI {

    @Autowired
    private IProductService productService;

    @Autowired
    private ProductRepository productRepository;

//	 @GetMapping
//	    public List<ProductResponseDTO> showProductList(@RequestParam(value = "brandcode", required = false) String brandCode) {
//	        return productService.findAll(brandCode);
//	    }

    @GetMapping
    public ResponseDTO showProductList(@RequestParam(value = "brandcode", required = false) String brandCode,
                                       @RequestParam(value = "page", required = false) Integer page,
                                       @RequestParam(value = "limit", required = false) Integer limit,
                                       @RequestParam(value = "maxValue") Integer maxValue,
                                       @RequestParam(value = "minValue") Integer minValue) {
        Pageable pageable = PageRequest.of(page - 1, limit);
        ResponseDTO result = productService.findAllCheckBandCode(minValue, maxValue, brandCode, pageable);
        result.setMessage("success");
        result.setPageSize(limit);
        result.setCurrentPage(page);
        return result;
    }

    @GetMapping("/find/{name}")
    public ResponseDTO findProductList(@PathVariable(value = "name", required = false) String name, @RequestParam(value = "page", required = false) Integer page,
                                       @RequestParam(value = "limit", required = false) Integer limit,
                                       @RequestParam(value = "maxValue") Integer maxValue,
                                       @RequestParam(value = "minValue") Integer minValue) {
        Pageable pageable = PageRequest.of(page - 1, limit);
        ResponseDTO result = productService.findAllProductActiveByName(minValue, maxValue, name, pageable);
        result.setMessage("success");
        result.setPageSize(limit);
        result.setCurrentPage(page);
        return result;
    }

    @GetMapping("/related/{id}")
    public ResponseDTO findTop3RelatedProduct(@PathVariable(name = "id") Long id) {
        ResponseDTO result = new ResponseDTO();
        Pageable pageable = PageRequest.of(1, 4);
        result.setMessage("success");
        Integer number = productService.findById(id).getPrice();
        result.setData(productService.findTop3ProductRelated(number, pageable));
        result.setTotalCount(4);
        result.setPageSize(4);
        result.setCurrentPage(1);
        return result;
    }

    @GetMapping("/{id}")
    public ProductResponseDTO showProductDetail(@RequestParam(value = "size", required = false) String size,
                                                @RequestParam(value = "color", required = false) String color, @PathVariable("id") Long productId) {
        return productService.findById(productId, color, size);
    }

    @GetMapping(value = "/image/{name}")
    public ResponseEntity<InputStreamResource> getImage(@PathVariable("name") String name) throws IOException {
        InputStream inputStream = FileUtils.getInputStream(name);
        return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(new InputStreamResource(inputStream));
    }
}
