package com.socialising.repository;

import com.socialising.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    RoleEntity findOneById(Long id);
    RoleEntity findOneByCode(String id);
}
