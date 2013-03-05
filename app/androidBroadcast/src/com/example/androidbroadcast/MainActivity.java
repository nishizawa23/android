package com.example.androidbroadcast;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;  
import android.view.View.OnClickListener;  
import android.widget.Button;


public class MainActivity extends Activity {
	
	private static final String MY_ACTION="android.broadcast.action.MY_ACTION";  
    private Button btn;  
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		btn=(Button)findViewById(R.id.button1);  
        btn.setOnClickListener(new OnClickListener()  
        {  
  
            @Override  
            public void onClick(View arg0) {  
                // TODO Auto-generated method stub  
                Intent intent=new Intent();  
                intent.setAction(MY_ACTION);  
                intent.putExtra("msg", "同志们好！同志们辛苦啦！");  
                sendBroadcast(intent);  
            }
        });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}	
}
