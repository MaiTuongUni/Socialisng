package com.spring.socialising.repositories.AccountRepository;

import com.spring.socialising.entities.Account;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AccountRepository extends MongoRepository<Account, UUID> {
  Account findByUserName(String username);
}
