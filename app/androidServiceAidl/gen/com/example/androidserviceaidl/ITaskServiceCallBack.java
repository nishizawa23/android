/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: /home/pete/workspace/androidServiceAidl/src/com/example/androidserviceaidl/ITaskServiceCallBack.aidl
 */
package com.example.androidserviceaidl;
public interface ITaskServiceCallBack extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements com.example.androidserviceaidl.ITaskServiceCallBack
{
private static final java.lang.String DESCRIPTOR = "com.example.androidserviceaidl.ITaskServiceCallBack";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an com.example.androidserviceaidl.ITaskServiceCallBack interface,
 * generating a proxy if needed.
 */
public static com.example.androidserviceaidl.ITaskServiceCallBack asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof com.example.androidserviceaidl.ITaskServiceCallBack))) {
return ((com.example.androidserviceaidl.ITaskServiceCallBack)iin);
}
return new com.example.androidserviceaidl.ITaskServiceCallBack.Stub.Proxy(obj);
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
case TRANSACTION_valueCounted:
{
data.enforceInterface(DESCRIPTOR);
int _arg0;
_arg0 = data.readInt();
this.valueCounted(_arg0);
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements com.example.androidserviceaidl.ITaskServiceCallBack
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
@Override public void valueCounted(int value) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(value);
mRemote.transact(Stub.TRANSACTION_valueCounted, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
}
static final int TRANSACTION_valueCounted = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
}
public void valueCounted(int value) throws android.os.RemoteException;
}
