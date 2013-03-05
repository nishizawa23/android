package com.example.androidnotificationtest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class notificationActivity extends Activity {
	Intent intent;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.notification);
		 intent = notificationActivity.this.getIntent();
	}
}
