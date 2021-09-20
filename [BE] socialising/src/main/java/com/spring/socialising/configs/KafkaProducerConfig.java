package com.spring.socialising.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaProducerConfig {
    @Value(value = "${kafka.bootstrap-servers}")
    private String bootstrapAddress;

}
