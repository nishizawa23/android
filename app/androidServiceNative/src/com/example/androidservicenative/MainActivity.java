package com.example.androidservicenative;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

//import android.content.Context;

public class MainActivity extends Activity {
	private static final String TAG = "LocalApp";

	// private Context mContext;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		final Button button_start = (Button) findViewById(R.id.startLocalService);
		final Button button_stop = (Button) findViewById(R.id.stopLocalService);
		// mContext = MainActivity.this;
		// startService(new Intent(this,MainService.class));
		// 匿名类使用
		button_start.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// Intent i = new Intent();
				// i.setClass(MainActivity.this, MainService.class);
				// mContext.startService(i);
				Log.i(TAG, "MainActivity startService");
				startService(new Intent(MainActivity.this, MainService.class));
			}
		});

		button_stop.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// Intent i = new Intent();
				// i.setClass(MainActivity.this, MainService.class);
				// mContext.stopService(i);
				Log.i(TAG, "MainActivity stopService");
				stopService(new Intent(MainActivity.this, MainService.class));
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
