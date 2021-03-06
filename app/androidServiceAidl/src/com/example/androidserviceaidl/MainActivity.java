package com.example.androidserviceaidl;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Process;
import android.util.Log;

public class MainActivity extends Activity {

	private Handler mHander = new Handler();

	private IRemoteService mService = null;

	/**
	 * 用于日志记录，记录PID，与RemoteService比较
	 */
	private static final String TAG = "MainActivity, PID=" + Process.myPid();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Log.d(TAG, TAG + " onCreate()");
		/**
		 * 启动Service，让Service在Activity结束后依然存在
		 */
		startService(new Intent("com.example.androidserviceaidl.RemoteService"));
		/**
		 * 绑定service
		 */
		bindService(new Intent("com.example.androidserviceaidl.RemoteService"),
				mConn, Context.BIND_AUTO_CREATE);
	}

	/**
	 * 定义一个ServiceConnection
	 */
	private ServiceConnection mConn = new ServiceConnection() {

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			// TODO Auto-generated method stub
			mService = IRemoteService.Stub.asInterface(service);
			TaskInfo mTaskInfo;
			Log.d(TAG, TAG + " Service Connected.");
			try {
				mService.getCount();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				Log.d(TAG, "MainActivity ---> getPid()");
				mService.getPid(mCounter);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
			try {
				Log.d(TAG, "MainActivity ---> getTaskInfo()");
				mTaskInfo = mService.getTaskInfo();
				Log.d(TAG, "getTaskInfo : " + "TaskInfo.mPss is "
						+ mTaskInfo.mPss + " TaskInfo.mPid is "
						+ mTaskInfo.mPid + " TaskInfo.mPackageName is "
						+ mTaskInfo.mPackageName);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub
			mService = null;
			Log.d(TAG, TAG + " Service Disconnected.");
		}

	};

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		unbindService(mConn);
		Log.d(TAG, TAG + " onDestroy().");
		super.onDestroy();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private ITaskServiceCallBack mCounter = new ITaskServiceCallBack.Stub() {

		@Override
		public void valueCounted(final int value) throws RemoteException {
			// TODO Auto-generated method stub
			mHander.post(new Runnable() {
				public void run() {
					Log.i(TAG, "at MainActivity callback value is--->" + value);
				}
			});
			Log.i(TAG, "at MainActivity callback finish");

		}
	};

}
