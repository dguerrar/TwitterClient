package com.codepath.apps.simpletwitterclient;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.scribe.builder.api.Api;
import org.scribe.builder.api.TwitterApi;

import android.content.Context;

import com.codepath.oauth.OAuthBaseClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/*
 * 
 * This is the object responsible for communicating with a REST API. 
 * Specify the constants below to change the API being communicated with.
 * See a full list of supported API classes: 
 *   https://github.com/fernandezpablo85/scribe-java/tree/master/src/main/java/org/scribe/builder/api
 * Key and Secret are provided by the developer site for the given API i.e dev.twitter.com
 * Add methods for each relevant endpoint in the API.
 * 
 * NOTE: You may want to rename this object based on the service i.e TwitterClient or FlickrClient
 * 
 */
public class TwitterClient extends OAuthBaseClient {
    public static final Class<? extends Api> REST_API_CLASS = TwitterApi.SSL.class; // Change this
    public static final String REST_URL = "https://api.twitter.com/1.1/"; // Change this, base API URL
    public static final String REST_CONSUMER_KEY = "WaqKWURhd5Lp47wvNKQ6KIouG";       // Change this
    public static final String REST_CONSUMER_SECRET = "gMaaVdAQogOhCIbfu9HJk7suRopugeeFIeelGqmaMLLA65QagQ"; // Change this
    public static final String REST_CALLBACK_URL = "oauth://simpletwitter"; // Change this (here and in manifest)
    public static final String TIMELINE_STATUS = "statuses/home_timeline.json";
    public static final String UPDATE_TWEETER = "statuses/update.json";
    
    
    public TwitterClient(Context context) {
        super(context, REST_API_CLASS, REST_URL, REST_CONSUMER_KEY, REST_CONSUMER_SECRET, REST_CALLBACK_URL);
    }
    
    public void getHomeTimeline(AsyncHttpResponseHandler handler) {
    	String url = getApiUrl(TIMELINE_STATUS);
    	RequestParams params = new RequestParams();
		params.put("format", "json");
    	client.get(url, params, handler);
    }
    
    public void postTwitter(String message, AsyncHttpResponseHandler handler) {
    	RequestParams params = new RequestParams();
    	try {
		   params.put("status", URLEncoder.encode(message,"UTF-8"));
    	} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		post(UPDATE_TWEETER, handler, params);
    }
    
    public void post(String apiUrl, AsyncHttpResponseHandler handler,
			RequestParams params) {
		String fullApiUrl = getApiUrl(apiUrl);
		params.put("format", "json");
		client.post(fullApiUrl, params, handler);
	}
    
    /* 1. Define the endpoint URL with getApiUrl and pass a relative path to the endpoint
     * 	  i.e getApiUrl("statuses/home_timeline.json");
     * 2. Define the parameters to pass to the request (query or body)
     *    i.e RequestParams params = new RequestParams("foo", "bar");
     * 3. Define the request method and make a call to the client
     *    i.e client.get(apiUrl, params, handler);
     *    i.e client.post(apiUrl, params, handler);
     */
}