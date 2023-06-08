package top.gate.boxcore.server;

import top.gate.boxcore.BEnvironment;
import top.gate.boxcore.server.am.BActivityManagerService;
import top.gate.boxcore.server.am.BJobManagerService;
import top.gate.boxcore.server.os.BStorageManagerService;
import top.gate.boxcore.server.pm.BPackageInstallerService;
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
public class BBoxSystem {
    private static BBoxSystem sBlackBoxSystem;

    public static BBoxSystem getSystem() {
        if (sBlackBoxSystem == null) {
            synchronized (BBoxSystem.class) {
                if (sBlackBoxSystem == null) {
                    sBlackBoxSystem = new BBoxSystem();
                }
            }
        }
        return sBlackBoxSystem;
    }

    public void startup() {
        BEnvironment.load();

        BPackageManagerService.get().systemReady();
        BUserManagerService.get().systemReady();
        BActivityManagerService.get().systemReady();
        BJobManagerService.get().systemReady();
        BStorageManagerService.get().systemReady();
        BPackageInstallerService.get().systemReady();
        BXposedManagerService.get().systemReady();
    }
}
