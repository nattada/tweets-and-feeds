package com.demo.tweetsandfeeds.client;


import com.demo.tweetsandfeeds.client.exception.DataProcessingException;
import com.demo.tweetsandfeeds.client.model.response.Response;

public interface ITwitterApiV2 {


 /**
  * calling https://api.twitter.com/2/tweets/search
  * @param query
  * @return
  */
  Response searchTweets(String query,String token) throws DataProcessingException;
  Response searchTweets(String query,String token, String paginationToken) throws DataProcessingException;

  
}
