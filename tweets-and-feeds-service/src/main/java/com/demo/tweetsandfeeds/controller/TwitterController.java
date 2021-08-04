package com.demo.tweetsandfeeds.controller;

import java.util.List;

import com.demo.tweetsandfeeds.dto.TweetDetail;
import com.demo.tweetsandfeeds.impl.TwitterServiceImpl;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TwitterController {

    private TwitterServiceImpl twitterImpl;

    public TwitterController(TwitterServiceImpl twitterImpl){
        this.twitterImpl = twitterImpl;

    }
    //move to application context
    public final String token = "AAAAAAAAAAAAAAAAAAAAAOc3SQEAAAAAfjH%2FUkbUOoBFE%2F22be3bV5hPGRg%3DQAwkuet2GlZIf9b54zpYafCyHkYFGhJOAjggPMbzBf9qfE5Z78";

    @GetMapping("/seachTweets")
    public List<TweetDetail> getTweetContainText(@RequestParam(value = "text") String text){
        List<TweetDetail> tweetList = twitterImpl.searchContent(text,token);
        tweetList.forEach(d -> System.out.println("Tweets: " +d.getContent()));
        return tweetList;
    }
  
}
