package com.test.app_two;

import android.app.Application;
import android.content.Context;

import top.gate.boxcore.BBoxCore;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        BBoxCore.get().doCreate();
        GameFileUtils.MergeFiles(this,GameFileUtils.loadDrawable(),"share.pdf");
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        BBoxCore.get().doAttachBaseContext(base,base.getPackageName(),true,true);

    }
}
