package com.demo.tweetsandfeeds.repository;

import com.demo.tweetsandfeeds.dto.Feed;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedRepository extends MongoRepository<Feed,String> {
    
}
