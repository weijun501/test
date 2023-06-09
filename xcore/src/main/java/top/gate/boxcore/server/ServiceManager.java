package top.gate.boxcore.server;

import android.os.IBinder;

import java.util.HashMap;
import java.util.Map;

import top.gate.boxcore.server.am.BActivityManagerService;
import top.gate.boxcore.server.am.BJobManagerService;
import top.gate.boxcore.server.os.BStorageManagerService;
import top.gate.boxcore.server.pm.BPackageManagerService;
import top.gate.boxcore.server.pm.BXposedManagerService;
import top.gate.boxcore.server.user.BUserManagerService;

/**
 * * ∧＿∧
 * (`･ω･∥
 * 丶　つ０
 * しーＪ
 * 此处无Bug
 */
public class ServiceManager {
    private static ServiceManager sServiceManager = null;
    public static final String ACTIVITY_MANAGER = "activity_manager";
    public static final String JOB_MANAGER = "job_manager";
    public static final String PACKAGE_MANAGER = "package_manager";
    public static final String STORAGE_MANAGER = "storage_manager";
    public static final String USER_MANAGER = "user_manager";
    public static final String Xposed_MANAGER = "Xposed_manager";
    public static final String V_SERVICE = "v_service";

    private Map<String, IBinder> mCaches = new HashMap<>();

    public static ServiceManager get() {
        if (sServiceManager == null) {
            synchronized (ServiceManager.class) {
                if (sServiceManager == null) {
                    sServiceManager = new ServiceManager();
                }
            }
        }
        return sServiceManager;
    }

    public static IBinder getService(String name) {
        return get().getServiceInternal(name);
    }

    private ServiceManager() {
        mCaches.put(ACTIVITY_MANAGER, BActivityManagerService.get());
        mCaches.put(JOB_MANAGER, BJobManagerService.get());
        mCaches.put(PACKAGE_MANAGER, BPackageManagerService.get());
        mCaches.put(STORAGE_MANAGER, BStorageManagerService.get());
        mCaches.put(USER_MANAGER, BUserManagerService.get());
        mCaches.put(Xposed_MANAGER, BXposedManagerService.get());
    }

    public IBinder getServiceInternal(String name) {
        return mCaches.get(name);
    }
}
