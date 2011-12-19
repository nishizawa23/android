#include <jni.h>
#include <cstddef>  //error: ¡®NULL¡¯ was not declared in this scope if not set
//#define LOG_TAG "HelloWorld"
//#include <utils/Log.h>
#include <android/log.h>
#include "jni_debug.h"

/*
  * Class:     com_simon_Helloworld
  * Method:    print
  * Signature: ()V
  */
/*JNIEXPORT void JNICALL Java_com_simon_Helloworld_print(JNIEnv *, jobject)*/
JNIEXPORT jstring JNICALL Java_com_helloworldcpp_HelloWorldCPPActivity_pJNI(JNIEnv *env, jobject obj)
{
    LOGI("Hello World From libhelloworld.so!");
	return env->NewStringUTF("Hello World!");
}

static const char *classPathName = "com/helloworldcpp/HelloWorldCPPActivity";

static JNINativeMethod methods[] = {
  {"pJNI", "()Ljava/lang/String;", (void*)Java_com_helloworldcpp_HelloWorldCPPActivity_pJNI},
};

/*
 * Register several native methods for one class.
 */
static int registerNativeMethods(JNIEnv* env, const char* className,
    JNINativeMethod* gMethods, int numMethods)
{
    jclass clazz;

    clazz = env->FindClass(className);
    if (clazz == NULL) {
        LOGE("Native registration unable to find class '%s'", className);
        return JNI_FALSE;
    }
    if (env->RegisterNatives(clazz, gMethods, numMethods) < 0) {
        LOGE("RegisterNatives failed for '%s'", className);
        return JNI_FALSE;
    }

    return JNI_TRUE;
}

/*
 * Register native methods for all classes we know about.
 *
 * returns JNI_TRUE on success.
 */
static int registerNatives(JNIEnv* env)
{
  if (!registerNativeMethods(env, classPathName,
                 methods, sizeof(methods) / sizeof(methods[0]))) {
    return JNI_FALSE;
  }

  return JNI_TRUE;
}

typedef union {
    JNIEnv* env;
    void* venv;
} UnionJNIEnvToVoid;

/* This function will be call when the library first be loaded */
jint JNI_OnLoad(JavaVM* vm, void* reserved)
{
    UnionJNIEnvToVoid uenv;
    JNIEnv* env = NULL;
    LOGI("JNI_OnLoad!");

    if (vm->GetEnv((void**)&uenv.venv, JNI_VERSION_1_4) != JNI_OK) {
        LOGE("ERROR: GetEnv failed");
        return -1;
    }

    env = uenv.env;;

    if (registerNatives(env) != JNI_TRUE) {
        LOGE("ERROR: registerNatives failed");
        return -1;
    }

     return JNI_VERSION_1_4;
}
