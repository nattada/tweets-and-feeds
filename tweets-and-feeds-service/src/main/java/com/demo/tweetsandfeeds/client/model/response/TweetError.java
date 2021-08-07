package com.demo.tweetsandfeeds.client.model.response;


public interface TweetError {

    public String getTitle();
    public void setTitle(String title);
    public String getType();
    public void setType(String type);
    public String getStatus();
    public void setStatus(String status);
    public String getDetail();
    public void setDetail(String detail);
}
