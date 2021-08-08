package com.demo.tweetsandfeeds.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import org.springframework.data.annotation.Id;

public class Feed {

    @Id
    public String id;
    public String content;
    public String postedBy;
    // @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy hh:mm:ss")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    public LocalDateTime postedOn;

    
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
    public String getPostedBy() {
        return postedBy;
    }
    public void setPostedBy(String postedBy) {
        this.postedBy = postedBy;
    }
    public LocalDateTime getPostedOn() {
        return postedOn;
    }
    public void setPostedOn(LocalDateTime postedOn) {
        this.postedOn = postedOn;
    }
}
