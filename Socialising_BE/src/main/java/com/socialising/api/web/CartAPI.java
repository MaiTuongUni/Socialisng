package com.socialising.api.web;

import com.socialising.constant.SystemConstant;
import com.socialising.dto.customexception.NotPermissionAddToCart;
import com.socialising.dto.request.CartDTO;
import com.socialising.dto.request.OrderDTO;
import com.socialising.dto.response.ProductResponseDTO;
import com.socialising.dto.response.ResponseDTO;
import com.socialising.entity.CartEntity;
import com.socialising.repository.CartRepository;
import com.socialising.service.ICartService;
import com.socialising.service.IOrderService;
import com.socialising.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController(value = "cartApiOfWeb")
@RequestMapping(SystemConstant.API_VERSION +"/cart")
public class CartAPI {

    @Autowired
    private IProductService productService;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ICartService cartService;

    @Autowired
    private IOrderService orderService;

    @GetMapping("/{cartid}/remain")
    public int getQuantityRemainOfProductByCartId(@PathVariable("cartid") Long cartId) {
        Optional<CartEntity> cart = cartRepository.findById(cartId);
        if (cart.isPresent()) {
            ProductResponseDTO product = productService.findById(cart.get().getProduct().getId());
            if (product != null) {
                return product.getNumberOfSell();
            }
        }
        return 0;
    }

    @GetMapping
    public ResponseDTO showCart(@RequestParam(value = "sessionId", required = false) String sessionId) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage(SystemConstant.SUCCESS_MESSAGE);
        responseDTO.setData(cartService.findAll(sessionId));
        return responseDTO;
    }

    @PostMapping("/{productid}")
    public ResponseDTO addToCart(@PathVariable("productid") Long productId, @RequestBody CartDTO cartDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            responseDTO.setMessage(SystemConstant.SUCCESS_MESSAGE);
            responseDTO.setData(cartService.addToCart(productId, cartDTO));
        } catch (NotPermissionAddToCart e) {
            responseDTO.setMessage(SystemConstant.FAIL_MESSAGE);
        }
        return responseDTO;
    }

    @PostMapping("/{cartid}/quantity")
    public ResponseDTO editQuantityCart(@PathVariable("cartid") Long cartId, @RequestBody CartDTO cartDTO) {
        cartService.editQuantityCart(cartId, cartDTO);
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage(SystemConstant.SUCCESS_MESSAGE);
        return responseDTO;
    }

    @DeleteMapping("/{cartid}")
    public ResponseDTO deleteCart(@PathVariable("cartid") Long cartId) {
        cartService.deleteCart(cartId);
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage(SystemConstant.SUCCESS_MESSAGE);
        return responseDTO;
    }

    @PostMapping
    public ResponseDTO addOrder(@RequestBody OrderDTO orderDTO, @RequestParam(value = "payment", required = false) String payment) {
        if (payment != null && payment != "" && payment.equalsIgnoreCase("y")) {
            orderDTO.setStatus(SystemConstant.ORDER_PAID);
        } else {
            orderDTO.setStatus(SystemConstant.ORDER_CONFIRM);
        }
        orderService.save(orderDTO);
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage(SystemConstant.SUCCESS_MESSAGE);
        orderDTO.setCode(orderDTO.getSessionId());
        responseDTO.setData(orderDTO);
        return responseDTO;
    }
}
