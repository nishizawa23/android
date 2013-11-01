package com.example.myfirstapp;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceActivity;
import android.preference.PreferenceScreen;
import android.util.Log;
import android.view.Menu;

//�̳�PreferenceActivity����ʵ��OnPreferenceChangeListener��OnPreferenceClickListener�����ӿ�
public class Setting extends PreferenceActivity implements OnPreferenceChangeListener, 
OnPreferenceClickListener,OnSharedPreferenceChangeListener {
	//������ر���
	final private int menuSettings=Menu.FIRST;  
	final private int MenuApn=Menu.FIRST+1;
	String updateSwitchKey;
	String updateFrequencyKey;
	CheckBoxPreference updateSwitchCheckPref;
	ListPreference updateFrequencyListPref;
  @SuppressWarnings("deprecation")
@Override
  public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      //��xml�ļ������Preference��
      addPreferencesFromResource(R.xml.preferencesii);
      //��ȡ����Preference
      updateSwitchKey = getResources().getString(R.string.auto_update_switch_key);
      updateFrequencyKey = getResources().getString(R.string.auto_update_frequency_key);
      updateSwitchCheckPref = (CheckBoxPreference)findPreference(updateSwitchKey);
      updateFrequencyListPref = (ListPreference)findPreference(updateFrequencyKey);
      //Ϊ����Preferenceע������ӿ�
      updateSwitchCheckPref.setOnPreferenceChangeListener(this);
      updateSwitchCheckPref.setOnPreferenceClickListener(this);
      updateFrequencyListPref.setOnPreferenceChangeListener(this);
      updateFrequencyListPref.setOnPreferenceClickListener(this);       
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
	@Override
	public boolean onPreferenceChange(Preference preference, Object newValue) {
		// TODO Auto-generated method stub
		Log.v("SystemSetting", "preference is changed");
		Log.v("Key_SystemSetting", preference.getKey());
		//�ж����ĸ�Preference�ı���
		if(preference.getKey().equals(updateSwitchKey))
		{
			Log.v("SystemSetting", "checkbox preference is changed");
		}
		else if(preference.getKey().equals(updateFrequencyKey))
		{
			Log.v("SystemSetting", "list preference is changed");
		}
		else
		{
			//�������false��ʾ�������ı�
			return false;
		}
		//����true��ʾ����ı�
		return true;
	}
	
	@Override
	public boolean onPreferenceClick(Preference preference) {
		// TODO Auto-generated method stub
		Log.v("SystemSetting", "preference is clicked");
		Log.v("Key_SystemSetting", preference.getKey());
		//�ж����ĸ�Preference�������
		if(preference.getKey().equals(updateSwitchKey))
		{
			Log.v("SystemSetting", "checkbox preference is clicked");
		}
		else if(preference.getKey().equals(updateFrequencyKey))
		{
			Log.v("SystemSetting", "list preference is clicked");
		}
		else
		{
			return false;
		}
		return true;
	}
	@Override
	public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen,	Preference preference) {
		String key = preference.getKey();
		Log.v("SystemSetting", "gggggggggggggggggggggg");
		if( key != null ){
			if(key.equals(updateSwitchKey)) {
				Log.v("SystemSetting", "onPreferenceTreeClick checkbox preference is clicked");
			}
			if(key.equals(updateFrequencyKey)) {
				Log.v("SystemSetting", "onPreferenceTreeClick ist preference is clicked");
			}
		}
	//	return super.onPreferenceTreeClick(preferenceScreen, preference);
		return true;
	}
	//��̬��ȡ CheckBoxPreerence ��ֵ
    @Override  
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {  
        if (key.equals(updateSwitchKey)) {  
            boolean value = sharedPreferences.getBoolean(key, false);  
            Log.d("mark", "onSharedPreferenceChanged is invoked !" + " and the value = " + value);  
        }  
    }  
}