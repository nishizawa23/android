package com.example.androidserviceaidl;

import com.example.androidserviceaidl.ITaskServiceCallBack;
import com.example.androidserviceaidl.TaskInfo;

interface IRemoteService {
	int getCount();
	int getPid(ITaskServiceCallBack callback);
	TaskInfo getTaskInfo();
}