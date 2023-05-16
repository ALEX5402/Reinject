#include <jni.h>
#include "obfuscate.h"




extern "C"
JNIEXPORT jstring JNICALL
Java_com_alex_injector_MainActivity_passwoeddd(JNIEnv *env, jobject clazz) {
    return(*env).NewStringUTF(OBFUSCATE("ajhfgayufbahjfvbuaifabfhgaiuwhradb"));
}
extern "C"
JNIEXPORT jstring JNICALL
Java_com_alex_injector_objects_global_00024Companion_localpath(JNIEnv *env, jobject clazz) {
    return(*env).NewStringUTF(OBFUSCATE("/data/local/tmp/"));
}

extern "C"
JNIEXPORT jstring JNICALL
Java_com_alex_injector_objects_global_00024Companion_libv1(JNIEnv *env, jobject clazz) {
    return(*env).NewStringUTF(OBFUSCATE("libv1.so"));  //global 64bit
}
extern "C"
JNIEXPORT jstring JNICALL
Java_com_alex_injector_objects_global_00024Companion_libv3(JNIEnv *env, jobject thiz) {
    return(*env).NewStringUTF(OBFUSCATE("libv3.so")); // global 32bit
}
extern "C"
JNIEXPORT jstring JNICALL
Java_com_alex_injector_objects_global_00024Companion_libv2(JNIEnv *env, jobject clazz) {
    return(*env).NewStringUTF(OBFUSCATE("libv2.so")); // bgmi64bit
}
extern "C"
JNIEXPORT jstring JNICALL
Java_com_alex_injector_objects_global_00024Companion_libv4(JNIEnv *env, jobject thiz) {
    return(*env).NewStringUTF(OBFUSCATE("libv4.so"));  // bgmi 32 bit
}
extern "C"
JNIEXPORT jstring JNICALL
Java_com_alex_injector_objects_global_00024Companion_jsondata(JNIEnv *env, jobject thiz) {
     return(*env).NewStringUTF(OBFUSCATE("https://raw.githubusercontent.com/ALEX5402/lib_online/main/update-alex.json"));
}
