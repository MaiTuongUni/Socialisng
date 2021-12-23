package com.spring.socialising.repositories.StoryRepository;

import com.spring.socialising.entities.Story;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface StoryRepository extends MongoRepository<Story, String> {

    Story findStoriesByUserId(String userId);
}
