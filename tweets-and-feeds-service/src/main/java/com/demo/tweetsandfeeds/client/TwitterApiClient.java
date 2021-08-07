package com.demo.tweetsandfeeds.client;


import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.List;

import com.demo.tweetsandfeeds.client.constant.RequestConstants;
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
    public Response searchTweets(String query, String bearerToken) {
        Response response = sendAsyncHttpRequest(query,bearerToken);
        return response;
    }

    private Response sendAsyncHttpRequest(String query, String bearerToken) {
        try {
            HttpRequest request  = buildRequest("/search/recent?",bearerToken);
            HttpResponse<String> response = httpClient.sendAsync(request, BodyHandlers.ofString()).join();        
            return processedResponse(response);

        } catch (Exception e) {
            return errorResponse("An exception has occured during the http request", e.getMessage());
        }
    }

    private Response errorResponse(String errorMessage, String exceptionMessage) {
        Response exceptionResponse = new Response();
        exceptionResponse.setErrorMessage(errorMessage);
        exceptionResponse.setException(exceptionMessage);
        return exceptionResponse;
    }

    private Response processedResponse(HttpResponse<String> response) {
        try {
            return new ObjectMapper().readValue(response.body(), Response.class);
        } catch (JsonMappingException e) {
            return errorResponse("Error during deserialize JSON from the response", e.getMessage());
        } catch (JsonProcessingException e) {
            return errorResponse("Error during processing JSON content", e.getMessage());
        }
    }

    private HttpRequest buildRequest(String path, String bearerToken) throws Exception {
        try {
            URIBuilder uriBuilder = new URIBuilder(RequestConstants.BASE_URL + path);
            uriBuilder.addParameters(getQueryParameters());
            return HttpRequest.newBuilder()
                .uri(uriBuilder.build())
                .header("Content-Type", "application/json")
                .header("Authorization", String.format("Bearer %s", bearerToken))
                .build();
        } catch (URISyntaxException e) {
            throw new Exception(e.getMessage());
        }

    }

    private List<NameValuePair> getQueryParameters() {
        ArrayList<NameValuePair> queryParameters;
        queryParameters = new ArrayList<>();
        queryParameters.add(new BasicNameValuePair(RequestConstants.QUERY_KEY, "@Fanatics is:verified"));
        queryParameters.add(new BasicNameValuePair(RequestConstants.TWEET_FIELD_KEY, "created_at"));
        queryParameters.add(new BasicNameValuePair(RequestConstants.EXPANSION_KEY, "author_id"));
        queryParameters.add(new BasicNameValuePair(RequestConstants.USER_FIELD_KEY, "description,profile_image_url,public_metrics,verified"));
        return queryParameters;
    }
}
        
    
    
    

