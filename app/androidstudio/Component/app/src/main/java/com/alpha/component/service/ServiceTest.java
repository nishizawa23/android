package com.alpha.component.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class ServiceTest extends Service {
    public ServiceTest() {
    }

    final String TAG = "ServiceTest";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.v(TAG,"on ServiceTest onCreate");
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        final String mAction = intent.getAction();
        if(mAction == "com.alpha.component.service.action.FOO")
        Log.i(TAG, "on ServiceTest onStartCommand get action FOO"
                + "EXTRA_PARAM1 :"+intent.getBooleanExtra("com.alpha.component.service.extra.PARAM1",true) +"\n"
                + "EXTRA_PARAM2 :"+intent.getStringExtra("com.alpha.component.service.extra.PARAM2"));
        //return Service.START_STICKY;
        //return Service.START_NOT_STICKY;
        //return Service.START_REDELIVER_INTENT;
        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.v(TAG,"on ServiceTest onDestroy");
    }
}
