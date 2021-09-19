package com.socialising.repository;

import com.socialising.entity.OrderDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetailEntity, Long> {
    List<OrderDetailEntity> findByOrder_Id(Long id);
    List<OrderDetailEntity> findByCreatedDateBefore(Date to);
    List<OrderDetailEntity> findByCreatedDateAfter(Date from);
    List<OrderDetailEntity> findByCreatedDateBetween(Date from, Date to);
}
