package com.example.androidserviceonbind;

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

public class MainActivity extends Activity  {
	private Button bindBtn,unbindBtn;  
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Log.i("APP", "Onclickkk");
		 
        bindBtn = (Button)findViewById(R.id.button1);  
        unbindBtn = (Button)findViewById(R.id.button2);  
          
        // 添加监听器  
        bindBtn.setOnClickListener(bindListener);
//        bindBtn.setOnClickListener((android.view.View.OnClickListener) this);
        unbindBtn.setOnClickListener(unBindListener);  
     }
      
   // 连接对象  
		private ServiceConnection conn = new ServiceConnection() {  
        @Override 
        	public void onServiceConnected(ComponentName name, IBinder service) {  
            Log.i("Service", "连接成功！");  
        	}  
        @Override 
        	public void onServiceDisconnected(ComponentName name) {  
            Log.i("Service", "断开连接！");  
        	}  
		};  
      
    // 綁定Service监听器  
    private OnClickListener bindListener = new OnClickListener() {  
        @Override 
        public void onClick(View v) {  
            // 创建Intent  
            Intent intent = new Intent();  
            // 设置Class属性  
            intent.setClass(MainActivity.this, MainService.class);  
           
            // 绑定Service  
            Log.i("APP", "Onclickk");
            bindService(intent, conn, Service.BIND_AUTO_CREATE);  
        }  
    };
          
    // 解除绑定Service监听器  
    private OnClickListener unBindListener = new OnClickListener() {  
        @Override 
        public void onClick(View v) {  
            // 创建Intent  
            Intent intent = new Intent();  
            // 设置Class属性  
            intent.setClass(MainActivity.this, MainService.class);  
            // 解除绑定Service  
            unbindService(conn);  
        }  
    };
}

