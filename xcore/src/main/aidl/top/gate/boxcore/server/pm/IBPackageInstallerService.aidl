// IBPackageInstallerService.aidl
package top.gate.boxcore.server.pm;

import top.gate.boxcore.server.pm.BPackageSettings;
import top.gate.boxcore.entity.pm.InstallOption;

// Declare any non-default types here with import statements

interface IBPackageInstallerService {
    int installPackageAsUser(in BPackageSettings file, int userId);
    int uninstallPackageAsUser(in BPackageSettings file, boolean removeApp, int userId);
    int updatePackage(in BPackageSettings file);
}
