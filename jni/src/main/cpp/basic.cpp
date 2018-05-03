//
// Created by 姜康 on 2018/4/21.
//


#include <jni.h>
#include "basic.h"


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
JNIEXPORT void JNICALL
Java_com_jiangkang_jni_JniBasic_callNative(JNIEnv *env, jobject instance) {

// TODO

}