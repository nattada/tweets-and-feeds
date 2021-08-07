package com.demo.tweetsandfeeds.client.exception;

import java.io.IOException;

public class DataProcessingException  extends IOException {
    public DataProcessingException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}