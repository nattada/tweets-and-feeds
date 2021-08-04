package com.demo.tweetsandfeeds.client;

import com.demo.tweetsandfeeds.model.request.Tweet;

public interface ITwitterApiV2 {


  Tweet searchTweets(String query);

  /**
   * Search tweets from last 7 days calling https://api.twitter.com/2/tweets/search
   *
   */
  Tweet searchTweets(String query, AdditionalParameters additionalParameters);

  
}
