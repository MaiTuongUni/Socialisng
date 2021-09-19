package com.socialising.service.impl;

import com.socialising.constant.SystemConstant;
import com.socialising.converter.OrderConverter;
import com.socialising.dto.ReportDTO;
import com.socialising.dto.UserDTO;
import com.socialising.dto.request.MailRequest;
import com.socialising.dto.request.OrderDTO;
import com.socialising.dto.request.OrderDetailDTO;
import com.socialising.dto.response.ReportResponseDTO;
import com.socialising.entity.*;
import com.socialising.repository.*;
import com.socialising.service.IOrderService;
import com.socialising.util.DateUtils;
import com.socialising.util.SecurityUtils;
import com.socialising.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class OrderService implements IOrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private EmailServiceInfoCart emailserviceinfo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderConverter orderConverter;
    @Autowired
    private EmailService emailService;

    @Override
    public List<OrderDTO> findAll() {
        List<OrderDTO> orderDTOS = orderRepository.findAll().stream()
                .map(item -> orderConverter.convertToDTO(item))
                .sorted((b, a) -> Long.compare(a.getCreatedDate().getTime(), b.getCreatedDate().getTime()))
                .collect(Collectors.toList());
        return orderDTOS;
    }

    @Override
    public List<OrderDTO> findAll(Long userId) {
        List<OrderEntity> orderEntities = orderRepository.findByUserEntity_Id(userId);
        List<OrderDTO> orderDTOS = orderEntities.stream()
                .map(item -> orderConverter.convertToDTO(item))
                .sorted((b, a) -> Long.compare(a.getCreatedDate().getTime(), b.getCreatedDate().getTime()))
                .collect(Collectors.toList());
        return orderDTOS;
    }

    @Override
    @Transactional
    public void save(OrderDTO orderDTO) {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            List<CartEntity> cartEntities = null;
            UserEntity userEntity = null;
            if (auth != null && !auth.getPrincipal().equals("anonymousUser")) {
                Long userId = SecurityUtils.getPrincipal().getId();
                cartEntities = cartRepository.findByUser_Id(userId);

                userEntity = userRepository.findById(userId).get();
            } else if (orderDTO.getSessionId() != null) {
                cartEntities = cartRepository.findBySessionId(orderDTO.getSessionId());
                userEntity = addNewUser(orderDTO);
            }
            List<OrderDetailEntity> orderDetailEntities = new ArrayList<>();
            Integer total = 0;
            for (CartEntity item : cartEntities) {
                OrderDetailEntity orderDetailEntity = new OrderDetailEntity();
                orderDetailEntity.setProduct(item.getProduct());
                orderDetailEntity.setQuantity(item.getQuantity());
                orderDetailEntity.setTotal(item.getTotal());
                orderDetailEntity.setSize(item.getSize());
                orderDetailEntity.setColor(item.getColor());
                total += item.getTotal();
                orderDetailEntities.add(orderDetailEntity);
            }
            OrderEntity orderEntity = new OrderEntity();
            orderEntity.setCode(StringUtils.generateValue(4));
            orderEntity.setTotal(total);
            orderEntity.setUserEntity(userEntity);
            if (orderDTO.getPerson() != null) {
                orderEntity.setFullName(orderDTO.getPerson().getFullName());
                orderEntity.setAddress(orderDTO.getPerson().getAddress());
                orderEntity.setPhone(orderDTO.getPerson().getPhone());
            }
            orderEntity.setStatus(orderDTO.getStatus());
            orderEntity = orderRepository.save(orderEntity);
            for (OrderDetailEntity item : orderDetailEntities) {
                item.setOrder(orderEntity);
                orderDetailRepository.save(item);

                // Update quantity product
                Optional<ProductEntity> productOptional = productRepository.findById(item.getProduct().getId());
                if (productOptional.isPresent()) {
                    ProductEntity product = productOptional.get();
                    product.setNumberOfSell(product.getNumberOfSell() - item.getQuantity());
                    productRepository.save(product);
                }
            }
            if (auth != null && !auth.getPrincipal().equals("anonymousUser")) {
                Long userId = SecurityUtils.getPrincipal().getId();
                cartRepository.deleteByUser_Id(userId);
            } else if (orderDTO.getSessionId() != null) {
                String sessionId = orderDTO.getSessionId();
                cartRepository.deleteBySessionId(sessionId);
            }
//            if(!orderDTO.getPerson().getEmail().isEmpty()) {
//                MailRequest mailRequest = new MailRequest();
//                mailRequest.setName(orderDTO.getFullName());
//                mailRequest.setFrom("nangtbph07510@fpt.edu.vn");
//                mailRequest.setTo(orderDTO.getPerson().getEmail());
//                mailRequest.setSubject("Đơn hàng #208WUMU" + orderDTO.getPhone());
//                Map<String, Object> model = new HashMap<>();
//                model.put("Name", "hehe");
//                model.put("Status", "Đặt hàng thành công");
//                model.put("location", "Xuân Phương , Hà Nội");
//                for (CartEntity item: cartEntities) {
//                	model.put("info","Tên sản phẩm: "+item.getProduct().getName()+ "số lượng: "+item.getQuantity()+"Số tiền: "+item.getTotal());             
//                }
//                
//                model.put("Email", mailRequest.getTo());
//                model.put("Don", "Đơn hàng #" + orderEntity.getCode());
//                emailserviceinfo.sendEmail(mailRequest, model);
//                System.out.println(orderDTO.getPerson().getEmail());
//    		}
            System.out.println("hhưew");
        } catch (Exception e) {
            throw e;
        }
    }

    private UserEntity addNewUser(OrderDTO orderDTO) {
        RoleEntity role = roleRepository.findOneByCode("USER");
        UserEntity userEntity = new UserEntity();
        userEntity.setRoles(Stream.of(role).collect(Collectors.toList()));
        userEntity.setStatus(1);
        userEntity.setUserName(orderDTO.getPerson().getPhone());
        userEntity.setFullName(orderDTO.getPerson().getFullName());
        userEntity.setPassword(passwordEncoder.encode("123456"));

        if (!orderDTO.getPerson().getEmail().isEmpty()) {
            MailRequest mailRequest = new MailRequest();
            mailRequest.setName(orderDTO.getFullName());
            mailRequest.setFrom("truyentmph09575@fpt.edu.vn");
            mailRequest.setTo(orderDTO.getPerson().getEmail());
            mailRequest.setSubject("Đơn hàng #208WUMU" + orderDTO.getPhone());
            Map<String, Object> model = new HashMap<>();
            model.put("Name", "Star Shop");
            model.put("sdt", "Tài khoản của bạn là: " + orderDTO.getPerson().getPhone());
            model.put("Pass", "Mật khẩu của bạn là : 123456");
            model.put("Status", "Đặt hàng thành công");
            model.put("location", "Trịnh Văn Bô , Hà Nội");
            model.put("Email", mailRequest.getTo());
            model.put("Don", "Đơn hàng #208WUMU" + orderDTO.getPhone());
            emailService.sendEmail(mailRequest, model);
        }
        return userRepository.save(userEntity);

    }

    @Override
    @Transactional
    public void updateStatus(OrderDTO orderDTO) {
        try {
            OrderEntity orderEntity = orderRepository.findById(orderDTO.getOrderId()).get();
            orderEntity.setStatus(orderDTO.getStatus());
            if (orderDTO.getNote() != null) {
                orderEntity.setNote(orderDTO.getNote());
            }
            orderRepository.save(orderEntity);
            List<OrderDetailEntity> orderDetailEntities = orderEntity.getOrderDetails();
            if (orderEntity.getStatus().equals(SystemConstant.ORDER_CONFIRM_OK)) {
                for (OrderDetailEntity item : orderDetailEntities) {
                    ProductEntity productEntity = item.getProduct();
                    productEntity.setNumberOfSell(productEntity.getNumberOfSell() - item.getQuantity());
                    productRepository.save(productEntity);
                }
            }
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Map<String, Object> getOrderDetail(Long orderId) {
        Map<String, Object> results = new HashMap<>();
        OrderEntity orderEntity = orderRepository.findById(orderId).get();
        List<OrderDetailDTO> orderDetailDTOS = new ArrayList<>();
        for (OrderDetailEntity item : orderEntity.getOrderDetails()) {
            OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
            orderDetailDTO.setProductName(item.getProduct().getName());
            orderDetailDTO.setProductCode(item.getProduct().getCode());
            orderDetailDTO.setPrice(item.getProduct().getPrice());
            orderDetailDTO.setQuantity(item.getQuantity());
            orderDetailDTO.setSubTotal(item.getTotal());
            orderDetailDTO.setSize(item.getSize());
            orderDetailDTO.setColor(item.getColor());
            orderDetailDTOS.add(orderDetailDTO);
        }
        results.put("products", orderDetailDTOS);
        results.put("createddate", orderEntity.getCreatedDate());
        results.put("status", orderEntity.getStatus());
        results.put("total", orderEntity.getTotal());
        results.put("note", orderEntity.getNote());
        UserDTO userDTO = new UserDTO();
        userDTO.setFullName(orderEntity.getFullName());
        userDTO.setPhone(orderEntity.getPhone());
        userDTO.setAddress(orderEntity.getAddress());
        results.put("user", userDTO);
        return results;
    }

    @Override
    public ReportResponseDTO report(String fromDateStr, String toDateStr) {
        ReportResponseDTO result = new ReportResponseDTO();
        List<ReportDTO> reportDTOS = new ArrayList<>();
        Date fromDate = org.apache.commons.lang.StringUtils.isNotBlank(fromDateStr) ? DateUtils.convertStringToDate("dd-MM-yyyy", fromDateStr) : null;
        Date toDate = org.apache.commons.lang.StringUtils.isNotBlank(toDateStr) ? DateUtils.convertStringToDate("dd-MM-yyyy", toDateStr) : null;
        List<OrderDetailEntity> orderDetailEntities = new ArrayList<>();
        if (fromDate != null && toDate == null) {
            orderDetailEntities = orderDetailRepository.findByCreatedDateAfter(fromDate);
        } else if (toDate != null && fromDate == null) {
            orderDetailEntities = orderDetailRepository.findByCreatedDateBefore(toDate);
        } else if (fromDate != null && toDate != null && (fromDate.compareTo(toDate) < 0)) {
            orderDetailEntities = orderDetailRepository.findByCreatedDateBetween(fromDate, toDate);
        }
        Map<Long, Object> maps = new HashMap<>();
        int totalPrice = 0;
        for (OrderDetailEntity item : orderDetailEntities) {
            ReportDTO reportDTO = new ReportDTO();
            maps.put(item.getProduct().getId(), reportDTO);
            totalPrice += item.getTotal();
        }
        for (Map.Entry<Long, Object> entry : maps.entrySet()) {
            Long productId = entry.getKey();
            ProductEntity productEntity = productRepository.findById(productId).get();
            int quantity = 0;
            for (OrderDetailEntity item : orderDetailEntities) {
                if (item.getProduct().getId().equals(productId)) {
                    quantity += item.getQuantity();
                }
            }
            ReportDTO reportDTO = new ReportDTO();
            reportDTO.setProductName(productEntity.getName());
            reportDTO.setProductCode(productEntity.getCode());
            reportDTO.setQuantity(quantity);
            reportDTO.setPrice(productEntity.getPrice());
            reportDTO.setTotal(reportDTO.getQuantity() * reportDTO.getPrice());
            reportDTOS.add(reportDTO);
        }
        result.setReports(reportDTOS);
        result.setTotalPrice(totalPrice);
        return result;
    }
}
