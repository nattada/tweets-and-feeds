package com.demo.tweetsandfeeds.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.demo.tweetsandfeeds.client.TwitterApiClient;
import com.demo.tweetsandfeeds.client.exception.DataProcessingException;
import com.demo.tweetsandfeeds.client.model.response.Includes;
import com.demo.tweetsandfeeds.client.model.response.Response;
import com.demo.tweetsandfeeds.client.model.response.UserData;
import com.demo.tweetsandfeeds.dto.TweetDetail;
import com.demo.tweetsandfeeds.service.ISocialMediaService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TwitterServiceImpl implements ISocialMediaService<List<TweetDetail>> {

    private TwitterApiClient twitterClient;

    public TwitterServiceImpl(TwitterApiClient twitterClient) {
        this.twitterClient = twitterClient;

    }

    @Override
    public ResponseEntity<List<TweetDetail>> searchContent(String text, String token) {
        try {
            String nextToken = null;
            List<TweetDetail> tweetList = new ArrayList<TweetDetail>();

            do {
                Response tweetReponse;
                if (nextToken == null)// first call
                    tweetReponse = twitterClient.searchTweets(text, token);
                else {
                    tweetReponse = twitterClient.searchTweets(text, token, nextToken);

                }
                if (tweetReponse.getStatus() != null) {
                    return new ResponseEntity<List<TweetDetail>>(Collections.emptyList(),
                            HttpStatus.valueOf(Integer.parseInt(tweetReponse.getStatus())));
                }
                nextToken = tweetReponse.getMeta().getNextToken();
                tweetReponse.getData().forEach(tweet -> {
                    TweetDetail detail = new TweetDetail();
                    UserData userData = findUser(tweetReponse.getIncludes(), tweet.getAuthorId());
                    detail.setContent(tweet.getText());
                    detail.setId(tweet.getId());
                    detail.setPostedOn(tweet.getCreatedAt());
                    detail.setImage(getUserImageFromTweetAuthor(userData));
                    detail.setDisplayName(getUserNameFromTweetAuthor(userData));
                    detail.setFromVerifiedUser(isTweetAuthorVerifiedUser(userData));
                    tweetList.add(detail);
                });
            } while (nextToken != null);
            return new ResponseEntity<List<TweetDetail>>(tweetList, HttpStatus.OK);
        } catch (DataProcessingException e) {
            return new ResponseEntity<List<TweetDetail>>(Collections.emptyList(), HttpStatus.valueOf(403));
        }

    }

    private String getUserNameFromTweetAuthor(UserData userData) {
        return userData == null ? null : userData.getName();
    }

    private String getUserImageFromTweetAuthor(UserData userData) {
        return userData == null ? null : userData.getProfileImageUrl();
    }

    private Boolean isTweetAuthorVerifiedUser(UserData userData) {
        return userData == null ? null : userData.isVerified();
    }

    private UserData findUser(Includes userDetail, String authorId) {
        return userDetail.getUsers().stream().filter(user -> user.getId().equals(authorId)).findFirst().orElse(null);

    }
}
