package com.demo.tweetsandfeeds.client;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import com.demo.tweetsandfeeds.model.request.AdditionalParameters;
import com.demo.tweetsandfeeds.model.response.Tweet;

/*
Client that will interact with Twitter Apis
*/

@Component
public class TwitterApiClient  implements ITwitterApiV2{

    //testing API
    public String getTweets(String ids,String bearerToken) throws IOException, URISyntaxException {
        String tweetResponse = null;
    
        HttpClient httpClient = HttpClients.custom()
            .setDefaultRequestConfig(RequestConfig.custom()
                .setCookieSpec(CookieSpecs.STANDARD).build())
            .build();
    
        URIBuilder uriBuilder = new URIBuilder("https://api.twitter.com/2/tweets");
        ArrayList<NameValuePair> queryParameters;
        queryParameters = new ArrayList<>();
        queryParameters.add(new BasicNameValuePair("ids", ids));
        queryParameters.add(new BasicNameValuePair("tweet.fields", "created_at"));
        uriBuilder.addParameters(queryParameters);
    
        HttpGet httpGet = new HttpGet(uriBuilder.build());
        httpGet.setHeader("Authorization", String.format("Bearer %s", bearerToken));
        httpGet.setHeader("Content-Type", "application/json");
    
        HttpResponse response = httpClient.execute(httpGet);
        HttpEntity entity = response.getEntity();
        if (null != entity) {
          tweetResponse = EntityUtils.toString(entity, "UTF-8");
        }
        return tweetResponse;
      }

    @Override
    public Tweet searchTweets(String query, AdditionalParameters additionalParameters) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
