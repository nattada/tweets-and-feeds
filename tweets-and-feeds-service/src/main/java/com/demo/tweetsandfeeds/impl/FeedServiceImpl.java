package com.demo.tweetsandfeeds.impl;

import java.util.List;

import com.demo.tweetsandfeeds.dto.Feed;
import com.demo.tweetsandfeeds.repository.FeedRepository;

import org.apache.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class FeedServiceImpl {

    public FeedRepository feedRepository;

    public FeedServiceImpl(FeedRepository feedRepository){
        this.feedRepository = feedRepository;
    }

    public ResponseEntity<List<Feed>>findAll(){
        List<Feed> feedList = feedRepository.findAll();
        return new ResponseEntity<List<Feed>>(feedList, null, HttpStatus.SC_OK);
    }

    public ResponseEntity<Feed> findById(String id){
        Feed feed = feedRepository.findById(id).orElse(null);
        return new ResponseEntity<Feed>(feed, null, HttpStatus.SC_OK);
    
    }

    public ResponseEntity<Feed> save(Feed feed){
        Feed newFeed =  feedRepository.save(feed);
        return new ResponseEntity<Feed>(newFeed, null, HttpStatus.SC_OK);
    }

    public ResponseEntity<Feed> deleteById(String id){ß
        feedRepository.deleteById(id);
        return new ResponseEntity<Feed>(null, null, HttpStatus.SC_OK);
    } 
    
}
 