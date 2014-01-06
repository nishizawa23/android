package com.example.androidserviceaidl;

import android.os.Parcel;
import android.os.Parcelable;

public class TaskInfo implements Parcelable {
	public long mPss;
	public long mTotalMemory;
	public long mElapsedTime;
	public int mPid;
	public int mUid;
	public String mPackageName;

	TaskInfo() {
		mPss = 0;
		mTotalMemory = 0;
		mElapsedTime = 0;
		mPid = -1;
		mUid = -1;
		mPackageName = null;
	}

	public int describeContents() {
		return 0;
	}

	public void writeToParcel(Parcel out, int flags) {
		out.writeLong(mPss);
		out.writeLong(mTotalMemory);
		out.writeLong(mElapsedTime);
		out.writeInt(mPid);
		out.writeInt(mUid);
		out.writeString(mPackageName);
	}

	public static final Parcelable.Creator<TaskInfo> CREATOR = new Parcelable.Creator<TaskInfo>() {
		public TaskInfo createFromParcel(Parcel in) {
			return new TaskInfo(in);
		}

		public TaskInfo[] newArray(int size) {
			return new TaskInfo[size];
		}
	};

	private TaskInfo(Parcel in) {
		mPss = in.readLong();
		mTotalMemory = in.readLong();
		mElapsedTime = in.readLong();
		mPid = in.readInt();
		mUid = in.readInt();
		mPackageName = in.readString();
	}
}