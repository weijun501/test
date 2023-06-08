// IBXposedManagerService.aidl

package top.gate.boxcore.server.pm;

import java.util.List;
import top.gate.boxcore.entity.pm.InstalledModule;

interface IBXposedManagerService {
    boolean isXPEnable();
    void setXPEnable(boolean enable);
    boolean isModuleEnable(String packageName);
    void setModuleEnable(String packageName, boolean enable);
    List<InstalledModule> getInstalledModules();
}