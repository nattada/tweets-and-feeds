package com.demo.tweetsandfeeds.impl;

import java.util.ArrayList;
import java.util.List;

import com.demo.tweetsandfeeds.client.TwitterApiClient;
import com.demo.tweetsandfeeds.client.model.response.Includes;
import com.demo.tweetsandfeeds.client.model.response.Tweet;
import com.demo.tweetsandfeeds.client.model.response.UserData;
import com.demo.tweetsandfeeds.dto.TweetDetail;
import com.demo.tweetsandfeeds.service.ISocialMediaService;

import org.springframework.stereotype.Service;

@Service
public class TwitterServiceImpl implements ISocialMediaService<TweetDetail> {

    private TwitterApiClient twitterClient;

    public TwitterServiceImpl(TwitterApiClient twitterClient) {
        this.twitterClient = twitterClient;

    }

    @Override
    public List<TweetDetail> searchContent(String text, String token) {
        Tweet tweetReponse = twitterClient.searchTweets(text, token);
        List<TweetDetail> tweetList = new ArrayList<TweetDetail>();

        if (tweetReponse == null || tweetReponse.getData() == null)
            return tweetList;

        tweetReponse.getData().forEach(tweet -> {
            TweetDetail detail = new TweetDetail();
            detail.setContent(tweet.getText());
            detail.setImage(getUserImageFromTweetAuthor(tweetReponse.getIncludes(), tweet.getAuthorId()));
            detail.setDisplayName(getUserNameFromTweetAuthor(tweetReponse.getIncludes(), tweet.getAuthorId()));
            tweetList.add(detail);
        });
        return tweetList;
    }

    private String getUserNameFromTweetAuthor(Includes userDetail, String authorId) {
        UserData userData = findUser(userDetail,authorId);
        return  userData == null ? null: userData.getName();
    }

    private String getUserImageFromTweetAuthor(Includes userDetail, String authorId) {
        UserData userData = findUser(userDetail,authorId);
        return  userData == null ? null: userData.getProfileImageUrl();
    }
    private UserData findUser(Includes userDetail, String authorId){
        if (userDetail == null)
         return null;
        else
         return userDetail.getUsers().stream().filter(user -> user.getId().equals(authorId)).findFirst().orElse(null);

    }
}
