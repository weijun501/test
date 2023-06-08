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
public class CreateUserExecutor implements Executor {

    @Override
    public int exec(BPackageSettings ps, InstallOption option, int userId) {
        String packageName = ps.pkg.packageName;
        FileUtils.deleteDir(BEnvironment.getDataLibDir(packageName, userId));

        // create user dir
        FileUtils.mkdirs(BEnvironment.getDataDir(packageName, userId));
        FileUtils.mkdirs(BEnvironment.getDataCacheDir(packageName, userId));
        FileUtils.mkdirs(BEnvironment.getDataFilesDir(packageName, userId));
        FileUtils.mkdirs(BEnvironment.getDataDatabasesDir(packageName, userId));
        FileUtils.mkdirs(BEnvironment.getDeDataDir(packageName, userId));

//        try {
//            // /data/data/xx/lib -> /data/app/xx/lib
//            FileUtils.createSymlink(BEnvironment.getAppLibDir(ps.pkg.packageName).getAbsolutePath(), BEnvironment.getDataLibDir(packageName, userId).getAbsolutePath());
//        } catch (Exception e) {
//            e.printStackTrace();
//            return -1;
//        }
        return 0;
    }
}
