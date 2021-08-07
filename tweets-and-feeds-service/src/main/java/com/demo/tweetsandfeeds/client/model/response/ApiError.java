package com.demo.tweetsandfeeds.client.model.response;

public interface ApiError  {

    public String getErrorMessage();
    public void setErrorMessage(String errorMessage);
    public String getException();
    public void setException(String exception);

}
