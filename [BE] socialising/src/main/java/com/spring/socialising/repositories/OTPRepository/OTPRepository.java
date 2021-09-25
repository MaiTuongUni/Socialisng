package com.spring.socialising.repositories.OTPRepository;

import com.spring.socialising.entities.OTP;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface OTPRepository extends MongoRepository<OTP, UUID> {


}

