package com.demo.tweetsandfeeds.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserPublicMetrics {
    
    @JsonProperty("followers_count")
    private int followersCount;

    @JsonProperty("following_count")
    private int followingCount;

    @JsonProperty("tweet_count")
    private int tweetCount;

    @JsonProperty("listed_count")
    private int listedCount;

    public int getFollowersCount() {
        return followersCount;
    }

    public void setFollowersCount(int followersCount) {
        this.followersCount = followersCount;
    }

    public int getFollowingCount() {
        return followingCount;
    }

    public void setFollowingCount(int followingCount) {
        this.followingCount = followingCount;
    }

    public int getTweetCount() {
        return tweetCount;
    }

    public void setTweetCount(int tweetCount) {
        this.tweetCount = tweetCount;
    }

    public int getListedCount() {
        return listedCount;
    }

    public void setListedCount(int listedCount) {
        this.listedCount = listedCount;
    }


}
