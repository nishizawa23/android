package com.example.androidserviceonbind;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;


public class MainService  extends Service {
	private static final String TAG = "ServiceApp";
    /**
     * �� Local Service ������ֱ�Ӽ̳� Binder ���� IBinder,��Ϊ Binder ʵ���� IBinder �ӿڣ��������ǿ��������ܶ๤����
     * @author newcj
     */
    public class SimpleBinder extends Binder{
        /**
         * ��ȡ Service ʵ��
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
        // ���� SimpleBinder
        Log.i(TAG,"onCreate");
        sBinder = new SimpleBinder();
    }
     
    @Override
    public IBinder onBind(Intent intent) {
    	Log.i(TAG,"onBind");
        // ���� SimpleBinder ����
        return sBinder;
    }
    
    public void onDestroy() {  
        Log.i(TAG, "Service onDestroy--->");  
    }  
    // ������ʱ����  
    public boolean onUnbind(Intent intent) {    
        Log.i(TAG, "Service onUnbind--->");    
        return super.onUnbind(intent);    
    }    
 
}
