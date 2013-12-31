package com.example.androidserviceonbind;

import com.example.androidserviceonbind.MainService.SimpleBinder;

import android.os.Bundle;
import android.os.IBinder;
import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	private Button bindBtn, unbindBtn;
	private SimpleBinder mSimpleBinder;
	private int addReturn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Log.i("APP", "onCreate");

		bindBtn = (Button) findViewById(R.id.button1);
		unbindBtn = (Button) findViewById(R.id.button2);

		// ��Ӽ�����
		bindBtn.setOnClickListener(bindListener);
		// bindBtn.setOnClickListener((android.view.View.OnClickListener) this);
		unbindBtn.setOnClickListener(unBindListener);
	}

	// ���Ӷ���
	private ServiceConnection conn = new ServiceConnection() {
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			Log.i("Service", "���ӳɹ���");

			mSimpleBinder = (SimpleBinder) service;
			addReturn = mSimpleBinder.add(1, 3);
			Log.i("Service", "ComponentName is " + name.toString()
					+ " addReturn is " + addReturn);
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			Log.i("Service", "�Ͽ����ӣ�");
		}
	};

	// ����Service������
	private OnClickListener bindListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			// ����Intent
			Intent intent = new Intent();
			// ����Class����
			intent.setClass(MainActivity.this, MainService.class);

			// ��Service
			Log.i("APP", "Onclick");
			bindService(intent, conn, Service.BIND_AUTO_CREATE);
		}
	};

	// ����Service������
	private OnClickListener unBindListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			// ����Intent
			Intent intent = new Intent();
			// ����Class����
			intent.setClass(MainActivity.this, MainService.class);
			// ����Service
			unbindService(conn);
		}
	};
}
