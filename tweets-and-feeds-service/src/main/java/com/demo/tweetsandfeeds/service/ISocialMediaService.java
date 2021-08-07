package com.demo.tweetsandfeeds.service;

import org.springframework.http.ResponseEntity;

public interface ISocialMediaService<T> {

    ResponseEntity<T> searchContent(String text, String authToken);
}
