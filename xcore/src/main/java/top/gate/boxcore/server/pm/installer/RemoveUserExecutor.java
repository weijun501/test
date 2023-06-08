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
 * 创建用户相关
 */
public class RemoveUserExecutor implements Executor {

    @Override
    public int exec(BPackageSettings ps, InstallOption option, int userId) {
        String packageName = ps.pkg.packageName;
        // delete user dir
        FileUtils.deleteDir(BEnvironment.getDataDir(packageName, userId));
        FileUtils.deleteDir(BEnvironment.getDeDataDir(packageName, userId));
        return 0;
    }
}
