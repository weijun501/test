package top.gate.boxcore.server.pm;

/**
 * * ∧＿∧
 * (`･ω･∥
 * 丶　つ０
 * しーＪ
 * 此处无Bug
 */
public interface PackageMonitor {
    void onPackageUninstalled(String packageName, int userId);

    void onPackageInstalled(String packageName, int userId);
}
