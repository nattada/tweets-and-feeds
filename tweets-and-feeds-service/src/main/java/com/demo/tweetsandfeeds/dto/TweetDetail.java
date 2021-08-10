package com.demo.tweetsandfeeds.dto;


import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TweetDetail {

    private String id;
    private String displayName;
    private String image;
    private String content;
    private String postedOn;
    private Boolean fromVerifiedUser;



    public Boolean getFromVerifiedUser() {
        return fromVerifiedUser;
    }
    public void setFromVerifiedUser(Boolean fromVerifiedUser) {
        this.fromVerifiedUser = fromVerifiedUser;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getPostedOn() {
        return postedOn;
    }
    public void setPostedOn(String postedOn) {
        this.postedOn = postedOn;
    }
    public String getDisplayName() {
        return displayName;
    }
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }    
}
