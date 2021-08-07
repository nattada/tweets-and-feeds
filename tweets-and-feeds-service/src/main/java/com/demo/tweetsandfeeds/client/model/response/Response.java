package com.demo.tweetsandfeeds.client.model.response;

import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response  implements Tweet, TweetError, ApiError{
    
    private List<TweetData> data;
    private TweetMeta meta;
    private Includes includes;
    private String errorMessage;
    private String exception;
    private String title;
    private String type;
    private String status;
    private String detail;


    public List<TweetData> getData() {

        return data == null ? Collections.emptyList() : data;
    }

    public void setData(List<TweetData> data) {
        this.data = data;
    }

    public TweetMeta getMeta() {
        return meta;
    }

    public void setMeta(TweetMeta meta) {
        this.meta = meta;
    }

    public Includes getIncludes() {
        return includes;
    }

    public void setIncludes(Includes includes) {
        this.includes = includes;
    }

    public String getErrorMessage() {
        return errorMessage;
    }


    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }
    
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getDetail() {
        return detail;
    }
    public void setDetail(String detail) {
        this.detail = detail;
    }

}
