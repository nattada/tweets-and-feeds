package com.demo.tweetsandfeeds.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;

import com.demo.tweetsandfeeds.client.TwitterApiClient;
import com.demo.tweetsandfeeds.client.model.response.Response;
import com.demo.tweetsandfeeds.dto.TweetDetail;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@WebMvcTest(TwitterServiceImpl.class)
public class TwitterServiceImplTest {

    @Autowired
    private TwitterServiceImpl twitterService;
    
    @MockBean
    private TwitterApiClient mockClient;

    private final  String MOCK_QUERY = "search string";
    private final  String TOKEN = "fakeToken";


    @Test
    public void returnEmptyListIfSearchIsNotFound(){
        when(mockClient.searchTweets(MOCK_QUERY, TOKEN)).thenReturn(new Response());
        ResponseEntity<List<TweetDetail>>  mockResponse = twitterService.searchContent(MOCK_QUERY, TOKEN);
        assertEquals(0, mockResponse.getBody().size());
    }

    @Test
    public void returnErrorCodeWhenExceptionOccursFromClient(){
        Response mockResposeError = new Response();
        mockResposeError.setStatus("401");
        when(mockClient.searchTweets(MOCK_QUERY, TOKEN)).thenReturn(mockResposeError);
        ResponseEntity<List<TweetDetail>>  mockResponse = twitterService.searchContent(MOCK_QUERY, TOKEN);
        assertEquals(0, mockResponse.getBody().size());
        assertEquals(mockResponse.getStatusCode(), HttpStatus.UNAUTHORIZED);
    }


    @Test
    public void  returnEmptyListWhenExceptionOccureOnClient(){
        when(mockClient.searchTweets(MOCK_QUERY, TOKEN)).thenThrow(Exception.class);
        ResponseEntity<List<TweetDetail>> mockResponse = twitterService.searchContent(MOCK_QUERY, TOKEN);
        assertEquals(0, mockResponse.getBody().size());
    }
}
