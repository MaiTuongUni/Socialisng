package com.socialising.repository;

import com.socialising.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<CartEntity, Long> {
    List<CartEntity> findBySessionId(String sessionId);
    List<CartEntity> findByUser_Id(Long userId);
    void deleteBySessionId(String sessionId);
    void deleteByUser_Id(Long userId);
    CartEntity findByProduct_IdAndSizeAndColor(Long productId, String size, String color);
}
