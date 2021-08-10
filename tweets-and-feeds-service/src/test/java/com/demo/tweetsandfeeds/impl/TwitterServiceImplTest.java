package com.demo.tweetsandfeeds.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.demo.tweetsandfeeds.client.TwitterApiClient;
import com.demo.tweetsandfeeds.client.exception.DataProcessingException;
import com.demo.tweetsandfeeds.client.model.response.Includes;
import com.demo.tweetsandfeeds.client.model.response.Response;
import com.demo.tweetsandfeeds.client.model.response.TweetData;
import com.demo.tweetsandfeeds.client.model.response.TweetMeta;
import com.demo.tweetsandfeeds.client.model.response.UserData;
import com.demo.tweetsandfeeds.dto.TweetDetail;
import com.fasterxml.jackson.databind.ObjectMapper;

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
    public void returnEmptyListIfSearchIsNotFound() throws DataProcessingException{
        Response mockRepsonse = new Response();
        TweetMeta meta = new TweetMeta();
        meta.setResultCount(0);
        mockRepsonse.setMeta(meta);
        when(mockClient.searchTweets(MOCK_QUERY, TOKEN)).thenReturn(mockRepsonse);
        ResponseEntity<List<TweetDetail>>  mockResponse = twitterService.searchContent(MOCK_QUERY, TOKEN);
        assertEquals(0, mockResponse.getBody().size());
    }

    @Test
    public void returnErrorCodeWhenExceptionOccursFromClient() throws DataProcessingException{
        Response mockResposeError = new Response();
        mockResposeError.setStatus("401");
        when(mockClient.searchTweets(MOCK_QUERY, TOKEN)).thenReturn(mockResposeError);
        ResponseEntity<List<TweetDetail>>  mockResponse = twitterService.searchContent(MOCK_QUERY, TOKEN);
        assertEquals(0, mockResponse.getBody().size());
        assertEquals(mockResponse.getStatusCode(), HttpStatus.UNAUTHORIZED);
    }

    @Test
    public void  returnDisplayNameIfAuthorIdIsFound() throws DataProcessingException{
        Response mockResponse = new Response();
        TweetData data = getTweetData("123");
        TweetMeta meta = new TweetMeta();
        meta.setResultCount(1);
        mockResponse.setMeta(meta);
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
    public void  returnProfileImagefAuthorIdIsFoundAndHasImage() throws DataProcessingException{
        Response mockResponse = new Response();
        TweetData data = getTweetData("123");
        TweetMeta meta = new TweetMeta();
        meta.setResultCount(1);
        mockResponse.setMeta(meta);
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
    public void  returnNoProfileImageIfAuthorIdIsNotFound() throws DataProcessingException{
        Response mockResponse = new Response();
        TweetData data = getTweetData("123");
        TweetMeta meta = new TweetMeta();
        meta.setResultCount(1);
        mockResponse.setMeta(meta);
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

    @Test
    public void returnOnlyTweetFromVerifiedUser() throws IOException  {
        Path file = Paths.get("src/test/resources/MockResponseData.json");
        String mockData = Files.readString(file);
        Response mockResponse =new ObjectMapper().readValue(mockData, Response.class);
        mockResponse.getMeta().setNextToken(null); 
        when(mockClient.searchTweets(MOCK_QUERY, TOKEN)).thenReturn(mockResponse);    
        ResponseEntity<List<TweetDetail>> tweetDetails = twitterService.searchContent(MOCK_QUERY, TOKEN);
        List<TweetDetail> filterTweetDetails = tweetDetails.getBody().stream().filter(tweets -> tweets.getFromVerifiedUser()).collect(Collectors.toList());
        assertEquals(2,filterTweetDetails.size());
    }

    @Test
    public void returnTheNextSetWhenCallWithNextToken() throws IOException  {
        Path file = Paths.get("src/test/resources/MockResponseData.json");
        String mockData = Files.readString(file);
        Response mockResponse =new ObjectMapper().readValue(mockData, Response.class);

        Path nextTokenFileData = Paths.get("src/test/resources/MockResponseDataFromNextToken.json");
        String mockDataForNextToken = Files.readString(nextTokenFileData);
        Response mockResponseFromNextTokenCall =new ObjectMapper().readValue(mockDataForNextToken, Response.class);


        when(mockClient.searchTweets(MOCK_QUERY, TOKEN)).thenReturn(mockResponse);    
        when(mockClient.searchTweets(MOCK_QUERY, TOKEN,"b26v89c19zqg8o3fpdm7bg8zcarn20qfhg3n7omm4sfi5")).thenReturn(mockResponseFromNextTokenCall);   
        ResponseEntity<List<TweetDetail>> tweetDetails = twitterService.searchContent(MOCK_QUERY, TOKEN);
        assertEquals(4,tweetDetails.getBody().size());
    }

    private TweetData getTweetData(String authorId){
        TweetData data = new TweetData();
        data.setAuthorId(authorId);
        return data;
    }


}
