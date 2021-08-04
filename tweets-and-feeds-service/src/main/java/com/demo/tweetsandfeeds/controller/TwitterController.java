package com.demo.tweetsandfeeds.controller;

import java.io.IOException;
import java.net.URISyntaxException;

import com.demo.tweetsandfeeds.client.TwitterApiClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TwitterController {

    @Autowired 
    public TwitterApiClient twitterClient;

    //move to application context
    public final String token = "AAAAAAAAAAAAAAAAAAAAAOc3SQEAAAAAfjH%2FUkbUOoBFE%2F22be3bV5hPGRg%3DQAwkuet2GlZIf9b54zpYafCyHkYFGhJOAjggPMbzBf9qfE5Z78";

    @GetMapping("/searchByText")
    public String getTweetContainText(String text) throws IOException, URISyntaxException{
        return twitterClient.getTweets("1138505981460193280,1261326399320715264",token);
    }
  
    
}
