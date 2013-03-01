package com.example.androidhandlertest;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends Activity implements OnClickListener {
	Button button1 = null;
	Button button2 = null;
	Button button3 = null;
	Button button4 = null;
	MyHandler mHandler;
	Message m;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        button1 = (Button)findViewById(R.id.button1);
        button1.setOnClickListener(this);
        button2 = (Button)findViewById(R.id.button2);
        button2.setOnClickListener(this);
        button3 = (Button)findViewById(R.id.button3);
        button3.setOnClickListener(this);
        button4 = (Button)findViewById(R.id.button4);
        button4.setOnClickListener(this);
    }
    @Override
    public void onClick(View v){
    	dealOnClick(v.getId());
    }
    
    public void dealOnClick(int buttonId){
		switch(buttonId){
    	case R.id.button1:
    		mHandler = new MyHandler();
    		m = mHandler.obtainMessage(1,(Object)"Main thread send message by Message Object");
    		m.sendToTarget();
    		break;
    	case R.id.button2:
    	//	mHandler = new MyHandler();
    	//	m = mHandler.obtainMessage(2,(Object)"Main thread send message by Message Object");
    	//	m.sendToTarget();
    		InnerMyThread imt = new InnerMyThread();
    		imt.start();
    		break;
    	case R.id.button3:
    		mHandler = new MyHandler();
    		MyThread mt = new MyThread(mHandler);
    		mt.start();
    		break;
    	}
    }
    
    public class MyThread extends Thread{
    	private Handler mHandler;
    	public MyThread (Handler mHandler){
    		this.mHandler = mHandler;
    	}
    	@Override
    	public void run(){
    		Message m = mHandler.obtainMessage(3, (Object)"Other thread send message by mHandler");
    		m.sendToTarget();
    	}
    }
    
    private class InnerMyThread extends Thread{
    	@Override
    	public void run(){
 //   		mHandler = new MyHandler();
 //   		mHandler = new MyHandler(Looper.myLooper());
    		mHandler = new MyHandler(Looper.getMainLooper());
    		Message m = mHandler.obtainMessage(2,(Object)"Inner thread send message by message Object");
    		m.sendToTarget();
    	}
    }
    
    public class MyHandler extends Handler{
    	public MyHandler(Looper myLooper){
    		super(myLooper);
    	}
    	public MyHandler(){
    		
    	}
    	@Override
    	public void handleMessage(Message msg){
    		String str = "";
    			switch(msg.what){
    			case 1:
    				str = "1:"+msg.obj;
    				break;
    			case 2:
    				str = "2:"+msg.obj;
    				break;
    			case 3:
    				str = "3:"+msg.obj;
    				break;
    			case 4:
    				str = "4:"+msg.obj;
    				break;
    			}
    		Toast toast = Toast.makeText(MainActivity.this, str, Toast.LENGTH_LONG);
    		toast.show();
    	}
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
}
