package com.socialising.api.admin;

import com.socialising.dto.request.OrderDTO;
import com.socialising.dto.request.OrderServiceDTO;
import com.socialising.dto.response.ResponseDTO;
import com.socialising.service.IOrderService;
import com.socialising.service.IOrderServiceSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController(value = "reportApiOfAdmin")
@RequestMapping("/api/admin/report")
public class ReportAPI {

    @Autowired
    private IOrderService orderService;

    @Autowired
    private IOrderServiceSer orderServiceSer;

    int total;

    @GetMapping("/statistic-by-day")
    public int statisticByDay() {
        total = 0;
        Date now = new Date();
        List<OrderDTO> orderDTOS = orderService.findAll();
        if (orderDTOS != null && orderDTOS.size() > 0) {
            total = orderDTOS.stream()
                    .filter(o -> o.getCreatedDate().getDate() == now.getDate())
                    .map(o -> o.getTotal())
                    .mapToInt(o -> o).sum();
        }
        List<OrderServiceDTO> orderServiceDTOS = orderServiceSer.findAll();
        if (orderServiceDTOS != null && orderServiceDTOS.size() > 0) {
            total += orderServiceDTOS.stream()
                    .filter(o -> o.getCreatedDate().getDate() == now.getDate())
                    .map(o -> o.getTotal())
                    .mapToInt(o -> o).sum();
        }
        return total;
    }

    @GetMapping("/statistic-by-week")
    public int statisticByWeek() {
        total = 0;
        LocalDate localDate = LocalDate.now().minusDays(7);
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        List<OrderDTO> orderDTOS = orderService.findAll();
        if (orderDTOS != null && orderDTOS.size() > 0) {
            total = orderDTOS.stream()
                    .filter(o -> o.getCreatedDate().after(date))
                    .map(o -> o.getTotal())
                    .mapToInt(o -> o).sum();
        }
        List<OrderServiceDTO> orderServiceDTOS = orderServiceSer.findAll();
        if (orderServiceDTOS != null && orderServiceDTOS.size() > 0) {
            total += orderServiceDTOS.stream()
                    .filter(o -> o.getCreatedDate().after(date))
                    .map(o -> o.getTotal())
                    .mapToInt(o -> o).sum();
        }
        return total;
    }

    @GetMapping("/statistic-by-month")
    public int statisticByMonth() {
        total = 0;
        Date now = new Date();
        List<OrderDTO> orderDTOS = orderService.findAll();
        if (orderDTOS != null && orderDTOS.size() > 0) {
            total = orderDTOS.stream()
                    .filter(o -> o.getCreatedDate().getMonth() == now.getMonth())
                    .map(o -> o.getTotal())
                    .mapToInt(o -> o).sum();
        }
        List<OrderServiceDTO> orderServiceDTOS = orderServiceSer.findAll();
        if (orderServiceDTOS != null && orderServiceDTOS.size() > 0) {
            total += orderServiceDTOS.stream()
                    .filter(o -> o.getCreatedDate().getMonth() == now.getMonth())
                    .map(o -> o.getTotal())
                    .mapToInt(o -> o).sum();
        }
        return total;
    }

    @GetMapping("/statistic-by-year")
    public int statisticByYear() {
        total = 0;
        Date now = new Date();
        List<OrderDTO> orderDTOS = orderService.findAll();
        if (orderDTOS != null && orderDTOS.size() > 0) {
            total = orderDTOS.stream()
                    .filter(o -> o.getCreatedDate().getYear() == now.getYear())
                    .map(o -> o.getTotal())
                    .mapToInt(o -> o).sum();
        }
        List<OrderServiceDTO> orderServiceDTOS = orderServiceSer.findAll();
        if (orderServiceDTOS != null && orderServiceDTOS.size() > 0) {
            total += orderServiceDTOS.stream()
                    .filter(o -> o.getCreatedDate().getYear() == now.getYear())
                    .map(o -> o.getTotal())
                    .mapToInt(o -> o).sum();
        }
        return total;
    }

    @GetMapping
    public ResponseDTO showReport(@RequestParam("toDate") String toDate, @RequestParam("fromDate") String fromDate) {
        ResponseDTO result = new ResponseDTO();
        result.setMessage("success");
        result.setData(orderService.report(fromDate, toDate));
        return result;
    }
}
