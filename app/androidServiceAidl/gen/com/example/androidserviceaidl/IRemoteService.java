/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: /home/pete/workspace/androidServiceAidl/src/com/example/androidserviceaidl/IRemoteService.aidl
 */
package com.example.androidserviceaidl;
public interface IRemoteService extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements com.example.androidserviceaidl.IRemoteService
{
private static final java.lang.String DESCRIPTOR = "com.example.androidserviceaidl.IRemoteService";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an com.example.androidserviceaidl.IRemoteService interface,
 * generating a proxy if needed.
 */
public static com.example.androidserviceaidl.IRemoteService asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof com.example.androidserviceaidl.IRemoteService))) {
return ((com.example.androidserviceaidl.IRemoteService)iin);
}
return new com.example.androidserviceaidl.IRemoteService.Stub.Proxy(obj);
}
@Override public android.os.IBinder asBinder()
{
return this;
}
@Override public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException
{
switch (code)
{
case INTERFACE_TRANSACTION:
{
reply.writeString(DESCRIPTOR);
return true;
}
case TRANSACTION_getCount:
{
data.enforceInterface(DESCRIPTOR);
int _result = this.getCount();
reply.writeNoException();
reply.writeInt(_result);
return true;
}
case TRANSACTION_getPid:
{
data.enforceInterface(DESCRIPTOR);
com.example.androidserviceaidl.ITaskServiceCallBack _arg0;
_arg0 = com.example.androidserviceaidl.ITaskServiceCallBack.Stub.asInterface(data.readStrongBinder());
int _result = this.getPid(_arg0);
reply.writeNoException();
reply.writeInt(_result);
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements com.example.androidserviceaidl.IRemoteService
{
private android.os.IBinder mRemote;
Proxy(android.os.IBinder remote)
{
mRemote = remote;
}
@Override public android.os.IBinder asBinder()
{
return mRemote;
}
public java.lang.String getInterfaceDescriptor()
{
return DESCRIPTOR;
}
@Override public int getCount() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
int _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getCount, _data, _reply, 0);
_reply.readException();
_result = _reply.readInt();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public int getPid(com.example.androidserviceaidl.ITaskServiceCallBack callback) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
int _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((callback!=null))?(callback.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_getPid, _data, _reply, 0);
_reply.readException();
_result = _reply.readInt();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
}
static final int TRANSACTION_getCount = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_getPid = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
}
public int getCount() throws android.os.RemoteException;
public int getPid(com.example.androidserviceaidl.ITaskServiceCallBack callback) throws android.os.RemoteException;
}
