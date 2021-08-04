package com.demo.tweetsandfeeds.client;

import com.demo.tweetsandfeeds.client.model.response.Tweet;

public interface ITwitterApiV2 {


 /**
  * calling https://api.twitter.com/2/tweets/search
  * @param query
  * @return
  */
  Tweet searchTweets(String query,String token);
  
}
