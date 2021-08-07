package com.demo.tweetsandfeeds.client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.concurrent.CompletableFuture;

import com.demo.tweetsandfeeds.client.model.response.Response;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@WebMvcTest(TwitterApiClient.class)
public class TwitterApiClientTest {

    @Autowired
    TwitterApiClient twitterApiClient;
    @MockBean
    HttpClient mockClient;
    @Mock
    HttpRequest mockRequest;
    @Mock
    HttpResponse<String> mockResponse;
    @Mock
    CompletableFuture<HttpResponse<String>> asyncMock;


    @Test
    public void returnErrorWhenUnauthorized() throws IOException, InterruptedException{
        when(mockResponse.body()).thenReturn("{\"title\":\"Unauthorized\",\"type\":\"about:blank\",\"status\":401,\"detail\":\"Unauthorized\"}");
        when(mockClient.sendAsync(mockRequest, BodyHandlers.ofString())).thenReturn(asyncMock);
        when(asyncMock.join()).thenReturn(mockResponse);

        Response tweetResponse =  twitterApiClient.searchTweets("test", "bearerToken");
        assertEquals("401", tweetResponse.getStatus());
        
    }

    // @Test
    // public void returnTweetDataWhenRequestIsSuccessfulWithOkStatus() throws IOException, InterruptedException{
    //     when(mockResponse.body()).thenReturn("{\"data\":\"Unauthorized\",\"type\":\"about:blank\",\"status\":401,\"detail\":\"Unauthorized\"}");
    //     when(mockResponse.statusCode()).thenReturn(HttpStatus.OK);
    //     when(mockClient.sendAsync(mockRequest, BodyHandlers.ofString())).thenReturn(asyncMock);
    //     when(asyncMock.join()).thenReturn(mockResponse);

    //     Response tweetResponse = twitterApiClient.searchTweets("test", "bearerToken");
    //     assertEquals(null, tweetResponse.getStatus());
        
    // }
    
}
