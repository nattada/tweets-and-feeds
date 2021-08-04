package com.demo.tweetsandfeeds.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TweetData {
    
    private String id;

    @JsonProperty("created_at")
    private String createdAt;

    private String text;

    @JsonProperty("author_id")
    private String authorId;

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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
    
}
