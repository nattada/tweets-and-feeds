package com.demo.tweetsandfeeds.client;


import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;

import com.demo.tweetsandfeeds.client.constant.RequestConstants;
import com.demo.tweetsandfeeds.client.model.response.Tweet;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.stereotype.Service;

/*
Client that will interact with Twitter Apis
*/

@Service
public class TwitterApiClient  implements ITwitterApiV2{

    private HttpClient client;
    

    public TwitterApiClient(){
        client = HttpClient.newBuilder()
                .build();

    }

    @Override
    public Tweet searchTweets(String query, String bearerToken) {
        try {

        URIBuilder uriBuilder = new URIBuilder(RequestConstants.BASE_URL + "/search/recent?");
        
        ArrayList<NameValuePair> queryParameters;
        queryParameters = new ArrayList<>();
        queryParameters.add(new BasicNameValuePair(RequestConstants.QUERY_KEY, "@Fanatics is:verified"));
        queryParameters.add(new BasicNameValuePair(RequestConstants.TWEET_FIELD_KEY, "created_at"));
        queryParameters.add(new BasicNameValuePair(RequestConstants.EXPANSION_KEY, "author_id"));
        queryParameters.add(new BasicNameValuePair(RequestConstants.USER_FIELD_KEY, "description,profile_image_url,public_metrics,verified"));
        uriBuilder.addParameters(queryParameters);

        HttpRequest request;
            request = HttpRequest.newBuilder()
            .uri(uriBuilder.build())
            .header("Content-Type", "application/json")
            .header("Authorization", String.format("Bearer %s", bearerToken))
            .build();
        

        HttpResponse<String> response = client.sendAsync(request, BodyHandlers.ofString()).join();
        return new ObjectMapper().readValue(response.body(), Tweet.class);

        } catch (JsonMappingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;

        } catch (URISyntaxException e) {
        // TODO Auto-generated catch block
            e.printStackTrace();
            return null;

        }
    }
}
        
    
    
    

