package com.demo.tweetsandfeeds.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.demo.tweetsandfeeds.client.TwitterApiClient;
import com.demo.tweetsandfeeds.client.model.response.Includes;
import com.demo.tweetsandfeeds.client.model.response.Response;
import com.demo.tweetsandfeeds.client.model.response.TweetData;
import com.demo.tweetsandfeeds.client.model.response.UserData;
import com.demo.tweetsandfeeds.dto.TweetDetail;

import org.junit.jupiter.api.BeforeAll;
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
    public void  returnEmptyDisplayNameIfAuthorIdIsNotFound(){
        Response mockResponse = new Response();
        TweetData data = new TweetData();
        data.setAuthorId("123");
        Includes includes = new Includes();
        UserData users = new UserData();
        users.setId("456");
        users.setName("Test Name");
        includes.setUsers(Arrays.asList(users));
        mockResponse.setData(Arrays.asList(data));
        mockResponse.setIncludes(includes);

        when(mockClient.searchTweets(MOCK_QUERY, TOKEN)).thenReturn(mockResponse);
        ResponseEntity<List<TweetDetail>> tweetDetails = twitterService.searchContent(MOCK_QUERY, TOKEN);
        assertEquals(1, tweetDetails.getBody().size());
        assertNull(tweetDetails.getBody().get(0).getDisplayName());
    }

    @Test
    public void  returnDisplayNameIfAuthorIdIsound(){
        Response mockResponse = new Response();
        TweetData data = getTweetData("123");

        Includes includes = new Includes();
        UserData users = new UserData();
        users.setId("123");
        users.setName("Test Name");
        includes.setUsers(Arrays.asList(users));
        mockResponse.setData(Arrays.asList(data));
        mockResponse.setIncludes(includes);

        when(mockClient.searchTweets(MOCK_QUERY, TOKEN)).thenReturn(mockResponse);
        ResponseEntity<List<TweetDetail>> tweetDetails = twitterService.searchContent(MOCK_QUERY, TOKEN);
        assertEquals(1, tweetDetails.getBody().size());
        assertEquals("Test Name", tweetDetails.getBody().get(0).getDisplayName());
    }

    @Test
    public void  returnProfileImagefAuthorIdIsFoundAndHasImage(){
        Response mockResponse = new Response();
        TweetData data = getTweetData("123");
        Includes includes = new Includes();
        UserData users = new UserData();
        users.setId("123");
        users.setProfileImageUrl("http://imageurl");
        includes.setUsers(Arrays.asList(users));
        mockResponse.setData(Arrays.asList(data));
        mockResponse.setIncludes(includes);

        when(mockClient.searchTweets(MOCK_QUERY, TOKEN)).thenReturn(mockResponse);
        ResponseEntity<List<TweetDetail>> tweetDetails = twitterService.searchContent(MOCK_QUERY, TOKEN);
        assertEquals(1, tweetDetails.getBody().size());
        assertEquals("http://imageurl", tweetDetails.getBody().get(0).getImage());
    }

    @Test
    public void  returnNpProfileImagefAuthorIdIsNotFound(){
        Response mockResponse = new Response();
        TweetData data = getTweetData("123");
        Includes includes = new Includes();
        UserData users = new UserData();
        users.setId("456");
        users.setProfileImageUrl("http://imageurl");
        includes.setUsers(Arrays.asList(users));
        mockResponse.setData(Arrays.asList(data));
        mockResponse.setIncludes(includes);

        when(mockClient.searchTweets(MOCK_QUERY, TOKEN)).thenReturn(mockResponse);
        ResponseEntity<List<TweetDetail>> tweetDetails = twitterService.searchContent(MOCK_QUERY, TOKEN);
        assertEquals(1, tweetDetails.getBody().size());
        assertNull(tweetDetails.getBody().get(0).getImage());
    }

    private TweetData getTweetData(String authorId){
        TweetData data = new TweetData();
        data.setAuthorId(authorId);
        return data;
    }
}
