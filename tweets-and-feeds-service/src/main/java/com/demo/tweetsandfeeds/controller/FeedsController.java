package com.demo.tweetsandfeeds.controller;

import java.util.List;

import com.demo.tweetsandfeeds.dto.Feed;
import com.demo.tweetsandfeeds.impl.FeedServiceImpl;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/feed")
public class FeedsController {

    FeedServiceImpl feedService; 
    
    public FeedsController(FeedServiceImpl feedService){
        this.feedService = feedService;
    }

    @PostMapping
    public ResponseEntity<Feed> postFeed(@RequestBody Feed feed){
        return feedService.save(feed);
    }

    @GetMapping
    public ResponseEntity<List<Feed>> findAll(){
        return feedService.findAll();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Feed> update(@RequestBody Feed Feed){
        return feedService.save(Feed);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Feed> deleteById(@PathVariable String id){
        return feedService.deleteById(id);
    }
}
