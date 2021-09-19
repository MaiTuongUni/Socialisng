package com.socialising.repository;

import com.socialising.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    @Query(value="select p from ProductEntity p where p.brand.name =:brandCode and p.statusProduct=true")
    Page<ProductEntity> findAllProductByBrandCodeAndActive(@Param("brandCode") String brandCode, Pageable pageable);

    long countByBrandCode(String brandCode);

    List<ProductEntity> findByIsNew(Integer news);

    List<ProductEntity> findByIsHot(Integer hots);

    @Query(value = "select p from ProductEntity p where  p.statusProduct = true and p.name like %:name%")
    Page<ProductEntity> findAllProductActiveByName(@Param("name") String name, Pageable pageable);

    @Query(value="select p from ProductEntity  p where  p.statusProduct = true")
    Page<ProductEntity> findAllProductActive(Pageable pageable);

    @Query(value = "select p from ProductEntity p where 1=1 order by abs(p.price- :number) asc ")
    Page<ProductEntity> findTop3ProductRelated(Integer number, Pageable pageable);

    @Query(value = "select p from ProductEntity p where p.isHot = 1")
    Page<ProductEntity> findProductHot(Pageable pageable);

    @Query(value = "select p from ProductEntity p where p.isNew = 1")
    Page<ProductEntity> findProductNew(Pageable pageable);

    @Query(value="select p from ProductEntity p where p.brand.name =:brandCode and p.statusProduct=true and p.price >= :min and p.price <=:max")
    Page<ProductEntity> findAllProductByBrandCodeAndActive(@Param("min") Integer min,
                                                           @Param("max") Integer max,
                                                           @Param("brandCode") String brandCode, Pageable pageable);

    @Query(value = "select p from ProductEntity p where  p.statusProduct = true and p.name like %:name% and p.price >= :min and p.price <=:max")
    Page<ProductEntity> findAllProductActiveByName(@Param("min") Integer min,
                                                   @Param("max") Integer max,
                                                   @Param("name") String name, Pageable pageable);

    @Query(value="select p from ProductEntity  p where  p.statusProduct = true and p.price >= :min and p.price <=:max")
    Page<ProductEntity> findAllProductActive(@Param("min") Integer min,
                                             @Param("max") Integer max,
                                             Pageable pageable);

}