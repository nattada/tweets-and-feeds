package com.demo.tweetsandfeeds.client.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TweetMeta {
    
    @JsonProperty("newest_id")
    private String newestId;

    @JsonProperty("oldest_id")
    private String oldestId;

    @JsonProperty("next_token")
    private String nextToken;
    
    @JsonProperty("result_count")
    private int    resultCount;

    public String getNewestId() {
        return newestId;
    }
    public void setNewestId(String newestId) {
        this.newestId = newestId;
    }
    public String getOldestId() {
        return oldestId;
    }
    public void setOldestId(String oldestId) {
        this.oldestId = oldestId;
    }
    public String getNextToken() {
        return nextToken;
    }
    public void setNextToken(String nextToken) {
        this.nextToken = nextToken;
    }
    public int getResultCount() {
        return resultCount;
    }
    public void setResultCount(int resultCount) {
        this.resultCount = resultCount;
    }
}
