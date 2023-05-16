#ifndef INCLUDES_H
#define INCLUDES_H

#include <iostream>
#include <stdio.h>
#include <string>
#include <unistd.h>
#include <stdint.h>
#include <inttypes.h>

#include <vector>
#include <map>
#include <chrono>
#include <fstream>
#include <thread>

#include <pthread.h>
#include <dirent.h>
#include <libgen.h>

#include <sys/stat.h>
#include <sys/mman.h>
#include <sys/uio.h>

#include <fcntl.h>

#include <jni.h>
#include <android/log.h>


enum daLogType {
    daDEBUG = 3,
    daERROR = 6,
    daINFO = 4,
    daWARN = 5
};

#define TAG ("Putri")

#define LOGD(...) ((void)__android_log_print(daDEBUG, TAG, __VA_ARGS__))
#define LOGE(...) ((void)__android_log_print(daERROR, TAG, __VA_ARGS__))
#define LOGI(...) ((void)__android_log_print(daINFO,  TAG, __VA_ARGS__))
#define LOGW(...) ((void)__android_log_print(daWARN,  TAG, __VA_ARGS__))

#endif 
