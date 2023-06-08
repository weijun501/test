//
//

#ifndef VIRTUALM_VMCORE_H
#define VIRTUALM_VMCORE_H

#include <jni.h>

#define VMCORE_CLASS "top/gate/boxcore/client/VMCore"

class VmCore {
public:
    static int getCallingUid(JNIEnv *env, int orig);
};


#endif //VIRTUALM_VMCORE_H
