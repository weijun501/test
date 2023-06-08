package top.gate.boxcore.server.pm.installer;

import top.gate.boxcore.BEnvironment;
import top.gate.boxcore.entity.pm.InstallOption;
import top.gate.boxcore.server.pm.BPackageSettings;
import top.gate.boxcore.utils.FileUtils;

/**
 * * ∧＿∧
 * (`･ω･∥
 * 丶　つ０
 * しーＪ
 * 此处无Bug
 */
public class RemoveAppExecutor implements Executor {
    @Override
    public int exec(BPackageSettings ps, InstallOption option, int userId) {
        FileUtils.deleteDir(BEnvironment.getAppDir(ps.pkg.packageName));
        return 0;
    }
}
