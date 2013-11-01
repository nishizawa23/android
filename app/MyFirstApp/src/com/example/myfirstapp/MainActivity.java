package com.example.myfirstapp;

import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {
	public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
	final private int menuSettings=Menu.FIRST;  
	final private int MenuApn=Menu.FIRST+1;
	private static final int REQ_SYSTEM_SETTINGS = 0;  
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	/** Called when the user clicks the Send button */
	public void sendMessage(View view) {
	    Intent intent = new Intent(this, DisplayMessageActivity.class);
	    EditText editText = (EditText) findViewById(R.id.edit_message);
	    String message = editText.getText().toString();
	    intent.putExtra(EXTRA_MESSAGE, message);
	    startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		/*����Activity��getMenuInflater()�õ�һ��MenuInflater�� 
	       ʹ��inflate�������Ѳ����ļ��еĶ���Ĳ˵� ���ظ� �ڶ�����������Ӧ��menu����
	       ��resĿ¼�½���һ��menu�ļ��У������������ļ�: options_menu.xml */
	//	getMenuInflater().inflate(R.menu.activity_main, menu);
		menu.add(0, menuSettings, 1, "����");
		menu.add(0,MenuApn,0,"APN");
	    return super.onCreateOptionsMenu(menu); 
	}
	
    public boolean onMenuItemSelected(int featureId, MenuItem item)  
    {  
        switch (item.getItemId())  
        {  
            case menuSettings:  
                //ת��Settings���ý���  
            	/*   1��������������Ļ���дMenu.NONE, 
            	      2��Id���������Ҫ��Android�������Id��ȷ����ͬ�Ĳ˵� 
            	      3��˳���ĸ��˵�����ǰ������������Ĵ�С���� 
            	      4���ı����˵������ʾ�ı�*/
                startActivityForResult(new Intent(this, Setting.class), REQ_SYSTEM_SETTINGS);  
                break;  
            case MenuApn:
            	Log.v("at menuApn","hellooooo");
     //      	Intent intent = new Intent(Intent.ACTION_INSERT,Uri.parse("content://telephony/carriers"));
            	Intent intent = new Intent(Intent.ACTION_INSERT);
            	intent.putExtra("apn_call", 7009);
            	startActivity(intent);
            default:  
                break;  
        }  
        return super.onMenuItemSelected(featureId, item);  
    }  
    //Settings���ý��淵�صĽ��  
    protected  void onActivityResult(int requestCode, int resultCode, Intent data) {  
        if(requestCode == REQ_SYSTEM_SETTINGS)  
        {  
            //��ȡ���ý���PreferenceActivity�и���Preference��ֵ  
            String updateSwitchKey = getResources().getString(R.string.auto_update_switch_key);  
            String updateFrequencyKey = getResources().getString(R.string.auto_update_frequency_key);  
            //ȡ����������Ӧ�ó����SharedPreferences  
            SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);  
            Boolean updateSwitch = settings.getBoolean(updateSwitchKey, true);  
            String updateFrequency = settings.getString(updateFrequencyKey, "10");  
            //��ӡ���  
            Log.v("CheckBoxPreference_Main", updateSwitch.toString());  
            Log.v("ListPreference_Main", updateFrequency);  
        }  
        else  
        {  
            //����Intent���صĽ��  
        }
    }

}
