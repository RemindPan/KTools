//
// Created by 姜康 on 2018/2/2.
//

#include <jni.h>
#include <string>
using namespace std;

//动态注册方式
JNIEXPORT jint JNI_OnLoad(JavaVM* vm, void* reserved) {
    JNIEnv* env;
    if (vm->GetEnv(reinterpret_cast<void**>(&env), JNI_VERSION_1_6) != JNI_OK) {
        return -1;
    }

    // Get jclass with env->FindClass.
    // Register methods with env->RegisterNatives.


    return JNI_VERSION_1_6;
}

extern "C"
JNIEXPORT jstring
JNICALL Java_com_jiangkang_jni_JNIActivity_helloWorld(JNIEnv *env, jobject instance) {
    string hello = "Hello World From JNI!";
    return env->NewStringUTF(hello.c_str());
}



extern "C"
JNIEXPORT jint JNICALL
Java_com_jiangkang_jni_JNIActivity_sumInt(JNIEnv *env, jobject pThis, jint a, jint b) {
    return a + b;
}

