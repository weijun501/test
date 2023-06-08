package top.gate.boxcore.server.pm.installer;

import top.gate.boxcore.entity.pm.InstallOption;
import top.gate.boxcore.server.pm.BPackageSettings;

/**
 * * ∧＿∧
 * (`･ω･∥
 * 丶　つ０
 * しーＪ
 * 此处无Bug
 */
public interface Executor {
    public static final String TAG = "InstallExecutor";

    int exec(BPackageSettings ps, InstallOption option, int userId);
}
