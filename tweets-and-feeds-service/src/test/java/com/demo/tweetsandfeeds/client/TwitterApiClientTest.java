package com.demo.tweetsandfeeds.client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandler;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    CompletableFuture<HttpResponse<HttpClient>> asyncMock;



    @Test
    public void returnErrorWhenUnauthorized() throws IOException, InterruptedException{
        when(mockResponse.body()).thenReturn("{\"title\":\"Unauthorized\",\"type\":\"about:blank\",\"status\":401,\"detail\":\"Unauthorized\"}");
        doReturn(asyncMock).when(mockClient).sendAsync(any(HttpRequest.class), any(BodyHandler.class));
        doReturn(mockResponse).when(asyncMock).join();

        Response tweetResponse =  twitterApiClient.searchTweets("test", "bearerToken");
        assertEquals("401", tweetResponse.getStatus());
        
    }

    @Test
    public void returnTweetDataWhenRequestIsSuccessfulWithOkStatus() throws IOException, InterruptedException{
        Path file = Paths.get("src/test/resources/MockResponseData.json");
        String mockData = Files.readString(file);
        when(mockResponse.body()).thenReturn(mockData);
        doReturn(asyncMock).when(mockClient).sendAsync(any(HttpRequest.class), any(BodyHandler.class));
        doReturn(mockResponse).when(asyncMock).join();
        Response tweetResponse =  twitterApiClient.searchTweets("test", "bearerToken");
        assertNull(tweetResponse.getStatus());
        assertNotNull(tweetResponse.getData());
        assertNotNull(tweetResponse.getIncludes());
        assertNotNull(tweetResponse.getMeta());
        
    }
    
}
