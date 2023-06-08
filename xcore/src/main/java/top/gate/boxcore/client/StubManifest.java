package top.gate.boxcore.client;

import java.util.Locale;

import top.gate.boxcore.BBoxCore;

/**
 * * ∧＿∧
 * (`･ω･∥
 * 丶　つ０
 * しーＪ
 * 此处无Bug
 */
public class StubManifest {
    public static final int FREE_COUNT = 100;

    public static boolean isStub(String msg) {
        return getBindProvider().equals(msg) || msg.contains("stub_content_provider_");
    }

    public static String getBindProvider() {
        return BBoxCore.getHostPkg() + ".blackbox.BindProvider";
    }

    public static String getStubAuthorities(int index) {
        return String.format(Locale.CHINA, "%s.stub_content_provider_%d", BBoxCore.getHostPkg(), index);
    }

    public static String getStubActivity(int index) {
        return String.format(Locale.CHINA, "top.gate.boxcore.client.stub.StubActivity$P%d", index);
    }

    public static String getStubService(int index) {
        return String.format(Locale.CHINA, "top.gate.boxcore.client.stub.StubService$P%d", index);
    }

    public static String getStubJobService(int index) {
        return String.format(Locale.CHINA, "top.gate.boxcore.client.stub.StubJobService$P%d", index);
    }

    public static String getStubFileProvider() {
        return BBoxCore.getHostPkg() + ".blackbox.FileProvider";
    }

    public static String getStubReceiver() {
        return BBoxCore.getHostPkg() + ".stub_receiver";
    }

    public static String getProcessName(int vpid) {
        return BBoxCore.getHostPkg() + ":p" + vpid;
    }
}
