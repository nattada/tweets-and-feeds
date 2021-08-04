package com.demo.tweetsandfeeds.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;

import com.demo.tweetsandfeeds.client.TwitterApiClient;
import com.demo.tweetsandfeeds.client.model.response.Tweet;
import com.demo.tweetsandfeeds.dto.TweetDetail;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

// @AutoConfigureMockMvc
@WebMvcTest(TwitterServiceImpl.class)
// @SpringBootTest
public class TwitterServiceImplTest {

    @Autowired
    private TwitterServiceImpl twitterService;
    
    @MockBean
    private TwitterApiClient mockClient;



    @Test
    public void shouldReturnEmptyListIfSearchIsNotFound(){
        String text = "any";
        String token = "mockToken";
        when(mockClient.searchTweets(text, token)).thenReturn(new Tweet());
        List <TweetDetail>  mockResponse = twitterService.searchContent(text, token);
        assertEquals(0, mockResponse.size());
    }
}
