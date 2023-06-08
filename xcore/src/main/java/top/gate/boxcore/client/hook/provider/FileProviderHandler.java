package top.gate.boxcore.client.hook.provider;

import android.content.Context;
import android.content.pm.ProviderInfo;
import android.net.Uri;

import java.io.File;
import java.util.List;

import top.gate.boxcore.BBoxCore;
import top.gate.boxcore.client.BClient;
import top.gate.boxcore.utils.compat.BuildCompat;

/**
 * * ∧＿∧
 * (`･ω･∥
 * 丶　つ０
 * しーＪ
 * 此处无Bug
 */
public class FileProviderHandler {

    public static Uri convertFileUri(Context context, Uri uri) {
        if (BuildCompat.isN()) {
            List<ProviderInfo> providers = BClient.getProviders();
            for (ProviderInfo provider : providers) {
                try {
                    File fileForUri = FileProvider.getFileForUri(context, provider.authority, uri);
                    if (fileForUri != null && fileForUri.exists()) {
                        return BBoxCore.getBStorageManager().getUriForFile(fileForUri.getAbsolutePath());
                    }
                } catch (Exception ignored) {
                }
            }
        }
        return uri;
    }
}
