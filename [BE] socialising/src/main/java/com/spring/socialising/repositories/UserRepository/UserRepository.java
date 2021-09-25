package com.spring.socialising.repositories.UserRepository;
import com.spring.socialising.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends MongoRepository<User, UUID> {
    User findByPhoneNumber(String phoneNumber);
}
