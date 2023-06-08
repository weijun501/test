package top.gate.boxcore.client.stub;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import top.gate.boxcore.BBoxCore;
import top.gate.boxcore.client.stub.record.StubBroadcastRecord;

/**
 * * ∧＿∧
 * (`･ω･∥
 * 丶　つ０
 * しーＪ
 * 此处无Bug
 */
public class StubBroadcastReceiver extends BroadcastReceiver {
    public static final String TAG = "StubBroadcastReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        StubBroadcastRecord record = StubBroadcastRecord.create(intent);
        Log.d(TAG, "onReceive: " + intent);

        BBoxCore.getContext().sendBroadcast(record.mIntent);
    }
}
