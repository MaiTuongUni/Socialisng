package com.socialising.api.admin;

import com.socialising.dto.request.AttributeRequestDTO;
import com.socialising.dto.request.ProductRequestDTO;
import com.socialising.dto.request.RequestDTO;
import com.socialising.dto.response.ResponseDTO;
import com.socialising.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@CrossOrigin(origins = "http://localhost:4200")
@RestController(value = "productApiOfAdmin")
@RequestMapping("/api/admin/product")
public class ProductAPI {

    @Autowired
    private IProductService productService;

    @GetMapping
    public ResponseDTO showProduct(@RequestParam(value = "page", required = false) Integer page,
                                   @RequestParam(value = "limit", required = false) Integer limit,
                                   @RequestParam(value = "filter", required = false) String filter) {

        Pageable pageable = PageRequest.of(page - 1, limit);
        ResponseDTO result = productService.findProduct(filter, pageable);
        result.setMessage("success");
        ArrayList<Object> numberCount = (ArrayList<Object>) result.getData();
        result.setTotalCount(numberCount.size());
        result.setPageSize(limit);
        result.setCurrentPage(page);
        return result;
    }

    @GetMapping("/{id}")
    public ResponseDTO showProductDetail(@PathVariable("id") long id) {
        ResponseDTO result = new ResponseDTO();
        result.setMessage("success");
        result.setData(productService.findById(id));
        return result;
    }

    @PostMapping
    public ResponseDTO createProduct(@RequestBody ProductRequestDTO newProduct) {
        ResponseDTO result = new ResponseDTO();
        result.setMessage("success");
        result.setData(productService.save(newProduct));
        return result;
    }

    @PutMapping("/{id}")
    public ResponseDTO updateProduct(@PathVariable("id") long id, @RequestBody ProductRequestDTO updateProduct) {
        ResponseDTO result = new ResponseDTO();
        result.setMessage("success");
        result.setData(productService.update(id, updateProduct));
        return result;
    }

    @DeleteMapping
    public ResponseDTO deleteProduct(@RequestBody RequestDTO requestDTO) {
        ResponseDTO result = new ResponseDTO();
        productService.delete(requestDTO.getIds());
        result.setMessage("success");
        return result;
    }

    @GetMapping("/{id}/attribute")
    public ResponseDTO showAttributeList(@PathVariable("id") long id) {
        ResponseDTO result = new ResponseDTO();
        result.setMessage("success");
        result.setData(productService.findAttributeList(id));
        return result;
    }

    @GetMapping("/{id}/attribute/{attributeId}")
    public ResponseDTO showAttributeDetail(@PathVariable("attributeId") long attrId) {
        ResponseDTO result = new ResponseDTO();
        result.setMessage("success");
        result.setData(productService.findAttribute(attrId));
        return result;
    }

    @PostMapping("/{id}/attribute")
    public ResponseDTO addAttribute(@PathVariable("id") Long productId, @RequestBody AttributeRequestDTO attributeRequestDTO) {
        ResponseDTO result = new ResponseDTO();
        result.setMessage("success");
        result.setData(productService.addAttribute(productId, attributeRequestDTO));
        return result;
    }

    @PutMapping("/{id}/attribute/{attributeId}")
    public ResponseDTO updateAttribute(@PathVariable("attributeId") Long attrId, @RequestBody AttributeRequestDTO attributeRequestDTO) {
        ResponseDTO result = new ResponseDTO();
        result.setMessage("success");
        result.setData(productService.updateAttribute(attrId, attributeRequestDTO));
        return result;
    }

    @DeleteMapping("/{id}/attribute")
    public ResponseDTO deleteAttribute(@RequestBody RequestDTO requestDTO) {
        ResponseDTO result = new ResponseDTO();
        productService.deleteAttribute(requestDTO.getIds());
        result.setMessage("success");
        return result;
    }
}
