package com.demo.tweetsandfeeds.client;

import java.net.URISyntaxException;

import com.demo.tweetsandfeeds.client.exception.DataProcessingException;
import com.demo.tweetsandfeeds.client.model.response.Response;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

public interface ITwitterApiV2 {


 /**
  * calling https://api.twitter.com/2/tweets/search
  * @param query
  * @return
 * @throws DataProcessingException
 * @throws URISyntaxException
 * @throws JsonProcessingException
 * @throws JsonMappingException
  */
  Response searchTweets(String query,String token) throws DataProcessingException;
  
}
