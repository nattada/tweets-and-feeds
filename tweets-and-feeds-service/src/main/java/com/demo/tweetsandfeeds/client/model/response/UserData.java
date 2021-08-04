package com.demo.tweetsandfeeds.client.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserData {
    private String id;
    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("username")
    private String name;

    @JsonProperty("name")
    private String accountName;

    private String location;

    private String url;

    private boolean verified;
    
    @JsonProperty("profile_image_url")
    private String profileImageUrl;

    @JsonProperty("public_metrics")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private UserPublicMetrics publicMetrics;

    @JsonProperty("pinned_tweet_id")
    private String pinnedTweetId;
    private String description;

    @JsonProperty("lang")
    private String language;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public UserPublicMetrics getPublicMetrics() {
        return publicMetrics;
    }

    public void setPublicMetrics(UserPublicMetrics publicMetrics) {
        this.publicMetrics = publicMetrics;
    }

    public String getPinnedTweetId() {
        return pinnedTweetId;
    }

    public void setPinnedTweetId(String pinnedTweetId) {
        this.pinnedTweetId = pinnedTweetId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    
}
