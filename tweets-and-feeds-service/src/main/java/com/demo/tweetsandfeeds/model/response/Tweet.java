package com.demo.tweetsandfeeds.model.response;

import java.util.List;

public class Tweet {

    private List<TweetData> data;
    private TweetMeta meta;
    private Includes includes;

    public List<TweetData> getData() {
        return data;
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
}
