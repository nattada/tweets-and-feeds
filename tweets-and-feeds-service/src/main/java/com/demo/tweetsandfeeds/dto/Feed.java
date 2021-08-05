package com.demo.tweetsandfeeds.dto;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;

public class Feed {

    @Id
    public String id;
    public String content;
    public String posterName;
    public LocalDateTime postedTime;

    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getPosterName() {
        return posterName;
    }
    public void setPosterName(String posterName) {
        this.posterName = posterName;
    }
    public LocalDateTime getPostedTime() {
        return postedTime;
    }
    public void setPostedTime(LocalDateTime postedTime) {
        this.postedTime = postedTime;
    }
}