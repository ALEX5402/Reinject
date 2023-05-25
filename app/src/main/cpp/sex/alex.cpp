#include <jni.h>

#include "Injector/StrEnc.h"



extern "C"
JNIEXPORT jstring JNICALL
Java_com_alex_injector_MainActivity_passwoeddd(JNIEnv *env, jobject clazz) {
    return(*env).NewStringUTF(StrEnc("0yd[jAHsr K=UH~0j^la7KEP^8I>,",
                                     "\x5D\x18\x0F\x2C\x07\x27\x26\x11\x13\x48\x3C\x57\x37\x2E\x09\x51\x00\x38\x02\x09\x40\x2A\x2F\x26\x3C\x5E\x2F\x5F\x5B", 29).c_str());
}
extern "C"
JNIEXPORT jstring JNICALL
Java_com_alex_injector_objects_global_00024Companion_localpath(JNIEnv *env, jobject clazz) {
    return(*env).NewStringUTF(StrEnc("RI4Eu.1p?B( Z{_R", "\x7D\x2D\x55\x31\x14\x01\x5D\x1F\x5C\x23\x44\x0F\x2E\x16\x2F\x7D", 16).c_str());
}

extern "C"
JNIEXPORT jstring JNICALL
Java_com_alex_injector_objects_global_00024Companion_libv1(JNIEnv *env, jobject clazz) {
    return(*env).NewStringUTF( StrEnc("9J@+>CGb", "\x55\x23\x22\x5D\x0F\x6D\x34\x0D", 8).c_str());  //global 64bit
}
extern "C"
JNIEXPORT jstring JNICALL
Java_com_alex_injector_objects_global_00024Companion_libv3(JNIEnv *env, jobject thiz) {
    return(*env).NewStringUTF( StrEnc("\"@nf.kXc", "\x4E\x29\x0C\x10\x1D\x45\x2B\x0C", 8).c_str()); // global 32bit
}
extern "C"
JNIEXPORT jstring JNICALL
Java_com_alex_injector_objects_global_00024Companion_libv2(JNIEnv *env, jobject clazz) {
    return(*env).NewStringUTF( StrEnc("I'b\\UM.>", "\x25\x4E\x00\x2A\x67\x63\x5D\x51", 8).c_str()); // bgmi64bit
}
extern "C"
JNIEXPORT jstring JNICALL
Java_com_alex_injector_objects_global_00024Companion_libv4(JNIEnv *env, jobject thiz) {
    return(*env).NewStringUTF(StrEnc("Mli2[rC9", "\x21\x05\x0B\x44\x6F\x5C\x30\x56", 8).c_str());  // bgmi 32 bit
}
extern "C"
JNIEXPORT jstring JNICALL
Java_com_alex_injector_objects_global_00024Companion_jsondata(JNIEnv *env, jobject thiz) {


//   // debug
//     return(*env).NewStringUTF(StrEnc("jm7D+|MsgiP^f5B xFHvxJ7bz+_#<3F'q;L=%>BGjP:zxelF<]\\kemc\"m~x\"SH/h)HzYdo",
//                                      "\x02\x19\x43\x34\x58\x46\x62\x5C\x00\x00\x24\x36\x13\x57\x6C\x43\x17\x2B\x67\x37\x34\x0F\x6F\x57\x4E\x1B\x6D\x0C\x50\x5A\x24\x78\x1E\x55\x20\x54\x4B\x5B\x6D\x35\x0B\x27\x15\x17\x19\x0C\x02\x69\x49\x2D\x38\x0A\x11\x08\x4E\x43\x01\x1B\x00\x0F\x37\x2D\x4D\x1D\x4E\x66\x10\x2A\x0B\x01", 70).c_str());



     // main
     return(*env).NewStringUTF(StrEnc(";av[D._%^qU`\\+0BpWI$YCiY_q)S2]m7)=*YjDi8wCH M,!+|9<.MWG`%l5L*{i/x%Qu\\vs8B.u",
                                      "\x53\x15\x02\x2B\x37\x14\x70\x0A\x2C\x10\x22\x4E\x3B\x42\x44\x2A\x05\x35\x3C\x57\x3C\x31\x0A\x36\x31\x05\x4C\x3D\x46\x73\x0E\x58\x44\x12\x6B\x15\x2F\x1C\x5C\x0C\x47\x71\x67\x4C\x24\x4E\x7E\x44\x12\x55\x55\x40\x28\x78\x2A\x01\x4C\x02\x1A\x39\x5A\x1F\x08\x5B\x1D\x08\x30\x19\x39\x0E\x5D\x52\x31\x41\x1B", 75).c_str());
}
