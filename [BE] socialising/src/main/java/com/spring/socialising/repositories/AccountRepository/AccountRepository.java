package com.spring.socialising.repositories.AccountRepository;

import com.spring.socialising.entities.Account;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface AccountRepository extends MongoRepository<Account, UUID> {

}
