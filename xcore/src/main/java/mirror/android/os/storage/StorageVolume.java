package mirror.android.os.storage;

import java.io.File;

import mirror.RefClass;
import mirror.RefObject;

/**
 * * ∧＿∧
 * (`･ω･∥
 * 丶　つ０
 * しーＪ
 * 此处无Bug
 */
public class StorageVolume {
    public static Class<?> TYPE = RefClass.load(StorageVolume.class, "android.os.storage.StorageVolume");
    public static RefObject<File> mPath;
    public static RefObject<File> mInternalPath;
}
