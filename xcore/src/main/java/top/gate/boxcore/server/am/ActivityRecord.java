package top.gate.boxcore.server.am;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Binder;
import android.os.IBinder;

import top.gate.boxcore.server.ProcessRecord;


/**
 * * ∧＿∧
 * (`･ω･∥
 * 丶　つ０
 * しーＪ
 * 此处无Bug
 */
public class ActivityRecord extends Binder {
    public TaskRecord task;
    public IBinder token;
    public IBinder resultTo;
    public ActivityInfo info;
    public ComponentName component;
    public Intent intent;
    public int userId;
    public boolean finished;
    public ProcessRecord processRecord;

    public static ActivityRecord create(Intent intent, ActivityInfo info, IBinder resultTo, int userId) {
        ActivityRecord record = new ActivityRecord();
        record.intent = intent;
        record.info = info;
        record.component = new ComponentName(info.packageName, info.name);
        record.resultTo = resultTo;
        record.userId = userId;
        return record;
    }


}
