package com.socialising.repository;

import com.socialising.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
	UserEntity findOneByUserNameAndStatus(String name, int status);
	UserEntity findOneById(Long id);
	UserEntity findOneByEmail(String email);
    @Query("select u from UserEntity u where  u.userName = :username")
	UserEntity findOneByPassword(@Param("username") String username);
}
