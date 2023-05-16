#include "Injector/Injector.h"
#include <cstdio>
#include "Logger.h"
#include "jni.h"
#include <vector>

extern "C" {
JNIEXPORT jint JNICALL
Java_com_alex_injector_callbacks_Load_Inject(JNIEnv *env, jobject clazz, jstring package_name,
                                             jstring library_path) {
    pkgName = env->GetStringUTFChars(package_name, nullptr);
    libraryPath = env->GetStringUTFChars(library_path, nullptr);
    int result = initInject();
    return result;
}

}
