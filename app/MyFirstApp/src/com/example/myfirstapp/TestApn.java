package com.example.myfirstapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;


import android.preference.PreferenceActivity;
import android.preference.Preference.OnPreferenceChangeListener;


import android.util.Log;
import android.view.KeyEvent;


public class TestApn extends PreferenceActivity {
	final int PROGRESS_DIALOG = 0;
	ProgressDialog pd;
	CheckBoxPreference updateSwitchCheckPref;
//	String updateSwitchKey;
	
	 @SuppressWarnings("deprecation")
	public void onCreate(Bundle savedInstanceState) {
	      super.onCreate(savedInstanceState);
	      //��xml�ļ������Preference��
	      addPreferencesFromResource(R.xml.preferencesii);
	//      updateSwitchCheckPref = (CheckBoxPreference)findPreference(updateSwitchKey);
	      Log.v("hellll","apnnnnnnnnnn");     
	  }
	 
	 @SuppressWarnings("deprecation")
	@Override
	 public boolean onKeyDown(int keyCode, KeyEvent event){
		 switch(keyCode){
		 case KeyEvent.KEYCODE_BACK:{
			 Log.v("hellll","at onKeyDown");
			// showDialog(PROGRESS_DIALOG);
			showDialog(PROGRESS_DIALOG);
		 }
		 }
		 return super.onKeyDown(keyCode,event);
	 }
	 @Override
	 public boolean onKeyUp(int keyCode, KeyEvent event){
		 switch(keyCode){
		 case KeyEvent.KEYCODE_BACK:{
			 Log.v("hellll","at onKeyUp");
			 
			 ContentResolver contentResolver=this.getContentResolver();
		        Uri uri=Uri.parse("content://com.jbridge.provider.personprovider/person");
		        ContentValues values=new ContentValues();
		        values.put("name", "���»�");
		        values.put("age",(short)55);
		        contentResolver.insert(uri, values);
		        
		       Cursor cursor=contentResolver.query(uri, new String[]{"personid","name","age"}, null, null, "personid desc");
		       while (cursor.moveToNext()) {
		   		Log.i("jjjj", "personid  "+cursor.getInt(0)+" name "+cursor.getString(cursor.getColumnIndex("name"))+" age " + cursor.getString(cursor.getColumnIndex("age")));
		       }
		 }
		 }
		 return super.onKeyUp(keyCode,event);		 
	 }
	 //AlertDialog��Dialog��һ��ֱ������
	 //ProgressDialog ���Dialog������û���ʾ���ȵ�������������AlertDialog��һ������
	  @SuppressWarnings("deprecation")
	@Override
	    public Dialog onCreateDialog(int id)
	    {
	        
			switch(id)
	        {
	              case  PROGRESS_DIALOG:
	            	  AlertDialog.Builder builder;
	                 builder = new AlertDialog.Builder(this);
	             
	                  
	                 builder.setTitle("��ʾ")//���ñ���
	                 .setCancelable(false)//���ý��ȶԻ������û��˰�ť�ر� 
	                 .setMessage("ȷ���Ƴ���");
	              //   .setPositiveButton(android.R.string.ok,null);
	                 
	                 
	                 builder.setPositiveButton("ȷ��", new OnClickListener() {  
	                	 public void onClick(DialogInterface dialog, int which) {
	                	    dialog.dismiss();
	                	    finish();
	                	   }
	                	  });
	                 builder.setNegativeButton("ȡ��", new OnClickListener() {  
	                	 public void onClick(DialogInterface dialog, int which) {
	                	    dialog.dismiss();
	                	   }
	                	  });
	                 builder.setNeutralButton("����", new OnClickListener() {  
	                	 public void onClick(DialogInterface dialog, int which) {
	                		 Log.v("kkk","at setNeutralButton");
	                	   }
	                	  });
	           //      builder.show();
	                 Log.d("kkk", "kkk");
	                 return builder.create();
	              //break;
	        }       
	       return super.onCreateDialog(id);
		//	return builder;
	       
	    }  
	  @SuppressWarnings("deprecation")
	@Override
	protected void onPrepareDialog(int id, Dialog dialog){  
	  
		  super.onPrepareDialog(id,dialog);
		Log.v("kkk","at onprepaerDiatlogg");
		  if(id == PROGRESS_DIALOG){
			  Log.v("kkk","lllllllllll");
			  ((AlertDialog)dialog).setMessage("hhhhheeeeelll");
		  }
	  }
	  
}
