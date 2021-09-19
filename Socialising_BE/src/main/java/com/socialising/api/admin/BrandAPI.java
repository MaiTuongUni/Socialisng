package com.socialising.api.admin;

import com.socialising.dto.BrandDTO;
import com.socialising.dto.request.RequestDTO;
import com.socialising.dto.response.ResponseDTO;
import com.socialising.service.IBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController(value = "brandApiOfAdmin")
@RequestMapping("/api/admin/brand")
public class BrandAPI {

    @Autowired
    private IBrandService brandService;

    @GetMapping
    public ResponseDTO showBrand() {
        ResponseDTO result = new ResponseDTO();
        result.setMessage("success");
        result.setData(brandService.findAll());
        return result;
    }

    @GetMapping("/{id}")
    public ResponseDTO showBrandDetail(@PathVariable("id") long id) {
        ResponseDTO result = new ResponseDTO();
        result.setMessage("success");
        result.setData(brandService.findById(id));
        return result;
    }

    @PostMapping
    public ResponseDTO createBrand(@RequestBody BrandDTO newBrand) {
        ResponseDTO result = new ResponseDTO();
        result.setMessage("success");
        result.setData(brandService.save(newBrand));
        return result;
    }

    @PutMapping("/{id}")
    public ResponseDTO updateBrand(@PathVariable("id") long id, @RequestBody BrandDTO updateBrand) {
        ResponseDTO result = new ResponseDTO();
        result.setMessage("success");
        result.setData(brandService.update(id, updateBrand));
        return result;
    }

    @DeleteMapping
    public ResponseDTO deleteProduct(@RequestBody RequestDTO requestDTO) {
        ResponseDTO result = new ResponseDTO();
        brandService.delete(requestDTO.getIds());
        result.setMessage("success");
        return result;
    }
}
