package com.demo.tweetsandfeeds.client;

import com.demo.tweetsandfeeds.model.request.AdditionalParameters;
import com.demo.tweetsandfeeds.model.response.Tweet;

public interface ITwitterApiV2 {


 /**
  * calling https://api.twitter.com/2/tweets/search
  * @param query
  * @param additionalParameters
  * @return
  */
  Tweet searchTweets(String query,AdditionalParameters additionalParameters);
  
}
