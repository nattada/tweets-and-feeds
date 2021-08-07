package com.demo.tweetsandfeeds.client.model.response;

import java.util.List;

public interface Tweet {

    public List<TweetData> getData() ;
    public void setData(List<TweetData> data);
    public TweetMeta getMeta();
    public void setMeta(TweetMeta meta);
    public Includes getIncludes() ;
    public void setIncludes(Includes includes);
}
