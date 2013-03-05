package com.example.androidnotificationtest;

import android.os.Bundle;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	
	int notification_id=19172439;
	NotificationManager nm;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		nm=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        Button bt1=(Button)findViewById(R.id.button1);
        bt1.setOnClickListener(bt1lis);
        Button bt2=(Button)findViewById(R.id.button2);
        bt2.setOnClickListener(bt2lis);
        Button bt3=(Button)findViewById(R.id.button3);
        bt3.setOnClickListener(bt3lis);
	}
	OnClickListener bt1lis=new OnClickListener(){
		 
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			showNotification(R.drawable.ic_launcher,"ͼ��ߵ�����","����","����");
		}
 
    };
    OnClickListener bt2lis=new OnClickListener(){
 
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			//showNotification(R.drawable.home,"ͼ��ߵ�����","����","����");
			nm.cancel(notification_id);
		}
 
    };
    static final private int GET_CODE = 0; 
    OnClickListener bt3lis=new OnClickListener(){
    	 
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(MainActivity.this, notificationActivity.class);  
            startActivityForResult(intent, GET_CODE);  
		}
 
    };
    @SuppressWarnings("deprecation")
	public void showNotification(int icon,String tickertext,String title,String content){
    	//����һ��Ψһ��ID���������
 
    	//Notification������
    	Notification notification=new Notification(icon,tickertext,System.currentTimeMillis());
    	//����Ĳ����ֱ�����ʾ�ڶ���֪ͨ����Сͼ�꣬Сͼ���Ե����֣�������ʾ���Զ���ʧ��ϵͳ��ǰʱ�䣨�����������ʲô�ã�
    	notification.defaults=Notification.DEFAULT_ALL; 
    	//��������֪ͨ�Ƿ�ͬʱ�����������񶯣�����ΪNotification.DEFAULT_SOUND
    	//��ΪNotification.DEFAULT_VIBRATE
    	//LightΪNotification.DEFAULT_LIGHTS
    	//ȫ��ΪNotification.DEFAULT_ALL
    	//������񶯻���ȫ����������AndroidManifest.xml������Ȩ��
    	//<uses-permission android:name="android.permission.VIBRATE" />
   // 	PendingIntent pt=PendingIntent.getActivity(this, 0, new Intent(this,notificationActivity.class), 0);
    	PendingIntent pt=PendingIntent.getActivity(this, 0, new Intent(this,notificationActivity.class), 0);
    	//���֪ͨ��Ķ�����������ת��main ���Acticity
    	notification.setLatestEventInfo(this,title,content,pt);
    	nm.notify(notification_id, notification);
 
    }
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
}