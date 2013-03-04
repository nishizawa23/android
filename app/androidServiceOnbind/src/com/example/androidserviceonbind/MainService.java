package com.example.androidserviceonbind;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;


public class MainService  extends Service {
	private static final String TAG = "ServiceApp";
    /**
     * 在 Local Service 中我们直接继承 Binder 而不是 IBinder,因为 Binder 实现了 IBinder 接口，这样我们可以少做很多工作。
     * @author newcj
     */
    public class SimpleBinder extends Binder{
        /**
         * 获取 Service 实例
         * @return
         */
        public MainService getService(){
            return MainService.this;
        }
         
        public int add(int a, int b){
        	Log.i(TAG,"simpleBinder add func");
            return a + b;
        }
    }
     
    public SimpleBinder sBinder;
     
    @Override
    public void onCreate() {
        super.onCreate();
        // 创建 SimpleBinder
        Log.i(TAG,"onCreate");
        sBinder = new SimpleBinder();
    }
     
    @Override
    public IBinder onBind(Intent intent) {
    	Log.i(TAG,"onBind");
        // 返回 SimpleBinder 对象
        return sBinder;
    }
    
    public void onDestroy() {  
        Log.i(TAG, "Service onDestroy--->");  
    }  
    // 当解除绑定时调用  
    public boolean onUnbind(Intent intent) {    
        Log.i(TAG, "Service onUnbind--->");    
        return super.onUnbind(intent);    
    }    
 
}
