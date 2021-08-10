package com.demo.tweetsandfeeds.client;


import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.List;

import com.demo.tweetsandfeeds.client.constant.RequestConstants;
import com.demo.tweetsandfeeds.client.exception.DataProcessingException;
import com.demo.tweetsandfeeds.client.model.response.Response;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
Client that will interact with Twitter Apis
*/

@Service
public class TwitterApiClient  implements ITwitterApiV2{

    
    private HttpClient httpClient;
    
    @Autowired
    public TwitterApiClient(HttpClient httpClient){
        this.httpClient = httpClient;
    }

    @Override
    public Response searchTweets(String query, String bearerToken) throws DataProcessingException {
        List<NameValuePair> additionalParameters = getQueryParameters(query);
        HttpRequest request  = buildRequest("/search/recent?",additionalParameters,bearerToken);
        HttpResponse<String> response = httpClient.sendAsync(request, BodyHandlers.ofString()).join();        
        return processedResponse(response);
    }

    @Override
    public Response searchTweets(String query, String bearerToken, String paginationToken) throws DataProcessingException {
        List<NameValuePair> additionalParameters = getQueryParameters(query);
        additionalParameters.add(new BasicNameValuePair(RequestConstants.NEXT_TOKEN, paginationToken));
        HttpRequest request  = buildRequest("/search/recent?",additionalParameters,bearerToken);
        HttpResponse<String> response = httpClient.sendAsync(request, BodyHandlers.ofString()).join();        
        return processedResponse(response);
    }


    private Response processedResponse(HttpResponse<String> response) throws DataProcessingException {
        try {
            return new ObjectMapper().readValue(response.body(), Response.class);
        } catch (JsonMappingException e) {
            throw new DataProcessingException("Error during deserialize JSON from the response", e);
        } catch (JsonProcessingException e) {
            throw new DataProcessingException("Error during processing JSON content" ,e);
        }
    }

    private HttpRequest buildRequest(String path, List<NameValuePair> additionalParameters ,String bearerToken) throws DataProcessingException {
        try {
            URIBuilder uriBuilder = new URIBuilder(RequestConstants.BASE_URL + path);
            uriBuilder.addParameters(additionalParameters);
            return HttpRequest.newBuilder()
                .uri(uriBuilder.build())
                .header("Content-Type", "application/json")
                .header("Authorization", String.format("Bearer %s", bearerToken))
                .build();
        } catch (URISyntaxException e) {
            throw new DataProcessingException("A string could not be parsed as a URI" ,e);
        }

    }

    private List<NameValuePair> getQueryParameters(String query) {
        ArrayList<NameValuePair> queryParameters;
        queryParameters = new ArrayList<>();
        queryParameters.add(new BasicNameValuePair(RequestConstants.QUERY_KEY, query));
        queryParameters.add(new BasicNameValuePair(RequestConstants.TWEET_FIELD_KEY, "created_at"));
        queryParameters.add(new BasicNameValuePair(RequestConstants.EXPANSION_KEY, "author_id"));
        queryParameters.add(new BasicNameValuePair(RequestConstants.USER_FIELD_KEY, "description,profile_image_url,public_metrics,verified"));
        queryParameters.add(new BasicNameValuePair(RequestConstants.MAX_RESULT, "50"));
        return queryParameters;
    }
}
        