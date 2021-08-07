package com.demo.tweetsandfeeds.controller;

import java.util.List;

import com.demo.tweetsandfeeds.dto.TweetDetail;
import com.demo.tweetsandfeeds.impl.TwitterServiceImpl;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/twitter")
public class TwitterController {

    private TwitterServiceImpl twitterImpl;

    public TwitterController(TwitterServiceImpl twitterImpl){
        this.twitterImpl = twitterImpl;

    }
   
    @GetMapping("/seachTweets")
    public ResponseEntity<List<TweetDetail>> getTweetContainText(@RequestHeader("Authorization") String authToken, @RequestParam(value = "text") String text){
        return twitterImpl.searchContent(text,authToken);
    }
  
}
