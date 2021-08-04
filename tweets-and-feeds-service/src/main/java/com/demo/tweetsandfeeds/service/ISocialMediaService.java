package com.demo.tweetsandfeeds.service;

import java.util.List;

public interface ISocialMediaService<T> {

    List<T> searchContent(String text, String authToken);
}
