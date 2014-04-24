package com.alpha.component.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class BindServiceTest extends Service {
    public BindServiceTest() {
    }

    final String TAG = "BindServiceTest";
    public SimpleBinder sBinder;

    @Override
    public void onCreate() {
        Log.v(TAG,"on BindServiceTest Create");
        sBinder = new SimpleBinder();
        super.onCreate();
    }

    public class SimpleBinder extends Binder {

        public BindServiceTest getService(){
            return BindServiceTest.this;
        }

        public int add(int a, int b){
            Log.i(TAG,"BindServiceTest add func");
            return a + b;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        Log.v(TAG,"on BindServiceTest onBinder: "+intent.getAction());
        return sBinder;
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        Log.i(TAG,"onRebind");
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.v(TAG,"on BindServiceTest onUnbind: "+ intent.getAction() );
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        Log.v(TAG,"on BindServiceTest onDestroy");
        super.onDestroy();
    }
}
