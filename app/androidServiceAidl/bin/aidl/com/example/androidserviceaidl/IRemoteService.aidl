package com.example.androidserviceaidl;

import com.example.androidserviceaidl.ITaskServiceCallBack;

interface IRemoteService {
	int getCount();
	int getPid(ITaskServiceCallBack callback);
}