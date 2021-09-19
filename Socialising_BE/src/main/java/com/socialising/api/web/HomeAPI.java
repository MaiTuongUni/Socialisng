package com.socialising.api.web;

import com.socialising.constant.SystemConstant;
import com.socialising.dto.GroupDTO;
import com.socialising.dto.SlideDTO;
import com.socialising.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController(value = "homeApiOfWeb")
@RequestMapping(SystemConstant.API_VERSION +"/home")
public class HomeAPI {

    @Autowired
    private IProductService productService;

    @GetMapping("/slide")
    public List<SlideDTO> getSlide() {
        return productService.getSlides();
    }

    @GetMapping("/new-best-sell-product")
    public List<GroupDTO> getNewAndBestSellProduct() {
        return productService.getGroups();
    }
}
