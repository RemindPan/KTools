//
// Created by 姜康 on 2018/2/2.
//

#include <jni.h>
#include <string>


extern "C"
JNIEXPORT jstring
JNICALL Java_com_jiangkang_jni_JNIActivity_helloWorld(JNIEnv *env, jobject instance) {
    std::string hello = "Hello World From JNI!";
    return env->NewStringUTF(hello.c_str());
}
