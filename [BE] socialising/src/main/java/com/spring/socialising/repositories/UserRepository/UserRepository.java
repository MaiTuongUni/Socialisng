package com.spring.socialising.repositories.UserRepository;
import com.spring.socialising.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Repository
@Transactional
public interface UserRepository extends MongoRepository<User, UUID> {
    User findByPhoneNumber(String phoneNumber);
}
