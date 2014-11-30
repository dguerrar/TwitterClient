package com.codepath.apps.simpletwitterclient;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;

import com.codepath.apps.simpletwitterclient.models.Tweet;
import com.codepath.apps.simpletwitterclient.ComposeActivity;
import com.codepath.oauth.OAuthLoginActivity;
import com.loopj.android.http.JsonHttpResponseHandler;

public class TweetsActivity extends OAuthLoginActivity<TwitterClient> {
	private final int ITEM_NUMBER = 25;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweets);
        
    	SimpleTwitterApp.getRestClient().getHomeTimeline(new JsonHttpResponseHandler() {
    		@Override
    		public void onSuccess(int arg0, JSONArray jsonTweets) {
    			ArrayList<Tweet> tweets = Tweet.fromJson(jsonTweets);
    			List<Tweet> FirstNItems = tweets;
    			if(tweets.size() > 25) {
    			    FirstNItems =  tweets.subList(0, ITEM_NUMBER);
    			}
    			TweetsAdapter adapter = new TweetsAdapter(getBaseContext(), FirstNItems);
    			ListView lv = (ListView)findViewById(R.id.lvTweets);
    			lv.setAdapter(adapter);
    		}
    	});
    }
    
    public void gotoCompose(View v) {
    	Intent i = new Intent(getApplicationContext(),
				ComposeActivity.class);
		startActivity(i);	
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.tweets, menu);
        return true;
    }

	@Override
	public void onLoginFailure(Exception arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onLoginSuccess() {
		// TODO Auto-generated method stub
		
	}
    
}
