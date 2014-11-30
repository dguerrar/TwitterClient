package com.codepath.apps.simpletwitterclient;

import org.json.JSONObject;

import com.codepath.oauth.OAuthLoginActivity;
import com.loopj.android.http.AsyncHttpResponseHandler;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class ComposeActivity extends OAuthLoginActivity<TwitterClient> {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_compose);
	}

	public void postTweeter(View v) {
		
		EditText TweeterContent = (EditText) findViewById(R.id.twiterContent);
		String text = TweeterContent.getText().toString();
		
		if(text.length() > 0) {
			
		//TwitterClient client = new TwitterClient(getApplicationContext());	
		getClient().postTwitter(text, new AsyncHttpResponseHandler());
		Intent i = new Intent(getApplicationContext(), TweetsActivity.class);
		startActivity(i);
		
		}
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_compose, menu);
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
