package com.example.androidserviceaidl;  
  
import android.app.Service;  
import android.content.Intent;  
import android.os.IBinder;  
import android.os.Process;  
import android.util.Log;  
  
public class RemoteService extends Service {  
  
    /** 
     * TAG,用于输出Log，在这里，我们把pid，即进程的ID也记录上 
     * 这样就可以比较Service的进程和Client的进程是否在同一进程 
     */  
    private static final String TAG = "RemoteService, PID=" + Process.myPid();  
      
    /** 
     * 定义RemoteService的Binder，在这个Binder里面，我们实现了一个 
     * 函数，getCount()，用于计算getCount()函数被调用的次数。 
     * 这个函数需要到IRemoteService.aidl注册。 
     */  
      
    private final IRemoteService.Stub mBinder = new IRemoteService.Stub() {  
        private int mCount = 0;  
        /** 
         * 返回函数调用次数，每调用一次，加1 
         */  
        public int getCount() {  
            mCount++;  
            Log.d(TAG, TAG + " getCount: " + mCount);  
            return mCount;  
        }  
    };  
      
    @Override  
    public IBinder onBind(Intent intent) {  
        // TODO Auto-generated method stub  
        return mBinder;  
    }  
  
    @Override  
    public void onCreate() {  
        // TODO Auto-generated method stub  
        Log.d(TAG, TAG + " onCreate()");  
        super.onCreate();  
    }  
  
    @SuppressWarnings("deprecation")
	@Override  
    public void onStart(Intent intent, int startId) {  
        // TODO Auto-generated method stub  
        Log.d(TAG, TAG + " onStart()");  
        super.onStart(intent, startId);  
    }  
  
    @Override  
    public void onDestroy() {  
        // TODO Auto-generated method stub  
        Log.d(TAG, TAG + " onDestroy()");  
        super.onDestroy();  
    }  
}  
