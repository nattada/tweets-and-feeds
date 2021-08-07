package com.demo.tweetsandfeeds.client.model.response;

import java.util.Collections;
import java.util.List;

public class Includes {
    
    private List<UserData>   users;
    private List<TweetData> tweets;

    public List<UserData> getUsers() {
        return users == null ? Collections.emptyList() : users;
    }
    public void setUsers(List<UserData> users) {
        this.users = users;
    }
    public List<TweetData> getTweets() {
        return tweets;
    }
    public void setTweets(List<TweetData> tweets) {
        this.tweets = tweets;
    }
}
