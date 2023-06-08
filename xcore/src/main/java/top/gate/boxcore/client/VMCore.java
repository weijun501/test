package top.gate.boxcore.client;

import androidx.annotation.Keep;

import java.io.File;

import top.gate.boxcore.BBoxCore;

/**
 * * ∧＿∧
 * (`･ω･∥
 * 丶　つ０
 * しーＪ
 * 此处无Bug
 */
public class VMCore {
    public static final String TAG = "VMCoreJava";

    static {
        if (BBoxCore.is64Bit()) {
            try {
                System.loadLibrary("vm64");
            } catch (Throwable e) {
                System.loadLibrary("vm");
            }
        } else {
            System.loadLibrary("vm");
        }

    }

    public static native void init(int apiLevel);

    public static native void addIORule(String targetPath, String relocatePath);

    public static native void hideXposed();

    @Keep
    public static int getCallingUid(int origCallingUid) {
//        if (origCallingUid > 0 && origCallingUid < Process.FIRST_APPLICATION_UID)
//            return origCallingUid;
//        // 非用户应用
//        if (origCallingUid > Process.LAST_APPLICATION_UID)
//            return origCallingUid;
//
//        Log.d(TAG, "origCallingUid: " + origCallingUid + " => " + BClient.getBaseVUid());
//        return BClient.getBaseVUid();
        return origCallingUid;
    }
}
