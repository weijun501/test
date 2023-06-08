package top.gate.boxcore.client.frameworks;

import android.os.RemoteException;

import java.util.Collections;
import java.util.List;

import top.gate.boxcore.BBoxCore;
import top.gate.boxcore.server.ServiceManager;
import top.gate.boxcore.server.user.BUserInfo;
import top.gate.boxcore.server.user.IBUserManagerService;

/**
 * * ∧＿∧
 * (`･ω･∥
 * 丶　つ０
 * しーＪ
 * 此处无Bug
 */
public class BUserManager {
    private static BUserManager sUserManager = new BUserManager();
    private IBUserManagerService mService;

    public static BUserManager get() {
        return sUserManager;
    }

    public BUserInfo createUser(int userId) {
        try {
            return getService().createUser(userId);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deleteUser(int userId) {
        try {
            getService().deleteUser(userId);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public List<BUserInfo> getUsers() {
        try {
            return getService().getUsers();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    private IBUserManagerService getService() {
        if (mService != null && mService.asBinder().isBinderAlive()) {
            return mService;
        }
        mService = IBUserManagerService.Stub.asInterface(BBoxCore.get().getService(ServiceManager.USER_MANAGER));
        return getService();
    }
}
