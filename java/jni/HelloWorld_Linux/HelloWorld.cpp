/*************************************************************************
 * Author: huangxinghua
 * Email: <nishizawa23@gmail.com>
 * Web: http://www.nishizawa23.com
 * File: HelloWorld.cpp
 * Create Date: 2011-12-16 10:42:42
 *************************************************************************/

using namespace std;
#include <iostream>
#include <jni.h>
//#include <stdio.h>
#include "HelloWorld.h"

JNIEXPORT void JNICALL

Java_HelloWorld_print(JNIEnv *env, jobject obj)
{
		cout << "Hello World!\n";
}

JNIEXPORT void JNICALL

Java_HelloWorld_SayHello(JNIEnv *env, jobject obj,jstring instring)
{
	const jbyte *str =
		(const jbyte *)env->GetStringUTFChars(instring,JNI_FALSE);
		cout << str;
		env->ReleaseStringUTFChars(instring,(const char*)str);
}
