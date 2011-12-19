#include <jni.h>
//#define LOG_TAG "HelloWorld"
//#include <utils/Log.h>
#include <android/log.h>
#include "jni_debug.h"

/* Native interface, it will be call in java code */
JNIEXPORT jstring JNICALL Java_com_helloworld_HelloWorldActivity_pJNI(JNIEnv *env, jobject obj)
{
	LOGI("Hello World From libhelloworld.so!");

	return (*env)->NewStringUTF(env, "Hello World!");
}

/* This function will be call when the library first be load.
 *  * You can do some init in the libray. return which version jni it support.
 *   */
jint JNI_OnLoad(JavaVM* vm, void* reserved)
{
	void *venv;
	LOGI("JNI_OnLoad!");

	if ((*vm)->GetEnv(vm, (void**)&venv, JNI_VERSION_1_4) != JNI_OK) {
		LOGE("ERROR: GetEnv failed");
		return -1;
	}

	return JNI_VERSION_1_4;
}
