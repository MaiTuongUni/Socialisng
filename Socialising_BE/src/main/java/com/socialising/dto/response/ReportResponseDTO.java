package com.socialising.dto.response;

import com.socialising.dto.ReportDTO;

import java.util.ArrayList;
import java.util.List;

public class ReportResponseDTO {

    private List<ReportDTO> reports = new ArrayList<>();
    private Integer totalPrice;

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<ReportDTO> getReports() {
        return reports;
    }

    public void setReports(List<ReportDTO> reports) {
        this.reports = reports;
    }
}
