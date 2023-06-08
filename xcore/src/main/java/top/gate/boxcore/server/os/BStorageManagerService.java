package top.gate.boxcore.server.os;

import android.content.Context;
import android.net.Uri;
import android.os.Process;
import android.os.RemoteException;
import android.os.storage.StorageManager;
import android.os.storage.StorageVolume;

import java.io.File;

import top.gate.boxcore.BEnvironment;
import top.gate.boxcore.BBoxCore;
import top.gate.boxcore.client.StubManifest;
import top.gate.boxcore.server.ISystemService;
import top.gate.boxcore.server.user.BUserHandle;
import top.gate.boxcore.utils.compat.BuildCompat;
import top.gate.boxcore.client.hook.provider.FileProvider;

/**
 * * ∧＿∧
 * (`･ω･∥
 * 丶　つ０
 * しーＪ
 * 此处无Bug
 */
public class BStorageManagerService extends IBStorageManagerService.Stub implements ISystemService {
    private static BStorageManagerService sService = new BStorageManagerService();
    private StorageManager mStorageManager;

    public static BStorageManagerService get() {
        return sService;
    }

    public BStorageManagerService() {
        mStorageManager = (StorageManager) BBoxCore.getContext().getSystemService(Context.STORAGE_SERVICE);
    }

    @Override
    public StorageVolume[] getVolumeList(int uid, String packageName, int flags, int userId) throws RemoteException {
        try {
            StorageVolume[] storageVolumes = mirror.android.os.storage.StorageManager.getVolumeList.call(BUserHandle.getUserId(Process.myUid()), 0);
            for (StorageVolume storageVolume : storageVolumes) {
                mirror.android.os.storage.StorageVolume.mPath.set(storageVolume, BEnvironment.getExternalUserDir(userId));

                if (BuildCompat.isPie()) {
                    mirror.android.os.storage.StorageVolume.mInternalPath.set(storageVolume, BEnvironment.getExternalUserDir(userId));
                }
            }
            return storageVolumes;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new StorageVolume[]{};
    }

    @Override
    public Uri getUriForFile(String file) throws RemoteException {
        return FileProvider.getUriForFile(BBoxCore.getContext(), StubManifest.getStubFileProvider(), new File(file));
    }

    @Override
    public void systemReady() {

    }
}
