package com.socialising.repository;

import com.socialising.entity.AttributeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttributeRepository extends JpaRepository<AttributeEntity, Long> {
    AttributeEntity findBySizeAndColorAndProduct_Id(String size, String color, Long id);
    List<AttributeEntity> findByProduct_Id(Long productId);
}
