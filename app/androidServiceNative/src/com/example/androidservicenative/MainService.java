package com.example.androidservicenative;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MainService  extends Service { 

    private static final String TAG = "LocalService"; 

    @Override 
    public IBinder onBind(Intent intent) { 
            Log.i(TAG, "onBind"); 
            return null; 
    } 

    @Override 
    public void onCreate() { 
            Log.i(TAG, "onCreate"); 
            super.onCreate(); 
    } 
    
    @Override
	public int onStartCommand(Intent intent,int flags,int startId){
		super.onStartCommand(intent, flags, startId);
		Log.i(TAG,"st onStartCommand");
		return START_STICKY;
    	
    }
    /*
	@SuppressWarnings("deprecation")
	@Override 
    public void onStart(Intent intent, int startId) { 
            Log.i(TAG, "onStart"); 
            super.onStart(intent, startId); 
    }
	*/
    @Override 
    public void onDestroy() { 
            Log.i(TAG, "onDestroy"); 
            super.onDestroy(); 
    } 
}
