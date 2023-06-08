//
//

#ifndef VIRTUALM_IO_H
#define VIRTUALM_IO_H

#include <jni.h>

#include <list>
#include <iostream>

using namespace std;

class IO {
public:
    static void init(JNIEnv *env);

    struct RelocateInfo {
        const char *targetPath;
        const char *relocatePath;
    };

    static void addRule(const char *targetPath, const char *relocatePath);

    static jstring redirectPath(JNIEnv *env, jstring path);

    static jobject redirectPath(JNIEnv *env, jobject path);

    static const char *redirectPath(const char *__path);
};


#endif //VIRTUALM_IO_H
